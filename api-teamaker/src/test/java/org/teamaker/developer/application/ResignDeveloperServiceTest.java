package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperCommand;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperResponse;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ResignDeveloperServiceTest {

    private static String mockId = "867GVC876a";
    private static String mockName = "John DOE";
    private static String mockEmail = "john.doe@teamaker.com";
    private static LoadDeveloperPort loadDeveloperPortMock;
    private static SaveDeveloperPort saveDeveloperPortMock;
    private static SaveTeamPort saveTeamPortMock;
    private static ResignDeveloperService resignDeveloperService;

    @BeforeEach
    public void setUp() {
        loadDeveloperPortMock = mock(LoadDeveloperPort.class);
        saveDeveloperPortMock = mock(SaveDeveloperPort.class);
        saveTeamPortMock = mock(SaveTeamPort.class);
        resignDeveloperService = new ResignDeveloperService(loadDeveloperPortMock, saveDeveloperPortMock, saveTeamPortMock);
    }

    @Test
    public void testResignDeveloperSuccess() {
        LocalDate mockResignationDate = LocalDate.now();
        ResignDeveloperCommand command = new ResignDeveloperCommand(mockId);

        Developer mockDeveloper = new Developer(mockId, mockName, mockEmail, LocalDate.of(2023, 12, 7));
        assertNull(mockDeveloper.getResignationDate());
        Project mockProject = new Project(mockId,  "test project", "test", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED,
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 9, 1),
                new Team("test project", getDevelopersForValidTeam(), true));
        List<Project> mockProjectList = new ArrayList<>();
        mockProjectList.add(mockProject);
        mockDeveloper.setProjectList(List.of(mockProject));
        mockDeveloper.resign(mockProjectList);

        when(saveDeveloperPortMock.saveDeveloper(any(SaveDeveloperCommand.class))).thenReturn(mockDeveloper);
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);
        when(saveTeamPortMock.saveTeam(any(Team.class))).thenReturn(new Team("test project", getDevelopersForInValidTeam(), true));

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

        Developer mockDeveloper = new Developer("dev1", mockName, mockEmail, LocalDate.of(2023, 12, 7));
        Project mockProject = new Project(mockId,  "test project", "test", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED,
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 9, 1),
                new Team("test project", getDevelopersForInValidTeam(), true));

        List<Project> mockProjectList = new ArrayList<>();
        mockProjectList.add(mockProject);
        mockDeveloper.setProjectList(List.of(mockProject));
        mockDeveloper.resign(mockProjectList);
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenThrow(new NoSuchElementException("developer not found"));

        ResignDeveloperResponse.Response result = resignDeveloperService.resignDeveloper(command);

        verify(saveDeveloperPortMock, never()).saveDeveloper(any(SaveDeveloperCommand.class));
        assertInstanceOf(ResignDeveloperResponse.ErrorResponse.class, result);
        assertNull(mockDeveloper.getResignationDate());
    }
    @Test
    public void testResignDeveloperMultipleError() {
        ResignDeveloperCommand command = new ResignDeveloperCommand("dev1");

        Developer mockDeveloper = new Developer("dev1", mockName, mockEmail, LocalDate.of(2023, 12, 7));
        Project mockProject = new Project(mockId,  "test project", "test", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED,
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 9, 1),
                new Team("test project", getDevelopersForInValidTeam(), true));

        List<Project> mockProjectList = new ArrayList<>();
        mockProjectList.add(mockProject);
        mockDeveloper.setProjectList(List.of(mockProject));
        mockDeveloper.resign(mockProjectList);
        when(saveDeveloperPortMock.saveDeveloper(any(SaveDeveloperCommand.class))).thenReturn(mockDeveloper);
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);

        ResignDeveloperResponse.Response result = resignDeveloperService.resignDeveloper(command);

        assertInstanceOf(ResignDeveloperResponse.MultipleErrorsResponse.class, result);
        assertNull(mockDeveloper.getResignationDate());
    }

    private List<Developer> getDevelopersForValidTeam() {
        ArrayList<Developer> developers = new ArrayList<>();

        developers.add(new Developer("dev1", "Paul", "p@gmail.com", LocalDate.of(2019, 1, 1)));
        developers.add(new Developer("dev2", "Tom", "p@gmail.com", LocalDate.of(2019, 1, 1)));
        developers.add(new Developer("dev3", "Anaelle", "p@gmail.com", LocalDate.of(2019, 1, 1)));
        developers.add(new Developer(mockId, mockName, mockEmail, LocalDate.of(2019, 1, 1)));

        return developers;
    }

    private List<Developer> getDevelopersForInValidTeam() {
        ArrayList<Developer> developers = new ArrayList<>();

        developers.add(new Developer("dev1", "Paul", "p@gmail.com", LocalDate.of(2019, 1, 1)));
        developers.add(new Developer("dev2", "Tom", "p@gmail.com", LocalDate.of(2019, 1, 1)));
        developers.add(new Developer("dev3", "Anaelle", "p@gmail.com", LocalDate.of(2019, 1, 1)));

        return developers;
    }
}
