package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.MembershipMapper;
import br.com.quatty.backend.application.dto.request.MembershipRequest;
import br.com.quatty.backend.application.dto.response.MembershipResponse;
import br.com.quatty.backend.application.dto.response.table.MembershipTableResponse;
import br.com.quatty.backend.business.entity.Membership;
import br.com.quatty.backend.business.entity.composite_key.MembershipPK;
import br.com.quatty.backend.business.service.MembershipService;
import br.com.quatty.backend.business.service.exception.DataIntegrityViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.resource.repository.MembershipRepository;
import br.com.quatty.backend.resource.utils.Log4JConstantService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MembershipServicePostgresql implements MembershipService {

    private MembershipRepository membershipRepository;
    private MembershipMapper membershipMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(MembershipServicePostgresql.class);

    @Override
    public MembershipResponse save(MembershipRequest membershipRequest) {
        LOGGER.info(Log4JConstantService.LOG4J_START_SAVE_ENTITY + "MEMBERSHIP");
        Membership membership = membershipMapper.membershipRequestToEntity(membershipRequest);
        try {
            membershipRepository.save(membership);
        }catch (RuntimeException e){
            LOGGER.info(Log4JConstantService.LOG4J_ERROR_SAVE_ENTITY);
            throw new DataIntegrityViolationException(e.getMessage());
        }
        LOGGER.info(Log4JConstantService.LOG4J_FINISH_SAVE_ENTITY + "MEMBERSHIP");
        return membershipMapper.entityToMembershipResponse(membership);
    }

    @Override
    public MembershipResponse update(MembershipRequest membershipRequest) {
        Membership membership = verifyIfExist(membershipRequest.getTeamId(), membershipRequest.getAthleteId());
        updateData(membership, membershipRequest);
        membershipRepository.save(membership);
        return membershipMapper.entityToMembershipResponse(membership);
    }

    private void updateData(Membership membership, MembershipRequest membershipRequest) {
        membership.setRegistrationStatus(membershipRequest.getRegistrationStatus());
    }

    private Membership verifyIfExist(Long teamId, Long athleteId) {
        return membershipRepository.findById(new MembershipPK(teamId, athleteId))
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("There are no applications for the time {} of the athlete {}.", teamId, athleteId)));
    }

    @Override
    public MembershipResponse delete(Long teamId, Long athleteId) {
        Membership membership = verifyIfExist(teamId,athleteId );
        membershipRepository.delete(membership);
        return membershipMapper.entityToMembershipResponse(membership);
    }

    @Override
    public MembershipTableResponse listAllRequestForAthletes(Long athleteId) {
        List<Membership> memberships = verifyIfExistApplyForAthlete(athleteId);
        return entityListToTableResponse(memberships);
    }

    private List<Membership> verifyIfExistApplyForAthlete(Long athleteId){
        return membershipRepository.findAllByAthleteId(athleteId)
                .orElseThrow(() -> new EntityNotFoundException("Athlete has no pending applications"));
    }

    @Override
    public MembershipTableResponse listAllRequestForTeam(Long teamId) {
        List<Membership> memberships = verifyIfExistApplyForTeam(teamId);
        return entityListToTableResponse(memberships);
    }

    private List<Membership> verifyIfExistApplyForTeam(Long teamId){
        return membershipRepository.findAllByTeamId(teamId)
                .orElseThrow(() -> new EntityNotFoundException("There are no athletes applied"));
    }

    private MembershipTableResponse entityListToTableResponse(List<Membership> list){
        List<MembershipResponse> membershipResponseList = list.stream()
                .map(membershipMapper::entityToMembershipResponse)
                .collect(Collectors.toList());
        return new MembershipTableResponse(membershipResponseList);
    }
}
