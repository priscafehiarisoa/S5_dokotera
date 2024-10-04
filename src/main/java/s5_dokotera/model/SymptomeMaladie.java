package s5_dokotera.model;

import jakarta.persistence.*;

@Entity
public class SymptomeMaladie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "maladie_id")
    private Maladie maladie;
    @ManyToOne
    @JoinColumn(name = "symptome_id")
    private Symptome symptome;
    private int debutNiveauSymptome;
    private int finNiveauSymptome;

    public int getDebutNiveauSymptome() {
        return debutNiveauSymptome;
    }

    public void setDebutNiveauSymptome(int debutNiveauSymptome) {
        this.debutNiveauSymptome = debutNiveauSymptome;
    }

    public int getFinNiveauSymptome() {
        return finNiveauSymptome;
    }

    public void setFinNiveauSymptome(int finNiveauSymptome) {
        this.finNiveauSymptome = finNiveauSymptome;
    }

    public Symptome getSymptome() {
        return symptome;
    }

    public void setSymptome(Symptome symptome) {
        this.symptome = symptome;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SymptomeMaladie(Integer id, Maladie maladie, Symptome symptome, int debutNiveauSymptome, int finNiveauSymptome) {
        setId(id);
        setMaladie(maladie);
        setSymptome(symptome);
        setDebutNiveauSymptome(debutNiveauSymptome);
        setFinNiveauSymptome(finNiveauSymptome);
    }
    public SymptomeMaladie( Maladie maladie, Symptome symptome, int debutNiveauSymptome, int finNiveauSymptome) {
        setMaladie(maladie);
        setSymptome(symptome);
        setDebutNiveauSymptome(debutNiveauSymptome);
        setFinNiveauSymptome(finNiveauSymptome);
    }

    public SymptomeMaladie() {
    }

    @Override
    public String toString() {
        return "SymptomeMaladie{" +
                "id=" + id +
                ", maladie=" + maladie +
                ", symptome=" + symptome +
                ", debutNiveauSymptome=" + debutNiveauSymptome +
                ", finNiveauSymptome=" + finNiveauSymptome +
                '}';
    }
}
