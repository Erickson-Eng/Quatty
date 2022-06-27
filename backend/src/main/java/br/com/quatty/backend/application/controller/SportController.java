package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.SportRequest;
import br.com.quatty.backend.application.dto.response.SportResponse;
import br.com.quatty.backend.business.service.SportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sport")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SportController {

    private SportService sportService;

    @ApiOperation(value = "Create a new sport in the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created", response = SportResponse.class)
    })
    @PostMapping
    public SportResponse createSport(@RequestBody @Valid SportRequest sportRequest){
        return sportService.save(sportRequest);
    }

    @ApiOperation(value = "update a sport need to provide an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SportResponse.class)
    })
    @PutMapping("/{id}")
    public SportResponse updateSport(@PathVariable Long id,
                                     @RequestBody @Valid SportRequest sportRequest){
        return sportService.update(id, sportRequest);
    }

    @ApiOperation(value = "delete a sport from the database from a given id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SportResponse.class)
    })
    @DeleteMapping(path = "/{id}")
    public SportResponse deleteSport(@PathVariable Long id){
        return sportService.delete(id);
    }

    @ApiOperation(value = "fetches a sport from a given id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SportResponse.class)
    })
    @GetMapping(path = "/find-by-id/{id}")
    public SportResponse findById(@PathVariable Long id){
        return sportService.findSportById(id);
    }

    @ApiOperation(value = "fetches a sport from a given name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SportResponse.class)
    })
    @GetMapping(path = "/find-by-name/{name}")
    public List<SportResponse> findAllByName(@PathVariable String name){
        return sportService.findSportByName(name);
    }

    @ApiOperation(value = "back all sports saved in the database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SportResponse.class)
    })
    @GetMapping("/list-all")
    public List<SportResponse> findAll(){
        return sportService.listAllSportResponse();
    }
}
