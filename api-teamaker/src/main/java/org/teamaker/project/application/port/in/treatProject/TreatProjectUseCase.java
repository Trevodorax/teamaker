package org.teamaker.project.application.port.in.treatProject;

import org.teamaker.project.domain.dto.TreatProjectResponse;

public interface TreatProjectUseCase {
    TreatProjectResponse treatProject(TreatProjectCommand command);
}
