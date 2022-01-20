package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/*  DTO для ProjectCard */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCardDto {

    /*  Поля не final из-за mapstruct    */

    private Integer id;

    @Length(max = 500)
    private String projectName;

    @Length(max = 500)
    private String projClientName;

    private String projectStage;

    private String functionalDirection;

    private String subjectArea;

    private String projectDescription;

    private String objectives;

    private String[] technologies;

    private String stakeholders;

    private String completionDate;

    private String peopleLaunchDate;

    private Boolean gost;

    private String cardStatus;

    private ProjectTypeDto projectType;

    private TeamDto team;

    private LocationDto location;

    private WorkingHoursPatternDto workingHoursPattern;

    private UserDto cardAuthor;
}
