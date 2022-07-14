package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t where upper(t.name) like concat('%', upper(:name), '%' ) ")
    Optional<List<Team>> findAllByNameContainingIgnoreCase(@Param("name") String name);


}