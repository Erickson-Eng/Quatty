package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.AthleteRequest;
import br.com.quatty.backend.application.dto.response.AthleteResponse;
import br.com.quatty.backend.application.dto.response.table.AthleteTableResponse;
import br.com.quatty.backend.business.service.AthleteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/athlete")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AthleteController {

    private AthleteService athleteService;

    @ApiOperation(value = "Create a new athlete in the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created", response = AthleteResponse.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AthleteResponse saveAthlete(@RequestBody @Valid AthleteRequest athleteRequest){
        return athleteService.save(athleteRequest);
    }

    @ApiOperation(value = "Create a new athlete in the database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = AthleteResponse.class)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AthleteResponse updateAthlete(@PathVariable Long id,
                                         @RequestBody @Valid AthleteRequest athleteRequest){
        return athleteService.update(id, athleteRequest);
    }

    @ApiOperation(value = "list all athletes with the same name as the one entered")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = AthleteTableResponse.class)
    })
    @GetMapping(path = "/find-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public AthleteTableResponse findAthleteByName(@PathVariable String name){
        return athleteService.listAthleteForName(name);
    }

}
