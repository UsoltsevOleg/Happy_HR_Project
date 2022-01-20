package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.ProjectType;
import com.example.happy.hr.json.dto.ProjectTypeDto;
import org.mapstruct.Mapper;

/*  Маппер для ProjectTypeDto и ProjectType */

@Mapper(componentModel = "spring")
public interface ProjectTypeMapper {

    ProjectTypeDto toProjectTypeDto(ProjectType projectType);

    ProjectType toProjectType(ProjectTypeDto dto);
}
