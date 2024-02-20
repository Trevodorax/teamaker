package org.teamaker.developer.application;

import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsCommand;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsResponse;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
public class GetDeveloperProjectsServiceTest {
    private static class LoadDeveloperProjectsMockAdapter implements LoadDeveloperProjectsPort {
        List<Project> projectsToReturn;

        public LoadDeveloperProjectsMockAdapter(List<Project> projectsToReturn) {
            this.projectsToReturn = projectsToReturn;
        }

        @Override
        public List<Project> loadDeveloperProjects(LoadDeveloperProjectsCommand command) throws NoSuchElementException {
            if(this.projectsToReturn == null) {
                throw new NoSuchElementException("developer not found");
            }
            return this.projectsToReturn;
        }
    }

    @Test
    public void testGetDeveloperProjectsSuccess() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        String mockName = "Project Name";
        String mockDescription = "Project Description";
        ProjectPriority mockPriority = ProjectPriority.CRITICAL;
        ProjectStatus mockStatus = ProjectStatus.PENDING;
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);
        GetDeveloperProjectsCommand command = new GetDeveloperProjectsCommand(mockId);

        List<Project> expectedProjectsList = List.of(new Project(mockId, mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate, new Team("id", new ArrayList<>(), false), Map.of()));
        LoadDeveloperProjectsMockAdapter loadDeveloperProjectsPortMock =
                new LoadDeveloperProjectsMockAdapter(expectedProjectsList);
        GetDeveloperProjectsService getDeveloperProjectsService = new GetDeveloperProjectsService(loadDeveloperProjectsPortMock);

        GetDeveloperProjectsResponse.Response result = getDeveloperProjectsService.getDeveloperProjects(command);

        assertInstanceOf(GetDeveloperProjectsResponse.SuccessResponse.class, result);
        assertEquals(new GetDeveloperProjectsResponse.SuccessResponse(expectedProjectsList.stream().map(Project::toResponse).toList()), result);
    }

    @Test
    public void testGetDeveloperProjectsError() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        GetDeveloperProjectsCommand command = new GetDeveloperProjectsCommand(mockId);

        LoadDeveloperProjectsMockAdapter loadDeveloperProjectsPortMock =
                new LoadDeveloperProjectsMockAdapter(null);
        GetDeveloperProjectsService getDeveloperProjectsService = new GetDeveloperProjectsService(loadDeveloperProjectsPortMock);

        GetDeveloperProjectsResponse.Response result = getDeveloperProjectsService.getDeveloperProjects(command);

        assertInstanceOf(GetDeveloperProjectsResponse.ErrorResponse.class, result);
        assertEquals(new GetDeveloperProjectsResponse.ErrorResponse("developer not found"), result);
    }
}
