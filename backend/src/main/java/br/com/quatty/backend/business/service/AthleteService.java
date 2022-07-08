package br.com.quatty.backend.business.service;

import br.com.quatty.backend.application.dto.request.AthleteRequest;
import br.com.quatty.backend.application.dto.response.AthleteResponse;
import br.com.quatty.backend.application.dto.response.table.AthleteTableResponse;

public interface AthleteService {

    AthleteResponse save (AthleteRequest athleteRequest);

    AthleteResponse update(Long id, AthleteRequest athleteRequest);

    AthleteTableResponse listAthleteForName(String name);
}
