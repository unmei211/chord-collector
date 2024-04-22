package it.omsu.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "progressions")
@Getter
@Setter
public class Progression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "progressions")
    private Set<User> users;

    public Progression() {
        users = new HashSet<>();
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Chord> chords;

    public void addUser(User user) {
        users.add(user);
        user.getProgressions().add(this);
    }
}
