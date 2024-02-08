package org.teamaker.project.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.out.loadProjects.LoadProjectsPort;
import org.teamaker.project.domain.Project;

import java.util.List;

@Component
public class LoadProjectsAdapter implements LoadProjectsPort {
    @Override
    public List<Project> loadProjects() throws IllegalArgumentException {
        return null;
    }
}
