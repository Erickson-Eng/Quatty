package br.com.quatty.backend.business.service;

import br.com.quatty.backend.application.dto.request.PracticableRequest;
import br.com.quatty.backend.application.dto.response.PracticableResponse;

import java.util.List;

public interface PracticableService {

    PracticableResponse save (PracticableRequest request);

    void saveMultiplePracticeSports(List<PracticableRequest> requestList);

    PracticableResponse update (Long id, PracticableRequest request);

    PracticableResponse delete (Long id);

    List<PracticableResponse> listSportsPracticableInTheGym(Long gymId);

    List<PracticableResponse> listAllPracticableSportsByName(String sportName);

    List<PracticableResponse> listAllPracticableSportsById(Long sportId);
}
