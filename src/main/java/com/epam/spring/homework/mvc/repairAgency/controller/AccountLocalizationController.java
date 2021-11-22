package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.AccountLocalizationDto;
import com.epam.spring.homework.mvc.repairAgency.service.AccountLocalizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountLocalizationController {

    private final AccountLocalizationService accountLocalizationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/account-localization/{userId}")
    public List<AccountLocalizationDto> getAllByUserId(@PathVariable Long userId) {
        return accountLocalizationService.getAllByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/account-localization/{userId}/{languageId}")
    public AccountLocalizationDto getByUserIdLanguageId(@PathVariable Long userId, @PathVariable Long languageId) {
        return accountLocalizationService.get(userId, languageId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/account-localization/{userId}/{languageId}/{name}")
    public String updateName(@PathVariable Long userId, @PathVariable Long languageId, @PathVariable String name) {

        int updatedAccountLocalization = accountLocalizationService.update(userId, languageId, name);
        if (updatedAccountLocalization != 1) {
            throw new NullPointerException();
        }
        return "updated";
    }
}
