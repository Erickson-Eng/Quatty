package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.SportMapper;
import br.com.quatty.backend.application.dto.request.SportRequest;
import br.com.quatty.backend.application.dto.response.SportResponse;
import br.com.quatty.backend.business.entity.Sport;
import br.com.quatty.backend.business.service.SportService;
import br.com.quatty.backend.business.service.exception.DataIntegrityViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.resource.repository.SportRepository;
import br.com.quatty.backend.resource.utils.Log4JConstant;
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
public class SportServicePostgres implements SportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServicePostgres.class);
    private SportMapper sportMapper;
    private SportRepository sportRepository;

    @Transactional
    @Override
    public SportResponse save(SportRequest sportRequest) {
        LOGGER.info(Log4JConstant.LOG4J_START_SAVE_ENTITY + "sport");
        Sport entity = sportMapper.sportRequestToEntity(sportRequest);
        try {
            sportRepository.save(entity);
        } catch (DataIntegrityViolationException e){
            LOGGER.info(Log4JConstant.LOG4J_ERROR_SAVE_ENTITY);
            e.printStackTrace();
        }
        LOGGER.info(Log4JConstant.LOG4J_FINISH_SAVE_ENTITY + "sport");
        return sportMapper.entityToSportResponse(entity);
    }

    @Transactional
    @Override
    public SportResponse update(Long id, SportRequest sportRequest) {
        LOGGER.info(Log4JConstant.LOG4J_START_UPDATE_ENTITY + " sport - ID: {} ", id);
        Sport sport = verifyIfExist(id);
        LOGGER.info(Log4JConstant.LOG4J_END_SEARCH+ "sport - ID: {}", id);
        updateData(sport, sportRequest);
        sportRepository.save(sport);
        LOGGER.info(Log4JConstant.LOG4J_FINISH_UPDATE_ENTITY + "sport - ID: {}", id);
        return sportMapper.entityToSportResponse(sport);
    }

    @Override
    public SportResponse delete(Long id) {
        LOGGER.info(Log4JConstant.LOG4J_START_DELETE_ENTITY+ "address - ID: {} ", id);
        Sport entity = verifyIfExist(id);
        LOGGER.info(Log4JConstant.LOG4J_END_SEARCH+ "sport - ID: {}", id);
        sportRepository.delete(entity);
        LOGGER.info(Log4JConstant.LOG4J_FINISH_DELETE_ENTITY+ "Address");
        return sportMapper.entityToSportResponse(entity);
    }

    @Override
    public List<SportResponse> findSportByName(String name) {
        List<Sport> entityList = verifyIfExist(name);
        LOGGER.info(Log4JConstant.LOG4J_END_SEARCH+ "address");
        return sportListToResponseList(entityList);
    }

    @Override
    public SportResponse findSportById(Long id) {
        Sport entity = verifyIfExist(id);
        LOGGER.info(Log4JConstant.LOG4J_END_SEARCH+ "address");
        return sportMapper.entityToSportResponse(entity);
    }
    @Transactional(readOnly = true)
    @Override
    public List<SportResponse> listAllSportResponse() {
        List<Sport> ListEntity = verifyIfExist();
        LOGGER.info(Log4JConstant.LOG4J_END_SEARCH+ "address");
        return sportListToResponseList(ListEntity);
    }

    private Sport verifyIfExist(Long id){
        LOGGER.info(Log4JConstant.LOG4J_START_OF_SEARCH + "address - ID: {} ", id);
        return sportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find any sport for the given id: %s.", String.valueOf(id))));
    }

    private List<Sport> verifyIfExist(String name){
        LOGGER.info(Log4JConstant.LOG4J_START_OF_SEARCH + "address - ID: {} ", name);
        return sportRepository.findAllByName(name)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("No results were found for your search for the given name: {} .", name)));
    }

    private List<Sport> verifyIfExist(){
        return sportRepository.findAll();
    }

    private List<SportResponse> sportListToResponseList(List<Sport> sportList){
        return sportList.stream()
                .map(sportMapper::entityToSportResponse)
                .collect(Collectors.toList());
    }

    private void updateData(Sport entity, SportRequest request){
        entity.setName(request.getName());
    }
}
