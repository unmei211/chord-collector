package it.omsu.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "progressions")
public class Progression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "progressions")
    private Set<User> users;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Chord> chords;
//    @OneToMany
//    @JoinTable(name = "progression_chords",
//            joinColumns = @JoinColumn(name = "progression_id"),
//            inverseJoinColumns = @JoinColumn(name = "chord_id"))
//    private List<Chord> chords;

    public List<Chord> getChords() {
        return chords;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setChords(List<Chord> chords) {
        this.chords = chords;
    }

    public Set<User> getUsers() {
        return users;
    }
}
