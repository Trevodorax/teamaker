package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperCommand;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperResponse;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ResignDeveloperServiceTest {

    private final static String mockId = "867GVC876a";
    private final static String mockName = "John DOE";
    private final static String mockEmail = "john.doe@teamaker.com";
    private static LoadDeveloperPort loadDeveloperPortMock;
    private static LoadDeveloperProjectsPort loadDeveloperProjectsPortMock;
    private static SaveDeveloperPort saveDeveloperPortMock;
    private static SaveTeamPort saveTeamPortMock;
    private static ResignDeveloperService resignDeveloperService;

    @BeforeEach
    public void setUp() {
        loadDeveloperPortMock = mock(LoadDeveloperPort.class);
        loadDeveloperProjectsPortMock = mock(LoadDeveloperProjectsPort.class);
        saveDeveloperPortMock = mock(SaveDeveloperPort.class);
        saveTeamPortMock = mock(SaveTeamPort.class);
        resignDeveloperService = new ResignDeveloperService(loadDeveloperPortMock, loadDeveloperProjectsPortMock, saveDeveloperPortMock, saveTeamPortMock);
    }

    @Test
    public void testResignDeveloperSuccess() {
        ResignDeveloperCommand command = new ResignDeveloperCommand(mockId);

        Developer mockDeveloper = new Developer(mockId, mockName, mockEmail, LocalDate.of(2023, 12, 7), null);
        assertNull(mockDeveloper.getResignationDate());
        Project mockProject = new Project(mockId,  "test project", "test", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED,
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 9, 1),
                new Team("test project", getDevelopersForValidTeam(), true), Map.of());
        mockDeveloper.setProjectList(List.of(mockProject));

        when(saveDeveloperPortMock.saveDeveloper(any(SaveDeveloperCommand.class))).thenReturn(mockDeveloper);
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);
        when(saveTeamPortMock.saveTeam(any(SaveTeamCommand.class))).thenReturn(new Team("test project", getDevelopersForInValidTeam(), true));

        ResignDeveloperResponse.Response result = resignDeveloperService.resignDeveloper(command);

        assertInstanceOf(ResignDeveloperResponse.SuccessResponse.class, result);
        assertEquals(mockDeveloper.getResignationDate(), ((ResignDeveloperResponse.SuccessResponse) result).resignationDate());
        for (Project project : mockDeveloper.getCurrentProjects()) {
            assertFalse(project.getTeam().getDevelopers().contains(mockDeveloper));
        }
    }
    @Test
    public void testResignDeveloperError() {
        ResignDeveloperCommand command = new ResignDeveloperCommand("dev1");

        Developer mockDeveloper = new Developer("dev1", mockName, mockEmail, LocalDate.of(2023, 12, 7), null);
        Project mockProject = new Project(mockId,  "test project", "test", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED,
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 9, 1),
                new Team("test project", getDevelopersForInValidTeam(), true), Map.of());

        mockDeveloper.setProjectList(List.of(mockProject));
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenThrow(new NoSuchElementException("developer not found"));
        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(List.of(mockProject));

        ResignDeveloperResponse.Response result = resignDeveloperService.resignDeveloper(command);

        verify(saveDeveloperPortMock, never()).saveDeveloper(any(SaveDeveloperCommand.class));
        assertInstanceOf(ResignDeveloperResponse.ErrorResponse.class, result);
        assertNull(mockDeveloper.getResignationDate());
    }
    @Test
    public void testResignDeveloperMultipleError() {
        ResignDeveloperCommand command = new ResignDeveloperCommand("dev1");

        Developer mockDeveloper = mock(Developer.class);
        when(mockDeveloper.getDeveloperId()).thenReturn("dev1");
        when(mockDeveloper.getFullName()).thenReturn(mockName);
        when(mockDeveloper.getEmail()).thenReturn(mockEmail);
        when(mockDeveloper.getHiringDate()).thenReturn(LocalDate.of(2023, 12, 7));
        when(mockDeveloper.getResignationDate()).thenReturn(null);

        Project mockProject = new Project(mockId,  "test project", "test", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED,
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 9, 1),
                new Team("test project", getDevelopersForInValidTeam(), true), Map.of());

        when(mockDeveloper.getProjectList()).thenReturn(List.of(mockProject));
        when(mockDeveloper.resign(any())).thenReturn(List.of("error1", "error2")); // Mock resign method to return a list of errors

        when(saveDeveloperPortMock.saveDeveloper(any(SaveDeveloperCommand.class))).thenReturn(mockDeveloper);
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);
        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(List.of(mockProject));

        ResignDeveloperResponse.Response result = resignDeveloperService.resignDeveloper(command);

        assertInstanceOf(ResignDeveloperResponse.MultipleErrorsResponse.class, result);
        assertNull(mockDeveloper.getResignationDate());
    }

    private List<Developer> getDevelopersForValidTeam() {
        ArrayList<Developer> developers = new ArrayList<>();

        developers.add(new Developer("dev1", "Paul", "p@gmail.com", LocalDate.of(2019, 1, 1), null));
        developers.add(new Developer("dev2", "Tom", "p@gmail.com", LocalDate.of(2019, 1, 1), null));
        developers.add(new Developer("dev3", "Anaelle", "p@gmail.com", LocalDate.of(2019, 1, 1), null));
        developers.add(new Developer(mockId, mockName, mockEmail, LocalDate.of(2019, 1, 1), null));

        return developers;
    }

    private List<Developer> getDevelopersForInValidTeam() {
        ArrayList<Developer> developers = new ArrayList<>();

        developers.add(new Developer("dev1", "Paul", "p@gmail.com", LocalDate.of(2019, 1, 1), null));
        developers.add(new Developer("dev2", "Tom", "p@gmail.com", LocalDate.of(2019, 1, 1), null));
        developers.add(new Developer("dev3", "Anaelle", "p@gmail.com", LocalDate.of(2019, 1, 1), null));

        return developers;
    }
}
