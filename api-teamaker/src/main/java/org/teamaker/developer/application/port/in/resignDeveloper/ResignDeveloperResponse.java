package org.teamaker.developer.application.port.in.resignDeveloper;

import java.time.LocalDate;
import java.util.List;

public class ResignDeveloperResponse {
    public interface Response {}

    public record SuccessResponse(LocalDate resignationDate) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}

    public record MultipleErrorsResponse(List<String> errorMessages) implements Response {}
}
