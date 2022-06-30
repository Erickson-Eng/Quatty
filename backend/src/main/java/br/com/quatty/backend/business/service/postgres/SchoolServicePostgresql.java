package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.SchoolMapper;
import br.com.quatty.backend.application.dto.request.SchoolRequest;
import br.com.quatty.backend.application.dto.response.SchoolResponse;
import br.com.quatty.backend.business.entity.School;
import br.com.quatty.backend.business.service.SchoolService;
import br.com.quatty.backend.business.service.exception.DataIntegrityViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.resource.repository.SchoolRepository;
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
public class SchoolServicePostgresql implements SchoolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolServicePostgresql.class);
    private SchoolRepository schoolRepository;
    private SchoolMapper schoolMapper;

    @Transactional
    @Override
    public SchoolResponse save(SchoolRequest schoolRequest) {
        LOGGER.info(Log4JConstantService.LOG4J_START_SAVE_ENTITY + "school");
        School school = schoolMapper.schoolRequestToEntity(schoolRequest);
        try {
            schoolRepository.save(school);
        } catch (RuntimeException e){
            LOGGER.info(Log4JConstantService.LOG4J_ERROR_SAVE_ENTITY);
            throw new DataIntegrityViolationException(e.getMessage());
        }
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_SAVE_ENTITY + "school");
        return schoolMapper.entityToSchoolResponse(school);
    }

    @Transactional
    @Override
    public SchoolResponse update(Long id, SchoolRequest schoolRequest) {
        LOGGER.info(Log4JConstantService.LOG4J_START_UPDATE_ENTITY + " school - ID: {} ", id);
        School school = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "school - ID: {}", id);
        updateData(school, schoolRequest);
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_UPDATE_ENTITY + "school - ID: {}", id);
        return schoolMapper.entityToSchoolResponse(school);
    }


    @Transactional
    @Override
    public SchoolResponse delete(Long id) {
        LOGGER.info(Log4JConstantService.LOG4J_START_DELETE_ENTITY+ "school - ID: {} ", id);
        School school = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "school - ID: {}", id);
        schoolRepository.delete(school);
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_DELETE_ENTITY+ "school");
        return schoolMapper.entityToSchoolResponse(school);
    }

    @Override
    public SchoolResponse findById(Long id) {
        School school = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "school - ID: {}", id);
        return schoolMapper.entityToSchoolResponse(school);
    }

    @Override
    public List<SchoolResponse> findAllByName(String name) {
        List<School> entityList = verifyIfExist(name);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "school name: {}", name);
        return entityListToSchoolResponseList(entityList);
    }

    @Override
    public List<SchoolResponse> findAllSchoolForCity(String cityName) {
        List<School> entityList = verifyIfExistSchoolForCity(cityName);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "schools for city: {}", cityName);
        return entityListToSchoolResponseList(entityList);
    }


    private School verifyIfExist(Long id){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "school - ID: {} ", id);
        return schoolRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Could not find any school for the given id: %s.",String.valueOf(id)))
                );
    }

    private List<School> verifyIfExist(String name){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "school - NAME: {} ", name);
        return schoolRepository.findAllByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException(MessageFormat.format(
                                "No results were found for your search by school name: {} .", name)));
    }

    private List<School> verifyIfExistSchoolForCity(String cityName){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "school - city name: {} ", cityName);
        return schoolRepository.findAllByAddress_City(cityName)
                .orElseThrow(() ->
                        new EntityNotFoundException(MessageFormat.format(
                                "There are no school registered for the given city. City name: {} .", cityName)));
    }

    private List<SchoolResponse> entityListToSchoolResponseList(List<School> schoolList){
        return schoolList.stream()
                .map(schoolMapper::entityToSchoolResponse)
                .collect(Collectors.toList());
    }

    private void updateData(School school, SchoolRequest schoolRequest) {
        school.setName(schoolRequest.getName());
        school.setBiography(schoolRequest.getBiography());
        school.setTelephone(schoolRequest.getTelephone());
    }
}
