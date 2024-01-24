package org.teamaker.technology.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.technology.application.port.in.getTechnologies.GetTechnologiesResponse;
import org.teamaker.technology.application.port.out.loadTechnologies.LoadTechnologiesPort;
import org.teamaker.technology.domain.Technology;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

public class GetTechnologiesServiceTest {
    private static LoadTechnologiesPort loadTechnologiesPortMock;
    private static GetTechnologiesService getTechnologiesService;

    @BeforeEach
    public void setUp() {
        loadTechnologiesPortMock = mock(LoadTechnologiesPort.class);
        getTechnologiesService = new GetTechnologiesService(loadTechnologiesPortMock);
    }

    @Test
    public void testGetTechnologies() {
        List<Technology> expectedTechnologies = List.of(
                new Technology("577c2860-b584-4d27-94d8-21b10c095aac", "Java"),
                new Technology("577c2860-b584-4d27-94d8-21b10c095aad", "JavaScript"),
                new Technology("577c2860-b584-4d27-94d8-21b10c095aae", "Python")
        );

        when(loadTechnologiesPortMock.loadTechnologies()).thenReturn(expectedTechnologies);

        GetTechnologiesResponse.Response result = getTechnologiesService.getTechnologies();

        verify(loadTechnologiesPortMock).loadTechnologies();
        assertInstanceOf(GetTechnologiesResponse.SuccessResponse.class, result);
        assertEquals(expectedTechnologies.stream().map(Technology::toResponse).toList(), ((GetTechnologiesResponse.SuccessResponse) result).technologies());

        assertEquals(expectedTechnologies.size(), ((GetTechnologiesResponse.SuccessResponse) result).technologies().size());
    }

    @Test
    public void testGetTechnologiesEmpty() {
        List<Technology> expectedTechnologies = List.of();

        when(loadTechnologiesPortMock.loadTechnologies()).thenReturn(expectedTechnologies);

        GetTechnologiesResponse.Response result = getTechnologiesService.getTechnologies();

        verify(loadTechnologiesPortMock).loadTechnologies();
        assertInstanceOf(GetTechnologiesResponse.SuccessResponse.class, result);
        assertEquals(List.of(), ((GetTechnologiesResponse.SuccessResponse) result).technologies());

        assertEquals(0, ((GetTechnologiesResponse.SuccessResponse) result).technologies().size());
    }
}
