package it.omsu.service;

import it.omsu.entity.Chord;
import it.omsu.repository.ChordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChordServiceImpl implements ChordService {
    private List<Chord> chords = new ArrayList<>();
    private ChordRepository chordRepository;

    public ChordServiceImpl(ChordRepository chordRepository) {
        this.chordRepository = chordRepository;
    }

    @Override
    public void createChord(Chord chord) {
        chords.add(chord);
        chordRepository.save(chord);
    }

    @Override
    public Chord getChordById(Long id) {
        return chords.stream()
                .filter(chord -> chord.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Chord> getAllChords()
    {
        return chordRepository.findAll();
    }

    @Override
    public void updateChord(Chord chord) {
        int index = chords.indexOf(chord);
        if (index != -1) {
            chords.set(index, chord);
        }
    }

    @Override
    public boolean deleteChord(Long chordId) {
        if (chordRepository.findById(chordId).isPresent()) {
            chordRepository.deleteById(chordId);
            return true;
        }
        return false;
    }
}
