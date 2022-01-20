package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.ProjectModel;
import com.example.happy.hr.json.dto.ProjectModelDto;
import org.mapstruct.Mapper;

/*  Маппер для ProjectModelDto и ProjectModel   */

@Mapper(componentModel = "spring")
public interface ProjectModelMapper {

    ProjectModelDto toProjectModelDto(ProjectModel projectModel);

    ProjectModel toProjectModel(ProjectModelDto dto);
}
