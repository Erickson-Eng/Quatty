package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.Practicable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PracticableRepository extends JpaRepository<Practicable, Long> {


    Optional<List<Practicable>> findAllByGymId(Long id);

    Optional<List<Practicable>> findAllBySportId(Long id);

    @Query("select p from Practicable p where upper(p.sport.name) like concat('%', upper(:name), '%' ) ")
    Optional<List<Practicable>> findAllBySportName(String name);
}