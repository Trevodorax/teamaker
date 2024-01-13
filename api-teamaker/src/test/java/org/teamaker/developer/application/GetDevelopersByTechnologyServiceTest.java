package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentCaptor;
import java.time.LocalDate;
import java.util.List;

import org.teamaker.developer.application.dto.GetDevelopersByTechnologyResponse;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDeveloperByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDeveloperByTechnology.FindDevelopersByTechnologyPort;
import org.teamaker.developer.domain.Developer;

public class GetDevelopersByTechnologyServiceTest {
    private static FindDevelopersByTechnologyPort findDevelopersByTechnologyPortMock;
    private static GetDevelopersByTechnologyService getDevelopersByTechnologyService;

    @BeforeAll
    public static void setUp() {
        findDevelopersByTechnologyPortMock = mock(FindDevelopersByTechnologyPort.class);
        getDevelopersByTechnologyService = new GetDevelopersByTechnologyService(findDevelopersByTechnologyPortMock);
    }

    @Test
    public void testGetDevelopersByTechnology() {
        String mockTechnologyId = "Technology Id";
        LocalDate mockDate = LocalDate.now();
        List<Developer> mockDevelopers = List.of(
                new Developer("Developer fullName", "Developer email", mockDate, null),
                new Developer("Developer fullName 2", "Developer email 2", mockDate, null)
        );
        GetDevelopersByTechnologyCommand command = new GetDevelopersByTechnologyCommand(mockTechnologyId);

        GetDevelopersByTechnologyResponse expectedResponse = new GetDevelopersByTechnologyResponse(mockTechnologyId, mockDevelopers);
        when(findDevelopersByTechnologyPortMock.findDevelopersByTechnology(any(FindDevelopersByTechnologyCommand.class))).thenReturn(mockDevelopers);

        GetDevelopersByTechnologyResponse result = getDevelopersByTechnologyService.getDevelopersByTechnology(command);

        ArgumentCaptor<FindDevelopersByTechnologyCommand> captor = ArgumentCaptor.forClass(FindDevelopersByTechnologyCommand.class);
        verify(findDevelopersByTechnologyPortMock).findDevelopersByTechnology(captor.capture());
        FindDevelopersByTechnologyCommand capturedCommand = captor.getValue();

        assertEquals(mockTechnologyId, capturedCommand.getTechnologyId());
        assertEquals(expectedResponse, result);
    }
}
