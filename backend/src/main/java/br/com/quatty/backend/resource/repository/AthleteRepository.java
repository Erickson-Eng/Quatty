package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    @Query("select a from Athlete a where upper(a.firstName) like concat('%', upper(:firstName), '%' ) ")
    Optional<List<Athlete>> findAthleteByFirstNameContainingIgnoreCase(String firstName);
}