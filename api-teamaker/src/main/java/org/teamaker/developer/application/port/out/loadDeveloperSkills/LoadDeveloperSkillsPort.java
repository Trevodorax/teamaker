package org.teamaker.developer.application.port.out.loadDeveloperSkills;

import org.teamaker.technology.domain.Technology;

import java.util.List;

public interface LoadDeveloperSkillsPort {
    List<Technology> loadDeveloperSkills(LoadDeveloperSkillsCommand command);
}
