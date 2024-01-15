package org.teamaker.project.application.port.in.treatProject;

import org.teamaker.project.application.port.dto.TreatProjectResponse;

public interface TreatProjectUseCase {
    TreatProjectResponse treatProject(TreatProjectCommand command);
}
