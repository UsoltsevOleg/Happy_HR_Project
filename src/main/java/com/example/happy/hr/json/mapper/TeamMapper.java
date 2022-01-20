package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.Team;
import com.example.happy.hr.json.dto.TeamDto;
import org.mapstruct.Mapper;

/*  маппер для TeamDto и Team   */

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDto toTeamDto(Team team);

    Team toTeam(TeamDto dto);
}
