package com.springboot.fullstack.mapper;

import com.springboot.fullstack.dto.AccountLocalizationDto;
import com.springboot.fullstack.model.AccountLocalization;
import org.mapstruct.Mapper;

@Mapper
public interface AccountLocalizationMapper {

    AccountLocalizationDto mapAccountLocalizationDto(AccountLocalization accountLocalization);
    AccountLocalization mapAccountLocalization(AccountLocalizationDto dto);
}