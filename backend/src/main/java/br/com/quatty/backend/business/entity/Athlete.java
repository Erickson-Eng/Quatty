package br.com.quatty.backend.business.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "profile_seq", sequenceName = "athlete_seq", allocationSize = 1)
@Entity
@Table(name = "athlete")
public class Athlete extends Profile {
    private static final long serialVersionUID = 7479031321670090597L;
    private Double weight;
    private Double height;
    private Double bicepsMeasurement;
    private Double forearmMeasurement;
    private Double chestMeasurement;
    private Double thighMeasurement;
    private Double calfMeasurement;


    public Athlete(Long id, String firstName, String lastName, String socialName,
                   LocalDate birtDate, Address address, Double weight, Double height) {
        super(id, firstName, lastName, socialName, birtDate, address);
        this.weight = weight;
        this.height = height;
    }

    public Athlete(Long id, String firstName, String lastName, String socialName,
                   LocalDate birtDate, Address address, Double weight, Double height,
                   Double bicepsMeasurement, Double chestMeasurement, Double thighMeasurement) {
        super(id, firstName, lastName, socialName, birtDate, address);
        this.weight = weight;
        this.height = height;
        this.bicepsMeasurement = bicepsMeasurement;
        this.chestMeasurement = chestMeasurement;
        this.thighMeasurement = thighMeasurement;
    }
}