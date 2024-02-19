package org.teamaker.technology.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.technology.application.port.in.addTechnology.AddTechnologyCommand;
import org.teamaker.technology.application.port.in.addTechnology.AddTechnologyResponse;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyCommand;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyPort;
import org.teamaker.technology.domain.Technology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

public class AddTechnologyServiceTest {
    private static CreateTechnologyPort createTechnologyPortMock;
    private static AddTechnologyService addTechnologyService;

    @BeforeAll
    public static void setUp() {
        createTechnologyPortMock = mock(CreateTechnologyPort.class);
        addTechnologyService = new AddTechnologyService(createTechnologyPortMock);
    }

    @Test
    public void testAddTechnology() {
        String mockId = "Tech Id";
        String mockName = "Tech Name";
        AddTechnologyCommand command = new AddTechnologyCommand(mockName);

        Technology expectedTechnology = new Technology(mockId, mockName);
        when(createTechnologyPortMock.createTechnology(any(CreateTechnologyCommand.class))).thenReturn(expectedTechnology);

        AddTechnologyResponse.Response result = addTechnologyService.addTechnology(command);

        assertInstanceOf(AddTechnologyResponse.SuccessResponse.class, result);

        assertEquals(expectedTechnology.getTechnologyId(), ((AddTechnologyResponse.SuccessResponse) result).technology().technologyId());
        assertEquals(expectedTechnology.getName(), ((AddTechnologyResponse.SuccessResponse) result).technology().name());
    }
}
