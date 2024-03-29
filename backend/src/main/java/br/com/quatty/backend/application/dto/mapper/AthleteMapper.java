package br.com.quatty.backend.application.dto.mapper;

import br.com.quatty.backend.application.dto.request.AthleteRequest;
import br.com.quatty.backend.application.dto.response.AthleteResponse;
import br.com.quatty.backend.business.entity.Athlete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface AthleteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address.id", source = "addressId")
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "memberships", ignore = true)
    Athlete athleteRequestToEntity(AthleteRequest athleteRequest);

    @Mapping(target = "addressResponse", source = "address")
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd/MM/yyyy")
    AthleteResponse entityToAthleteResponse(Athlete athlete);

}
