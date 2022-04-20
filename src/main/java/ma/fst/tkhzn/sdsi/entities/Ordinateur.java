package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @ToString
public class Ordinateur extends Ressource implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String cpu;
    private int dd;
    private float ecran;
    private String marque;
    private int ram;

    public Ordinateur(String code, Date dateLiv, int dureeGarantie, boolean estAffecter, Fournisseur fournisseur, Utilisateur responsable, String cpu, int dd, float ecran, String marque, int ram) {
        super(code, dateLiv, dureeGarantie, estAffecter, fournisseur);
        this.cpu = cpu;
        this.dd = dd;
        this.ecran = ecran;
        this.marque = marque;
        this.ram = ram;
    }

    public Ordinateur(Ordinateur ord) {
        super(ord.getCode(), ord.getDateLiv(), ord.getDureeGarantie(), ord.isEstAffecter(), ord.getFournisseur());
        this.cpu = ord.getCpu();
        this.dd = ord.getDd();
        this.ecran = ord.getEcran();
        this.marque = ord.getMarque();
        this.ram = ord.getRam();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public float getEcran() {
        return ecran;
    }

    public void setEcran(float ecran) {
        this.ecran = ecran;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
