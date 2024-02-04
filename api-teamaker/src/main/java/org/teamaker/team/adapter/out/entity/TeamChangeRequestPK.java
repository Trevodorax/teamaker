package org.teamaker.team.adapter.out.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamChangeRequestPK implements Serializable {
    private String currentProjectId;
    private String developerId;
}
