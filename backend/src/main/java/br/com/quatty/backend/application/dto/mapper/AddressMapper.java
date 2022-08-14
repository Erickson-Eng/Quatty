package br.com.quatty.backend.application.dto.mapper;

import br.com.quatty.backend.application.dto.request.AddressRequest;
import br.com.quatty.backend.application.dto.response.AddressResponse;
import br.com.quatty.backend.business.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {


    @Mapping(target = "id", ignore = true)
    Address addressRequestToEntity(AddressRequest addressRequest);

    AddressResponse entityToAddressResponse(Address address);
}
