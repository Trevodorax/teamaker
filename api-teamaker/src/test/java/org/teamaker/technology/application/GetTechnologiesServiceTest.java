package org.teamaker.technology.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.technology.application.port.out.LoadTechnologiesPort;
import org.teamaker.technology.domain.Technology;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetTechnologiesServiceTest {
    private static LoadTechnologiesPort loadTechnologiesPortMock;
    private static GetTechnologiesService getTechnologiesService;

    @BeforeAll
    public static void setUp() {
        loadTechnologiesPortMock = mock(LoadTechnologiesPort.class);
        getTechnologiesService = new GetTechnologiesService(loadTechnologiesPortMock);
    }

    @Test
    public void testGetTechnologies() {
        List<Technology> expectedTechnologies = List.of(
                new Technology("Java"),
                new Technology("Typescript"),
                new Technology("PHP")
        );

        when(loadTechnologiesPortMock.loadTechnologies()).thenReturn(expectedTechnologies);

        List<Technology> result = getTechnologiesService.getTechnologies();

        verify(loadTechnologiesPortMock).loadTechnologies();
        assertEquals(expectedTechnologies, result);
    }

}
