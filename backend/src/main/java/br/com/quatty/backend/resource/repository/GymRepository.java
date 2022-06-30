package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {

    @Query("select g from Gym g where upper(g.name) like concat('%', upper(:name), '%' ) ")
    Optional<List<Gym>> findAllByNameContainingIgnoreCase(@Param("name") String name);

    @Query("select g from Gym g where upper(g.school.address.city) like concat('%', upper(:city), '%' ) ")
    Optional<List<Gym>> findAllBySchool_Address_City(String city);
}