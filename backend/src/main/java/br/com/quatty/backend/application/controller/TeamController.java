package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.TeamRequest;
import br.com.quatty.backend.application.dto.response.TeamResponse;
import br.com.quatty.backend.application.dto.response.table.TeamTableResponse;
import br.com.quatty.backend.business.service.TeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/team")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TeamController {

    private TeamService teamService;


    @ApiOperation(value = "Save the creation of a team in the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created", response = TeamResponse.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponse save (@Valid @RequestBody TeamRequest teamRequest){
        return teamService.save(teamRequest);
    }

    @ApiOperation(value = "Update a team saved in the database after providing the id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = TeamResponse.class)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamResponse update (@PathVariable Long id, @Valid @RequestBody TeamRequest teamRequest){
        return teamService.update(id, teamRequest);
    }


    @ApiOperation(value = "Delete a team in the database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = TeamResponse.class)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamResponse delete (@PathVariable Long id){
        return teamService.delete(id);
    }

    @ApiOperation(value = "Search for a team by name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = TeamTableResponse.class)
    })
    @GetMapping("/find-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public TeamTableResponse findByName (@PathVariable String name){
        return teamService.findTeamByName(name);
    }


}
