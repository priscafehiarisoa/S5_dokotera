package s5_dokotera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s5_dokotera.model.MedicamentSymptome;
import s5_dokotera.model.Patient;
import s5_dokotera.model.Symptome;
import s5_dokotera.model.SymptomePatient;

import java.util.List;

public interface MedicamentSymptomeRepository extends JpaRepository<MedicamentSymptome, Integer> {

    //get list medicament symptomes par rapport à l'age et qui correspond à un symptome du patient
//    @Query("SELECT distinct smm.maladie FROM SymptomeMaladie smm " +
//            "JOIN SymptomePatient sp on smm.symptome=sp.symptome " +
//            "WHERE sp IN :listepatient" +
//            " and sp.niveauSymptome between smm.debutNiveauSymptome and smm.finNiveauSymptome" +
//            " and sp.patient.agePatient between smm.maladie.intervalleDebutAge and smm.maladie.intervalleFinAge")


    @Query("select distinct  ms from MedicamentSymptome ms " +
            "join SymptomePatient sp on ms.symptome=sp.symptome  " +
            "where sp in :listeSymptomePatient " +
            "and sp.patient.agePatient between ms.medicament.ageDebut and ms.medicament.ageFin " +
            "and  sp.patient=:patient")
    List<MedicamentSymptome> getListMedicamentParListSymptomes(@Param("listeSymptomePatient")List<SymptomePatient> symptomePatientList, @Param("patient")Patient patient);

    @Query("select ms from MedicamentSymptome ms " +
            "join SymptomePatient sp on ms.symptome=sp.symptome " +
            "where sp=:SymptomePatient "+
            "and sp.patient.agePatient between ms.medicament.ageDebut and ms.medicament.ageFin " +
            "and  sp.patient=:patient "
    )
    List<MedicamentSymptome> getListMedicamentParSymptomes(@Param("SymptomePatient") SymptomePatient symptome, @Param("patient")Patient patient);
}