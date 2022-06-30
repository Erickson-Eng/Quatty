package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.GymRequest;
import br.com.quatty.backend.application.dto.response.GymResponse;
import br.com.quatty.backend.application.dto.response.table.GymTableResponse;
import br.com.quatty.backend.business.service.GymService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/gym")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GymController {

    private GymService gymService;

    @ApiOperation(value = "Create a new gym in the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created", response = GymResponse.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GymResponse createGym(@RequestBody @Valid GymRequest gymRequest){
        return gymService.save(gymRequest);
    }

    @ApiOperation(value = "update a gym need to provide an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = GymResponse.class)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GymResponse updateGym(@PathVariable Long id,
                                 @RequestBody @Valid GymRequest gymRequest){
        return gymService.update(id, gymRequest);
    }

    @ApiOperation(value = "delete a gym need to provide an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = GymRequest.class)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GymResponse deleteGym(@PathVariable Long id){
        return gymService.delete(id);
    }

    @ApiOperation(value = "fetches a gym from a given id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = GymRequest.class)
    })
    @GetMapping(path = "/find-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GymResponse findGymById(@PathVariable Long id){
        return gymService.findById(id);
    }


    @ApiOperation(value = "fetches a gym from a given name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = GymTableResponse.class)
    })
    @GetMapping(path = "/find-by-name/{name}")
    public GymTableResponse findGymByName(@PathVariable String name){
        return gymService.findGymByName(name);
    }

    @ApiOperation(value = "fetches a school from a given name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = GymTableResponse.class)
    })
    @GetMapping(path = "/find-by-city/{city}")
    public GymTableResponse findGymByCityName(@PathVariable String city){
        return gymService.findGymByCity(city);
    }


}
