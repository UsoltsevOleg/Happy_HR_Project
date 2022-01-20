package com.example.happy.hr.repositories.custom.impl;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.domain.entities.ProjectCard_;
import com.example.happy.hr.domain.entities.User;
import com.example.happy.hr.domain.entities.User_;
import com.example.happy.hr.domain.wrappers.ProjectCardWrapper;
import com.example.happy.hr.json.dto.auxiliary.SortInfo;
import com.example.happy.hr.repositories.custom.CustomProjectCardRepository;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Objects;

/*  1. Пагинация
    2. Для формирования реестра
       Реестр это просто оболочка на фронте, которая вытягивает данные из карточки. А сам список проектов он никак не хранит в себе
    3. Данные для него собираются из таблицы с карточками
       Заранее неизвестно, какие фильтры придут
       Поэтому резонно динамически строить запрос
       Это можно сделать с помощью criteria api
       Что и происходит в кастомном репозитории  */

@AllArgsConstructor
public class CustomProjectCardRepositoryImpl implements CustomProjectCardRepository {

    private EntityManager entityManager;

    /*
        Этот метод равносилен sql запросу: SELECT card.id, card.project_name, card.proj_client_name, users.surname || '  '
        || users.name || '  ' || users.patronymic, card.card_status, card.functional_direction, card.subject_area, card.project_stage
        FROM project_card AS card JOIN users ON card.card_author_id = users.id WHERE фильтр LIMIT колич_проектов_на_странице
        OFFSET с_какого_проекта_выбрать;    */
    @Override
    public List<ProjectCardWrapper> getProjectCardPage(ProjectRegistryFilter filter, PageInfo pageInfo, SortInfo sortInfo) {
    /*  Список врапперов - это одна страница с проектами
        ProjectRegistryFilter - это пойдет в часть WHERE
        PageInfo - это пойдет в LIMIT и OFFSET  */
        if (pageInfo == null) {
            throw new IllegalArgumentException("Page info must be specified");
        }
        /*
            Классы, которые заканчиваются на _, называются метамоделями. Они генерируются jpamodelgen.
            Они используются для построения типобезопасных sql запросов в рантайме.
            В метамоделях хранится информация о типах полей entity класса   */

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProjectCardWrapper> criteriaQuery = criteriaBuilder.createQuery(ProjectCardWrapper.class);
        Root<ProjectCard> projectCardRoot = criteriaQuery.from(ProjectCard.class);
        Join<ProjectCard, User> joinUserToProjectCard = projectCardRoot.join(ProjectCard_.cardAuthor, JoinType.LEFT);

        Expression<String> fullName =
                criteriaBuilder.concat(
                        joinUserToProjectCard.get(User_.name),
                        criteriaBuilder.concat(
                                " ",
                                criteriaBuilder.concat(
                                        joinUserToProjectCard.get(User_.patronymic),
                                        criteriaBuilder.concat(
                                                " ", joinUserToProjectCard.get(User_.surname)
                                        )
                                )
                        )
                );

        criteriaQuery
                .select(
                        criteriaBuilder.construct(
                                ProjectCardWrapper.class,
                                projectCardRoot.get(ProjectCard_.id),
                                projectCardRoot.get(ProjectCard_.projectName),
                                projectCardRoot.get(ProjectCard_.projClientName),
                                fullName,
                                projectCardRoot.get(ProjectCard_.cardStatus),
                                projectCardRoot.get(ProjectCard_.functionalDirection),
                                projectCardRoot.get(ProjectCard_.subjectArea),
                                projectCardRoot.get(ProjectCard_.projectStage)
                        )
                );

        if (sortInfo != null) {
            if (sortInfo.getField().equals("cardAuthor")) {
                if (sortInfo.getSort().equalsIgnoreCase("asc")) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(fullName));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(fullName));
                }
            } else {
                if (sortInfo.getSort().equalsIgnoreCase("asc")) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(projectCardRoot.get(sortInfo.getField())));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(projectCardRoot.get(sortInfo.getField())));
                }
            }
        }

        if (filter != null) {
            String projectName = filter.getProjName();
            String projectClientName = filter.getProjClientName();
            String projectCardAuthor = filter.getCardAuthor();
            String projectCardStatus = filter.getCardStatus();

            Predicate projectNamePredicate = projectName != null && !projectName.isBlank() ?
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projectName), "%" + projectName + "%")
                    : criteriaBuilder.or(
                            criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projectName), "%"),
                            criteriaBuilder.isNull(projectCardRoot.get(ProjectCard_.projectName))
                    );

            Predicate projectClientNamePredicate = projectClientName != null && !projectClientName.isBlank() ?
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projClientName), "%" + projectClientName + "%")
                    : criteriaBuilder.or(
                            criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projClientName), "%"),
                            criteriaBuilder.isNull(projectCardRoot.get(ProjectCard_.projClientName))
                    );

            Predicate projectCardAuthorPredicate = projectCardAuthor != null && !projectCardAuthor.isBlank() ?
                    criteriaBuilder.like(fullName, "%" + projectCardAuthor + "%") :
                    criteriaBuilder.like(fullName, "%");

            Predicate projectCardStatusPredicate = projectCardStatus != null && !projectCardStatus.isBlank() ?
                    criteriaBuilder.equal(projectCardRoot.get(ProjectCard_.cardStatus), projectCardStatus) :
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.cardStatus), "%");

            criteriaQuery.where(
                    criteriaBuilder.and(
                            projectNamePredicate,
                            projectClientNamePredicate,
                            projectCardAuthorPredicate,
                            projectCardStatusPredicate
                    )
            );
        }

        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(pageInfo.getPageSize())
                .setFirstResult((pageInfo.getPageNum() - 1) * pageInfo.getPageSize())
                .getResultList();
    }

    @Override
    public ProjectCardWrapper getRegistryRecordById(Integer id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProjectCardWrapper> criteriaQuery = criteriaBuilder.createQuery(ProjectCardWrapper.class);
        Root<ProjectCard> projectCardRoot = criteriaQuery.from(ProjectCard.class);
        Join<ProjectCard, User> joinUserToProjectCard = projectCardRoot.join(ProjectCard_.cardAuthor, JoinType.LEFT);

        Expression<String> fullName =
                criteriaBuilder.concat(
                        joinUserToProjectCard.get(User_.name),
                        criteriaBuilder.concat(
                                " ",
                                criteriaBuilder.concat(
                                        joinUserToProjectCard.get(User_.patronymic),
                                        criteriaBuilder.concat(
                                                " ", joinUserToProjectCard.get(User_.surname)
                                        )
                                )
                        )
                );

        criteriaQuery
                .select(
                        criteriaBuilder.construct(
                                ProjectCardWrapper.class,
                                projectCardRoot.get(ProjectCard_.id),
                                projectCardRoot.get(ProjectCard_.projectName),
                                projectCardRoot.get(ProjectCard_.projClientName),
                                fullName,
                                projectCardRoot.get(ProjectCard_.cardStatus),
                                projectCardRoot.get(ProjectCard_.functionalDirection),
                                projectCardRoot.get(ProjectCard_.subjectArea),
                                projectCardRoot.get(ProjectCard_.projectStage)
                        )
                )
                .where(
                        criteriaBuilder.equal(projectCardRoot.get(ProjectCard_.id), id)
                );
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public Long count(ProjectRegistryFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<ProjectCard> projectCardRoot = criteriaQuery.from(ProjectCard.class);
        Join<ProjectCard, User> joinUserToProjectCard = projectCardRoot.join(ProjectCard_.cardAuthor, JoinType.LEFT);

        Expression<String> fullName =
                criteriaBuilder.concat(
                        joinUserToProjectCard.get(User_.name),
                        criteriaBuilder.concat(
                                " ",
                                criteriaBuilder.concat(
                                        joinUserToProjectCard.get(User_.patronymic),
                                        criteriaBuilder.concat(
                                                " ", joinUserToProjectCard.get(User_.surname)
                                        )
                                )
                        )
                );

        criteriaQuery.select(criteriaBuilder.count(projectCardRoot.get(ProjectCard_.id)));

        if (filter != null) {
            String projectName = filter.getProjName();
            String projectClientName = filter.getProjClientName();
            String projectCardAuthor = filter.getCardAuthor();
            String projectCardStatus = filter.getCardStatus();

            Predicate projectNamePredicate = projectName != null && !projectName.isBlank() ?
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projectName), "%" + projectName + "%")
                    : criteriaBuilder.or(
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projectName), "%"),
                    criteriaBuilder.isNull(projectCardRoot.get(ProjectCard_.projectName))
            );

            Predicate projectClientNamePredicate = projectClientName != null && !projectClientName.isBlank() ?
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projClientName), "%" + projectClientName + "%")
                    : criteriaBuilder.or(
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projClientName), "%"),
                    criteriaBuilder.isNull(projectCardRoot.get(ProjectCard_.projClientName))
            );

            Predicate projectCardAuthorPredicate = projectCardAuthor != null && !projectCardAuthor.isBlank() ?
                    criteriaBuilder.like(fullName, "%" + projectCardAuthor + "%") :
                    criteriaBuilder.like(fullName, "%");

            Predicate projectCardStatusPredicate = projectCardStatus != null && !projectCardStatus.isBlank() ?
                    criteriaBuilder.equal(projectCardRoot.get(ProjectCard_.cardStatus), projectCardStatus) :
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.cardStatus), "%");

            criteriaQuery.where(
                    criteriaBuilder.and(
                            projectNamePredicate,
                            projectClientNamePredicate,
                            projectCardAuthorPredicate,
                            projectCardStatusPredicate
                    )
            );
        }

        return entityManager
                .createQuery(criteriaQuery)
                .getSingleResult();
        /*
            Это окончательное формирование запроса, установка LIMIT и OFFSET и получение результата выполнения запроса в виде списка
            ЭнтитиМенеджер - это интерфейс для управления сущностями, если своими словами...
            Центральная часть в jpa и hibernate, в частности
            Он управляет жизненным циклом сущностей */
    }
}
