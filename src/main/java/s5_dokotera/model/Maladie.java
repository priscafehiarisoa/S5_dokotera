package s5_dokotera.model;

import jakarta.persistence.*;
import s5_dokotera.repository.SymptomeMaladieRepository;
import s5_dokotera.repository.SymptomePatientRepository;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Maladie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nomMaladie;
    private int intervalleDebutAge;
    private int intervalleFinAge;

    public int getIntervalleDebutAge() {
        return intervalleDebutAge;
    }

    public void setIntervalleDebutAge(int intervalleDebutAge) {
        this.intervalleDebutAge = intervalleDebutAge;
    }

    public int getIntervalleFinAge() {
        return intervalleFinAge;
    }

    public void setIntervalleFinAge(int intervalleFinAge) {
        this.intervalleFinAge = intervalleFinAge;
    }

    public String getNomMaladie() {
        return nomMaladie;
    }

    public void setNomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Maladie(Integer id, String nomMaladie, int intervalleDebutAge, int intervalleFinAge) {
        this.id = id;
        this.nomMaladie = nomMaladie;
        this.intervalleDebutAge = intervalleDebutAge;
        this.intervalleFinAge = intervalleFinAge;
    }

    public Maladie(String nomMaladie, int intervalleDebutAge, int intervalleFinAge) {
        this.nomMaladie = nomMaladie;
        this.intervalleDebutAge = intervalleDebutAge;
        this.intervalleFinAge = intervalleFinAge;
    }

    public Maladie() {
    }

    @Override
    public String toString() {
        return "Maladie{" +
                "id=" + id +
                ", nomMaladie='" + nomMaladie + '\'' +
                ", intervalleDebutAge=" + intervalleDebutAge +
                ", intervalleFinAge=" + intervalleFinAge +
                '}';
    }
    public List<SymptomeMaladie> getListSymptomeMaladie(SymptomeMaladieRepository symptomeMaladieRepository){
        return symptomeMaladieRepository.getAllByMaladie(this);
    }

    public static List<Maladie> getListeMaladieOfPatient(SymptomeMaladieRepository symptomeMaladieRepository, SymptomePatientRepository symptomePatientRepository, Patient patient){
        //nouvelle liste
        List<Maladie> realMaladies=new ArrayList<>();
        // 1 get possible maladies
        List<SymptomePatient> symptomePatientList=symptomePatientRepository.getSymptomePatientByPatient(patient);
        List<Maladie> maladies1=symptomeMaladieRepository.getMaladieInListSymptomePatient(symptomePatientList, patient.getId());

        // test si tout les symptomes du patient correspondent à une maladie de la liste
        for (int i = 0; i < maladies1.size(); i++) {
            try{
                realMaladies.add(maladies1.get(i).testSiLaMaladieCorrespond(symptomeMaladieRepository,symptomePatientList));
            }
            catch (Exception e){
                System.out.println("+++++");
                System.out.println(e.getMessage());
                System.out.println("+++++");
            }
        }
        // retourne la liste des maladies
        return realMaladies;

    }
    public Maladie testSiLaMaladieCorrespond (SymptomeMaladieRepository symptomeMaladieRepository,List<SymptomePatient> symptomePatientList) throws Exception {
        List<SymptomeMaladie> symptomeMaladies=this.getListSymptomeMaladie(symptomeMaladieRepository);
        int nombreSymptomes=0;
        for (int i = 0; i < symptomeMaladies.size(); i++) {
            for (int j = 0; j < symptomePatientList.size(); j++) {
                if(symptomePatientList.get(j).getId().equals(symptomeMaladies.get(i).getSymptome().getId())){
                    int niveau=symptomePatientList.get(j).getNiveauSymptome();
                    int inf=symptomeMaladies.get(i).getDebutNiveauSymptome();
                    int sup=symptomeMaladies.get(i).getFinNiveauSymptome();
                    int age=symptomePatientList.get(j).getPatient().getAgePatient();
                    int ageinf=symptomeMaladies.get(i).getMaladie().getIntervalleDebutAge();
                    int agesup=symptomeMaladies.get(i).getMaladie().getIntervalleFinAge();
                    if((niveau>=inf && niveau<=sup)||(age>=ageinf && age<=agesup)){
                        nombreSymptomes++;
                    }
                }
            }
        }
        if(nombreSymptomes!=symptomeMaladies.size()){
            throw new Exception("not this disease");
        }
        return this;
    }
}
