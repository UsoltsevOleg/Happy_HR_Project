package com.example.happy.hr.controllers;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.auxiliary.ProjectCardInfo;
import com.example.happy.hr.json.dto.auxiliary.SortInfo;
import com.example.happy.hr.services.ProjectCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/*  Контроллер для работы с карточками и реестром проектов  */

@RestController // Эта аннотация автоматически добавит в тело запроса объект (дто), возвращаемый каким-либо методом контроллера
@RequestMapping("/api/cards")   // Не придётся над каждым методом контроллера прописывать в url "/api/cards"
@AllArgsConstructor // Автовайрит через конструктор
@Slf4j
public class ProjectCardController {

    private ProjectCardService projectCardService;

    /*  Эндпойнт для создания новой карточки */
    @PostMapping("/new")
    public ResponseEntity<?> createProjectCard(@RequestBody @Valid ProjectCardDto projectCardDto) {
        /*  Например, http://какой-то сайт.com/api/123
            @RequestBody представляет собой тело HTTP-запроса. Там может быть json, xml или даже файлы
            @Valid нужно, чтобы при получении dto выполнились все проверки, @NotNull, @NotBlank и тд  */
        log.info("Request on POST /api/cards/new");
        return ResponseEntity.status(201).body(projectCardService.save(projectCardDto));
    }

    /*  Эндпойнт для редактирования существующей карточки    */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCard(@RequestBody @Valid ProjectCardDto projectCardDto) {
        log.info("Request on PUT /api/cards/{id}");
        log.info("Updating card {}", projectCardDto);
        projectCardService.save(projectCardDto);    // Методом save можно обновлять сущности, если у них id не null и в бд есть запись с таким же id
        return ResponseEntity.status(204).build();
    }

    /*  Эндпойнт возвращает страницу реестра проектов (с фильтрацией и пагинацией)  */
    @GetMapping(value = "/registry", produces = "application/json")
    public ResponseEntity<List<ProjectCardInfo>> getRegistryPage(@RequestParam(required = false) String projName,
                                                                 @RequestParam(required = false) String projClient,
                                                                 @RequestParam(required = false) String cardAuthor,
                                                                 @RequestParam(required = false) String cardStatus,
                                                                 @RequestParam(required = false) Integer page,
                                                                 @RequestParam(required = false) String field,
                                                                 @RequestParam(required = false) String sort) {
        /*  http://какой-то сайт.com/api/do-something?key1=val1&key2=val2
            key1 и key2 это @RequestParam
            & для разделения нескольких параметров друг от друга    */
        log.info("Request on GET /api/cards/registry");

        SortInfo sortInfo = null;

        if (field != null && sort != null) {
            sortInfo = new SortInfo(field, sort);
        }

        return ResponseEntity.ok(
                projectCardService
                        .getProjectCardPage(
                                new ProjectRegistryFilter(projName, projClient, cardAuthor, cardStatus),
                                new PageInfo(page == null || page < 0 ? 1 : page, 10),
                                sortInfo
                        )
        );
    }

    /*  Эндпойнт возвращает данные выбранной карточки   */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProjectCardDto> getProjectCardById(@PathVariable Integer id) {
        /*  http://какой-то сайт.com/api/{value}
            Здесь {value} @PathVariable, вместо нее можно подставлять строки или числа в url    */
        log.info("Request on GET /api/cards/{id}");
        return ResponseEntity.ok(projectCardService.getProjectCardById(id));
    }

    /*  Эндпойнт удаляет выбранную карточку */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer id) {
        log.info("Request on DELETE /api/cards/{id}");
        projectCardService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    /*  Эндпойнт архивирует карточку    */
    @PutMapping("/archive/{id}")
    public ResponseEntity<ProjectCardInfo> archiveCard(@PathVariable Integer id) {
        log.info("Request on PUT /api/cards/archive/{id}");
        return ResponseEntity.ok(projectCardService.archiveById(id));
    }

    /*  Эндпойнт восстанавливает карточку из архива  */
    @DeleteMapping("/archive/{id}")
    public ResponseEntity<ProjectCardInfo> unarchiveCard(@PathVariable Integer id) {
        log.info("Request on DELETE /api/cards/archive/{id}");
        return ResponseEntity.ok(projectCardService.unarchiveById(id));
    }

    /*  Эндпойнт для получения количества созданных карточек    */
    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> getProjCardsNum(@RequestParam(required = false) String projName,
                                                             @RequestParam(required = false) String projClient,
                                                             @RequestParam(required = false) String cardAuthor,
                                                             @RequestParam(required = false) String cardStatus) {
        log.info("Request on GET /api/cards/total");
        return ResponseEntity.ok(
                projectCardService
                        .count(
                                new ProjectRegistryFilter(projName, projClient, cardAuthor, cardStatus)
                        )
        );
    }
        /*
        Для того, чтобы перевести json в объект java в спринге используется библиотека jackson
        Эта библиотека занимается специализацией java объектов в json и обратно
        Это происходит автоматически при получении методом контроллера тела запроса и при отправке ответа

        Дто это и есть json
        Если в энтити в качестве поля используется другой энтити, а он в свою очередь связан с другими энтити,
        то при сериализации в json (jackson использует геттеры для считывания данных из полей java объекта)
        вызовы геттеров могут образовать замкнутую цепочку вызовов. Она будет разрастаться до тех пор,
        пока приложение не вылетит с ошибкой StackOverflow
     */
}
