package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.teamaker.developer.application.port.in.HireDeveloperCommand;
import org.teamaker.developer.application.port.out.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.CreateDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.time.LocalDate;

public class HireDeveloperServiceTest {
    private static CreateDeveloperPort createDeveloperPortMock;
    private static HireDeveloperService hireDeveloperService;

    @BeforeAll
    public static void setUp() {
        createDeveloperPortMock = mock(CreateDeveloperPort.class);
        hireDeveloperService = new HireDeveloperService(createDeveloperPortMock);
    }

    @Test
    public void testHireDeveloper() {
        String mockName = "John DOE";
        String mockEmail = "john.doe@teamaker.com";
        HireDeveloperCommand command = new HireDeveloperCommand(mockName, mockEmail);

        Developer expectedDeveloper = new Developer(mockName, mockEmail, LocalDate.now(), null);
        when(createDeveloperPortMock.createDeveloper(any(CreateDeveloperCommand.class))).thenReturn(expectedDeveloper);

        Developer result = hireDeveloperService.hireDeveloper(command);

        ArgumentCaptor<CreateDeveloperCommand> captor = ArgumentCaptor.forClass(CreateDeveloperCommand.class);
        verify(createDeveloperPortMock).createDeveloper(captor.capture());
        CreateDeveloperCommand capturedCommand = captor.getValue();

        assertEquals(mockName, capturedCommand.fullName());
        assertEquals(mockEmail, capturedCommand.email());
        assertEquals(expectedDeveloper, result);
    }
}
