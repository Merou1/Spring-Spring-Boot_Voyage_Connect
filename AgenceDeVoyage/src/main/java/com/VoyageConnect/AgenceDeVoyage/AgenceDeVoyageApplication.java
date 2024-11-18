package com.VoyageConnect.AgenceDeVoyage;

import com.VoyageConnect.AgenceDeVoyage.entity.Role;
import com.VoyageConnect.AgenceDeVoyage.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgenceDeVoyageApplication implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public AgenceDeVoyageApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AgenceDeVoyageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if roles already exist before inserting to avoid duplicates
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_CLIENT"));
            System.out.println("Roles inserted!");
        } else {
            System.out.println("Roles already exist, skipping insertion.");
        }
    }
}
