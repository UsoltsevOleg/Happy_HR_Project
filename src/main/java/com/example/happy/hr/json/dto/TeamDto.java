package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.PositiveOrZero;

/*  DTO для Team    */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamDto {

    private Integer id;

    @Length(max = 500)
    private String devMethodology;

    private Boolean productDevelopment;

    private Boolean testers;

    private Boolean techWriters;

    @PositiveOrZero
    private Integer analystsNum;

    private Boolean hasAnalysts;

    @PositiveOrZero
    private Integer devsNum;

    private Boolean hasDevs;

    private Boolean teamReady;

    @PositiveOrZero
    private Integer peopleInTeamNum;
}
