package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.UserRequest;
import br.com.quatty.backend.business.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/register")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    private UserService userService;

    @ApiOperation(value = "Create a new address in the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created", response = UserRequest.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createAddress(@RequestBody @Valid UserRequest userRequest){
        userService.registerUser(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
