package org.teamaker.developer.application.port.in.getDeveloperSkills;

public interface GetDeveloperSkillsUseCase {
    GetDeveloperSkillsResponse.Response getDeveloperSkills(GetDeveloperSkillsCommand command);
}
