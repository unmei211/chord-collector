package it.omsu.repository;

import it.omsu.entity.Chord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChordRepository extends JpaRepository<Chord, Long> {
    List<Chord> findByIsPublicTrue();
}
