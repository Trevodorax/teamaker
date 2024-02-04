package org.teamaker.technology.adapter.out.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyRequirementPK implements Serializable {
    private String technologyId;
    private String projectId;
}
