package br.com.quatty.backend.business.service;

import br.com.quatty.backend.application.dto.request.TeamRequest;
import br.com.quatty.backend.application.dto.response.TeamResponse;
import br.com.quatty.backend.application.dto.response.table.TeamTableResponse;

public interface TeamService {

    TeamResponse save (TeamRequest teamRequest);

    TeamResponse update(Long id, TeamRequest teamRequest);

    TeamResponse delete (Long id);

    TeamTableResponse findTeamByName(String name);


}
