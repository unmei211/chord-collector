package it.omsu.entity;


public class AcousticGuitar implements Guitar {

    int[] openNotes;
    NoteArray noteArray;

    public AcousticGuitar() {
        openNotes = new int[6];
        noteArray = new NoteArray();
        openNotes[0] = noteArray.getIndexNoteByName("E4");
        openNotes[1] = noteArray.getIndexNoteByName("B3");
        openNotes[2] = noteArray.getIndexNoteByName("G3");
        openNotes[3] = noteArray.getIndexNoteByName("D3");
        openNotes[4] = noteArray.getIndexNoteByName("A2");
        openNotes[5] = noteArray.getIndexNoteByName("E2");
    }

    @Override
    public void tuningGuitarString(int string, int idx) {
        openNotes[string] = idx;
    }

    @Override
    public int[] getOpenNotes() {
        return openNotes;
    }
}
