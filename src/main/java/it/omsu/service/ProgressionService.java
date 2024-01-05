package it.omsu.service;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;

import java.util.ArrayList;
import java.util.List;

public interface ProgressionService {
    void createProgression(Progression progression);

    Progression getProgressionById(Long id);

    List<Progression> getAllProgressions();

    void updateProgression(Long id, ArrayList<Chord> chords);

    boolean deleteProgression(Long id);
}
