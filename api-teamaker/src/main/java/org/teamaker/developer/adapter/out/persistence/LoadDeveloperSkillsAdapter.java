package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class LoadDeveloperSkillsAdapter implements LoadDeveloperSkillsPort {
    @Override
    public List<LoadDeveloperSkillsResponse> loadDeveloperSkills(LoadDeveloperSkillsCommand command) throws NoSuchElementException {
        return null;
    }
}
