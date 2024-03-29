package org.teamaker.project.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.domain.dto.ProjectResponse;
import org.teamaker.team.domain.Team;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {

    private static Project project;
    private static Team mockTeam;

    @BeforeAll
    public static void setUp() {
        mockTeam = new Team("projectId", new ArrayList<>(), false);
        project = new Project("projectId", "name", "description", ProjectPriority.BEST_EFFORT, ProjectStatus.PENDING, LocalDate.now(), LocalDate.now().plusDays(5), mockTeam, Map.of());
    }

    @Test
    public void postpone_shouldUpdateDates() {
        LocalDate newStartDate = LocalDate.now().plusDays(5);
        LocalDate newEndDate = LocalDate.now().plusDays(40);
        project.postpone(newStartDate, newEndDate);
        assertEquals(newStartDate, project.getStartDate());
        assertEquals(newEndDate, project.getEndDate());
    }

    @Test
    public void postpone_shouldUpdateStartDateAndShiftEndDate() {
        LocalDate newStartDate = LocalDate.now().plusDays(5);
        LocalDate initialEndDate = project.getEndDate();
        int daysToPostpone = newStartDate.getDayOfMonth() - project.getStartDate().getDayOfMonth();
        project.postpone(newStartDate);
        assertEquals(newStartDate, project.getStartDate());
        assertEquals(initialEndDate.plusDays(daysToPostpone), project.getEndDate());
    }

    @Test
    public void treat() {
        ProjectStatus newStatus = ProjectStatus.ACCEPTED;
        project.treat(newStatus);
        assertEquals(newStatus, project.getStatus());
    }

    @Test
    public void projectProgress_WhenPending_ShouldBeNotStarted() {
        Project project = new Project("projectId", "name", "description", ProjectPriority.BEST_EFFORT, ProjectStatus.PENDING, LocalDate.now(), LocalDate.now().plusDays(10), mockTeam, Map.of());
        assertEquals(ProjectProgress.NOT_STARTED, project.projectProgress());
    }

    @Test
    public void projectProgress_WhenAcceptedAndBeforeStartDate_ShouldBeNotStarted() {
        LocalDate futureDate = LocalDate.now().plusDays(5);
        Project project = new Project("projectId", "name", "description", ProjectPriority.BEST_EFFORT, ProjectStatus.ACCEPTED, futureDate, futureDate.plusDays(10), mockTeam, Map.of());
        assertEquals(ProjectProgress.NOT_STARTED, project.projectProgress());
    }

    @Test
    public void projectProgress_WhenAcceptedAndAfterEndDate_ShouldBeDone() {
        LocalDate pastDate = LocalDate.now().minusDays(10);
        Project project = new Project("projectId", "name", "description", ProjectPriority.BEST_EFFORT, ProjectStatus.ACCEPTED, pastDate, pastDate.plusDays(5), mockTeam, Map.of());
        assertEquals(ProjectProgress.DONE, project.projectProgress());
    }

    @Test
    public void projectProgress_WhenAcceptedAndBetweenDates_ShouldBeInProgress() {
        LocalDate startDate = LocalDate.now().minusDays(5);
        LocalDate endDate = LocalDate.now().plusDays(5);
        Project project = new Project("projectId", "name", "description", ProjectPriority.BEST_EFFORT, ProjectStatus.ACCEPTED, startDate, endDate, mockTeam, Map.of());
        assertEquals(ProjectProgress.IN_PROGRESS, project.projectProgress());
    }

    @Test
    public void projectProgress_WhenNotAccepted_ShouldBeAborted() {
        Project projectRefused = new Project("projectId", "name", "description", ProjectPriority.BEST_EFFORT, ProjectStatus.REFUSED, LocalDate.now(), LocalDate.now().plusDays(10), mockTeam, Map.of());
        assertEquals(ProjectProgress.ABORTED, projectRefused.projectProgress());

        Project projectCancelled = new Project("projectId", "name", "description", ProjectPriority.BEST_EFFORT, ProjectStatus.CANCELLED, LocalDate.now(), LocalDate.now().plusDays(10), mockTeam, Map.of());
        assertEquals(ProjectProgress.ABORTED, projectCancelled.projectProgress());
    }

    @Test
    public void updateInfoWithAllFields() {
        String newName = "newName";
        String newDescription = "newDescription";
        ProjectPriority newPriority = ProjectPriority.BEST_EFFORT;
        project.updateInfo(newName, newDescription, newPriority);
        assertEquals(newName, project.getName());
        assertEquals(newDescription, project.getDescription());
        assertEquals(newPriority, project.getPriority());
    }

    @Test
    public void updateInfoWithNullName() {
        String actualName = project.getName();
        String newDescription = "newDescription";
        ProjectPriority newPriority = ProjectPriority.BEST_EFFORT;
        project.updateInfo(null, newDescription, newPriority);
        assertEquals(actualName, project.getName());
        assertEquals(newDescription, project.getDescription());
        assertEquals(newPriority, project.getPriority());
    }

    @Test
    public void updateInfoWithNullDescription() {
        String newName = "newName";
        String actualDescription = project.getDescription();
        ProjectPriority newPriority = ProjectPriority.BEST_EFFORT;
        project.updateInfo(newName, null, newPriority);
        assertEquals(newName, project.getName());
        assertEquals(actualDescription, project.getDescription());
        assertEquals(newPriority, project.getPriority());
    }

    @Test
    public void updateInfoWithNullPriority() {
        String newName = "newName";
        String newDescription = "newDescription";
        ProjectPriority actualPriority = project.getPriority();
        project.updateInfo(newName, newDescription, null);
        assertEquals(newName, project.getName());
        assertEquals(newDescription, project.getDescription());
        assertEquals(actualPriority, project.getPriority());
    }

    @Test
    public void toResponse() {
        ProjectResponse projectResponse = project.toResponse();
        assertEquals(project.getProjectId(), projectResponse.projectId());
        assertEquals(project.getName(), projectResponse.name());
        assertEquals(project.getDescription(), projectResponse.description());
        assertEquals(project.getStatus(), projectResponse.status());
        assertEquals(project.getPriority(), projectResponse.priority());
        assertEquals(project.getStartDate(), projectResponse.startDate());
        assertEquals(project.getEndDate(), projectResponse.endDate());
        assertEquals(project.projectProgress(), projectResponse.progress());
    }

    @Test
    public void isOverlapping_True() {
        Project testProject = new Project("id", "name", "description", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 10),
                mockTeam, Map.of()
        );

        Project overlappingProject = new Project("id2", "name2", "description2", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED,
                LocalDate.of(2024, 1, 5),
                LocalDate.of(2024, 1, 15),
                mockTeam, Map.of()
        );

        Project overlappingProject2 = new Project("id3", "name3", "description3", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED,
                LocalDate.of(2024, 1, 7),
                LocalDate.of(2024, 1, 12),
                mockTeam, Map.of()
        );

        boolean result1 = testProject.isOverlapping(overlappingProject);
        boolean result2 = testProject.isOverlapping(overlappingProject2);

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void isOverlapping_False() {
        Project testProject = new Project("id", "name", "description", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 10),
                mockTeam, Map.of()
        );

        Project nonOverlappingProject = new Project("id2", "name2", "description2", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2024, 1, 11),
                LocalDate.of(2024, 1, 15),
                mockTeam, Map.of()
        );

        Project nonOverlappingProject2 = new Project("id3", "name3",  "description3", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2023, 12, 25),
                LocalDate.of(2023, 12, 31),
                mockTeam, Map.of()
        );

        boolean result1 = testProject.isOverlapping(nonOverlappingProject);
        boolean result2 = testProject.isOverlapping(nonOverlappingProject2);

        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void testGetDuration() {
        Project testProject = new Project("id", "name", "description", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 12, 31),
                mockTeam, Map.of()
        );

        Duration duration = testProject.getDuration();

        assertEquals(364, duration.toDays());
    }
}