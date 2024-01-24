package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentCaptor;
import java.time.LocalDate;
import java.util.List;

import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.developer.domain.dto.GetDevelopersByTechnologyResponse;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyPort;
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
        List<DeveloperResponse> mockDevelopers = List.of(
                new Developer("867GVC876a", "Developer fullName", "Developer email", mockDate).toResponse(),
                new Developer("867GVC876b", "Developer fullName 2", "Developer email 2", mockDate).toResponse()
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
