package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.StatusDto;
import com.epam.spring.homework.mvc.repairAgency.model.Status;
import com.epam.spring.homework.mvc.repairAgency.repository.StatusRepository;
import com.epam.spring.homework.mvc.repairAgency.service.impl.StatusServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class StatusServiceImplTest {

    @InjectMocks
    private StatusServiceImpl statusService;


    @Mock
    private StatusRepository statusRepository;


    @Test
    void getStatusTest() {

        //given
        Status expectedStatus = new Status();
        expectedStatus.setId(1L);
        expectedStatus.setName("new");

        when(statusRepository.get(1L, "ru")).thenReturn(expectedStatus);

        //when
        StatusDto actualStatus = statusService.get(1L, "ru");

        //then
        assertEquals(expectedStatus.getName(), actualStatus.getName());
    }

    @Test
    void getAllStatusTest() {

        //given
        Status expectedStatusNew = new Status();
        expectedStatusNew.setName("new");
        Status expectedStatusClosed = new Status();
        expectedStatusClosed.setName("closed");

        List<StatusDto> expectedStatuses = new ArrayList<>();

        when(statusRepository.getAll("en")).thenReturn((Arrays.asList(expectedStatusNew, expectedStatusClosed)));

        //when
        List<StatusDto> actualStatuses = statusService.getAll("en");

        //then
        assertThat(actualStatuses, hasSize(2));
        assertEquals("closed", actualStatuses.get(1).getName());
    }
}
