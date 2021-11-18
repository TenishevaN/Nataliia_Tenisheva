package com.epam.spring.homework.mvc.repairAgency.repository;

import com.epam.spring.homework.mvc.repairAgency.model.Status;
import java.util.List;

public interface StatusRepository {

    Status get(int idStatus, String locale);

    List<Status> getAll(String locale);
}
