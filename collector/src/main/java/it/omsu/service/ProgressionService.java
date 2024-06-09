package it.omsu.service;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;
import it.omsu.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface ProgressionService {
    void createProgression(Progression progression);

    Progression getProgressionById(Long id);

    List<Progression> getAllProgressions();

    void updateProgressionByChords(Long id, ArrayList<Chord> chords);
    void updateProgressionByTemplate(Long id, Progression progression);

    boolean deleteProgression(Long id);
}
