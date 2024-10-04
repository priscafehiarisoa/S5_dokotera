package s5_dokotera.model;

import jakarta.persistence.*;

@Entity
public class SymptomePatient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "symptome_id")
    private Symptome symptome;
    private int niveauSymptome;


    public int getNiveauSymptome() {
        return niveauSymptome;
    }

    public void setNiveauSymptome(int niveauSymptome) {
        this.niveauSymptome = niveauSymptome;
    }


    public Symptome getSymptome() {
        return symptome;
    }

    public void setSymptome(Symptome symptome) {
        this.symptome = symptome;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SymptomePatient(Integer id, Patient patient, Symptome symptome, int niveauSymptome) {
        setId(id);
        setPatient(patient);
        setSymptome(symptome);
        setNiveauSymptome(niveauSymptome);
    }

    public SymptomePatient(Patient patient, Symptome symptome, int niveauSymptome) {
        setPatient(patient);
        setSymptome(symptome);
        setNiveauSymptome(niveauSymptome);
    }

    public SymptomePatient() {
    }

    @Override
    public String toString() {
        return "SymptomePatient{" +
                "id=" + id +
                ", patient=" + patient +
                ", symptome=" + symptome +
                ", niveauSymptome=" + niveauSymptome +
                '}';
    }
}
