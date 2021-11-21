package com.epam.spring.homework.mvc.repairAgency.repository;

import com.epam.spring.homework.mvc.repairAgency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByLogin(String login);

    List<User> findAll();

    User save(User user);

    @Modifying
    @Query("update User u set u.name = ?1 where u.id = ?2")
    User updateName(String name, Long id);
}