package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SportRepository extends JpaRepository<Sport, Long> {

    @Query("select u from Sport u where upper(:name) like concat('%', upper(u.name) , '%') ")
    Optional<List<Sport>> findAllByName(String name);
}