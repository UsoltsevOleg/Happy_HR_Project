package com.example.happy.hr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*  Контроллер, возвращающий index.html при запросе на `GET /`
*   Он вернёт index.html. index.html лежит в resources/templates
    Т.к. я добавил thymeleaf, шаблонизатор страниц позволяет код java в html вставлять + конфигурации добавляет
    Чтобы в методах контроллеров можно было сразу возвращать имя html файла */

@Controller
public class DefaultController {

    @GetMapping("/")
    public String getWelcomePage() {
        return "index";
    }
}
