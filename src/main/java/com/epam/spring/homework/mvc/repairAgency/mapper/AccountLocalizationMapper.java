package com.epam.spring.homework.mvc.repairAgency.mapper;

import com.epam.spring.homework.mvc.repairAgency.dto.AccountLocalizationDto;
import com.epam.spring.homework.mvc.repairAgency.model.AccountLocalization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface AccountLocalizationMapper {
    AccountLocalizationMapper INSTANCE = Mappers.getMapper(AccountLocalizationMapper.class);
    Set<AccountLocalizationDto> mapAccountLocalizationDtos(Set<AccountLocalization> accountLocalizations);
    AccountLocalizationDto mapAccountLocalizationDto(AccountLocalization accountLocalization);
    AccountLocalization mapAccountLocalization(AccountLocalizationDto dto);
}