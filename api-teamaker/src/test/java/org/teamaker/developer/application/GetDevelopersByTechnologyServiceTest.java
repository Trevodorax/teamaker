package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyResponse;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.GetDevelopersByTechnologyDtoResponse;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyCommand;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyPort;
import org.teamaker.technology.domain.Technology;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

public class GetDevelopersByTechnologyServiceTest {
    private static FindDevelopersByTechnologyPort findDevelopersByTechnologyPortMock;
    private static GetDevelopersByTechnologyService getDevelopersByTechnologyService;
    private static LoadTechnologyPort loadTechnologyPortMock;

    @BeforeEach
    public void setUp() {
        findDevelopersByTechnologyPortMock = mock(FindDevelopersByTechnologyPort.class);
        loadTechnologyPortMock = mock(LoadTechnologyPort.class);
        getDevelopersByTechnologyService = new GetDevelopersByTechnologyService(findDevelopersByTechnologyPortMock, loadTechnologyPortMock);
    }

    @Test
    public void testGetDevelopersByTechnology() {
        String mockTechnologyId = "Technology Id";
        LocalDate mockDate = LocalDate.now();
        List<Developer> mockDevelopers = List.of(
                new Developer("867GVC876a", "Developer fullName", "Developer email", mockDate, null),
                new Developer("867GVC876b", "Developer fullName 2", "Developer email 2", mockDate, null)
        );
        Technology mockTechnology = new Technology(mockTechnologyId, "Technology name");
        GetDevelopersByTechnologyCommand command = new GetDevelopersByTechnologyCommand(mockTechnologyId);

        GetDevelopersByTechnologyDtoResponse expectedResponse = new GetDevelopersByTechnologyDtoResponse(mockTechnologyId, mockDevelopers.stream().map(Developer::toResponse).toList());
        when(loadTechnologyPortMock.loadTechnology(any())).thenReturn(mockTechnology);
        when(findDevelopersByTechnologyPortMock.findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class))).thenReturn(mockDevelopers);

        GetDevelopersByTechnologyResponse.Response result = getDevelopersByTechnologyService.getDevelopersByTechnology(command);

        verify(loadTechnologyPortMock).loadTechnology(any(LoadTechnologyCommand.class));
        verify(findDevelopersByTechnologyPortMock).findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class));

        assertInstanceOf(GetDevelopersByTechnologyResponse.SuccessResponse.class, result);
        assertEquals(expectedResponse, ((GetDevelopersByTechnologyResponse.SuccessResponse) result).developers());
        assertEquals(expectedResponse.developers().size(), ((GetDevelopersByTechnologyResponse.SuccessResponse) result).developers().developers().size());
    }

    @Test
    public void testGetDevelopersByTechnology_Empty() {
        String mockTechnologyId = "Technology Id";
        LocalDate mockDate = LocalDate.now();
        List<Developer> mockDevelopers = List.of();
        Technology mockTechnology = new Technology(mockTechnologyId, "Technology name");
        GetDevelopersByTechnologyCommand command = new GetDevelopersByTechnologyCommand(mockTechnologyId);

        GetDevelopersByTechnologyDtoResponse expectedResponse = new GetDevelopersByTechnologyDtoResponse(mockTechnologyId, List.of());
        when(loadTechnologyPortMock.loadTechnology(any())).thenReturn(mockTechnology);
        when(findDevelopersByTechnologyPortMock.findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class))).thenReturn(mockDevelopers);

        GetDevelopersByTechnologyResponse.Response result = getDevelopersByTechnologyService.getDevelopersByTechnology(command);

        verify(loadTechnologyPortMock).loadTechnology(any(LoadTechnologyCommand.class));
        verify(findDevelopersByTechnologyPortMock).findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class));

        assertInstanceOf(GetDevelopersByTechnologyResponse.SuccessResponse.class, result);
        assertEquals(expectedResponse, ((GetDevelopersByTechnologyResponse.SuccessResponse) result).developers());
        assertEquals(0, ((GetDevelopersByTechnologyResponse.SuccessResponse) result).developers().developers().size());
    }

    @Test
    public void testGetDevelopersByTechnology_Error() {
        String mockTechnologyId = "Invalid Technology Id";
        LocalDate mockDate = LocalDate.now();
        List<Developer> mockDevelopers = List.of();
        Technology mockTechnology = new Technology(mockTechnologyId, "Technology name");
        GetDevelopersByTechnologyCommand command = new GetDevelopersByTechnologyCommand(mockTechnologyId);

        when(loadTechnologyPortMock.loadTechnology(any())).thenThrow(new NoSuchElementException());
        when(findDevelopersByTechnologyPortMock.findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class))).thenThrow(new NoSuchElementException());

        GetDevelopersByTechnologyResponse.Response result = getDevelopersByTechnologyService.getDevelopersByTechnology(command);

        verify(loadTechnologyPortMock).loadTechnology(any(LoadTechnologyCommand.class));

        assertInstanceOf(GetDevelopersByTechnologyResponse.ErrorResponse.class, result);
        assertEquals("Technology not found", ((GetDevelopersByTechnologyResponse.ErrorResponse) result).message());
    }
}
