package com.example.happy.hr.services;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.auxiliary.ProjectCardInfo;
import com.example.happy.hr.json.dto.auxiliary.SortInfo;

import java.util.List;
import java.util.Map;

/*  Сервис для ProjectCard  */

public interface ProjectCardService {

    List<ProjectCardInfo> getProjectCardPage(ProjectRegistryFilter filter, PageInfo info, SortInfo sortInfo);

    ProjectCardDto getProjectCardById(Integer projectCardId);

    Integer save(ProjectCardDto cardDto);

    ProjectCardInfo archiveById(Integer id);

    ProjectCardInfo unarchiveById(Integer id);

    Map<String, Long> count();

    Map<String, Long> count(ProjectRegistryFilter filter);

    void deleteById(Integer id);
}
