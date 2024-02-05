package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyResponse;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.GetDevelopersByTechnologyDtoResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

public class GetDevelopersByTechnologyServiceTest {
    private static FindDevelopersByTechnologyPort findDevelopersByTechnologyPortMock;
    private static GetDevelopersByTechnologyService getDevelopersByTechnologyService;

    @BeforeEach
    public void setUp() {
        findDevelopersByTechnologyPortMock = mock(FindDevelopersByTechnologyPort.class);
        getDevelopersByTechnologyService = new GetDevelopersByTechnologyService(findDevelopersByTechnologyPortMock);
    }

    @Test
    public void testGetDevelopersByTechnology() {
        String mockTechnologyId = "Technology Id";
        LocalDate mockDate = LocalDate.now();
        List<Developer> mockDevelopers = List.of(
                new Developer("867GVC876a", "Developer fullName", "Developer email", mockDate, null),
                new Developer("867GVC876b", "Developer fullName 2", "Developer email 2", mockDate, null)
        );
        GetDevelopersByTechnologyCommand command = new GetDevelopersByTechnologyCommand(mockTechnologyId);

        GetDevelopersByTechnologyDtoResponse expectedResponse = new GetDevelopersByTechnologyDtoResponse(mockTechnologyId, mockDevelopers.stream().map(Developer::toResponse).toList());
        when(findDevelopersByTechnologyPortMock.findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class))).thenReturn(mockDevelopers);

        GetDevelopersByTechnologyResponse.Response result = getDevelopersByTechnologyService.getDevelopersByTechnology(command);

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
        GetDevelopersByTechnologyCommand command = new GetDevelopersByTechnologyCommand(mockTechnologyId);

        GetDevelopersByTechnologyDtoResponse expectedResponse = new GetDevelopersByTechnologyDtoResponse(mockTechnologyId, List.of());
        when(findDevelopersByTechnologyPortMock.findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class))).thenReturn(mockDevelopers);

        GetDevelopersByTechnologyResponse.Response result = getDevelopersByTechnologyService.getDevelopersByTechnology(command);

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
        GetDevelopersByTechnologyCommand command = new GetDevelopersByTechnologyCommand(mockTechnologyId);

        when(findDevelopersByTechnologyPortMock.findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class))).thenThrow(new NoSuchElementException());

        GetDevelopersByTechnologyResponse.Response result = getDevelopersByTechnologyService.getDevelopersByTechnology(command);

        verify(findDevelopersByTechnologyPortMock).findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class));

        assertInstanceOf(GetDevelopersByTechnologyResponse.ErrorResponse.class, result);
        assertEquals("Technology not found", ((GetDevelopersByTechnologyResponse.ErrorResponse) result).message());
    }
}
