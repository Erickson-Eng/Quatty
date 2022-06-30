package br.com.quatty.backend.application.dto.mapper;

import br.com.quatty.backend.application.dto.request.SchoolRequest;
import br.com.quatty.backend.application.dto.response.SchoolResponse;
import br.com.quatty.backend.business.entity.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface SchoolMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address.id", source = "addressId")
    School schoolRequestToEntity(SchoolRequest schoolRequest);


    @Mapping(target = "addressResponse", source = "address")
    SchoolResponse entityToSchoolResponse(School school);
}
