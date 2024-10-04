package s5_dokotera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_dokotera.model.Symptome;

public interface SymptomeRepository extends JpaRepository<Symptome, Integer> {
}