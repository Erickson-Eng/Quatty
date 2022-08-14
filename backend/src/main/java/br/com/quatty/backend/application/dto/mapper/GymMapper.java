package br.com.quatty.backend.application.dto.mapper;

import br.com.quatty.backend.application.dto.request.GymRequest;
import br.com.quatty.backend.application.dto.response.GymResponse;
import br.com.quatty.backend.business.entity.Gym;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SchoolMapper.class})
public interface GymMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "school.id", source = "schoolId")
    @Mapping(target = "practicableSports", ignore = true)
    Gym gymRequestToEntity(GymRequest gymRequest);

    @Mapping(target = "schoolResponse", source = "school")
    @Mapping(target = "practicableResponses", source = "practicableSports")
    GymResponse entityToGymResponse(Gym gym);
}
