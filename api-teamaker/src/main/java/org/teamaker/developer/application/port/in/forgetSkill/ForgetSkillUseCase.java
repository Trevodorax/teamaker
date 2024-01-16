package org.teamaker.developer.application.port.in.forgetSkill;

import java.time.LocalDate;

public interface ForgetSkillUseCase {
    LocalDate forgetSkill(ForgetSkillCommand command);
}
