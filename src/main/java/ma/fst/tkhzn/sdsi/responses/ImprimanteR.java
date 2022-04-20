package ma.fst.tkhzn.sdsi.responses;

import ma.fst.tkhzn.sdsi.entities.Imprimante_d;

import java.io.Serializable;

public class ImprimanteR extends Ressource_DR implements Serializable {
    private String marque;
    private float resolution;
    private float vitesse;

    public ImprimanteR() {}

    public ImprimanteR(Imprimante_d imp) {
        super(imp.getCode(),imp.getQteD(),imp.getId_demande());
        this.marque = imp.getMarque();
        this.resolution = imp.getResolution();
        this.vitesse = imp.getVitesse();
    }

    @Override
    public String toString() {
        return super.toString() + " ImprimanteR{" +
                "marque='" + marque + '\'' +
                ", resolution=" + resolution +
                ", vitesse=" + vitesse +
                '}';
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public float getResolution() {
        return resolution;
    }

    public void setResolution(float resolution) {
        this.resolution = resolution;
    }

    public float getVitesse() {
        return vitesse;
    }

    public void setVitesse(float vitesse) {
        this.vitesse = vitesse;
    }
}
