package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.domain.Developer;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectCommand;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectPort;

import java.util.List;

@Component
public class LoadPossibleDevelopersForProjectAdapter implements LoadPossibleDevelopersForProjectPort {
    @Override
    public List<Developer> loadPossibleDevelopersForProject(LoadPossibleDevelopersForProjectCommand command) {
        return null;
    }
}
