package com.example.happy.hr.repositories;

import com.example.happy.hr.domain.entities.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*  Репозиторий для ProjectType */

@Repository
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Integer> {
}
