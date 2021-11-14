package com.epam.spring.homework.mvc.repairAgency.service.repository.impl;

import java.util.List;

public interface GenerationId {

    default Integer generateId(final List<Integer> idList) {

        return idList.stream()
                .mapToInt(v -> v + 1)
                .max().orElse(1);
    }
}
