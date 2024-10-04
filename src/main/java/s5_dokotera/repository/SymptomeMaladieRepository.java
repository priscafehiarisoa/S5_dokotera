package s5_dokotera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s5_dokotera.model.Maladie;
import s5_dokotera.model.Patient;
import s5_dokotera.model.SymptomeMaladie;
import s5_dokotera.model.SymptomePatient;

import java.util.List;

public interface SymptomeMaladieRepository extends JpaRepository<SymptomeMaladie, Integer> {
    @Query("SELECT distinct smm.maladie FROM SymptomeMaladie smm JOIN SymptomePatient sp on smm.symptome=sp.symptome WHERE sp IN :listepatient and sp.niveauSymptome between smm.debutNiveauSymptome and smm.finNiveauSymptome and sp.patient.agePatient between smm.maladie.intervalleDebutAge and smm.maladie.intervalleFinAge and sp.patient.id=:idPatient")
    List<Maladie> getMaladieInListSymptomePatient(@Param("listepatient")List<SymptomePatient> listePatients,@Param("idPatient") int idpatient);

    List<SymptomeMaladie> getAllByMaladie(Maladie maladie);
}