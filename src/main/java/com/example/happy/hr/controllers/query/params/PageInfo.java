package com.example.happy.hr.controllers.query.params;

import lombok.AllArgsConstructor;
import lombok.Data;

/*  Класс-обертка с номером и размером страницы реестра проектов
*   Обёртки оборачивают параметры http запроса   */

@AllArgsConstructor
@Data
public class PageInfo {
    private final Integer pageNum;
    private final Integer pageSize;
}
