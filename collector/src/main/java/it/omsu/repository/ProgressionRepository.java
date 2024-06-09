package it.omsu.repository;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressionRepository extends JpaRepository<Progression, Long> {
}
