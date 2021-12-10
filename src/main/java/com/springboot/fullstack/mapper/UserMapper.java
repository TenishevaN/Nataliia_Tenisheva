package com.springboot.fullstack.mapper;

import com.springboot.fullstack.dto.UserDto;
import com.springboot.fullstack.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDto mapUserDto(User user);
    User mapUser(UserDto dto);
}