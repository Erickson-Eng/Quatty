package br.com.quatty.backend.business.service;

import br.com.quatty.backend.application.dto.request.GymRequest;
import br.com.quatty.backend.application.dto.response.GymResponse;
import br.com.quatty.backend.application.dto.response.table.GymTableResponse;

public interface GymService {

    GymResponse save(GymRequest gymRequest);

    GymResponse update(Long id, GymRequest gymRequest);

    GymResponse delete (Long id);

    GymTableResponse findGymByCity(String city);

    GymTableResponse findGymByName(String name);

    GymResponse findById(Long id);

}
