package org.teamaker.adapter.out.persistence.db;

import org.teamaker.adapter.out.persistence.DeveloperRepository;
import org.teamaker.developer.domain.Developer;

public class DBDeveloperRepository implements DeveloperRepository {
    public Developer createDeveloper(String fullName, String email) {
        return new Developer(fullName, email);
    }
}
