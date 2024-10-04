package s5_dokotera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_dokotera.model.Patient;
import s5_dokotera.model.SymptomePatient;

import java.util.List;

public interface SymptomePatientRepository extends JpaRepository<SymptomePatient, Integer> {
    List<SymptomePatient> getSymptomePatientByPatient(Patient patient);
}