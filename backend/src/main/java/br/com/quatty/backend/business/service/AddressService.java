package br.com.quatty.backend.business.service;

import br.com.quatty.backend.application.dto.request.AddressRequest;
import br.com.quatty.backend.application.dto.response.AddressResponse;

public interface AddressService {

    AddressResponse save (AddressRequest addressRequest);

    AddressResponse update(Long id, AddressRequest addressRequest);

    AddressResponse delete(Long id);

    AddressResponse findAddressById(Long id);
}
