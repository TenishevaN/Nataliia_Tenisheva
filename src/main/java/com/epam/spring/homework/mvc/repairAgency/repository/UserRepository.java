package com.epam.spring.homework.mvc.repairAgency.repository;

import com.epam.spring.homework.mvc.repairAgency.model.User;

import java.util.List;

public interface UserRepository {

  User get(String login);

  List<User> getAll();

  User add(User user);

  User update(String login, User user);

  boolean delete(String login);
}