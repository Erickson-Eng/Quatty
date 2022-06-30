package br.com.quatty.backend.application.dto.mapper;

import br.com.quatty.backend.application.dto.request.PracticableRequest;
import br.com.quatty.backend.application.dto.response.PracticableResponse;
import br.com.quatty.backend.business.entity.Practicable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GymMapper.class, SportMapper.class})
public interface PracticableMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sport.id", source = "sportId")
    @Mapping(target = "gym.id", source = "gymId")
    Practicable practicableRequestToEntity(PracticableRequest practicableRequest);


    @Mapping(target = "sportResponse", source = "sport")
    @Mapping(target = "gymResponse", source = "gym")
    PracticableResponse entityToPracticableResponse(Practicable practicable);
}
