package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.team.application.port.in.getPossibleDevelopersForProject.GetPossibleDevelopersForProjectCommand;
import org.teamaker.team.application.port.in.getPossibleDevelopersForProject.GetPossibleDevelopersForTeamResponse;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectCommand;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectPort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetPossibleDevelopersForProjectServiceTest {
    private static GetPossibleDevelopersForProjectService getPossibleDevelopersForProjectService;
    private static LoadPossibleDevelopersForProjectPort loadPossibleDevelopersForProjectPortMock;

    @BeforeAll
    public static void setUp() {
        loadPossibleDevelopersForProjectPortMock = mock(LoadPossibleDevelopersForProjectPort.class);

        getPossibleDevelopersForProjectService = new GetPossibleDevelopersForProjectService(
                loadPossibleDevelopersForProjectPortMock
        );
    }

    @Test
    public void testGetPossibleDevelopersForProject_Success() {
        // mock out ports
        List<Developer> developers = getSampleDevelopers();
        when(loadPossibleDevelopersForProjectPortMock.loadPossibleDevelopersForProject(any(LoadPossibleDevelopersForProjectCommand.class))).thenReturn(developers);

        // call
        GetPossibleDevelopersForTeamResponse.Response response = getPossibleDevelopersForProjectService.getPossibleDevelopersForProjectUseCase(
                new GetPossibleDevelopersForProjectCommand("projectId")
        );

        // checks
        assertTrue(response instanceof GetPossibleDevelopersForTeamResponse.SuccessResponse);
        List<DeveloperResponse> returnedDevelopers = ((GetPossibleDevelopersForTeamResponse.SuccessResponse) response).developers();
        assertEquals(developers.size(), returnedDevelopers.size());

        for (int i = 0; i < developers.size(); i++) {
            Developer expectedDeveloper = developers.get(i);
            DeveloperResponse returnedDeveloper = returnedDevelopers.get(i);
            assertEquals(expectedDeveloper.toResponse(), returnedDeveloper);
        }
    }

    @Test
    public void testGetPossibleDevelopersForProject_Error() {
        // mock out ports to throw an exception
        when(loadPossibleDevelopersForProjectPortMock.loadPossibleDevelopersForProject(any(LoadPossibleDevelopersForProjectCommand.class))).thenThrow(new IllegalArgumentException("Invalid project ID"));

        // call
        GetPossibleDevelopersForTeamResponse.Response response = getPossibleDevelopersForProjectService.getPossibleDevelopersForProjectUseCase(
                new GetPossibleDevelopersForProjectCommand("invalidProjectId")
        );

        // checks
        assertTrue(response instanceof GetPossibleDevelopersForTeamResponse.ErrorResponse);
        assertEquals("Invalid project ID", ((GetPossibleDevelopersForTeamResponse.ErrorResponse) response).errorMessage());
    }

    private List<Developer> getSampleDevelopers() {
        ArrayList<Developer> developers = new ArrayList<>();

        developers.add(new Developer("dev1", "pol", "p@example.com", LocalDate.of(2018, 6, 6)));
        developers.add(new Developer("dev2", "anl", "p@example.com", LocalDate.of(2018, 6, 6)));
        developers.add(new Developer("dev3", "tom", "p@example.com", LocalDate.of(2018, 6, 6)));

        return developers;
    }
}
