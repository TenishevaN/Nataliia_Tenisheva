package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.service.RepairRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RepairRequestController {

    private final RepairRequestService repairRequestService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/repair-request")
    public RepairRequestDto addRepairRequest(@Valid @RequestBody RepairRequestDto repairRequestDto) {
        return repairRequestService.add(repairRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/repair-request/{id}")
    public RepairRequestDto updateRepairRequest(@PathVariable int id, @Valid @RequestBody RepairRequestDto repairRequestDto) {
        return repairRequestService.update(id, repairRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/repair-request")
    public List<RepairRequestDto> getAllRepairRequests() {
        return repairRequestService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/repair-request/{id}")
    public RepairRequestDto getRepairRequest(@PathVariable int id) {
        return repairRequestService.get(id);
    }

    @DeleteMapping(value = "/repair-request/{id}")
    public ResponseEntity<Void> deleteRepairRequest(@PathVariable int id) {
        repairRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
