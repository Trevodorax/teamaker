package org.teamaker.developer.application.port.in.forgetSkill;

public class ForgetSkillResponse {
    public interface Response {}

    public record SuccessResponse(String message) implements Response {}

    public record ErrorResponse(String message) implements Response {}
}
