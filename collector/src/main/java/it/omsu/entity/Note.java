package it.omsu.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Note {

    private String name;

    public Note(String name) {
        this.name = name;
    }
}
