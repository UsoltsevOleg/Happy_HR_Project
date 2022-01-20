package com.example.happy.hr.repositories;

import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.repositories.custom.CustomProjectCardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*  репозиторий для ProjectCard */

@Repository
public interface ProjectCardRepository extends JpaRepository<ProjectCard, Integer>, CustomProjectCardRepository {

    @Modifying
    @Query("UPDATE ProjectCard c SET c.prevCardStatus = c.cardStatus, c.cardStatus = 'Архив' WHERE c.id=:id AND c.cardStatus <> 'Архив'")
    void archiveById(Integer id);   //  Архивация

    @Modifying
    @Query("UPDATE ProjectCard c SET c.cardStatus = c.prevCardStatus WHERE c.id=:id")
    void unarchiveById(Integer id); // Разархивация
}
