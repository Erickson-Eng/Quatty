package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.MembershipRequest;
import br.com.quatty.backend.application.dto.response.MembershipResponse;
import br.com.quatty.backend.application.dto.response.table.MembershipTableResponse;
import br.com.quatty.backend.business.service.MembershipService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/membership")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MembershipController {

    private MembershipService membershipService;

    @ApiOperation(value = "Save the application of an athlete in the team")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created", response = MembershipResponse.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MembershipResponse save (@Valid @RequestBody MembershipRequest membershipRequest){
        return membershipService.save(membershipRequest);
    }

    @ApiOperation(value = "Save the application of an athlete in the team")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = MembershipResponse.class)
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MembershipResponse update (@Valid @RequestBody MembershipRequest membershipRequest){
        return membershipService.update(membershipRequest);
    }


    @ApiOperation(value = "Excludes application of an athlete to a team")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = MembershipResponse.class)
    })
    @DeleteMapping("/team-id/{teamId}/athlete-id/{athleteId}")
    @ResponseStatus(HttpStatus.OK)
    public MembershipResponse delete (@PathVariable Long teamId, @PathVariable Long athleteId){
        return membershipService.delete(teamId, athleteId);
    }


    @ApiOperation(value = "Lists all requests made by the athlete for team entries  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = MembershipTableResponse.class)
    })
    @GetMapping("/athlete-id/{athleteId}")
    @ResponseStatus(HttpStatus.OK)
    public MembershipTableResponse listAllRequestForAthlete (@PathVariable Long athleteId){
        return membershipService.listAllRequestForAthletes(athleteId);
    }


    @ApiOperation(value = "list all incoming requests to the team")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = MembershipTableResponse.class)
    })
    @GetMapping("/team-id/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public MembershipTableResponse listAllRequestForTeam (@PathVariable Long teamId){
        return membershipService.listAllRequestForTeam(teamId);
    }



}
