package org.teamaker.developer.application;

import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsCommand;
import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsResponse;
import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsUseCase;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.SkillResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

class GetDeveloperSkillsService implements GetDeveloperSkillsUseCase {
    private final LoadDeveloperSkillsPort loadDeveloperSkillsPort;

    public GetDeveloperSkillsService(LoadDeveloperSkillsPort loadDeveloperSkillsPort) {
        this.loadDeveloperSkillsPort = loadDeveloperSkillsPort;
    }

    public GetDeveloperSkillsResponse.Response getDeveloperSkills(GetDeveloperSkillsCommand command) {
        try {
            List<LoadDeveloperSkillsResponse> loadDeveloperSkillsResponse = loadDeveloperSkillsPort.loadDeveloperSkills(
                    new LoadDeveloperSkillsCommand(command.getDeveloperId())
            );

            List<SkillResponse> result = loadDeveloperSkillsResponse.stream()
                    .map(loadDeveloperSkillsResponse1
                            -> new SkillResponse(loadDeveloperSkillsResponse1.technology(), loadDeveloperSkillsResponse1.learningDate()))
                    .toList();

            return new GetDeveloperSkillsResponse.SuccessResponse(result);
        } catch (NoSuchElementException exception) {
            return new GetDeveloperSkillsResponse.ErrorResponse("developer not found");
        }
    }
}
