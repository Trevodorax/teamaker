package org.teamaker.developer.application.port.out.findDevelopersByTechnology;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class FindDevelopersByTechnologyCommand extends SelfValidating<FindDevelopersByTechnologyCommand> {
        @NotNull
        private final String technologyId;

        public FindDevelopersByTechnologyCommand(String technologyId) {
            this.technologyId = technologyId;

            this.validateSelf();
        }
}
