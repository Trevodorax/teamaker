package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDevelopers.LoadDevelopersPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetDevelopersServiceTest {
    private static LoadDevelopersPort loadDevelopersPortMock;
    private static GetDevelopersService getDevelopersServiceMock;

    @BeforeAll
    public static void setUp() {
        loadDevelopersPortMock = mock(LoadDevelopersPort.class);
        getDevelopersServiceMock = new GetDevelopersService(loadDevelopersPortMock);
    }

    @Test
    public void testGetDevelopers() {
        LocalDate mockDate = LocalDate.now();
        List<Developer> mockDevelopers = List.of(
                new Developer("867GVC876a", "Developer fullName", "Developer email", mockDate),
                new Developer("867GVC876b", "Developer fullName 2", "Developer email 2", mockDate)
        );
        when(loadDevelopersPortMock.loadDevelopers()).thenReturn(mockDevelopers);
        List<DeveloperResponse> result = getDevelopersServiceMock.getDevelopers();

        verify(loadDevelopersPortMock).loadDevelopers();
        assertEquals(mockDevelopers.stream().map(Developer::toResponse).toList(), result);
    }
}
