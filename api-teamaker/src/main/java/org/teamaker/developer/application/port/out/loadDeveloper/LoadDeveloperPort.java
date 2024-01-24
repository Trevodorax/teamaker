package org.teamaker.developer.application.port.out.loadDeveloper;

import org.teamaker.developer.domain.Developer;

import java.util.NoSuchElementException;

public interface LoadDeveloperPort {
    Developer loadDeveloper(LoadDeveloperCommand command) throws NoSuchElementException, IllegalArgumentException;
}
