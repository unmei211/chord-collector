package it.omsu.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chords")
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

    public void setProgressions(List<Progression> progressions) {
        this.progressions = progressions;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public User getUser() {
        return user;
    }

    @ManyToOne
    private User user;

    public List<Progression> getProgressions() {
        return progressions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}