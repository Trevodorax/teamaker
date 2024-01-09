package org.teamaker.project.application.port.out.saveProject;

import org.teamaker.project.domain.Project;

public interface SaveProjectPort {
    Project modifyProjectDates(SaveProjectCommand command);
}
