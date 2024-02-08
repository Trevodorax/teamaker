package org.teamaker.project.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.out.findNextProject.FindNextProjectPort;
import org.teamaker.project.domain.Project;

import java.util.NoSuchElementException;

@Component
public class FindNextProjectAdapter implements FindNextProjectPort {
    @Override
    public Project findNextProject() throws NoSuchElementException {
        return null;
    }
}
