package org.teamaker.developer.application.port.out;

import org.teamaker.shared.validation.SelfValidating;
import javax.validation.constraints.NotNull;

public class FindDevelopersByTechnologyCommand extends SelfValidating<FindDevelopersByTechnologyCommand> {

        @NotNull
        private final String technologyGuid;

        public FindDevelopersByTechnologyCommand(String technologyGuid) {
            this.technologyGuid = technologyGuid;

            this.validateSelf();
        }

        public String getTechnology() {
            return technologyGuid;
        }
}
