package s5_dokotera.model;

import jakarta.persistence.*;

@Entity
public class Symptome {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
     Integer id;
     String nomSymptome;

    public String getNomSymptome() {
        return nomSymptome;
    }

    public void setNomSymptome(String nomSymptome) {
        this.nomSymptome = nomSymptome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Symptome(Integer id, String nomSymptome) {
        this.id = id;
        this.nomSymptome = nomSymptome;
    }

    public Symptome(String nomSymptome) {
        this.nomSymptome = nomSymptome;
    }

    public Symptome() {
    }

    @Override
    public String toString() {
        return "Symptome{" +
                "id=" + id +
                ", nomSymptome='" + nomSymptome + '\'' +
                '}';
    }
}
