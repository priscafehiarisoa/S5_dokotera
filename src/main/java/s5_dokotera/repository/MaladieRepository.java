package s5_dokotera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s5_dokotera.model.Maladie;
import s5_dokotera.model.Patient;
import s5_dokotera.model.SymptomeMaladie;

import java.util.List;

public interface MaladieRepository extends JpaRepository<Maladie, Integer> {
}