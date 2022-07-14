package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.AthleteMapper;
import br.com.quatty.backend.application.dto.request.AthleteRequest;
import br.com.quatty.backend.application.dto.response.AthleteResponse;
import br.com.quatty.backend.application.dto.response.table.AthleteTableResponse;
import br.com.quatty.backend.business.entity.Athlete;
import br.com.quatty.backend.business.service.AthleteService;
import br.com.quatty.backend.business.service.exception.DataIntegrityViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.resource.repository.AthleteRepository;
import br.com.quatty.backend.resource.utils.DataUtils;
import br.com.quatty.backend.resource.utils.Log4JConstantService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AthleteServicePostgresql implements AthleteService {

    private AthleteMapper athleteMapper;
    private AthleteRepository athleteRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AthleteServicePostgresql.class);

    @Transactional
    @Override
    public AthleteResponse save(AthleteRequest athleteRequest) {
        LOGGER.info(Log4JConstantService.LOG4J_START_SAVE_ENTITY + "athlete");
        Athlete athlete = athleteMapper.athleteRequestToEntity(athleteRequest);
        try {
            athleteRepository.save(athlete);
            LOGGER.info(Log4JConstantService.LOG4J_FINISH_SAVE_ENTITY + "athlete");
        }catch (RuntimeException e){
            LOGGER.info(Log4JConstantService.LOG4J_ERROR_SAVE_ENTITY + "athlete");
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return athleteMapper.entityToAthleteResponse(athlete);
    }

    @Transactional
    @Override
    public AthleteResponse update(Long id, AthleteRequest athleteRequest) {
        Athlete athlete = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "athlete - ID: {}", id);
        updateDate(athlete, athleteRequest);
        athleteRepository.save(athlete);
        return athleteMapper.entityToAthleteResponse(athlete);
    }

    @Override
    public AthleteTableResponse listAthleteForName(String name) {
        List<Athlete> athletes = findAthleteByName(name);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "athlete NAME: {}", name);
        return new AthleteTableResponse(entityListToResponseList(athletes));
    }

    private List<Athlete> findAthleteByName(String name){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "athlete - ATHLETE NAME: {} ", name);
        return athleteRepository.findAthleteByFirstNameContainingIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "There are no athlete registered for the given name. Name: {}", name)));
    }

    private Athlete verifyIfExist(Long id){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "athlete - ATHLETE ID: {} ", id);
        return athleteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("athlete not registered"));
    }

    private List<AthleteResponse> entityListToResponseList(List<Athlete> athletes){
        return athletes.stream().map(athleteMapper::entityToAthleteResponse).collect(Collectors.toList());
    }

    private void updateDate(Athlete athlete, AthleteRequest athleteRequest) {
        athlete.setFirstName(athleteRequest.getFirstName());
        athlete.setSocialName(athleteRequest.getSocialName());
        athlete.setLastName(athleteRequest.getLastName());
        if (athleteRequest.getBirthDate() != null){
            athlete.setBirthDate(LocalDate.parse(athleteRequest.getBirthDate(),
                    DateTimeFormatter.ofPattern(DataUtils.pattern)));
        }
        athlete.setWeight(athleteRequest.getWeight());
        athlete.setHeight(athleteRequest.getHeight());
        athlete.setBicepsMeasurement(athleteRequest.getBicepsMeasurement());
        athlete.setCalfMeasurement(athleteRequest.getCalfMeasurement());
        athlete.setChestMeasurement(athleteRequest.getChestMeasurement());
        athlete.setForearmMeasurement(athlete.getForearmMeasurement());
        athlete.setThighMeasurement(athleteRequest.getThighMeasurement());
    }
}
