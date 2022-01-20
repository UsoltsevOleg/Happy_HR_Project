package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.User;
import com.example.happy.hr.json.dto.UserDto;
import org.mapstruct.Mapper;

/*  Маппер для UserDto и User   */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto dto);
}
