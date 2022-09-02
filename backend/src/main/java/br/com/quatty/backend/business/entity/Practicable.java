package br.com.quatty.backend.business.entity;

import br.com.quatty.backend.business.entity.enums.AvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Gym_Sport_Practicable")
@SequenceGenerator(
        name = Practicable.SEQUENCE_NAME,
        sequenceName = Practicable.SEQUENCE_NAME,
        initialValue = 1)
public class Practicable implements Serializable {
    private static final long serialVersionUID = 9080780808115043548L;
    public static final String SEQUENCE_NAME = "PRACTICABLE_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "practicable_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(cascade = CascadeType.REFRESH, optional = false)
    private Sport sport;
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Gym gym;

    public void setStatus(String status) {
        this.status = AvailabilityStatus.valueOf(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Practicable that = (Practicable) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}