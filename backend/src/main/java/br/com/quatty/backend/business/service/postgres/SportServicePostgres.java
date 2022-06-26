package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.SportMapper;
import br.com.quatty.backend.application.dto.request.SportRequest;
import br.com.quatty.backend.application.dto.response.SportResponse;
import br.com.quatty.backend.business.entity.Sport;
import br.com.quatty.backend.business.service.SportService;
import br.com.quatty.backend.business.service.exception.DataIntegrityViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.resource.repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SportServicePostgres implements SportService {

    private SportMapper sportMapper;
    private SportRepository sportRepository;

    @Transactional
    @Override
    public SportResponse save(SportRequest sportRequest) {
        Sport entity = sportMapper.sportRequestToEntity(sportRequest);
        try {
            sportRepository.save(entity);
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
        }
        return sportMapper.entityToSportResponse(entity);
    }

    @Transactional
    @Override
    public SportResponse update(Long id, SportRequest sportRequest) {
        Sport sport = verifyIfExist(id);
        updateData(sport, sportRequest);
        sportRepository.save(sport);
        return sportMapper.entityToSportResponse(sport);
    }

    @Override
    public SportResponse delete(Long id) {
        Sport entity = verifyIfExist(id);
        sportRepository.delete(entity);
        return sportMapper.entityToSportResponse(entity);
    }

    @Override
    public List<SportResponse> findSportByName(String name) {
        List<Sport> entityList = verifyIfExist(name);
        return sportListToResponseList(entityList);
    }

    @Override
    public SportResponse findSportById(Long id) {
        Sport entity = verifyIfExist(id);
        return sportMapper.entityToSportResponse(entity);
    }
    @Transactional(readOnly = true)
    @Override
    public List<SportResponse> ListAllSportResponse() {
        List<Sport> ListEntity = verifyIfExist();
        return sportListToResponseList(ListEntity);
    }

    private Sport verifyIfExist(Long id){
        return sportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Could not find any sport for the given id: {} .", id)));
    }

    private List<Sport> verifyIfExist(String name){
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
