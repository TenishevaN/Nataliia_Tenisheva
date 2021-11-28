package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.AccountLocalizationDto;
import com.epam.spring.homework.mvc.repairAgency.mapper.AccountLocalizationMapper;
import com.epam.spring.homework.mvc.repairAgency.model.AccountLocalization;
import com.epam.spring.homework.mvc.repairAgency.repository.AccountLocalizationRepository;
import com.epam.spring.homework.mvc.repairAgency.service.impl.AccountLocalizationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AccountLocalizationServiceTest {

    @InjectMocks
    private AccountLocalizationServiceImpl accountLocalizationService;

    @Mock
    private AccountLocalizationRepository accountLocalizationRepository;

    @Spy
    private AccountLocalizationMapper accountLocalizationMapper = Mappers.getMapper(AccountLocalizationMapper.class);

    private static final String TEST_NAME = "testName";

    @Test
    void createAccountLocalizationTest() {

        //given
        AccountLocalization expectedAccountLocalization = new AccountLocalization();
        expectedAccountLocalization.setName(TEST_NAME);
        when(accountLocalizationRepository.save((any(AccountLocalization.class)))).thenReturn(expectedAccountLocalization);

        //when
        AccountLocalizationDto actualAccountLocalization = accountLocalizationService.add(accountLocalizationMapper.mapAccountLocalizationDto(expectedAccountLocalization));

        //then
        assertEquals(expectedAccountLocalization.getName(), actualAccountLocalization.getName());
    }

    @Test
    void updateAccountLocalizationTest() {

        //given
        AccountLocalization expectedAccountLocalization = new AccountLocalization();

        AccountLocalizationDto updatedAccountLocalization = accountLocalizationMapper.mapAccountLocalizationDto(expectedAccountLocalization);
        updatedAccountLocalization.setName(TEST_NAME);

        when(accountLocalizationRepository.updateByAccountIdAndLanuageId(TEST_NAME, 1L, 1L)).thenReturn(1);

        //when
        int result = accountLocalizationService.update(1L, 1L, TEST_NAME);

        //then
        assertEquals(1, result);
    }

    @Test
    void getAllAccountLocalizationByUserIdTest() {

        when(accountLocalizationRepository.findByAccountId(1L)).thenReturn(Arrays.asList(new AccountLocalization(), new AccountLocalization()));

        List<AccountLocalizationDto> data = accountLocalizationService.getAllByUserId(1L);

        assertEquals(2, data.size());
    }

    @Test
    void getAccountLocalizationByUserIdLanguageIdTest() {

        when(accountLocalizationRepository.findByAccountIdAndLanuageId(1L, 1L)).thenReturn(new AccountLocalization());

        AccountLocalizationDto accountLocalizationDto = accountLocalizationService.get(1L, 1L);

        assertNotNull(accountLocalizationDto);
    }
}
