package com.epam.spring.homework.mvc.repairAgency.repository.impl;

import com.epam.spring.homework.mvc.repairAgency.model.User;
import com.epam.spring.homework.mvc.repairAgency.repository.UserRepository;
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
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public User add(final User user) {

        if (user.getId() == 0) {
            List<Integer> idList = list
                    .stream()
                    .map(x -> x.getId()).collect(Collectors.toList());
            int id = generateId(idList);
            log.info("id for new  user {}", id);
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
            return null;
        }
        return user;
    }

    @Override
    public boolean delete(final String login) {

        return list.removeIf(user -> user.getLogin().equals(login));
    }
}
