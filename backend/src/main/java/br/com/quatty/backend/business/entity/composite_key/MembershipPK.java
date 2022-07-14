package br.com.quatty.backend.business.entity.composite_key;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MembershipPK implements Serializable {
    private static final long serialVersionUID = 656614889487771797L;

    @Column(name = "team_id")
    private Long teamId;
    @Column(name = "athlete_id")
    private Long athleteId;
}
