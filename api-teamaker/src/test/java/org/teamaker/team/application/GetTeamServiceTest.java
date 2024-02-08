package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.team.application.port.in.getTeam.GetTeamCommand;
import org.teamaker.team.application.port.in.getTeam.GetTeamResponse;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GetTeamServiceTest {
    private static LoadTeamPort loadTeamPortMock;
    private static GetTeamService getTeamService;

    @BeforeAll
    public static void setUp() {
        loadTeamPortMock = mock(LoadTeamPort.class);
        getTeamService = new GetTeamService(loadTeamPortMock);
    }

    @Test
    public void testGetTeam() {
        // === given === //
        String mockId = "mock-id";
        GetTeamCommand command = new GetTeamCommand(mockId);

        // mock the injected command
        Developer mockDeveloper1 = new Developer("mock-developer-id", "John McLane", "johnmcclane@test.com", LocalDate.now().minusDays(1), null);
        Developer mockDeveloper2 = new Developer("mock-developer-id", "Jane McLane", "janemcclane@test.com", LocalDate.now().minusDays(10), null);
        List<Developer> givenDevelopers = List.of(mockDeveloper1, mockDeveloper2);
        Team targettedTeam = new Team(mockId, givenDevelopers, false);

        when(loadTeamPortMock.loadTeam(any(LoadTeamCommand.class))).thenReturn(targettedTeam);

        // === when === //
        GetTeamResponse.Response result = getTeamService.getTeam(command);

        // === then === //
        List<DeveloperResponse> developerResponses = givenDevelopers.stream().map(Developer::toResponse).toList();
        assertInstanceOf(GetTeamResponse.SuccessResponse.class, result);
        assertEquals(developerResponses, ((GetTeamResponse.SuccessResponse) result).team());
    }
}
