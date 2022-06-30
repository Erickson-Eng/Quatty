package br.com.quatty.backend.application.controller;

import br.com.quatty.backend.application.dto.request.SchoolRequest;
import br.com.quatty.backend.application.dto.response.SchoolResponse;
import br.com.quatty.backend.business.service.SchoolService;
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
@RequestMapping("/api/v1/school")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SchoolController {

    private SchoolService schoolService;

    @ApiOperation(value = "Create a new school in the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "created", response = SchoolResponse.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolResponse createSchool(@RequestBody @Valid SchoolRequest schoolRequest){
        return schoolService.save(schoolRequest);
    }

    @ApiOperation(value = "update a school need to provide an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SchoolResponse.class)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolResponse updateSchool(@PathVariable Long id,
                                       @RequestBody @Valid SchoolRequest schoolRequest){
        return schoolService.update(id, schoolRequest);
    }

    @ApiOperation(value = "delete a school need to provide an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SchoolResponse.class)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolResponse deleteSchool(@PathVariable Long id){
        return schoolService.delete(id);
    }

    @ApiOperation(value = "fetches a school from a given id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SchoolResponse.class)
    })
    @GetMapping(path = "/find-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolResponse findById(@PathVariable Long id){
        return schoolService.findById(id);
    }

    @ApiOperation(value = "fetches a school from a given name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SchoolResponse.class)
    })
    @GetMapping(path = "/find-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolResponse> findAllByName(@PathVariable String name){
        return schoolService.findAllByName(name);
    }

    @ApiOperation(value = "fetches a school from a given name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = SchoolResponse.class)
    })
    @GetMapping(path = "/find-by-city/{city}")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolResponse> findAllSchoolForCity(@PathVariable String city){
        return schoolService.findAllSchoolForCity(city);
    }

}
