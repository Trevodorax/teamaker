package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoCommand;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoResponse;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateDeveloperInfoServiceTest {
    private static LoadDeveloperPort loadDeveloperPortMock;
    private static SaveDeveloperPort saveDeveloperPortMock;
    private static UpdateDeveloperInfoService updateDeveloperInfoServiceMock;

    @BeforeEach
    public void setUp() {
        loadDeveloperPortMock = mock(LoadDeveloperPort.class);
        saveDeveloperPortMock = mock(SaveDeveloperPort.class);
        updateDeveloperInfoServiceMock = new UpdateDeveloperInfoService(loadDeveloperPortMock, saveDeveloperPortMock);
    }

    @Test
    public void testUpdateDeveloperInfoSuccess_AllFields() {
        String mockDeveloperId = "mockDeveloperId";
        String mockFullName = "mockFullName";
        String mockEmail = "mock@email.com";

        String mockNewFullName = "mockNewFullName";
        String mockNewEmail = "new.mock@email.com";
        Developer expectedDeveloper = new Developer(mockDeveloperId, mockNewFullName, mockNewEmail, LocalDate.now(), null);

        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(
                new Developer(mockDeveloperId, mockFullName, mockEmail, LocalDate.now(), null)
        );
        when(saveDeveloperPortMock.saveDeveloper(any(SaveDeveloperCommand.class))).thenReturn(expectedDeveloper);

        UpdateDeveloperInfoResponse.Response response = updateDeveloperInfoServiceMock.updateDeveloperInfo(
                new UpdateDeveloperInfoCommand(mockDeveloperId, mockNewFullName, mockNewEmail)
        );

        assertInstanceOf(UpdateDeveloperInfoResponse.SuccessResponse.class, response);
        assertEquals(expectedDeveloper.toResponse(), ((UpdateDeveloperInfoResponse.SuccessResponse) response).developer());
    }

    @Test
    public void testUpdateDeveloperInfoSuccess_FullNameOnly() {
        String mockDeveloperId = "mockDeveloperId";
        String mockFullName = "mockFullName";
        String mockEmail = "mock@email.com";

        String mockNewFullName = "mockNewFullName";
        Developer expectedDeveloper = new Developer(mockDeveloperId, mockNewFullName, mockEmail, LocalDate.now(), null);

        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(
                new Developer(mockDeveloperId, mockFullName, mockEmail, LocalDate.now(), null)
        );
        when(saveDeveloperPortMock.saveDeveloper(any(SaveDeveloperCommand.class))).thenReturn(expectedDeveloper);

        UpdateDeveloperInfoResponse.Response response = updateDeveloperInfoServiceMock.updateDeveloperInfo(
                new UpdateDeveloperInfoCommand(mockDeveloperId, mockNewFullName, null)
        );

        assertInstanceOf(UpdateDeveloperInfoResponse.SuccessResponse.class, response);
        assertEquals(expectedDeveloper.toResponse(), ((UpdateDeveloperInfoResponse.SuccessResponse) response).developer());
    }

    @Test
    public void testUpdateDeveloperInfoSuccess_EmailOnly() {
        String mockDeveloperId = "mockDeveloperId";
        String mockFullName = "mockFullName";
        String mockEmail = "mock@email.com";

        String mockNewEmail = "john@doe.fr";
        Developer expectedDeveloper = new Developer(mockDeveloperId, mockFullName, mockNewEmail, LocalDate.now(), null);

        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(
                new Developer(mockDeveloperId, mockFullName, mockEmail, LocalDate.now(), null)
        );
        when(saveDeveloperPortMock.saveDeveloper(any(SaveDeveloperCommand.class))).thenReturn(expectedDeveloper);

        UpdateDeveloperInfoResponse.Response response = updateDeveloperInfoServiceMock.updateDeveloperInfo(
                new UpdateDeveloperInfoCommand(mockDeveloperId, null, mockNewEmail)
        );

        assertInstanceOf(UpdateDeveloperInfoResponse.SuccessResponse.class, response);
        assertEquals(expectedDeveloper.toResponse(), ((UpdateDeveloperInfoResponse.SuccessResponse) response).developer());
    }

    @Test
    public void testUpdateDeveloperInfoError() {
        String mockDeveloperId = "mockDeveloperId";
        String mockNewFullName = "mockNewFullName";
        String mockNewEmail = "new.mock@email.com";

        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenThrow(new NoSuchElementException("developer not found"));

        UpdateDeveloperInfoResponse.Response response = updateDeveloperInfoServiceMock.updateDeveloperInfo(
                new UpdateDeveloperInfoCommand(mockDeveloperId, mockNewFullName, mockNewEmail)
        );

        assertInstanceOf(UpdateDeveloperInfoResponse.ErrorResponse.class, response);
        assertEquals(new UpdateDeveloperInfoResponse.ErrorResponse("developer not found"), response);
    }
}
