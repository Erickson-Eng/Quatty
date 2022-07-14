package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.Membership;
import br.com.quatty.backend.business.entity.composite_key.MembershipPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, MembershipPK> {

    Optional<List<Membership>> findAllByAthleteId(Long id);

    Optional<List<Membership>> findAllByTeamId(Long id);
}