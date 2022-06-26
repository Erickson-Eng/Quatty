package br.com.quatty.backend.business.service;

import br.com.quatty.backend.application.dto.request.SportRequest;
import br.com.quatty.backend.application.dto.response.SportResponse;

import java.util.List;

public interface SportService {


    SportResponse save (SportRequest sportRequest);

    SportResponse update (Long id, SportRequest sportRequest);

    SportResponse delete (Long id);

    List<SportResponse> findSportByName(String name);

    SportResponse findSportById(Long id);

    List<SportResponse> ListAllSportResponse();
}
