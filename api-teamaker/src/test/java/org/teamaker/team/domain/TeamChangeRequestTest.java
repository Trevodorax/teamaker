package org.teamaker.team.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.domain.Project;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeamChangeRequestTest {

        @Mock
        private Project fromProject;
        @Mock
        private Project toProject;

        private TeamChangeRequest teamChangeRequest;
        private final String developerId = "dev123";
        private final Developer developer = new Developer(developerId, "Paul GAUDEAUX", "p@g.c", LocalDate.of(2018, 6, 6));
        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);
            fromProject = mock(Project.class);
            toProject = mock(Project.class);

            teamChangeRequest = new TeamChangeRequest("id", developerId, fromProject.getProjectId(), toProject.getProjectId(), TeamRequestStatus.PENDING,  LocalDate.of(2024, 1, 1));
        }

        @Test
        public void testSwitchStatusRefused() {
            List<String> result = teamChangeRequest.treat(TreatTeamStatus.DENIED, fromProject, toProject, developer);

            assertEquals(TeamRequestStatus.REFUSED, teamChangeRequest.getStatus());
            assertNull(result);
        }

        @Test
        public void testSwitchStatusAcceptedNoProblems() {
            when(fromProject.removeDeveloperById(anyString(), anyBoolean())).thenReturn(null);
            when(toProject.addDeveloper(any(Developer.class), anyBoolean())).thenReturn(null);

            List<String> result = teamChangeRequest.treat(TreatTeamStatus.APPROVED, fromProject, toProject, developer);

            assertEquals(TeamRequestStatus.ACCEPTED, teamChangeRequest.getStatus());
            assertNull(result);
        }

    @Test
    public void testSwitchStatusAccepted_RemovalError() {
        when(fromProject.removeDeveloperById(anyString(), anyBoolean())).thenReturn(List.of("error"));
        when(toProject.addDeveloper(any(Developer.class), anyBoolean())).thenReturn(null);

        List<String> result = teamChangeRequest.treat(TreatTeamStatus.APPROVED, fromProject, toProject, developer);

        assertEquals(TeamRequestStatus.PENDING, teamChangeRequest.getStatus());
        assertEquals(List.of("error"), result);
    }

    @Test
    public void testSwitchStatusAccepted_AddingError() {
        when(fromProject.removeDeveloperById(anyString(), anyBoolean())).thenReturn(null);
        when(toProject.addDeveloper(any(Developer.class), anyBoolean())).thenReturn(List.of("error"));

        List<String> result = teamChangeRequest.treat(TreatTeamStatus.APPROVED, fromProject, toProject, developer);

        assertEquals(TeamRequestStatus.PENDING, teamChangeRequest.getStatus());
        assertEquals(List.of("error"), result);
    }
}