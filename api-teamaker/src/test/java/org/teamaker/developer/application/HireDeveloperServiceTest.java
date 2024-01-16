package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperCommand;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperPort;
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
        String mockDeveloperId = "867GVC876a";
        String mockName = "John DOE";
        String mockEmail = "john.doe@teamaker.com";
        HireDeveloperCommand command = new HireDeveloperCommand(mockName, mockEmail);

        Developer expectedDeveloper = new Developer(mockDeveloperId, mockName, mockEmail, LocalDate.now(), null);
        when(createDeveloperPortMock.createDeveloper(any(CreateDeveloperCommand.class))).thenReturn(expectedDeveloper);

        DeveloperResponse result = hireDeveloperService.hireDeveloper(command);

        ArgumentCaptor<CreateDeveloperCommand> captor = ArgumentCaptor.forClass(CreateDeveloperCommand.class);
        verify(createDeveloperPortMock).createDeveloper(captor.capture());
        CreateDeveloperCommand capturedCommand = captor.getValue();

        assertEquals(mockName, capturedCommand.getFullName());
        assertEquals(mockEmail, capturedCommand.getEmail());
        assertEquals(expectedDeveloper.toResponse(), result);
    }
}
