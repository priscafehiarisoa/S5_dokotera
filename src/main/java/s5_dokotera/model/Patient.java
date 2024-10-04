package s5_dokotera.model;

import jakarta.persistence.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nomPatient;
    private int agePatient;

    public Patient() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public int getAgePatient() {
        return agePatient;
    }

    public void setAgePatient(int agePatient) {
        this.agePatient = agePatient;
    }

    public Patient(Integer id, String nomPatient, int agePatient) {
        this.id = id;
        this.nomPatient = nomPatient;
        this.agePatient = agePatient;
    }

    public Patient(String nomPatient, int agePatient) {
        this.nomPatient = nomPatient;
        this.agePatient = agePatient;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", nomPatient='" + nomPatient + '\'' +
                ", agePatient=" + agePatient +
                '}';
    }
}
