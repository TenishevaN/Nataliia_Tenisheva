package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.service.RepairRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RepairRequestController {

    private final RepairRequestService repairRequestService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/repair-request")
    public RepairRequestDto addRepairRequest(@Valid @RequestBody RepairRequestDto repairRequestDto) {

        RepairRequestDto repairRequest = repairRequestService.add(repairRequestDto);
        if (repairRequest == null) {
            throw new NullPointerException();
        }
        return repairRequest;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/repair-request/")
    public RepairRequestDto updateRepairRequest(@PathVariable Long id, @Valid @RequestBody RepairRequestDto repairRequestDto) {

        RepairRequestDto repairRequest = repairRequestService.update(id, repairRequestDto);
        if (repairRequest == null) {
            throw new NullPointerException();
        }
        return repairRequest;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/repair-request/page/{page}")
    public List<RepairRequestDto> getAllPaginated(@PathVariable int page) {
        return repairRequestService.getAllSortedAndPaginated(page);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/repair-request/")
    public List<RepairRequestDto> getAllRepairRequests() {
        return repairRequestService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/repair-request/{id}")
    public RepairRequestDto getRepairRequest(@PathVariable Long id) {

        RepairRequestDto repairRequest = repairRequestService.get(id);
        if (repairRequest == null) {
            throw new NullPointerException();
        }
        return repairRequest;
    }

    @DeleteMapping(value = "/repair-request/{id}")
    public ResponseEntity<Void> deleteRepairRequest(@PathVariable Long id) {

        boolean repairRequestDeleted = repairRequestService.delete(id);
        if (!repairRequestDeleted) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.noContent().build();
    }
}
