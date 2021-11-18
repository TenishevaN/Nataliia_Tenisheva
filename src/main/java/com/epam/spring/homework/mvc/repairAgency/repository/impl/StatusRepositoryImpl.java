package com.epam.spring.homework.mvc.repairAgency.repository.impl;

import com.epam.spring.homework.mvc.repairAgency.model.Status;
import com.epam.spring.homework.mvc.repairAgency.repository.StatusRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatusRepositoryImpl implements StatusRepository {

    private final List<Status> list = new ArrayList<>();

    @Override
    public Status get(int idStatus, String locale) {
        return list.stream()
                .filter(status -> (status.getId() == idStatus && status.getLocale().equals(locale)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Status> getAll(String locale) {
        return new ArrayList<>(list);
    }
}
