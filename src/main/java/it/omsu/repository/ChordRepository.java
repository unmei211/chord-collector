package it.omsu.repository;

import it.omsu.entity.Chord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChordRepository extends JpaRepository<Chord, Long> {
}
