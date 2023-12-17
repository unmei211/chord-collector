package it.omsu.service;

import it.omsu.entity.Chord;

import java.util.List;

public interface ChordService {
    void createChord(Chord chord);
    Chord getChordById(Long id);
    List<Chord> getAllChords();
    void updateChord(Chord chord);
    boolean deleteChord(Long id);
}
