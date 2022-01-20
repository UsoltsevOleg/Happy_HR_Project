package com.example.happy.hr.services.impl;

import com.example.happy.hr.json.dto.UserDto;
import com.example.happy.hr.json.mapper.UserMapper;
import com.example.happy.hr.repositories.UserRepository;
import com.example.happy.hr.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*  Реализация UserService  */

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserDto getFirstUser() {
        log.info("Getting dummy user info");
        return userMapper.toUserDto(userRepository.findById(1).orElseThrow());
        /*
        Optional специальный класс, который хранит некоторое значение. Это значение может быть, а может не быть.
        Метод orElseThrow выбросит исключение, если значения в optional не будет. Если оно есть, то оно возвращается
        UserRepository наследует JpaRepository. А JpaRepository наследует CrudRepository.
        В CrudRepository есть метод findById, который возвращает Optional
        */
    }
}
