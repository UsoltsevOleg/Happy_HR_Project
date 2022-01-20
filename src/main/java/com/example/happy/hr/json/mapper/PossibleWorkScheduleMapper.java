package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.PossibleWorkSchedule;
import com.example.happy.hr.json.dto.PossibleWorkScheduleDto;
import org.mapstruct.Mapper;

/*  Маппер для PossibleWorkScheduleDto и PossibleWorkSchedule   */

@Mapper(componentModel = "spring")
public interface PossibleWorkScheduleMapper {

    PossibleWorkScheduleDto toPossibleWorkScheduleDto(PossibleWorkSchedule possibleWorkSchedule);

    PossibleWorkSchedule toPossibleWorkSchedule(PossibleWorkScheduleDto dto);
}
