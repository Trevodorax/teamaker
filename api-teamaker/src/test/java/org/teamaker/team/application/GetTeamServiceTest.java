package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.team.application.port.in.getTeam.GetTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.List;


class GetTeamServiceTest {
    private static LoadTeamPort loadTeamPortMock;
    private static GetTeamService getTeamService;

    @BeforeAll
    public static void setUp() {
        loadTeamPortMock = mock(LoadTeamPort.class);
        getTeamService = new GetTeamService(loadTeamPortMock);
    }
    @Test
    public void testSubmitProject() {
        // === given === //
        String mockId = "mock-id";
        GetTeamCommand command = new GetTeamCommand(mockId);

        // mock the injected command
        Team expectedTeam = new Team("mock-id");
        Developer mockDeveloper1 = new Developer("mock-developer-id", "John McLane", "johnmcclane@test.com", LocalDate.now().minusDays(1));
        Developer mockDeveloper2 = new Developer("mock-developer-id", "Jane McLane", "janemcclane@test.com", LocalDate.now().minusDays(10));
        List<Developer> expectedDevelopers = List.of(mockDeveloper1, mockDeveloper2);
        when(loadTeamPortMock.loadTeam(any(LoadTeamCommand.class))).thenReturn(expectedDevelopers);

        // === when === //
        List<DeveloperResponse> result = getTeamService.getTeam(command);

        // === then === //
        ArgumentCaptor<LoadTeamCommand> captor = ArgumentCaptor.forClass(LoadTeamCommand.class);
        verify(loadTeamPortMock).loadTeam(captor.capture());
        LoadTeamCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getId());
    }
}
