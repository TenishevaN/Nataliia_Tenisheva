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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class StatusServiceImplTest {

    @InjectMocks
    private StatusServiceImpl statusService;


    @Mock
    private StatusRepository statusRepository;


    @Test
    void getUserByLoginTest(){

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
}
