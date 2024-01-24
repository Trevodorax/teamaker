package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperCommand;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperResponse;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetDeveloperServiceTest {
    private static LoadDeveloperPort loadDeveloperPortMock;
    private static GetDeveloperService getDeveloperServiceMock;

    @BeforeEach
    public void setUp() {
        loadDeveloperPortMock = mock(LoadDeveloperPort.class);
        getDeveloperServiceMock = new GetDeveloperService(loadDeveloperPortMock);
    }

    @Test
    public void testGetDeveloperSuccess() {
        String mockId = "test";
        String mockName = "John DOE";
        String mockEmail = "john@doe.fr";
        LocalDate mockHiringDate = LocalDate.now();
        GetDeveloperCommand command = new GetDeveloperCommand(mockId);

        Developer expectedDeveloper = new Developer(mockId, mockName, mockEmail, mockHiringDate);
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(expectedDeveloper);

        GetDeveloperResponse.Response result = getDeveloperServiceMock.getDeveloper(command);
        ArgumentCaptor<LoadDeveloperCommand> captor = ArgumentCaptor.forClass(LoadDeveloperCommand.class);
        verify(loadDeveloperPortMock).loadDeveloper(captor.capture());
        LoadDeveloperCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getDeveloperId());
        assertInstanceOf(GetDeveloperResponse.SuccessResponse.class, result);
        assertEquals(expectedDeveloper.toResponse(), ((GetDeveloperResponse.SuccessResponse) result).developer());
    }

    @Test
    public void testGetDeveloperNotFoundError() {
        String mockId = "test";
        GetDeveloperCommand command = new GetDeveloperCommand(mockId);
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenThrow(new NoSuchElementException("developer not found"));

        GetDeveloperResponse.Response result = getDeveloperServiceMock.getDeveloper(command);

        ArgumentCaptor<LoadDeveloperCommand> captor = ArgumentCaptor.forClass(LoadDeveloperCommand.class);
        verify(loadDeveloperPortMock).loadDeveloper(captor.capture());
        LoadDeveloperCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getDeveloperId());
        assertEquals(result, new GetDeveloperResponse.ErrorResponse("developer not found"));
    }

    @Test
    public void testGetDeveloperIllegalError() {
        String mockId = "test";
        GetDeveloperCommand command = new GetDeveloperCommand(mockId);
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenThrow(new IllegalArgumentException("invalid developer id"));

        GetDeveloperResponse.Response result = getDeveloperServiceMock.getDeveloper(command);

        ArgumentCaptor<LoadDeveloperCommand> captor = ArgumentCaptor.forClass(LoadDeveloperCommand.class);
        verify(loadDeveloperPortMock).loadDeveloper(captor.capture());
        LoadDeveloperCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getDeveloperId());
        assertEquals(result, new GetDeveloperResponse.ErrorResponse("invalid developer id"));
    }
}
