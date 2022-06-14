package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.AddressRequest;
import br.com.quatty.backend.application.dto.response.AddressResponse;
import br.com.quatty.backend.business.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/address")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController {

    private AddressService addressService;

    @PostMapping
    public AddressResponse createAddress(@RequestBody @Valid AddressRequest addressRequest){
        return addressService.save(addressRequest);
    }

    @PutMapping("/{id}")
    public AddressResponse updateAddress(@PathVariable Long id,
                                         @RequestBody @Valid AddressRequest addressRequest){
        return addressService.update(id, addressRequest);
    }

    @DeleteMapping("/{id}")
    public AddressResponse deleteAddress(@PathVariable Long id){
        return addressService.delete(id);
    }

    @GetMapping("/{id}")
    public AddressResponse findAddressById(@PathVariable Long id){
        return addressService.findAddressById(id);
    }
}
