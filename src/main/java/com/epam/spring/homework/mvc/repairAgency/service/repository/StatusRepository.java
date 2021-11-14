package com.epam.spring.homework.mvc.repairAgency.service.repository;

import com.epam.spring.homework.mvc.repairAgency.service.model.Status;
import java.util.List;

public interface StatusRepository {

    Status get(int idStatus, String locale);

    List<Status> getAll(String locale);
}
