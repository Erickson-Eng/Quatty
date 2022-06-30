package br.com.quatty.backend.business.service;

import br.com.quatty.backend.application.dto.request.SchoolRequest;
import br.com.quatty.backend.application.dto.response.SchoolResponse;

import java.util.List;

public interface SchoolService {

    SchoolResponse save (SchoolRequest schoolRequest);

    SchoolResponse update (Long id, SchoolRequest schoolRequest);

    SchoolResponse delete (Long id);

    SchoolResponse findById(Long id);

    List<SchoolResponse> findAllByName(String name);

    List<SchoolResponse> findAllSchoolForCity(String cityName);
}
