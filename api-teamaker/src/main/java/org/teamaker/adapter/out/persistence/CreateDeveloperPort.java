package org.teamaker.adapter.out.persistence;

import org.teamaker.developer.domain.Developer;

public interface CreateDeveloperPort {
    public Developer createDeveloper(String fullName, String email);
}
