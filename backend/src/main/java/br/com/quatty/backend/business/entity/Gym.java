package br.com.quatty.backend.business.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "gym")
@SequenceGenerator(
        name = Gym.SEQUENCE_NAME,
        sequenceName = Gym.SEQUENCE_NAME,
        initialValue = 1)
public class Gym implements Serializable {
    private static final long serialVersionUID = 3089325093587425003L;
    public static final String SEQUENCE_NAME = "GYM_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gym_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String information;

    @OneToMany(mappedBy = "gym")
    private Set<Practicable> practicableSports;

    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn
    private School school;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Gym gym = (Gym) o;
        return id != null && Objects.equals(id, gym.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}