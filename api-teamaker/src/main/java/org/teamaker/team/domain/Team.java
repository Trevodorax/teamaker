package org.teamaker.team.domain;

import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.ExperienceLevel;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String projectId;
    private final List<Developer> developers;
    private boolean isLocked; // a locked team is a valid team for which the rules can't change anymore

    public Team(String projectId, List<Developer> developers, boolean isLocked) {
        this.projectId = projectId;
        this.developers = developers;
        this.isLocked = isLocked;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public String getProjectId() {
        return projectId;
    }

    /**
     * Once a team satisfies the rules, locking it prevents the rules from being broken
     *
     * @param teamProject The project the team is for
     * @throws IllegalArgumentException If the project isn't the one for this team
     * @throws IllegalStateException If the team doesn't respect the rules
     */
    public void lock(Project teamProject) throws IllegalArgumentException, IllegalStateException {
        if(this.isLocked) {
            return;
        }

        if(!teamProject.getProjectId().equals(this.projectId)) {
            throw new IllegalArgumentException("This isn't the project of this team.");
        }

        if(!getTeamProblems(teamProject).isEmpty()) {
            throw new IllegalStateException("Cannot lock the team because it doesn't respect the rules.");
        }

        this.isLocked = true;
    }

    /**
     * Checks which team rules aren't respected by this team
     *
     * @param teamProject Project to check the team for
     * @return A list of all the problems (empty for valid team)
     */
    public List<String> getTeamProblems(Project teamProject) {
        ArrayList<String> teamProblems = new ArrayList<>();

        if(this.developers.size() < 3) {
            teamProblems.add("Not enough developers in team.");
        }

        if(this.developers.size() > 8) {
            teamProblems.add("Too many developers in team.");
        }

        if(this.countDevelopersWithLevelOfExperience(ExperienceLevel.JUNIOR) > 0
                && this.countDevelopersWithLevelOfExperience(ExperienceLevel.EXPERT) < 1) {
            teamProblems.add("Cannot have a junior without an expert in a team.");
        }

        if(this.countDevelopersWithLevelOfExperience(ExperienceLevel.JUNIOR) > 3) {
            teamProblems.add("Cannot have more than 3 juniors in a team.");
        }

        if(teamProject.getDuration().compareTo(Duration.ofDays(30 * 6)) > 0
                && this.countDevelopersWithLevelOfExperience(ExperienceLevel.EXPERT) == 0) {
            teamProblems.add("Cannot do a project longer than 6 months without an expert in the team.");
        }

        if(this.developers.size() < 5
                && this.countDevelopersWithLevelOfExperience(ExperienceLevel.EXPERT) > 0
                && teamProject.getPriority() != ProjectPriority.CRITICAL) {
            teamProblems.add("An expert cannot be in a team of less than 5 developers unless it is a critical project.");
        }

        return teamProblems;
    }

    public boolean isLocked() {
        return isLocked;
    }

    private int countDevelopersWithLevelOfExperience(ExperienceLevel level) {
        int count = 0;
        for (Developer developer : this.developers) {
            if (developer.getExperienceLevel() == level) {
                count++;
            }
        }

        return count;
    }
}
