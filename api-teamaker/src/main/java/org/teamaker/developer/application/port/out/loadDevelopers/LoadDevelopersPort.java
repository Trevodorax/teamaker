package org.teamaker.developer.application.port.out.loadDevelopers;

import org.teamaker.developer.domain.Developer;

import java.util.List;

public interface LoadDevelopersPort {
    List<Developer> loadDevelopers();
}
