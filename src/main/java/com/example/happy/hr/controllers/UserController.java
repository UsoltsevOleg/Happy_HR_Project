package com.example.happy.hr.controllers;

import com.example.happy.hr.json.dto.UserDto;
import com.example.happy.hr.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*  Контроллер, возвращающий dummy data о пользователе
*   Отправка фронту данных пользователя
    Это для того, чтобы фронту отправить данные пользователя. Без них карточка не сохранится  */

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    /*  Возвращает данные встроенного пользователя, т.к. пока нет авторизации
    *   Для того, чтобы фронту отправить данные пользователя. Без них карточка не сохранится   */
    @GetMapping(value = "/api/users/auth", produces = "application/json")   //  produces добавит http заголовок о типе данных в теле ответа
    public ResponseEntity<UserDto> getSampleUser() {
        return ResponseEntity.ok(userService.getFirstUser());
    }
}
