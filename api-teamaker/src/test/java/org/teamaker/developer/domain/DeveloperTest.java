package org.teamaker.developer.domain;

import org.junit.jupiter.api.Test;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {
    private static List<Project> getMockProjects() {
        List<Project> projectList = new ArrayList<>();
        Project project1 = new Project("proj1", "Project 1", "Description 1", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10)
        );
        Project project2 = new Project("proj2", "Project 2", "Description 2", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 2, 15)
        );

        projectList.add(project1);
        projectList.add(project2);
        return projectList;
    }

    @Test
    public void testCheckAvailability_True() {
        Developer developer = new Developer("dev123", "John Doe", "johndoe@example.com", LocalDate.of(2023, 1, 1));

        List<Project> projectList = getMockProjects();

        developer.setProjectList(projectList);

        Project projectToCheck = new Project("proj3", "Project 3", "Description 3", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 20)
        );

        boolean isAvailable = developer.checkAvailability(projectToCheck);

        assertTrue(isAvailable);
    }

    @Test
    public void testCheckAvailability_False() {
        Developer developer = new Developer("dev123", "John Doe", "johndoe@example.com", LocalDate.of(2023, 1, 1));

        List<Project> projectList = getMockProjects();

        developer.setProjectList(projectList);

        Project projectToCheck = new Project("proj3", "Project 3", "Description 3", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2023, 1, 5),
                LocalDate.of(2023, 1, 20)
        );

        boolean isAvailable = developer.checkAvailability(projectToCheck);

        assertFalse(isAvailable);
    }

    @Test
    public void testCheckAvailability_NoProjects() {
        Developer developer = new Developer("dev123", "John Doe", "johndoe@example.com", LocalDate.of(2023, 1, 1));

        Project projectToCheck = new Project("proj3", "Project 3", "Description 3", ProjectPriority.NORMAL, ProjectStatus.PENDING,
                LocalDate.of(2023, 1, 5),
                LocalDate.of(2023, 1, 20)
        );

        assertThrows(IllegalStateException.class,
                () -> developer.checkAvailability(projectToCheck));
    }

    @Test
    public void testAddProject_Success() {
        Developer developer = new Developer("dev123", "John Doe", "johndoe@example.com", LocalDate.of(2023, 1, 1));
        List<Project> projectList = new ArrayList<>();
        developer.setProjectList(projectList);
        Project addedProject = new Project("proj1", "Project 1", "Description 1", ProjectPriority.NORMAL, ProjectStatus.PENDING, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 10));
        developer.addProject(addedProject);
        assertTrue(developer.getProjectList().contains(addedProject));
    }

    @Test()
    public void testAddProject_Failure() {
        Developer developer = new Developer("dev123", "John Doe", "johndoe@example.com", LocalDate.of(2023, 1, 1));
        Project addedProject = new Project("proj1", "Project 1", "Description 1", ProjectPriority.NORMAL, ProjectStatus.PENDING, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 10));
        assertThrows(IllegalStateException.class,
                () -> developer.addProject(addedProject));
    }
}