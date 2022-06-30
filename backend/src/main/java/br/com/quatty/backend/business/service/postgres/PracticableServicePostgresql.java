package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.PracticableMapper;
import br.com.quatty.backend.application.dto.request.PracticableRequest;
import br.com.quatty.backend.application.dto.response.PracticableResponse;
import br.com.quatty.backend.business.entity.Practicable;
import br.com.quatty.backend.business.entity.Sport;
import br.com.quatty.backend.business.service.PracticableService;
import br.com.quatty.backend.business.service.exception.DataIntegrityViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.resource.repository.PracticableRepository;
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
public class PracticableServicePostgresql implements PracticableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PracticableServicePostgresql.class);
    private PracticableRepository practicableRepository;
    private PracticableMapper practicableMapper;


    @Transactional
    @Override
    public PracticableResponse save(PracticableRequest request) {
        Practicable practicable = practicableMapper.practicableRequestToEntity(request);
        try {
            practicableRepository.save(practicable);
        }catch (RuntimeException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return practicableMapper.entityToPracticableResponse(practicable);
    }

    @Transactional
    @Override
    public void saveMultiplePracticeSports(List<PracticableRequest> requestList) {
        List<Practicable> practicableList = practicableResponseToEntityList(requestList);
        try {
            practicableRepository.saveAll(practicableList);
        }catch (RuntimeException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public PracticableResponse update(Long id, PracticableRequest request) {
        Practicable practicable = verifyIfExist(id);
        updateData(practicable, request);
        practicableRepository.save(practicable);
        return practicableMapper.entityToPracticableResponse(practicable);
    }


    @Transactional
    @Override
    public PracticableResponse delete(Long id) {
        Practicable practicable = verifyIfExist(id);
        practicableRepository.delete(practicable);
        return practicableMapper.entityToPracticableResponse(practicable);
    }

    @Override
    public List<PracticableResponse> listAllPracticableSportsById(Long sportId) {
        List<Practicable> practicableSports = verifyIfExistBySport(sportId);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "practicable sports - sport id: {}", sportId);
        return entityListToResponse(practicableSports);
    }

    @Override
    public List<PracticableResponse> listAllPracticableSportsByName(String sportName) {
        List<Practicable> practicableSports = verifyIfExistBySport(sportName);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "practicable sports - sport name: {}", sportName);
        return entityListToResponse(practicableSports);
    }


    @Override
    public List<PracticableResponse> listSportsPracticableInTheGym(Long gymId) {
        List<Practicable> practicableSports = verifyIfExistByGymId(gymId);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "practicable sports - gym id: {}", gymId);
        return entityListToResponse(practicableSports);
    }


    private void updateData(Practicable practicable, PracticableRequest request) {
        practicable.setDescription(request.getDescription());
        practicable.setStatus(request.getStatus());
        if (!practicable.getSport().getId().equals(request.getSportId())){
            Sport newSport = new Sport();
            newSport.setId(request.getSportId());
            practicable.setSport(newSport);
        }
    }
    private Practicable verifyIfExist(Long id){
        return practicableRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(MessageFormat.format(
                                "There is no practicable with this id {} informed.", String.valueOf(id))));
    }

    private List<Practicable> verifyIfExistBySport(Long id){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "practicable sports - sport id: {} ", id);
        return practicableRepository.findAllBySportId(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(MessageFormat.format(
                                "There is no gym that supports the sport {} at the moment.", String.valueOf(id))));
    }

    private List<Practicable> verifyIfExistBySport(String name){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "practicable sports - sport name: {} ", name);
        return practicableRepository.findAllBySportName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException(MessageFormat.format(
                                "There is no gym that supports the sport {} at the moment.", name)));
    }

    private List<Practicable> verifyIfExistByGymId(Long id){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "practicable sports - gym id: {} ", id);
        return practicableRepository.findAllByGymId(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(MessageFormat.format(
                                "There is no gym that supports the sport {} at the moment.", String.valueOf(id))));
    }

    private List<PracticableResponse> entityListToResponse(List<Practicable> practicable){
        return practicable.stream()
                .map(practicableMapper::entityToPracticableResponse)
                .collect(Collectors.toList());
    }


    private List<Practicable> practicableResponseToEntityList(List<PracticableRequest> practicableRequests){
        return practicableRequests
                .stream()
                .map(practicableMapper::practicableRequestToEntity)
                .collect(Collectors.toList());
    }
}
