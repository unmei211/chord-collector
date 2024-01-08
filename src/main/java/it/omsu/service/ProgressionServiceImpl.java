package it.omsu.service;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;
import it.omsu.entity.User;
import it.omsu.repository.ChordRepository;
import it.omsu.repository.ProgressionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressionServiceImpl implements ProgressionService {
    private List<Progression> progressions = new ArrayList<>();
    private ProgressionRepository progressionRepository;

    public ProgressionServiceImpl(ProgressionRepository progressionRepository) {
        this.progressionRepository = progressionRepository;
    }

    @Override
    public void createProgression(Progression progression) {
        progressions.add(progression);
        progressionRepository.save(progression);
    }

    @Override
    public Progression getProgressionById(Long id) {
        return progressions.stream()
                .filter(progression -> progression.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Progression> getAllProgressions() {
        return progressionRepository.findAll();
    }

    @Override
    public void updateProgression(Long id, ArrayList<Chord> chords) {
        Progression progression = progressionRepository.findById(id).get();
        progression.setChords(chords);
        progressionRepository.save(progression);
    }

    @Override
    public boolean deleteProgression(Long id) {
        if (progressionRepository.findById(id).isPresent()) {
            Progression progression = progressionRepository.findById(id).get();
            for (User user : progression.getUsers()) {
                user.getProgressions().remove(progression);
            }
            progressionRepository.delete(progression);
            return true;
        }
        return false;
    }
}
