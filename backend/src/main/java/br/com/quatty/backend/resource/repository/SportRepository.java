package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SportRepository extends JpaRepository<Sport, Long> {

    @Query("select s from Sport s where upper(s.name) like concat('%', upper(:name) , '%')")
    Optional<List<Sport>> findAllByName(@Param("name") String name);
}