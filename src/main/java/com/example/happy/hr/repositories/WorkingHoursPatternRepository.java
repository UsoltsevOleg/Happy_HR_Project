package com.example.happy.hr.repositories;

import com.example.happy.hr.domain.entities.WorkingHoursPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*  Репозиторий для WorkingHoursPattern */

@Repository
public interface WorkingHoursPatternRepository extends JpaRepository<WorkingHoursPattern, Integer> {
}
