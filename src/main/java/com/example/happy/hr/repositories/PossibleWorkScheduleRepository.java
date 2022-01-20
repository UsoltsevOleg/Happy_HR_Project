package com.example.happy.hr.repositories;

import com.example.happy.hr.domain.entities.PossibleWorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*  Репозиторий для PossibleWorkSchedule    */

@Repository
public interface PossibleWorkScheduleRepository extends JpaRepository<PossibleWorkSchedule, Integer> {
    List<PossibleWorkSchedule> findByFlextime(Boolean flextime);
}
