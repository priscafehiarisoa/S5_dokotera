package s5_dokotera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_dokotera.model.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Integer> {
}