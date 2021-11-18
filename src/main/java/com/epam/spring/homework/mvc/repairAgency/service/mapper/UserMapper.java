package com.epam.spring.homework.mvc.repairAgency.service.mapper;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.UserDto;
import com.epam.spring.homework.mvc.repairAgency.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    Set<UserDto> mapUserDtos(Set<User> users);
    UserDto mapUserDto(User users);
    User mapUser(UserDto dto);
}