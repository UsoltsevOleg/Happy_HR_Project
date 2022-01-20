package com.example.happy.hr.services.impl;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.domain.wrappers.ProjectCardWrapper;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.auxiliary.ProjectCardInfo;
import com.example.happy.hr.json.dto.auxiliary.SortInfo;
import com.example.happy.hr.json.mapper.ProjectCardMapper;
import com.example.happy.hr.repositories.ProjectCardRepository;
import com.example.happy.hr.services.ProjectCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*  Реализация ProjectCardService   */

@Service
@AllArgsConstructor
@Slf4j
public class ProjectCardServiceImpl implements ProjectCardService {

    private ProjectCardRepository projectCardRepository;
    private ProjectCardMapper projectCardMapper;
    public static final String URL_PREFIX = "http://localhost:8080/api/cards";

    @Override
    public List<ProjectCardInfo> getProjectCardPage(ProjectRegistryFilter filter, PageInfo info, SortInfo sortInfo) {

        log.info("Getting project registry page " + info.getPageNum());

        return projectCardRepository
                .getProjectCardPage(filter, info, sortInfo)
                .stream()
                .map(wrapper -> new ProjectCardInfo(
                        wrapper,
                        URL_PREFIX + "/" + wrapper.getId(),
                        URL_PREFIX + "/archive/" + wrapper.getId(),
                        URL_PREFIX + "/archive/" + wrapper.getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectCardDto getProjectCardById(Integer projectCardId) {
        log.info("Getting card " + projectCardId);

        ProjectCardDto projectCardDto = projectCardMapper
                .toProjectCardDto(
                        projectCardRepository.getById(projectCardId)
                );
        if (projectCardDto == null) {
            throw new RuntimeException("Project card not found");
        }
        return projectCardDto;
    }

    @Override
    public Integer save(ProjectCardDto cardDto) {
        log.info("Saving card: {}", cardDto);
        ProjectCard projectCard = projectCardMapper.toProjectCard(cardDto);
        projectCardRepository.save(projectCard);
        return projectCard.getId();
    }

    @Override
    @Transactional
    public ProjectCardInfo archiveById(Integer id) {
        log.info("Archiving card " + id);
        projectCardRepository.archiveById(id);
        ProjectCardWrapper wrapper = projectCardRepository.getRegistryRecordById(id);
        return new ProjectCardInfo(
                wrapper,
                URL_PREFIX + "/" + wrapper.getId(),
                URL_PREFIX + "/archive/" + wrapper.getId(),
                URL_PREFIX + "/archive/" + wrapper.getId()
        );
    }

    @Override
    @Transactional
    public ProjectCardInfo unarchiveById(Integer id) {
        log.info("Unarchiving card " + id);
        projectCardRepository.unarchiveById(id);
        ProjectCardWrapper wrapper = projectCardRepository.getRegistryRecordById(id);
        return new ProjectCardInfo(
                wrapper,
                URL_PREFIX + "/" + wrapper.getId(),
                URL_PREFIX + "/archive/" + wrapper.getId(),
                URL_PREFIX + "/archive/" + wrapper.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting card " + id);
        projectCardRepository.deleteById(id);
    }

    @Override
    public Map<String, Long> count() {
        log.info("Counting all cards");
        Map<String, Long> wrapper = new HashMap<>();
        wrapper.put("projectsNum", projectCardRepository.count());
        return wrapper;
    }

    @Override
    public Map<String, Long> count(ProjectRegistryFilter filter) {
        log.info("Counting cards with filter applied");
        Map<String, Long> wrapper = new HashMap<>();
        wrapper.put("projectsNum", projectCardRepository.count(filter));
        return wrapper;
    }
}
