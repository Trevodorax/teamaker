package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.teamaker.team.application.port.in.getTeam.GetTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.domain.Team;



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
        when(loadTeamPortMock.loadTeam(any(LoadTeamCommand.class))).thenReturn(expectedTeam); // mock createProject method

        // === when === //
        Team result = getTeamService.getTeam(command);

        // === then === //
        ArgumentCaptor<LoadTeamCommand> captor = ArgumentCaptor.forClass(LoadTeamCommand.class);
        verify(loadTeamPortMock).loadTeam(captor.capture());
        LoadTeamCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getId());
    }
}
