package br.com.quatty.backend.application.dto.mapper;

import br.com.quatty.backend.application.dto.request.SportRequest;
import br.com.quatty.backend.application.dto.response.SportResponse;
import br.com.quatty.backend.business.entity.Sport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SportMapper {

    Sport sportRequestToEntity(SportRequest sportRequest);


    SportResponse entityToSportResponse(Sport sport);
}
