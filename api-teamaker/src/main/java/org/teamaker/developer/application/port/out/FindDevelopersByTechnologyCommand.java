package org.teamaker.developer.application.port.out;

import org.teamaker.shared.validation.SelfValidating;
import javax.validation.constraints.NotNull;

public class FindDevelopersByTechnologyCommand extends SelfValidating<FindDevelopersByTechnologyCommand> {

        @NotNull
        private final String technology;

        public FindDevelopersByTechnologyCommand(String technology) {
            this.technology = technology;

            this.validateSelf();
        }

        public String getTechnology() {
            return technology;
        }
}
