package cours.java.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Ville implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200)
    private String libelle;

    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)

    private List<Rue> rues;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Rue> getRues() {
        return rues;
    }

    public void setRues(List<Rue> rues) {
        this.rues = rues;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
