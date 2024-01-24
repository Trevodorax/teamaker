package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperCommand;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperResponse;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HireDeveloperServiceTest {
    private static CreateDeveloperPort createDeveloperPortMock;
    private static HireDeveloperService hireDeveloperService;

    @BeforeAll
    public static void setUp() {
        createDeveloperPortMock = mock(CreateDeveloperPort.class);
        hireDeveloperService = new HireDeveloperService(createDeveloperPortMock);
    }

    @Test
    public void testHireDeveloperSuccess() {
        String mockDeveloperId = "867GVC876a";
        String mockName = "John DOE";
        String mockEmail = "john.doe@teamaker.com";
        HireDeveloperCommand command = new HireDeveloperCommand(mockName, mockEmail);

        Developer mockDeveloper = new Developer(mockDeveloperId, mockName, mockEmail, LocalDate.now());
        when(createDeveloperPortMock.createDeveloper(any(CreateDeveloperCommand.class))).thenReturn(mockDeveloper);

        HireDeveloperResponse.Response result = hireDeveloperService.hireDeveloper(command);

        ArgumentCaptor<CreateDeveloperCommand> captor = ArgumentCaptor.forClass(CreateDeveloperCommand.class);
        verify(createDeveloperPortMock).createDeveloper(captor.capture());
        CreateDeveloperCommand capturedCommand = captor.getValue();

        assertInstanceOf(HireDeveloperResponse.SuccessResponse.class, result);
        assertEquals(mockName, capturedCommand.getFullName());
        assertEquals(mockEmail, capturedCommand.getEmail());
        assertEquals(mockDeveloper.toResponse(), ((HireDeveloperResponse.SuccessResponse) result).developer());
    }

    @Test
    public void testHireDeveloperThrows() {
        String mockName = "John DOE";
        String mockEmail = "john.doe@teamaker.com";
        HireDeveloperCommand command = new HireDeveloperCommand(mockName, mockEmail);

        when(createDeveloperPortMock.createDeveloper(any(CreateDeveloperCommand.class))).thenThrow(new IllegalArgumentException("email already taken"));
        HireDeveloperResponse.Response result = hireDeveloperService.hireDeveloper(command);

        assertEquals(result, new HireDeveloperResponse.ErrorResponse("email already taken"));
    }
}
