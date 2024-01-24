package org.teamaker.team.domain;

import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.ExperienceLevel;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;

import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private static final int MAX_NB_DEVS = 8;
    private static final int MIN_NB_DEVS = 3;
    private static final int MAX_NB_JUNIORS = 3;
    private static final int MIN_NB_DEVS_FOR_EXPERT = 5;
    private static final Duration LONG_PROJECT_DURATION = Duration.ofDays(Period.ofMonths(6).plusDays(1).toTotalMonths() * 30);
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

        if(this.developers.size() < MIN_NB_DEVS) {
            teamProblems.add("Not enough developers in team.");
        }

        if(this.developers.size() > MAX_NB_DEVS) {
            teamProblems.add("Too many developers in team.");
        }

        if(this.countDevelopersWithLevelOfExperience(ExperienceLevel.JUNIOR) > 0
                && this.countDevelopersWithLevelOfExperience(ExperienceLevel.EXPERT) < 1) {
            teamProblems.add("Cannot have a junior without an expert in a team.");
        }

        if(this.countDevelopersWithLevelOfExperience(ExperienceLevel.JUNIOR) > MAX_NB_JUNIORS) {
            teamProblems.add("Cannot have more than " + MAX_NB_JUNIORS + " juniors in a team.");
        }

        if(teamProject.getDuration().compareTo(LONG_PROJECT_DURATION) > 0
                && this.countDevelopersWithLevelOfExperience(ExperienceLevel.EXPERT) == 0) {
            teamProblems.add("Cannot do a project longer than 6 months without an expert in the team.");
        }

        if(this.developers.size() < MIN_NB_DEVS_FOR_EXPERT
                && this.countDevelopersWithLevelOfExperience(ExperienceLevel.EXPERT) > 0
                && teamProject.getPriority() != ProjectPriority.CRITICAL) {
            teamProblems.add("An expert cannot be in a team of less than " + MIN_NB_DEVS_FOR_EXPERT + " developers unless it is a critical project.");
        }

        return teamProblems;
    }

    public boolean isLocked() {
        return isLocked;
    }

    /**
     * @param developerId id of the developer we are trying to remove
     * @param noRemove true if we don't want to remove the dev, but just see if we could
     * @return Team rules broken if there were any (if so, the dev isn't removed)
     * @throws IllegalStateException If the devs haven't been loaded in the team
     */
    public List<String> removeDeveloperById(String developerId, Project teamProject, boolean noRemove) throws IllegalStateException {
        if (this.getDevelopers() == null) {
            throw new IllegalStateException("Please load the developers in the team before editing them.");
        }

        // to revert in case of failure
        List<Developer> originalDevelopers = new ArrayList<>(this.getDevelopers());

        this.getDevelopers().removeIf(developer -> developer.getDeveloperId().equals(developerId));

        if (!isLocked()) {
            return null;
        }

        // check that the removal didn't break any rules
        List<String> problems = this.getTeamProblems(teamProject);
        if (!problems.isEmpty()) {
            this.getDevelopers().clear();
            this.getDevelopers().addAll(originalDevelopers);
            return problems;
        }

        // cancel removal if noRemove
        if (noRemove) {
            this.getDevelopers().clear();
            this.getDevelopers().addAll(originalDevelopers);
        }

        return null;
    }

    /**
     * @param newDeveloper Developer object we are trying to add
     * @param teamProject Current project of the team
     * @param noAdd true if we don't want to actually add the dev, but just see if we could
     * @return Team rules broken if there were any (if so, the dev isn't added)
     * @throws IllegalStateException If the devs haven't been loaded in the team
     */
    public List<String> addDeveloper(Developer newDeveloper, Project teamProject, boolean noAdd) throws IllegalStateException {
        if (this.getDevelopers() == null) {
            throw new IllegalStateException("Please load the developers in the team before editing them.");
        }

        // to revert in case of failure
        List<Developer> originalDevelopers = new ArrayList<>(this.getDevelopers());

        this.getDevelopers().add(newDeveloper);

        if (!isLocked()) {
            return null;
        }

        // check that the addition didn't break any rules
        List<String> problems = this.getTeamProblems(teamProject);
        if (!problems.isEmpty()) {
            this.getDevelopers().clear();
            this.getDevelopers().addAll(originalDevelopers);
            return problems;
        }

        // cancel addition if noAdd
        if (noAdd) {
            this.getDevelopers().clear();
            this.getDevelopers().addAll(originalDevelopers);
        }

        return null;
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
