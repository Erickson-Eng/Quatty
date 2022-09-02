package br.com.quatty.backend.application.dto.mapper;

import br.com.quatty.backend.application.dto.request.MembershipRequest;
import br.com.quatty.backend.application.dto.response.MembershipResponse;
import br.com.quatty.backend.business.entity.Membership;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TeamMapper.class, AthleteMapper.class})
public interface MembershipMapper {


    @Mapping(target = "athlete.id", source = "athleteId")
    @Mapping(target = "team.id", source = "teamId")
    @Mapping(target = "membershipPK.teamId", source = "teamId")
    @Mapping(target = "membershipPK.athleteId", source = "athleteId")
    @Mapping(target = "registrationStatus", source = "registrationStatus")
    Membership membershipRequestToEntity(MembershipRequest membershipRequest);
    @Mapping(target = "athleteId", source = "athlete.id")
    @Mapping(target = "teamId", source = "team.id")
    @Mapping(target = "teamName", source = "team.name")
    MembershipResponse entityToMembershipResponse(Membership membership);
}
