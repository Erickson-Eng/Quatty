package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.TeamMapper;
import br.com.quatty.backend.application.dto.request.TeamRequest;
import br.com.quatty.backend.application.dto.response.TeamResponse;
import br.com.quatty.backend.application.dto.response.table.TeamTableResponse;
import br.com.quatty.backend.business.entity.Team;
import br.com.quatty.backend.business.service.TeamService;
import br.com.quatty.backend.business.service.exception.DataIntegrityViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.resource.repository.TeamRepository;
import br.com.quatty.backend.resource.utils.DataUtils;
import br.com.quatty.backend.resource.utils.Log4JConstantService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TeamServicePostgresql implements TeamService {

    private TeamRepository teamRepository;
    private TeamMapper teamMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServicePostgresql.class);

    @Override
    public TeamResponse save(TeamRequest teamRequest) {
        LOGGER.info(Log4JConstantService.LOG4J_START_SAVE_ENTITY + "TEAM");
        Team team = teamMapper.teamRequestToEntity(teamRequest);
        try {
            teamRepository.save(team);
        }catch (RuntimeException e){
            LOGGER.info(Log4JConstantService.LOG4J_ERROR_SAVE_ENTITY);
            throw new DataIntegrityViolationException(e.getMessage());
        }
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_SAVE_ENTITY + "TEAM");
        return teamMapper.entityToTeamResponse(team);
    }

    @Override
    public TeamResponse update(Long id, TeamRequest teamRequest) {
        Team team = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "TEAM");
        updateData(team, teamRequest);
        teamRepository.save(team);
        return teamMapper.entityToTeamResponse(team);
    }

    @Override
    public TeamResponse delete(Long id) {
        Team team = verifyIfExist(id);
        teamRepository.delete(team);
        return teamMapper.entityToTeamResponse(team);
    }

    @Override
    public TeamTableResponse findTeamByName(String name) {
        List<Team> teamList = verifyIfExist(name);
        return entityListToTeamTableResponse(teamList);
    }

    private void updateData(Team team, TeamRequest teamRequest) {
        team.setName(teamRequest.getName());
        team.setTeamCreationDate(LocalDate.parse(teamRequest.getTeamCreationDate(), DateTimeFormatter.ofPattern(DataUtils.pattern)));
    }

    private Team verifyIfExist(Long id) {
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "TEAM - ID: {} ", id);
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("No results were found for your search for the given id: {} .", id)));
    }

    private List<Team> verifyIfExist(String name){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "TEAM - NAME: {} ", name);
        return teamRepository.findAllByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("No results were found for your search for the given name: {} .", name)));
    }

    private TeamTableResponse entityListToTeamTableResponse(List<Team> teamList){
        List<TeamResponse> teamResponseList = teamList.stream()
                .map(teamMapper::entityToTeamResponse)
                .collect(Collectors.toList());
        return new TeamTableResponse(teamResponseList);
    }
}
