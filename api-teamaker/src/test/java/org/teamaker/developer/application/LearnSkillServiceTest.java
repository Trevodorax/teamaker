package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsResponse;
import org.teamaker.developer.application.port.in.learnSkill.LearnSkillCommand;
import org.teamaker.developer.application.port.in.learnSkill.LearnSkillResponse;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillCommand;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillPort;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillResponse;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;
import org.teamaker.developer.domain.dto.LearnSkillDtoResponse;
import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LearnSkillServiceTest {
    private static AcquireSkillPort acquireSkillPortMock;
    private static LoadDeveloperSkillsPort loadDeveloperSkillsPortMock;
    private static LearnSkillService learnSkillService;

    @BeforeEach
    public void setUp() {
        acquireSkillPortMock = mock(AcquireSkillPort.class);
        loadDeveloperSkillsPortMock = mock(LoadDeveloperSkillsPort.class);
        learnSkillService = new LearnSkillService(acquireSkillPortMock, loadDeveloperSkillsPortMock);
    }

    @Test
    public void testLearnSkillSuccess() {
        LocalDate mockLearningDate = LocalDate.now();
        AcquireSkillResponse expectedAcquireSkillResponse = new AcquireSkillResponse(new TechnologyResponse("technologyId", "technologyName"), mockLearningDate);
        when(acquireSkillPortMock.acquireSkill(any(AcquireSkillCommand.class))).thenReturn(expectedAcquireSkillResponse);

        List<LoadDeveloperSkillsResponse> expectedLoadSkillsResponsesList = List.of(new LoadDeveloperSkillsResponse(expectedAcquireSkillResponse.technology(), expectedAcquireSkillResponse.learningDate()), new LoadDeveloperSkillsResponse(new TechnologyResponse("technologyId2", "technologyName2"), mockLearningDate));
        when(loadDeveloperSkillsPortMock.loadDeveloperSkills(any(LoadDeveloperSkillsCommand.class))).thenReturn(expectedLoadSkillsResponsesList);
        List<LearnSkillDtoResponse> expectedLearnSkillDtoResponseList = List.of(new LearnSkillDtoResponse(expectedAcquireSkillResponse.technology(), expectedAcquireSkillResponse.learningDate()), new LearnSkillDtoResponse(new TechnologyResponse("technologyId2", "technologyName2"), mockLearningDate));

        LearnSkillResponse.Response result = learnSkillService.learnSkill(new LearnSkillCommand("developerId", "skillId"));

        verify(acquireSkillPortMock).acquireSkill(any(AcquireSkillCommand.class));
        verify(loadDeveloperSkillsPortMock).loadDeveloperSkills(any(LoadDeveloperSkillsCommand.class));

        assertInstanceOf(LearnSkillResponse.SuccessResponse.class, result);
        assertEquals(new LearnSkillResponse.SuccessResponse(expectedLearnSkillDtoResponseList), result);
    }

    @Test
    public void testLearnSkillError() {
        when(acquireSkillPortMock.acquireSkill(any(AcquireSkillCommand.class))).thenThrow(new NoSuchElementException("developer not found"));

        LearnSkillResponse.Response result = learnSkillService.learnSkill(new LearnSkillCommand("developerId", "skillId"));

        verify(acquireSkillPortMock).acquireSkill(any(AcquireSkillCommand.class));
        assertInstanceOf(LearnSkillResponse.ErrorResponse.class, result);
        assertEquals(new LearnSkillResponse.ErrorResponse("developer not found"), result);
    }
}
