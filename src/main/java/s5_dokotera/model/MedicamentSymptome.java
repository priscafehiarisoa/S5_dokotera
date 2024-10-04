package s5_dokotera.model;

import jakarta.persistence.*;

@Entity
public class MedicamentSymptome {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    private Medicament medicament;
    private int niveauGuerison;
    @ManyToOne
    @JoinColumn(name = "symptome_id")
    private Symptome symptome;

    public int getNiveauGuerison() {
        return niveauGuerison;
    }

    public void setNiveauGuerison(int niveauGuerison) {
        this.niveauGuerison = niveauGuerison;
    }

    public Symptome getSymptome() {
        return symptome;
    }

    public void setSymptome(Symptome symptome) {
        this.symptome = symptome;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MedicamentSymptome(Integer id, Medicament medicament, int niveauGuerison, Symptome symptome) {
        this.id = id;
        this.medicament = medicament;
        this.niveauGuerison = niveauGuerison;
        this.symptome = symptome;
    }

    public MedicamentSymptome(Medicament medicament, int niveauGuerison, Symptome symptome) {
        this.medicament = medicament;
        this.niveauGuerison = niveauGuerison;
        this.symptome = symptome;
    }

    public MedicamentSymptome() {
    }

    @Override
    public String toString() {
        return "MedicamentSymptome{" +
                "id=" + id +
                ", medicament=" + medicament +
                ", niveauGuerison=" + niveauGuerison +
                ", symptome=" + symptome +
                '}';
    }
}
