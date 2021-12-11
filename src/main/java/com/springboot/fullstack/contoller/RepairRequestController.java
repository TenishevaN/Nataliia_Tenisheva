package com.springboot.fullstack.contoller;

import com.springboot.fullstack.dto.RepairRequestDto;
import com.springboot.fullstack.dto.UserDto;
import com.springboot.fullstack.service.RepairRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RepairRequestController {

    private final RepairRequestService repairRequestService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/repair-request")
    public String getListRepairRequests(Model model) {

        model.addAttribute("role", model.getAttribute("role"));
        model.addAttribute("area","list");
        model.addAttribute("repairRequests", repairRequestService.getAll());
        model.addAttribute("userId",1);
        model.addAttribute("command", "listRequests");
     //   model.addAttribute("currentLocale","en");
        model.addAttribute("idStatus", "-1");
        model.addAttribute("idMaster", "-1");

//        req.setAttribute("repairRequests", repairRequests);
//        req.setAttribute("role", currentRole.getName());
//        req.setAttribute("userId", currentUser.getId());
//        req.setAttribute("command", "listRequests");
//        paginationUtil.setPaginationSessionParameterPage(req);
//        String orderBy = req.getParameter("orderBy");
//        if ("null".equals(orderBy)) {
//            req.setAttribute("orderBy", "ASC");
//        }
//        req.setAttribute("idStatus", "-1");
//        req.setAttribute("idMaster", "-1");
//        req.setAttribute("currentLocale", currentLocale);


//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.getModel().put("repairRequests", repairRequestService.getAll());
//        modelAndView.setViewName("listRepairRequests");
        return "listRepairRequests";
    }

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
    @PutMapping(value = "/repair-request/{id}")
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
