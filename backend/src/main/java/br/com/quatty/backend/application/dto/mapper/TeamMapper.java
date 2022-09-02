package br.com.quatty.backend.application.dto.mapper;

import br.com.quatty.backend.application.dto.request.TeamRequest;
import br.com.quatty.backend.application.dto.response.TeamResponse;
import br.com.quatty.backend.business.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamCreationDate", source = "teamCreationDate", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "memberships", ignore = true)
    Team teamRequestToEntity(TeamRequest teamRequest);

    
    TeamResponse entityToTeamResponse(Team team);
}
