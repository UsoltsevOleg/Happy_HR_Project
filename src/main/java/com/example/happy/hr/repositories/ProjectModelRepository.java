package com.example.happy.hr.repositories;

import com.example.happy.hr.domain.entities.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*  Репозиторий для ProjectModel    */

@Repository
public interface ProjectModelRepository extends JpaRepository<ProjectModel, Integer> {
}
