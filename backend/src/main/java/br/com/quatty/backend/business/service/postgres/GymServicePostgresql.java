package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.GymMapper;
import br.com.quatty.backend.application.dto.request.GymRequest;
import br.com.quatty.backend.application.dto.response.GymResponse;
import br.com.quatty.backend.application.dto.response.table.GymTableResponse;
import br.com.quatty.backend.business.entity.Gym;
import br.com.quatty.backend.business.service.GymService;
import br.com.quatty.backend.business.service.exception.DataIntegrityViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.resource.repository.GymRepository;
import br.com.quatty.backend.resource.utils.Log4JConstantService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GymServicePostgresql implements GymService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServicePostgresql.class);
    private GymMapper gymMapper;
    private GymRepository gymRepository;

    @Transactional
    @Override
    public GymResponse save(GymRequest gymRequest) {
        LOGGER.info(Log4JConstantService.LOG4J_START_SAVE_ENTITY + "gym");
        Gym gym = gymMapper.gymRequestToEntity(gymRequest);
        try {
            gymRepository.save(gym);
        } catch (RuntimeException e){
            LOGGER.info(Log4JConstantService.LOG4J_ERROR_SAVE_ENTITY);
            throw new DataIntegrityViolationException(e.getMessage());
        }
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_SAVE_ENTITY + "gym");
        return gymMapper.entityToGymResponse(gym);
    }

    @Transactional
    @Override
    public GymResponse update(Long id, GymRequest gymRequest) {
        LOGGER.info(Log4JConstantService.LOG4J_START_UPDATE_ENTITY + " gym - ID: {} ", id);
        Gym gym = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "gym - ID: {}", id);
        updateData(gym, gymRequest);
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_UPDATE_ENTITY + "gym - ID: {}", id);
        return gymMapper.entityToGymResponse(gym);
    }

    @Transactional
    @Override
    public GymResponse delete(Long id) {
        LOGGER.info(Log4JConstantService.LOG4J_START_DELETE_ENTITY+ "gym - ID: {} ", id);
        Gym gym = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "gym - ID: {}", id);
        gymRepository.delete(gym);
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_DELETE_ENTITY+ "gym");
        return gymMapper.entityToGymResponse(gym);
    }

    @Override
    public GymTableResponse findGymByCity(String city) {
        List<Gym> gyms = searchGymForCity(city);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "gym - CITY NAME: {}", city);
        return GymTableResponse.builder()
                .gymResponseList(entityListToResponseList(gyms))
                .build();
    }

    @Override
    public GymTableResponse findGymByName(String name) {
        List<Gym> gyms = verifyIfExist(name);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "gym NAME: {}", name);
        return GymTableResponse.builder()
                .gymResponseList(entityListToResponseList(gyms))
                .build();
    }

    @Override
    public GymResponse findById(Long id) {
        Gym gym = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "gym - ID: {}", id);
        return gymMapper.entityToGymResponse(gym);
    }


    private Gym verifyIfExist(Long id){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "gym - ID: {} ", id);
        return gymRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Could not find any gym for the given id: %s.",String.valueOf(id)))
                );
    }

    private List<Gym> verifyIfExist(String name){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "gym - NAME: {} ", name);
        return gymRepository.findAllByNameContainingIgnoreCase(name)
                .orElseThrow(() ->
                        new EntityNotFoundException(MessageFormat.format(
                                "No results were found for your search by school name: {} .", name)));
    }

    private List<Gym> searchGymForCity(String city){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "gym -  CITY NAME: {} ", city);
        return gymRepository.findAllBySchool_Address_City(city)
                .orElseThrow(() ->
                        new EntityNotFoundException(MessageFormat.format(
                                "There are no gyms registered for the given city. City name: {} .", city)));
    }

    private List<GymResponse> entityListToResponseList(List<Gym> gyms){
        return gyms.stream()
                .map(gymMapper::entityToGymResponse)
                .collect(Collectors.toList());
    }

    private void updateData(Gym gym, GymRequest gymRequest) {
        gym.setInformation(gymRequest.getInformation());
        gym.setName(gymRequest.getName());
    }

}
