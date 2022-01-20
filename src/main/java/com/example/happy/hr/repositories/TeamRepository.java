package com.example.happy.hr.repositories;

import com.example.happy.hr.domain.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*  Репозиторий для TeamRepository  */

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
