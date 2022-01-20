package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*  DTO для WorkingHoursPattern */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkingHoursPatternDto {

    private Integer id;

    private String whpFrom;

    private String whpTill;

    private String overtime;

    private PossibleWorkScheduleDto possibleWorkSchedule;
}
