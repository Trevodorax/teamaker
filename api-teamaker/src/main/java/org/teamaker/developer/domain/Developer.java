package org.teamaker.developer.domain;

import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectProgress;
import org.teamaker.technology.domain.Technology;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Developer {
    private final String developerId;
    private String fullName;
    private String email;
    private final LocalDate hiringDate;
    private LocalDate resignationDate;
    private List<Project> projectList;
    private List<Technology> developerSkills;

    public Developer(String developerId, String fullName, String email, LocalDate hiringDate, List<Technology> developerSkills) {
        this.developerId = developerId;
        this.fullName = fullName;
        this.email = email;
        this.hiringDate = hiringDate;
        this.developerSkills = developerSkills;
    }

    public List<String> resign(List<Project> currentProjects){
        for (Project project : currentProjects) {
            List<String> errors = project.removeDeveloperById(this.developerId, true);
            if (errors != null) {
                return errors;
            }
            project.removeDeveloperById(this.developerId, false);
        }

        this.resignationDate = LocalDate.now();
        return null;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public DeveloperStatus getStatus() {
        if (this.resignationDate == null || this.resignationDate.isAfter(LocalDate.now())) {
            return DeveloperStatus.ACTIVE;
        }
        return DeveloperStatus.RESIGNED;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void addProject(Project addedProject) {
        if(!checkAvailability(addedProject)) {
            throw new IllegalStateException("Please load the developer's projects before modifying them");
        }

        projectList.add(addedProject);
    }

    /*
     * @param checkedProject The project to check availability for
     * @return Is the developer available for this project?
     */
    public boolean checkAvailability(Project checkedProject) {
        if (this.projectList == null) {
            throw new IllegalStateException("projectList has not been set.");
        }

        for (Project project : this.projectList) {
            if (project.isOverlapping(checkedProject)) {
                return false;
            }
        }

        return true;
    }

    public DeveloperResponse toResponse() {
        return new DeveloperResponse(this.developerId, this.fullName, this.email, this.hiringDate, this.resignationDate, this.getStatus());
    }

    public ExperienceLevel getExperienceLevel() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(this.hiringDate, currentDate);
        int yearsOfExperience = period.getYears();

        if (yearsOfExperience >= 0 && yearsOfExperience <= 3) {
            return ExperienceLevel.JUNIOR;
        } else if (yearsOfExperience > 3 && yearsOfExperience <= 5) {
            return ExperienceLevel.EXPERIENCED;
        } else {
            return ExperienceLevel.EXPERT;
        }
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void updateInfo(String newName, String newEmail) {
        this.fullName = newName != null ? newName : this.fullName;
        this.email = newEmail != null ? newEmail : this.email;
    }

    public List<Project> getCurrentProjects() throws IllegalStateException{
        if (this.projectList == null) {
            throw new IllegalStateException("projectList has not been set.");
        }

        return this.projectList
                .stream()
                .filter(project -> project.projectProgress() != ProjectProgress.DONE)
                .toList();
    }

    public List<Technology> getDeveloperSkills() {
        return developerSkills;
    }
}
