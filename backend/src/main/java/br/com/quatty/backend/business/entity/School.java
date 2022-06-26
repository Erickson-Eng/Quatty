package br.com.quatty.backend.business.entity;

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
@Table(name = "school")
@SequenceGenerator(
        name = School.SEQUENCE_NAME,
        sequenceName = School.SEQUENCE_NAME,
        initialValue = 1)
public class School implements Serializable {
    private static final long serialVersionUID = -8982521562935140956L;
    public static final String SEQUENCE_NAME = "SCHOOL_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "school_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", length = 512)
    private String biography;

    @OneToOne(cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JoinColumn
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        School school = (School) o;
        return id != null && Objects.equals(id, school.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}