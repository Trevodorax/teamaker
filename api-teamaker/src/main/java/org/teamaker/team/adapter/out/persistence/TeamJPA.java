package org.teamaker.team.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TeamJPA {
    @Id
    private Long id;
}
