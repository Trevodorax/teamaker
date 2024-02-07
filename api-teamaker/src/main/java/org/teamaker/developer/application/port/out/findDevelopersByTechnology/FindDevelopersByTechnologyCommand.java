package org.teamaker.developer.application.port.out.findDevelopersByTechnology;

import org.teamaker.shared.validation.SelfValidating;
import org.teamaker.technology.domain.Technology;

import javax.validation.constraints.NotNull;

public class FindDevelopersByTechnologyCommand extends SelfValidating<FindDevelopersByTechnologyCommand> {

        @NotNull
        private final Technology technology;

        public FindDevelopersByTechnologyCommand(Technology technology) {
            this.technology = technology;

            this.validateSelf();
        }

        public Technology getTechnology() {
            return technology;
        }
}
