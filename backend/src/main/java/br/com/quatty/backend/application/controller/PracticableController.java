package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.PracticableRequest;

import br.com.quatty.backend.application.dto.request.table.PracticableTableRequest;
import br.com.quatty.backend.application.dto.response.PracticableResponse;
import br.com.quatty.backend.application.dto.response.table.PracticableTableResponse;
import br.com.quatty.backend.business.service.PracticableService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sport_practicable")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PracticableController {

    private PracticableService practicableService;


    @ApiOperation(value = "Create a new practicable sport in the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created", response = PracticableResponse.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PracticableResponse registerPracticable(@RequestBody
                                                  @Valid PracticableRequest practicableRequest){
        return practicableService.save(practicableRequest);
    }

    @ApiOperation(value = "multiple practicable registration in the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created")
    })
    @PostMapping("/multiple-sports-registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAllPracticable(@RequestBody
                                       @Valid PracticableTableRequest practicableTableRequest){
        practicableService.saveMultiplePracticeSports(practicableTableRequest.getPracticableRequests());
    }

    @ApiOperation(value = "Update practicable sport in the database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response =  PracticableResponse.class)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PracticableResponse updatePracticable(@PathVariable Long id,
                                                 @RequestBody @Valid PracticableRequest practicableRequest){
        return practicableService.update(id, practicableRequest);
    }

    @ApiOperation(value = "Delete practicable sport in the database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response =  PracticableResponse.class)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PracticableResponse deletePracticable(@PathVariable Long id){
        return practicableService.delete(id);
    }

    @ApiOperation(value = "Select all practicable sport in the database for gym")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response =  PracticableTableResponse.class)
    })
    @GetMapping("/list-all-practicable-for-gym/{gymId}")
    @ResponseStatus(HttpStatus.OK)
    public PracticableTableResponse listAllGymPracticable(@PathVariable  Long gymId){
        List<PracticableResponse> practicableResponses = practicableService.listSportsPracticableInTheGym(gymId);
        return new PracticableTableResponse(practicableResponses);
    }

    @ApiOperation(value = "Select all practicable sport in the database for gym")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response =  PracticableTableResponse.class)
    })
    @GetMapping("/list-all-practicable-for-sport-name/{sportName}")
    @ResponseStatus(HttpStatus.OK)
    public PracticableTableResponse listAllPracticableSportsByName(@PathVariable  String sportName){
        List<PracticableResponse> practicableResponses = practicableService.listAllPracticableSportsByName(sportName);
        return new PracticableTableResponse(practicableResponses);
    }


    @ApiOperation(value = "Select all practicable sport in the database for gym")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response =  PracticableTableResponse.class)
    })
    @GetMapping("/list-all-practicable-for-sport-id/{sportId}")
    @ResponseStatus(HttpStatus.OK)
    public PracticableTableResponse listAllPracticableSportsById(@PathVariable  Long sportId){
        List<PracticableResponse> practicableResponses = practicableService.listAllPracticableSportsById(sportId);
        return new PracticableTableResponse(practicableResponses);
    }

}
