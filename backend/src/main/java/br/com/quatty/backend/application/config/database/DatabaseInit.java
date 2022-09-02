package br.com.quatty.backend.application.config.database;

import br.com.quatty.backend.business.entity.Role;
import br.com.quatty.backend.business.entity.enums.RoleName;
import br.com.quatty.backend.resource.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class DatabaseInit implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        roleRepository.saveAll(Arrays.asList(new Role(RoleName.ROLE_USER), new Role(RoleName.ROLE_ADMIN)));
    }
}
