package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsCommand;
import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsResponse;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;
import org.teamaker.developer.domain.dto.SkillResponse;
import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetDeveloperSkillsServiceTest {
    private static LoadDeveloperSkillsPort loadDeveloperSkillsPortMock;
    private static GetDeveloperSkillsService getDeveloperSkillsService;

    @BeforeEach
    public void setUp() {
        loadDeveloperSkillsPortMock = mock(LoadDeveloperSkillsPort.class);
        getDeveloperSkillsService = new GetDeveloperSkillsService(loadDeveloperSkillsPortMock);
    }

    @Test
    public void testGetDeveloperSkillsSuccess() {
        LocalDate mockLearningDate = LocalDate.now().minusYears(1);
        List<LoadDeveloperSkillsResponse> expectedLoadSkillsResponsesList = List.of(new LoadDeveloperSkillsResponse(new TechnologyResponse("technologyId", "technologyName"), mockLearningDate));
        List<SkillResponse> expectedSkillsResponseList = List.of(new SkillResponse(new TechnologyResponse("technologyId", "technologyName"), mockLearningDate));
        when(loadDeveloperSkillsPortMock.loadDeveloperSkills(any(LoadDeveloperSkillsCommand.class))).thenReturn(expectedLoadSkillsResponsesList);

        GetDeveloperSkillsResponse.Response result = getDeveloperSkillsService.getDeveloperSkills(new GetDeveloperSkillsCommand("developerId"));

        verify(loadDeveloperSkillsPortMock).loadDeveloperSkills(any(LoadDeveloperSkillsCommand.class));
        assertInstanceOf(GetDeveloperSkillsResponse.SuccessResponse.class, result);
        assertEquals(new GetDeveloperSkillsResponse.SuccessResponse(expectedSkillsResponseList), result);
    }

    @Test
    public void testGetDeveloperSkillsError() {
        when(loadDeveloperSkillsPortMock.loadDeveloperSkills(any(LoadDeveloperSkillsCommand.class))).thenThrow(new NoSuchElementException("developer not found"));

        GetDeveloperSkillsResponse.Response result = getDeveloperSkillsService.getDeveloperSkills(new GetDeveloperSkillsCommand("developerId"));

        verify(loadDeveloperSkillsPortMock).loadDeveloperSkills(any(LoadDeveloperSkillsCommand.class));
        assertInstanceOf(GetDeveloperSkillsResponse.ErrorResponse.class, result);
        assertEquals(new GetDeveloperSkillsResponse.ErrorResponse("developer not found"), result);
    }
}
