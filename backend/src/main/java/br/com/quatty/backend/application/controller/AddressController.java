package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.AddressRequest;
import br.com.quatty.backend.application.dto.response.AddressResponse;
import br.com.quatty.backend.business.service.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/address")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController {

    private AddressService addressService;
    @ApiOperation(value = "Create a new address in the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created", response = AddressResponse.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse createAddress(@RequestBody @Valid AddressRequest addressRequest){
        return addressService.save(addressRequest);
    }
    @ApiOperation(value = "Update a address in the database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = AddressResponse.class)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse updateAddress(@PathVariable Long id,
                                         @RequestBody @Valid AddressRequest addressRequest){
        return addressService.update(id, addressRequest);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value = "Delete a address in the database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = AddressResponse.class)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse deleteAddress(@PathVariable Long id){
        return addressService.delete(id);
    }

    @ApiOperation(value = "Find a address in the database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = AddressResponse.class)
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse findAddressById(@PathVariable Long id){
        return addressService.findAddressById(id);
    }
}
