package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.time.LocalDate;

import org.teamaker.developer.application.port.in.HireDeveloperCommand;
import org.teamaker.developer.application.port.in.ResignDeveloperCommand;
import org.teamaker.developer.application.port.out.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.CreateDeveloperPort;
import org.teamaker.developer.application.port.out.UpdateDeveloperPort;
import org.teamaker.developer.application.port.out.UpdateDeveloperResignationDateCommand;
import org.teamaker.developer.domain.Developer;

public class ResignDeveloperServiceTest {
    private static UpdateDeveloperPort updateDeveloperPortMock;
    private static ResignDeveloperService resignDeveloperService;

    @BeforeAll
    public static void setUp() {
        updateDeveloperPortMock = mock(UpdateDeveloperPort.class);
        resignDeveloperService = new ResignDeveloperService(updateDeveloperPortMock);
    }

    @Test
    public void testResignDeveloper() {
        String mockEmail = "john.doe@teamaker.com";
        LocalDate mockResignationDate = LocalDate.of(2024, 6, 21);
        ResignDeveloperCommand command = new ResignDeveloperCommand(mockEmail, mockResignationDate);

        LocalDate expectedDate = mockResignationDate;
        when(updateDeveloperPortMock.resignDeveloper(any(UpdateDeveloperResignationDateCommand.class))).thenReturn(expectedDate);

        LocalDate result = resignDeveloperService.resignDeveloper(command);

        ArgumentCaptor<UpdateDeveloperResignationDateCommand> captor = ArgumentCaptor.forClass(UpdateDeveloperResignationDateCommand.class);
        verify(updateDeveloperPortMock).resignDeveloper(captor.capture());
        UpdateDeveloperResignationDateCommand capturedCommand = captor.getValue();

        assertEquals(mockEmail, capturedCommand.email());
        assertEquals(expectedDate, result);
    }
}
