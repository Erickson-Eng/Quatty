package br.com.quatty.backend.business.service;

import br.com.quatty.backend.application.dto.request.MembershipRequest;
import br.com.quatty.backend.application.dto.response.MembershipResponse;
import br.com.quatty.backend.application.dto.response.table.MembershipTableResponse;

public interface MembershipService {

    MembershipResponse save (MembershipRequest membershipRequest);

    MembershipResponse update (MembershipRequest membershipRequest );

    MembershipResponse delete (Long teamId, Long athleteId);

    MembershipTableResponse listAllRequestForAthletes(Long athleteId);

    MembershipTableResponse listAllRequestForTeam(Long teamId);
}
