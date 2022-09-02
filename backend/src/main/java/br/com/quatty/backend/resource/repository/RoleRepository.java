package br.com.quatty.backend.resource.repository;

import br.com.quatty.backend.business.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}