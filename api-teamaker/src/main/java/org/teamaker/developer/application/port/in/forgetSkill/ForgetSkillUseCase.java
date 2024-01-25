package org.teamaker.developer.application.port.in.forgetSkill;

public interface ForgetSkillUseCase {
    ForgetSkillResponse.Response forgetSkill(ForgetSkillCommand command);
}
