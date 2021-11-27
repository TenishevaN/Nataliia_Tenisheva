package com.epam.spring.homework.mvc.repairAgency.mapper;

import com.epam.spring.homework.mvc.repairAgency.dto.UserDto;
import com.epam.spring.homework.mvc.repairAgency.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Set<UserDto> mapUserDtos(Set<User> users);

    UserDto mapUserDto(User user);

    User mapUser(UserDto dto);
}