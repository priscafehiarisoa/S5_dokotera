package s5_dokotera.model;

import jakarta.persistence.*;

@Entity
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nomMedicament;
    private int ageDebut;
    private int ageFin;
    private double prixMedicament;

    public double getPrixMedicament() {
        return prixMedicament;
    }

    public void setPrixMedicament(double prixMedicament) {
        this.prixMedicament = prixMedicament;
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    public int getAgeDebut() {
        return ageDebut;
    }

    public void setAgeDebut(int ageDebut) {
        this.ageDebut = ageDebut;
    }

    public int getAgeFin() {
        return ageFin;
    }

    public void setAgeFin(int ageFin) {
        this.ageFin = ageFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medicament(Integer id, String nomMedicament, int ageDebut, int ageFin,double prixMedicament) {
        setId(id);
        setNomMedicament(nomMedicament);
        setAgeDebut(ageDebut);
        setAgeFin(ageFin);
        setPrixMedicament(prixMedicament);
    }
    public Medicament( String nomMedicament, int ageDebut, int ageFin,double prixMedicament) {
        setNomMedicament(nomMedicament);
        setAgeDebut(ageDebut);
        setAgeFin(ageFin);
        setPrixMedicament(prixMedicament);
    }

    public Medicament() {
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", nomMedicament='" + nomMedicament + '\'' +
                ", ageDebut=" + ageDebut +
                ", ageFin=" + ageFin +
                ", prixMedicament=" + prixMedicament +
                '}';
    }
}
