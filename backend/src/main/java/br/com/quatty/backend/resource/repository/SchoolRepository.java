package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("select s from School s where upper(s.name) like concat('%', upper(:name), '%' ) ")
    Optional<List<School>> findAllByName(@Param("name") String name);

    @Query("select s from School s where upper(s.address.city) like concat('%', upper(:cityName), '%' ) ")
    Optional<List<School>> findAllByAddress_City(@Param("cityName") String city);
}