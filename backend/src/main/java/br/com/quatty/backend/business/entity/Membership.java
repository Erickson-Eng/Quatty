package br.com.quatty.backend.business.entity;

import br.com.quatty.backend.business.entity.composite_key.MembershipPK;
import br.com.quatty.backend.business.entity.enums.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "membership")
public class Membership implements Serializable {
    private static final long serialVersionUID = 656614882487771797L;

    @EmbeddedId
    @Column(unique = true, nullable = false)
    private MembershipPK membershipPK;
    @MapsId("athleteId")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "athlete_id", updatable = false, referencedColumnName = "id")
    private Athlete athlete;
    @MapsId("teamId")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "team_id", updatable = false, referencedColumnName = "id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;


    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = RegistrationStatus.getRegistrationStatus(registrationStatus);
    }
}