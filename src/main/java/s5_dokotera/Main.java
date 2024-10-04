package s5_dokotera;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s5_dokotera.model.*;
import s5_dokotera.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class Main {
    @Bean
    CommandLineRunner commandLineRunner(MaladieRepository maladieRepository,
                                        MedicamentRepository medicamentRepository,
                                        SymptomeRepository symptomeRepository,
                                        SymptomeMaladieRepository symptomeMaladieRepository,
                                        PatientRepository patientRepository,
                                        SymptomePatientRepository symptomePatientRepository,
                                        MedicamentSymptomeRepository medicamentSymptomeRepository){
        return args -> {
            List<Maladie> maladies=new ArrayList<>();
            maladies.add(new Maladie(1,"migraine aigue",0,100));
            maladies.add(new Maladie(2,"infections",0,100));
            maladies.add(new Maladie(3,"grippe",0,100));
            maladies.add(new Maladie(4,"marary kibo",0,100));
            maladieRepository.saveAll(maladies);

            List<Medicament> medicaments=new ArrayList<>();
            medicaments.add(new Medicament(1,"paracetamol Enfant",0,15,200));
            medicaments.add(new Medicament(2,"paracetamol Adulte",16,100,300));
            medicaments.add(new Medicament(3,"omeprazole",0,150,250));
            medicaments.add(new Medicament(4,"charbon",0,100,100));
            medicaments.add(new Medicament(5,"doliprane enfant 1",0,15,300));
            medicaments.add(new Medicament(6,"doliprane adulte 1",15,150,1000));
            medicaments.add(new Medicament(7,"fervex enfant 2",0,15,2000));
            medicaments.add(new Medicament(8,"fervex adulte 2",15,150,1500));
            medicamentRepository.saveAll(medicaments);

            List<Symptome> symptomes=new ArrayList<>();
            symptomes.add(new Symptome(1,"maux de tête"));
            symptomes.add(new Symptome(2,"selles"));
            symptomes.add(new Symptome(3,"maux de ventres"));
            symptomes.add(new Symptome(4,"maux de gorges"));
            symptomes.add(new Symptome(5,"temperature"));
            symptomeRepository.saveAll(symptomes);

            List<SymptomeMaladie> symptomeMaladies=new ArrayList<>();
            //symptome maladie 0
            symptomeMaladies.add(new SymptomeMaladie(1,maladies.get(0),symptomes.get(0),7,10));
            //symptome maladie 1
            symptomeMaladies.add(new SymptomeMaladie(2,maladies.get(1),symptomes.get(4),3,10));
            symptomeMaladies.add(new SymptomeMaladie(3,maladies.get(1),symptomes.get(0),3,6));
            //symptome maladie 2
            symptomeMaladies.add(new SymptomeMaladie(4,maladies.get(2),symptomes.get(0),3,6));
            symptomeMaladies.add(new SymptomeMaladie(5,maladies.get(2),symptomes.get(4),3,6));
            symptomeMaladies.add(new SymptomeMaladie(6,maladies.get(2),symptomes.get(3),3,6));
            //symptome maladie 3
            symptomeMaladies.add(new SymptomeMaladie(7,maladies.get(3),symptomes.get(1),3,10));
            symptomeMaladies.add(new SymptomeMaladie(8,maladies.get(3),symptomes.get(2),3,10));

            symptomeMaladies.add(new SymptomeMaladie(9,maladies.get(0),symptomes.get(1),0,10));

            symptomeMaladieRepository.saveAll(symptomeMaladies);

            Patient patient=new Patient(1,"prisca",22);
            patientRepository.save(patient);

            List<SymptomePatient> symptomePatientList=new ArrayList<>();
            symptomePatientList.add(new SymptomePatient(1,patient,symptomes.get(1),8));
            symptomePatientList.add(new SymptomePatient(2,patient,symptomes.get(0),0));
//            symptomePatientList.add(new SymptomePatient(3,patient,symptomes.get(2),5));
            symptomePatientRepository.saveAll(symptomePatientList);

            // medicaments
            List<MedicamentSymptome> medicamentSymptomes=new ArrayList<>();
            medicamentSymptomes.add(new MedicamentSymptome(1,medicaments.get(0),3,symptomes.get(0)));
            medicamentSymptomes.add(new MedicamentSymptome(2,medicaments.get(1),1,symptomes.get(0)));
            medicamentSymptomes.add(new MedicamentSymptome(3,medicaments.get(4),2,symptomes.get(0)));
            medicamentSymptomes.add(new MedicamentSymptome(4,medicaments.get(5),4,symptomes.get(0)));
            medicamentSymptomeRepository.saveAll(medicamentSymptomes);

//            Maladie.getListeMaladieOfPatient(symptomeMaladieRepository,symptomePatientRepository,patient).forEach(System.out::println);
            // test combinaison
            List<MedicamentSymptome> ms=medicamentSymptomeRepository.getListMedicamentParSymptomes(symptomePatientList.get(1),patient);
            // Recherche de la combinaison avec une somme de niveau de guérison égale à 10
            List<CombinaisonAvecPrix> result = genererCombinaisonsAvecPrix(ms, 10);

            // Affichage des combinaisons possibles avec le total des prix
            for (CombinaisonAvecPrix combinaison : result) {
                System.out.println("Combinaison : ");
                for (Map.Entry<Integer, MedicamentSymptome> entry : combinaison.getCombinaison().entrySet()) {
                    System.out.println(entry.getKey() + " * " + entry.getValue());
                }
                System.out.println("Total des prix : " + combinaison.getTotalPrix());
                System.out.println();
            }

        };
    }

    private static List<Map<Integer, MedicamentSymptome>> genererCombinaisons(List<MedicamentSymptome> medicamentSymptomes, int sommeCible) {
        List<Map<Integer, MedicamentSymptome>> result = new ArrayList<>();
        genererCombinaisonsRecursive(medicamentSymptomes, sommeCible, 0, new HashMap<>(), result);
        return result;
    }

    private static void genererCombinaisonsRecursive(List<MedicamentSymptome> medicamentSymptomes, int sommeCible,
                                                     int index, Map<Integer, MedicamentSymptome> combinaisonActuelle,
                                                     List<Map<Integer, MedicamentSymptome>> result) {
        if (sommeCible == 0) {
            // Ajouter la combinaison actuelle à la liste de résultats
            if (getTotalNiveauGuerison(combinaisonActuelle) >= 10) {
                result.add(new HashMap<>(combinaisonActuelle));
            }
            return;
        }

        if (index == medicamentSymptomes.size()) {
            return;
        }

        int niveauGuerisonActuel = medicamentSymptomes.get(index).getNiveauGuerison();
        int maxOccurrences = sommeCible / niveauGuerisonActuel;

        for (int occurrences = 0; occurrences <= maxOccurrences; occurrences++) {
            int nouvelleSomme = sommeCible - occurrences * niveauGuerisonActuel;

            if (nouvelleSomme >= 0) {
                combinaisonActuelle.put(occurrences, medicamentSymptomes.get(index));

                genererCombinaisonsRecursive(medicamentSymptomes, nouvelleSomme, index + 1, combinaisonActuelle, result);

                // Retirer l'élément ajouté pour essayer d'autres combinaisons
                combinaisonActuelle.remove(occurrences);
            }
        }
    }

    private static int getTotalNiveauGuerison(Map<Integer, MedicamentSymptome> combinaison) {
        int total = 0;
        for (Map.Entry<Integer, MedicamentSymptome> entry : combinaison.entrySet()) {
            total += entry.getKey() * entry.getValue().getNiveauGuerison();
        }
        return total;
    }

    //=====================combinaisons ===========
    private static List<CombinaisonAvecPrix> genererCombinaisonsAvecPrix(List<MedicamentSymptome> medicamentSymptomes, int sommeCible) {
        List<CombinaisonAvecPrix> result = new ArrayList<>();
        genererCombinaisonsAvecPrixRecursive(medicamentSymptomes, sommeCible, 0, new HashMap<>(), result);
        return result;
    }

    private static void genererCombinaisonsAvecPrixRecursive(List<MedicamentSymptome> medicamentSymptomes, int sommeCible,
                                                             int index, Map<Integer, MedicamentSymptome> combinaisonActuelle,
                                                             List<CombinaisonAvecPrix> result) {
        if (sommeCible == 0) {
            int totalPrix = getTotalPrix(combinaisonActuelle);
            if (getTotalNiveauGuerison(combinaisonActuelle) >= 10) {
                result.add(new CombinaisonAvecPrix(new HashMap<>(combinaisonActuelle), totalPrix));
            }
            return;
        }

        if (index == medicamentSymptomes.size()) {
            return;
        }

        int niveauGuerisonActuel = medicamentSymptomes.get(index).getNiveauGuerison();
        int maxOccurrences = sommeCible / niveauGuerisonActuel;

        for (int occurrences = 0; occurrences <= maxOccurrences; occurrences++) {
            int nouvelleSomme = sommeCible - occurrences * niveauGuerisonActuel;

            if (nouvelleSomme >= 0) {
                combinaisonActuelle.put(occurrences, medicamentSymptomes.get(index));

                genererCombinaisonsAvecPrixRecursive(medicamentSymptomes, nouvelleSomme, index + 1, combinaisonActuelle, result);

                // Retirer l'élément ajouté pour essayer d'autres combinaisons
                combinaisonActuelle.remove(occurrences);
            }
        }
    }

    private static int getTotalPrix(Map<Integer, MedicamentSymptome> combinaison) {
        int totalPrix = 0;
        for (Map.Entry<Integer, MedicamentSymptome> entry : combinaison.entrySet()) {
            totalPrix += entry.getKey() * entry.getValue().getMedicament().getPrixMedicament();
        }
        return totalPrix;
    }

    static class CombinaisonAvecPrix {
        private final Map<Integer, MedicamentSymptome> combinaison;
        private final int totalPrix;

        public CombinaisonAvecPrix(Map<Integer, MedicamentSymptome> combinaison, int totalPrix) {
            this.combinaison = combinaison;
            this.totalPrix = totalPrix;
        }

        public Map<Integer, MedicamentSymptome> getCombinaison() {
            return combinaison;
        }

        public int getTotalPrix() {
            return totalPrix;
        }
    }
}
