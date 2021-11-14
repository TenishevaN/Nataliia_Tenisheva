package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.service.RepairRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RepairRequestController {

    private final RepairRequestService repairRequestService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/repairRequest")
    public RepairRequestDto addRepairRequest(@RequestBody RepairRequestDto repairRequestDto) {
        return repairRequestService.add(repairRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/repairRequest/{id}")
    public RepairRequestDto updateRepairRequest(@PathVariable int id, @RequestBody RepairRequestDto repairRequestDto) {
        return repairRequestService.update(id, repairRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/repairRequest")
    public List<RepairRequestDto> getAllRepairRequests() {
        return repairRequestService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/repairRequest/{id}")
    public RepairRequestDto getRepairRequest(@PathVariable int id) {
        return repairRequestService.get(id);
    }

    @DeleteMapping(value = "/repairRequest/{id}")
    public ResponseEntity<Void> deleteRepairRequest(@PathVariable int id) {
        repairRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
