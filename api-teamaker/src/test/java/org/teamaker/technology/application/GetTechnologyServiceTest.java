package org.teamaker.technology.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.technology.application.port.in.getTechnology.GetTechnologyCommand;
import org.teamaker.technology.application.port.in.getTechnology.GetTechnologyResponse;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyCommand;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyPort;
import org.teamaker.technology.domain.Technology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

public class GetTechnologyServiceTest {
    private static LoadTechnologyPort loadTechnologyPortMock;
    private static GetTechnologyService getTechnologyService;
    private static String validId;

    @BeforeAll
    public static void setUp() {
        loadTechnologyPortMock = mock(LoadTechnologyPort.class);
        getTechnologyService = new GetTechnologyService(loadTechnologyPortMock);
        validId = "validId";
    }

    @Test
    public void testGetTechnology() {
        GetTechnologyCommand command = new GetTechnologyCommand(validId);
        Technology expectedTechnology = new Technology(validId, "Java");

        when(loadTechnologyPortMock.loadTechnology(any(LoadTechnologyCommand.class))).thenReturn(expectedTechnology);

        GetTechnologyResponse.Response result = getTechnologyService.getTechnology(command);

        ArgumentCaptor<LoadTechnologyCommand> captor = ArgumentCaptor.forClass(LoadTechnologyCommand.class);
        verify(loadTechnologyPortMock, times(2)).loadTechnology(captor.capture());
        LoadTechnologyCommand capturedCommand = captor.getValue();
        assertInstanceOf(GetTechnologyResponse.SuccessResponse.class, result);
        assertEquals(validId, capturedCommand.getTechnologyId());
        assertEquals(expectedTechnology.toResponse(), ((GetTechnologyResponse.SuccessResponse) result).technology());
    }

    @Test
    public void testGetTechnologyNotFound() {
        GetTechnologyCommand command = new GetTechnologyCommand(validId);
        when(loadTechnologyPortMock.loadTechnology(any(LoadTechnologyCommand.class))).thenThrow(new IllegalArgumentException("Technology not found"));

        GetTechnologyResponse.Response result = getTechnologyService.getTechnology(command);

        verify(loadTechnologyPortMock).loadTechnology(any(LoadTechnologyCommand.class));
        assertInstanceOf(GetTechnologyResponse.ErrorResponse.class, result);
        assertEquals("Technology not found", ((GetTechnologyResponse.ErrorResponse) result).message());
    }
}
