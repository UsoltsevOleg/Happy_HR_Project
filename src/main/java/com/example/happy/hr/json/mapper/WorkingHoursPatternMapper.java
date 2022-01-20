package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.WorkingHoursPattern;
import com.example.happy.hr.json.dto.WorkingHoursPatternDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/*  Маппер для WorkingHoursPatternDto и WorkingHoursPattern */

@Mapper(componentModel = "spring")
public interface WorkingHoursPatternMapper {

    @Mapping(target = "whpFrom", expression = "java(pattern.getWhpFrom() == null ? null : pattern.getWhpFrom().toString())")
    @Mapping(target = "whpTill", expression = "java(pattern.getWhpTill() == null ? null : pattern.getWhpTill().toString())")
    WorkingHoursPatternDto toWorkingHoursPatternDto(WorkingHoursPattern pattern);

    @Mapping(target = "whpFrom", expression = "java(dto.getWhpFrom() == null ? null : java.sql.Time.valueOf(dto.getWhpFrom()))")
    @Mapping(target = "whpTill", expression = "java(dto.getWhpTill() == null ? null : java.sql.Time.valueOf(dto.getWhpTill()))")
    WorkingHoursPattern toWorkingHoursPattern(WorkingHoursPatternDto dto);

}
