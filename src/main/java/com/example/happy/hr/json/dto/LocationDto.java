package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/*  DTO для Location    */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDto {

    private Integer id;

    @Length(max = 1024)
    private String address;

    private Boolean office;

    private Boolean outsource;
}
