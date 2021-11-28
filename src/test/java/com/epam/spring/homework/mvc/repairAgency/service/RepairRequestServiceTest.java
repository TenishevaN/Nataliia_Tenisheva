package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.mapper.RepairRequestMapper;
import com.epam.spring.homework.mvc.repairAgency.model.RepairRequest;
import com.epam.spring.homework.mvc.repairAgency.repository.RepairRequestRepository;
import com.epam.spring.homework.mvc.repairAgency.service.impl.RepairRequestServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class RepairRequestServiceTest {

    @InjectMocks
    private RepairRequestServiceImpl repairRequestService;

    @Mock
    private RepairRequestRepository repairRequestRepository;

    @Spy
    private RepairRequestMapper repairRequestMapper = Mappers.getMapper(RepairRequestMapper.class);


    @Test
    void getRepairRequestById() {

        //given
        RepairRequest expectedRepairRequest = new RepairRequest();
        expectedRepairRequest.setDescription("description");
        when(repairRequestRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(expectedRepairRequest));

        //when
        RepairRequestDto actualRepairRequest = repairRequestService.get(1L);

        //then
        assertEquals(expectedRepairRequest.getDescription(), actualRepairRequest.getDescription());
    }

    @Test
    void getAllTest() {

        when(repairRequestRepository.findAll()).thenReturn(Arrays.asList(new RepairRequest(), new RepairRequest()));

        List<RepairRequestDto> data = repairRequestService.getAll();

        assertEquals(2, data.size());
    }

    @Test
    void getAllSortedAndPaginatedTest() {

        int pageSize = 2;
        RepairRequest expectedRepairRequest = new RepairRequest();

        List<RepairRequest> expected = new ArrayList<>();
        expected.add(expectedRepairRequest);
        expected.add(expectedRepairRequest);
        Page foundPage = new PageImpl<>(expected);

        when(repairRequestRepository.findAll(any(Pageable.class))).thenReturn(foundPage);
        List<RepairRequestDto> repairRequests = repairRequestService.getAllSortedAndPaginated(pageSize);

        assertEquals(pageSize, repairRequests.size());
    }

    @Test
    void createRepairRequest() {

        //given
        RepairRequest expectedRepairRequest = new RepairRequest();
        expectedRepairRequest.setDescription("description");
        when(repairRequestRepository.save((any(RepairRequest.class)))).thenReturn(expectedRepairRequest);

        //when
        RepairRequestDto actualRepairRequest = repairRequestService.add(repairRequestMapper.mapRepairRequestDto(expectedRepairRequest));

        //then
        assertEquals(expectedRepairRequest.getDescription(), actualRepairRequest.getDescription());
    }

    @Test
    void updateRepairRequest() {

        final String UPDATE_DESCRIPTION = "update description";
        //given
        RepairRequest expectedRepairRequest = new RepairRequest();
        expectedRepairRequest.setDescription(UPDATE_DESCRIPTION);

        RepairRequestDto updatedRepairRequest = repairRequestMapper.mapRepairRequestDto(expectedRepairRequest);
        updatedRepairRequest.setDescription(UPDATE_DESCRIPTION);

        when(repairRequestRepository.findById(1L)).thenReturn(Optional.of(expectedRepairRequest));

        //when
        RepairRequestDto actualRepairRequest = repairRequestService.update(1L, updatedRepairRequest);

        //then
        assertEquals(UPDATE_DESCRIPTION, actualRepairRequest.getDescription());
    }

    @Test
    void deleteRepairRequest() {

        //given
        doNothing().when(repairRequestRepository).deleteById(1L);

        //when
        repairRequestService.delete(1L);
        repairRequestService.delete(1L);

        //then
        verify(repairRequestRepository, times(2)).deleteById(1L);
    }
}
