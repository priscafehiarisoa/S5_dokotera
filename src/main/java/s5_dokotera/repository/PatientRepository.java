package s5_dokotera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_dokotera.model.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}