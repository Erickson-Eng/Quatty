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
@Table(name = "sport")
@SequenceGenerator(
        name = Sport.SEQUENCE_NAME,
        sequenceName = Sport.SEQUENCE_NAME,
        initialValue = 1)
public class Sport implements Serializable {
    private static final long serialVersionUID = -151311785416351489L;
    public static final String SEQUENCE_NAME = "SPORT_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sport_seq")

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", length = 512)
    private String biography;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sport sport = (Sport) o;
        return id != null && Objects.equals(id, sport.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}