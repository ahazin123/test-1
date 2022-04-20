package ma.fst.tkhzn.sdsi.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
@Entity
public class Offre implements Serializable {
    @EmbeddedId
    offreId id;
    private float prix_unit;
    private int duree;

    public Offre(offreId id, float prix_unit, int duree) {
        this.id = id;
        this.prix_unit = prix_unit;
        this.duree = duree;
    }

    public Offre() {
    }

    public offreId getId() {
        return id;
    }

    public float getPrix_unit() {
        return prix_unit;
    }

    public int getDuree() {
        return duree;
    }

    public void setId(offreId id) {
        this.id = id;
    }

    public void setPrix_unit(float prix_unit) {
        this.prix_unit = prix_unit;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
