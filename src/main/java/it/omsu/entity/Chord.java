package it.omsu.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "chords")
public class Chord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, message = "Не меньше 1 знака")
    private String name;

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