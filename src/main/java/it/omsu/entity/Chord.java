package it.omsu.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private User user;
}