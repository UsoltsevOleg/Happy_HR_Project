package com.example.happy.hr.domain.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*  Класс-обертка для формирования записи реестра проекта
*   Wrapper используется criteria api, чтобы из таблички с карточками не все поля селектить, а только те, которые обозначены во wrapper
    SELECT столбец1, столбец2, ... FROM таблица
    Колонки, которые написаны после селекта   */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCardWrapper {

    private Integer id;

    private String projectName;

    private String projClientName;

    private String cardAuthor;

    private String cardStatus;

    private String functionalDirection;

    private String subjectArea;

    private String projectStage;
}
