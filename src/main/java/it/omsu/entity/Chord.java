package it.omsu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "chords")
@Getter
@Setter
public class Chord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, message = "Не меньше 1 знака")
    private String name;

    @ManyToMany(mappedBy = "chords")
    private List<Progression> progressions;

    @ElementCollection
    private List<Integer> chordShape;

    @Column
    private Boolean isPublic;
    @ManyToOne
    private User chordUser;

}