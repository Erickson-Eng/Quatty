package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.AddressMapper;
import br.com.quatty.backend.application.dto.request.AddressRequest;
import br.com.quatty.backend.application.dto.response.AddressResponse;
import br.com.quatty.backend.business.entity.Address;
import br.com.quatty.backend.business.service.AddressService;
import br.com.quatty.backend.business.service.exception.DataIntegrityViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.resource.repository.AddressRepository;
import br.com.quatty.backend.resource.utils.Log4JConstantService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;



@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressServicePostgresql implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServicePostgresql.class);
    private AddressRepository addressRepository;
    private AddressMapper addressMapper;
    @Transactional
    @Override
    public AddressResponse save(AddressRequest addressRequest) {
        LOGGER.info(Log4JConstantService.LOG4J_START_SAVE_ENTITY + "address");
        try{
            Address address = addressMapper.addressRequestToEntity(addressRequest);
            addressRepository.save(address);
            LOGGER.info(Log4JConstantService.LOG4J_FINISH_SAVE_ENTITY + "address");
            return addressMapper.entityToAddressResponse(address);
        } catch (RuntimeException e){
            LOGGER.info(Log4JConstantService.LOG4J_ERROR_SAVE_ENTITY);
            throw new DataIntegrityViolationException("Unable to save address");
        }
    }

    @Transactional
    @Override
    public AddressResponse update(Long id, AddressRequest addressRequest) {
        LOGGER.info(Log4JConstantService.LOG4J_START_UPDATE_ENTITY + " address - ID: {} ", id);
        Address obj = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "address");
        updateData(obj, addressRequest);
        addressRepository.save(obj);
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_UPDATE_ENTITY + "Address");
        return addressMapper.entityToAddressResponse(obj);
    }

    @Override
    public AddressResponse delete(Long id) {
        LOGGER.info(Log4JConstantService.LOG4J_START_DELETE_ENTITY+ "address - ID: {} ", id);
        Address obj = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "address");
        AddressResponse addressResponse = addressMapper.entityToAddressResponse(obj);
        addressRepository.delete(obj);
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_DELETE_ENTITY+ "Address");
        return addressResponse;
    }

    @Override
    public AddressResponse findAddressById(Long id) {
        Address obj  = verifyIfExist(id);
        LOGGER.info(Log4JConstantService.LOG4J_END_SEARCH+ "address");
        return addressMapper.entityToAddressResponse(obj);

    }


    protected Address verifyIfExist(Long id){
        LOGGER.info(Log4JConstantService.LOG4J_START_OF_SEARCH + "address - ID: {} ", id);
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("address not registered"));
    }

    protected void updateData(Address address, AddressRequest addressRequest){
        address.setStreet(addressRequest.getStreet());
        address.setComplement(addressRequest.getComplement());
        address.setCity(addressRequest.getCity());
        address.setUf(addressRequest.getUf());
        address.setZipCode(addressRequest.getZipCode());
    }

}
