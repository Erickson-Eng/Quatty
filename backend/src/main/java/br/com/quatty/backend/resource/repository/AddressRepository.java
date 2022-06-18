package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}