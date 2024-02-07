package org.teamaker.developer.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.in.learnSkill.LearnSkillCommand;
import org.teamaker.developer.application.port.in.learnSkill.LearnSkillResponse;
import org.teamaker.developer.application.port.in.learnSkill.LearnSkillUseCase;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillCommand;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;
import org.teamaker.developer.domain.dto.LearnSkillDtoResponse;

import java.util.List;
import java.util.NoSuchElementException;

@Component
class LearnSkillService implements LearnSkillUseCase {
    private final AcquireSkillPort acquireSkillPort;
    private final LoadDeveloperSkillsPort loadDeveloperSkillsPort;

    public LearnSkillService(AcquireSkillPort acquireSkillPort, LoadDeveloperSkillsPort loadDeveloperSkillsPort) {
        this.acquireSkillPort = acquireSkillPort;
        this.loadDeveloperSkillsPort = loadDeveloperSkillsPort;
    }

    @Override
    public LearnSkillResponse.Response learnSkill(LearnSkillCommand command) {
        try {
            acquireSkillPort.acquireSkill(new AcquireSkillCommand(command.getDeveloperId(), command.getTechnologyId()));
            List<LoadDeveloperSkillsResponse> loadDeveloperSkillsResponse = loadDeveloperSkillsPort.loadDeveloperSkills(
                    new LoadDeveloperSkillsCommand(command.getDeveloperId())
            );

            List<LearnSkillDtoResponse> result = loadDeveloperSkillsResponse.stream()
                    .map(loadDeveloperSkillsResponse1
                        -> new LearnSkillDtoResponse(loadDeveloperSkillsResponse1.technology(), loadDeveloperSkillsResponse1.learningDate()))
                    .toList();

            return new LearnSkillResponse.SuccessResponse(result);
        } catch (NoSuchElementException exception) {
            return new LearnSkillResponse.ErrorResponseNotFound(exception.getMessage());
        } catch (IllegalArgumentException exception) {
            return new LearnSkillResponse.ErrorResponseConflict(exception.getMessage());
        }
    }
}
