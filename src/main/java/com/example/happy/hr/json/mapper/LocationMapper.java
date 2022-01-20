package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.Location;
import com.example.happy.hr.json.dto.LocationDto;
import org.mapstruct.Mapper;

/*  Маппер для LocationDto и Location   */

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto toLocationDto(Location location);

    Location toLocation(LocationDto dto);
}
