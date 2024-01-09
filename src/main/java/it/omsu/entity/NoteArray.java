package it.omsu.entity;

import org.aspectj.weaver.ast.Not;

import java.util.ArrayList;

public class NoteArray {

    private ArrayList<Note> notes;

    public NoteArray() {
        notes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            notes.add(new Note("C" + (i + 1)));
            notes.add(new Note("C#" + (i + 1)));
            notes.add(new Note("D" + (i + 1)));
            notes.add(new Note("D#" + (i + 1)));
            notes.add(new Note("E" + (i + 1)));
            notes.add(new Note("F" + (i + 1)));
            notes.add(new Note("F#" + (i + 1)));
            notes.add(new Note("G" + (i + 1)));
            notes.add(new Note("G#" + (i + 1)));
            notes.add(new Note("A" + (i + 1)));
            notes.add(new Note("A#" + (i + 1)));
            notes.add(new Note("B" + (i + 1)));
        }
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public Note getNoteByName(String name) {
        for (Note note : notes) {
            if (note.getName().equals(name)) {
                return note;
            }
        }
        return null;
    }

    public int getIndexNoteByName(String name) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
