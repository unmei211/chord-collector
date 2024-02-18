package it.omsu.entity;

import java.util.ArrayList;

public interface Guitar {

    int[] getOpenNotes();

    void tuningGuitarString(int string, int idx);
}
