package com.epam.spring.homework.mvc.repairAgency.service.repository.impl;

import com.epam.spring.homework.mvc.repairAgency.service.model.User;
import com.epam.spring.homework.mvc.repairAgency.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserRepositoryImpl implements UserRepository, GenerationId {

    private final List<User> list = new ArrayList<>();


    @Override
    public User get(String login) {
        return list.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User is not found!"));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public User add(final User user) {

        List<Integer> idList = list
                .stream()
                .map(x -> x.getId()).collect(Collectors.toList());
        int id = generateId(idList);
        log.info("id for new  user {}", id);
        if (user.getId() == 0) {
            user.setId(id);
        }
        list.add(user);
        return user;
    }

    @Override
    public User update(final String login, final User user) {
        boolean isDeleted = list.removeIf(u -> u.getLogin().equals(login));
        if (isDeleted) {
            list.add(user);
        } else {
            throw new RuntimeException("User is not found!");
        }
        return user;
    }

    @Override
    public void delete(final String login) {
        list.removeIf(user -> user.getLogin().equals(login));
    }
}
