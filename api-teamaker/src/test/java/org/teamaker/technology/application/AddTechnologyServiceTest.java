package org.teamaker.technology.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentCaptor;

import org.teamaker.technology.application.port.in.AddTechnologyCommand;
import org.teamaker.technology.application.port.out.CreateTechnologyCommand;
import org.teamaker.technology.application.port.out.CreateTechnologyPort;
import org.teamaker.technology.domain.Technology;

public class AddTechnologyServiceTest {
    private static CreateTechnologyPort createTechnologyPortMock;
    private static AddTechnologyService addTechnologyService;

    @BeforeAll
    public static void setUp() {
        createTechnologyPortMock = mock(CreateTechnologyPort.class);
        addTechnologyService = new AddTechnologyService(createTechnologyPortMock);
    }

    @Test
    public void testAddTechnology() {
        String mockGuid = "Tech Guid";
        String mockName = "Tech Name";
        AddTechnologyCommand command = new AddTechnologyCommand(mockName);

        Technology expectedTechnology = new Technology(mockGuid, mockName);
        when(createTechnologyPortMock.createTechnology(any(CreateTechnologyCommand.class))).thenReturn(expectedTechnology);

        Technology result = addTechnologyService.addTechnology(command);

        ArgumentCaptor<CreateTechnologyCommand> captor = ArgumentCaptor.forClass(CreateTechnologyCommand.class);
        verify(createTechnologyPortMock).createTechnology(captor.capture());
        CreateTechnologyCommand capturedCommand = captor.getValue();

        assertEquals(mockName, capturedCommand.getName());
        assertEquals(expectedTechnology, result);
    }
}
