package org.teamaker.developer.application.port.out.loadDeveloperSkills;

import java.util.List;
import java.util.NoSuchElementException;

public interface LoadDeveloperSkillsPort {
    List<LoadDeveloperSkillsResponse> loadDeveloperSkills(LoadDeveloperSkillsCommand command) throws NoSuchElementException;
}
