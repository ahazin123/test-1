package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @ToString
public class Ordinateur_d extends Ressource_d implements Serializable{
    /**
     *
     */

    private static final long serialVersionUID = 1L;
    private String cpu;
    private int disque_d;
    private float ecran;
    private String marque;
    private int ram;


    //constructeur




    public String getCpu() {
        return cpu;
    }


    public void setCpu(String cpu) {
        this.cpu = cpu;
    }


    public int getDisque_d() {
        return disque_d;
    }

    public void setDisque_d(int disque_d) {
        this.disque_d = disque_d;
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
