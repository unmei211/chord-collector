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
public class ChordServiceImpl implements ChordService {
    private List<Chord> chords = new ArrayList<>();
    private ChordRepository chordRepository;

    private ProgressionRepository progressionRepository;

    public ChordServiceImpl(ChordRepository chordRepository, ProgressionRepository progressionRepository) {
        this.chordRepository = chordRepository;
        this.progressionRepository = progressionRepository;
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
    public List<Chord> getAllChords() {
        return chordRepository.findAll();
    }

    @Override
    public void updateChord(Long id, String updateForm) {
        Chord chord = chordRepository.findById(id).get();
        chord.setName(updateForm);
        chordRepository.save(chord);
    }

    /**
     * Тут смотрим, если аккорд, коддорый мы удаляем есть в прогрессии, то
     * мы удаляем эту прогрессию у всех пользователей, затем саму прогрессию, а потом и аккорд
     * @param chordId -- id аккорда
     * @return -- true если удаление прошло успешно
     */
    @Override
    public boolean deleteChord(Long chordId) {
        if (chordRepository.existsById(chordId)) {
            Chord chord = chordRepository.findById(chordId).get();
            List<Progression> progressions = chord.getProgressions();
            for (Progression progression : progressions) {

                for (User user : progression.getUsers()) {
                    user.getProgressions().remove(progression);
                }
                progressionRepository.delete(progression);
            }
            chordRepository.deleteById(chordId);
            return true;
        }
        return false;
    }
}
