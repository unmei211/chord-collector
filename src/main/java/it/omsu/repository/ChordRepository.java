package it.omsu.repository;

import it.omsu.entity.Chord;
import it.omsu.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChordRepository extends JpaRepository<Chord, Long> {
}
