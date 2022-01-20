package com.example.happy.hr.json.dto.auxiliary;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.ValidationException;
import java.util.regex.Pattern;

/*  Класс-обертка с данными для сортировки реестра  */

@Data
public class SortInfo {

    private final String field;

    private final String sort;

    public SortInfo(String field, String sort) {
        String fieldPattern = "(id)|(projectName)|(projClientName)|(cardAuthor)|(cardStatus)|(functionalDirection)|(subjectArea)|(projectStage)";
        String sortPattern = "([Aa][Ss][Cc])|([Dd][Ee][Ss][Cc])";
        if (!Pattern.compile(fieldPattern).matcher(field).matches() || !Pattern.compile(sortPattern).matcher(sort).matches()) {
            throw new ValidationException("Invalid filed name");
        }
        this.field = field;
        this.sort = sort;
    }
}
