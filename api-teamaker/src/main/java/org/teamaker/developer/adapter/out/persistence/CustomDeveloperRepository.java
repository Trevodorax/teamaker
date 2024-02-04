package org.teamaker.developer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;

import java.util.Optional;

public interface CustomDeveloperRepository extends JpaRepository<DeveloperJPA, String> {
    Optional<DeveloperJPA> findByEmail(String email);
}
