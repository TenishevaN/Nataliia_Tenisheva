package com.springboot.fullstack.service.implementation;

import com.springboot.fullstack.dto.RepairRequestDto;
import com.springboot.fullstack.mapper.RepairRequestMapper;
import com.springboot.fullstack.model.RepairRequest;
import com.springboot.fullstack.repository.RepairRequestRepository;
import com.springboot.fullstack.repository.UserRepository;
import com.springboot.fullstack.service.RepairRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepairRequestServiceImpl implements RepairRequestService {

    private final RepairRequestRepository repairRequestRepository;
    private final UserRepository userRepository;
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

        List<RepairRequest> listRepairRequest = repairRequestRepository.findAll();
        List<RepairRequestDto> list = new ArrayList<>();
        for (RepairRequest item : listRepairRequest) {
            RepairRequestDto newRR = repairRequestMapper.mapRepairRequestDto(item);
            if (item.getMaster() != null) {
                newRR.setMasterName(item.getMaster().getName());
            }
            list.add(newRR);
        }
        return list;
    }

    @Override
    public List<RepairRequestDto> getAllSortedAndPaginated(int page) {

        Pageable paging = PageRequest.of(page, 2, Sort.by("date").descending().and(Sort.by("cost").descending()));
        Page<RepairRequest> pagedResult = repairRequestRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent()
                    .stream()
                    .map(
                            repairRequestMapper::mapRepairRequestDto
                    )
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Override
    public RepairRequestDto add(final RepairRequestDto repairRequestDto) {

        RepairRequest repairRequest = repairRequestMapper.mapRepairRequest(repairRequestDto);
        RepairRequestDto updatedRepairRequestDto = repairRequestMapper.mapRepairRequestDto(repairRequest);
        if (repairRequestDto.getUserId() != null) {
            repairRequest.setUser(userRepository.getById(Long.valueOf(repairRequestDto.getUserId())));
            updatedRepairRequestDto.setUserName(repairRequest.getUser().getName());
        }
        if (repairRequestDto.getMasterId() != null) {
            repairRequest.setMaster(userRepository.getById(Long.valueOf(repairRequestDto.getMasterId())));
            updatedRepairRequestDto.setMasterName(repairRequest.getMaster().getName());
        }
        repairRequest.setStatusId(1L);
        repairRequestRepository.save(repairRequest);

        return updatedRepairRequestDto;
    }

    @Override
    public RepairRequestDto update(final Long id, final RepairRequestDto repairRequestDto) {

        Optional<RepairRequest> repairRequestOptional = repairRequestRepository.findById(id);
        RepairRequestDto updatedRepairRequestDto = null;

        if (repairRequestOptional.isPresent()) {
            RepairRequest repairRequest = repairRequestOptional.get();
            updatedRepairRequestDto = repairRequestMapper.mapRepairRequestDto(repairRequest);
            if (repairRequestDto.getUserId() != null) {
                repairRequest.setUser(userRepository.getById(Long.valueOf(repairRequestDto.getUserId())));
                updatedRepairRequestDto.setUserName(repairRequest.getUser().getName());
            }
            repairRequest.setDescription(repairRequestDto.getDescription());
            repairRequest.setCost(repairRequestDto.getCost());
            repairRequest.setDate(repairRequestDto.getDate());
            repairRequestRepository.save(repairRequest);
            log.info("repairRequest Updated {}", repairRequest);

            return updatedRepairRequestDto;
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
