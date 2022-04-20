package ma.fst.tkhzn.sdsi.responses;

import ma.fst.tkhzn.sdsi.entities.Ordinateur_d;

import java.io.Serializable;

public class OrdinateurR extends Ressource_DR implements Serializable {
    private String cpu;
    private int disque_d;
    private float ecran;
    private String marque;
    private int ram;

//    public OrdinateurR(Long code, UtilisateurR user, int qteD, int id_demande, String cpu, int disque_d, float ecran, String marque, int ram) {
//        super(code, qteD, id_demande);
//        this.cpu = cpu;
//        this.disque_d = disque_d;
//        this.ecran = ecran;
//        this.marque = marque;
//        this.ram = ram;
//    }

    public OrdinateurR() {
        super();
    }

    public OrdinateurR(Ordinateur_d ord) {
        super(ord.getCode(),ord.getQteD(),ord.getId_demande());
        this.cpu = ord.getCpu();
        this.disque_d = ord.getDisque_d();
        this.ecran = ord.getEcran();
        this.marque = ord.getMarque();
        this.ram = ord.getRam();
    }

    @Override
    public String toString() {
        return super.toString() + " OrdinateurR{" +
                "cpu='" + cpu + '\'' +
                ", disque_d=" + disque_d +
                ", ecran=" + ecran +
                ", marque='" + marque + '\'' +
                ", ram=" + ram +
                '}';
    }

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

