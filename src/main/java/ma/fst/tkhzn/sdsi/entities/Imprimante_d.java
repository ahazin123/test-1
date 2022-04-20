package ma.fst.tkhzn.sdsi.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Imprimante_d extends Ressource_d implements Serializable {
    private static final long serialVersionUID = 1L;
    private String marque;
    private float resolution;
    private float vitesse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Imprimante_d that = (Imprimante_d) o;
        return Float.compare(that.resolution, resolution) == 0 && Float.compare(that.vitesse, vitesse) == 0 && Objects.equals(marque, that.marque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), marque, resolution, vitesse);
    }

    public String getMarque() {
        return marque;
    }

    public float getResolution() {
        return resolution;
    }

    public float getVitesse() {
        return vitesse;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setResolution(float resolution) {
        this.resolution = resolution;
    }

    public void setVitesse(float vitesse) {
        this.vitesse = vitesse;
    }

    
}
