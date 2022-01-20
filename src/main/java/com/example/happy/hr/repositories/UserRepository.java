package com.example.happy.hr.repositories;

import com.example.happy.hr.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*  Репозиторий для UserRepository  */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
