package com.example.happy.hr.controllers.query.params;

import lombok.AllArgsConstructor;
import lombok.Data;

/*  Класс-обертка с фильтрами для поиска проектов в реестре
    Обёртки оборачивают параметры http запроса  */

@AllArgsConstructor
@Data
public class ProjectRegistryFilter {
    private final String projName;
    private final String projClientName;
    private final String cardAuthor;
    private final String cardStatus;
}
