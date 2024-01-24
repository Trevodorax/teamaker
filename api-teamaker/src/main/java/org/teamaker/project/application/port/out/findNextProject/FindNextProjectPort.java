package org.teamaker.project.application.port.out.findNextProject;

import org.teamaker.project.domain.Project;

import java.util.NoSuchElementException;

public interface FindNextProjectPort {
    /**
     * Find the next project to be worked on.
     * @return The next project
     * @throws NoSuchElementException If there are no next projects
     */
    Project findNextProject() throws NoSuchElementException;
}
