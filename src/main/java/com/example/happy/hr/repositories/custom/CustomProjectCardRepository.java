package com.example.happy.hr.repositories.custom;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.domain.wrappers.ProjectCardWrapper;
import com.example.happy.hr.json.dto.auxiliary.SortInfo;

import java.util.List;

/*  Репозиторий для динамического формирования запросов в реестр    */

public interface CustomProjectCardRepository {
    List<ProjectCardWrapper> getProjectCardPage(ProjectRegistryFilter filter, PageInfo pageInfo, SortInfo sortInfo);
    ProjectCardWrapper getRegistryRecordById(Integer id);
    Long count(ProjectRegistryFilter filter);
}
