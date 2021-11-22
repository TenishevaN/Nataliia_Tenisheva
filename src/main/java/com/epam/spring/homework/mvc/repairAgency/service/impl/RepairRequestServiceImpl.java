package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.mapper.RepairRequestMapper;
import com.epam.spring.homework.mvc.repairAgency.service.RepairRequestService;
import com.epam.spring.homework.mvc.repairAgency.model.RepairRequest;
import com.epam.spring.homework.mvc.repairAgency.repository.RepairRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepairRequestServiceImpl implements RepairRequestService {

    private final RepairRequestRepository repairRequestRepository;
    private final RepairRequestMapper repairRequestMapper;

    @Override
    public RepairRequestDto get(final Long id) {
        Optional<RepairRequest> repairRequestOptional = repairRequestRepository.findById(id);
        if (repairRequestOptional.isPresent()) {
            return repairRequestMapper.mapRepairRequestDto(repairRequestOptional.get());
        }
        return null;
    }

    @Override
    public List<RepairRequestDto> getAll() {

        return repairRequestRepository.findAll()
                .stream()
                .map(repairRequestMapper::mapRepairRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairRequestDto> getAllSortedAndPaginated(int page) {

        Pageable paging = PageRequest.of(page, 2, Sort.by("date").descending().and(Sort.by("cost").descending()));
        Page<RepairRequest> pagedResult = repairRequestRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent()
                    .stream()
                    .map(repairRequestMapper::mapRepairRequestDto)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Override
    public RepairRequestDto add(final RepairRequestDto repairRequestDto) {
        RepairRequest repairRequest = repairRequestMapper.mapRepairRequest(repairRequestDto);
        repairRequest = repairRequestRepository.save(repairRequest);
        return repairRequestMapper.mapRepairRequestDto(repairRequest);
    }

    @Override
    public RepairRequestDto update(final Long id, final RepairRequestDto repairRequestDto) {

        Optional<RepairRequest> repairRequestOptional = repairRequestRepository.findById(id);
        if (repairRequestOptional.isPresent()) {
            RepairRequest repairRequest = repairRequestOptional.get();
            repairRequest.setDescription(repairRequestDto.getDescription());
            repairRequest.setCost(repairRequestDto.getCost());
            repairRequest.setMasterId(repairRequestDto.getMasterId());
            repairRequest.setStatusId(repairRequestDto.getStatusId());
            repairRequest.setDate(repairRequestDto.getDate());
            repairRequestRepository.save(repairRequest);
            log.info("repairRequest Updated {}", repairRequest);
            return repairRequestMapper.mapRepairRequestDto(repairRequest);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete RepairRequest by id {}", id);
        repairRequestRepository.deleteById(Long.valueOf(id));
        return true;
    }
}
