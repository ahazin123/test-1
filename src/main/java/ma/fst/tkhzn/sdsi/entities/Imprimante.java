package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor @ToString
 
public class Imprimante extends Ressource implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String marque;
	private float resolution;
	private float vitesse;

	public Imprimante(String code, Date dateLiv, int dureeGarantie, boolean estAffecter, Fournisseur fournisseur, Utilisateur responsable,  String marque, float resolution, float vitesse) {
		super(code, dateLiv, dureeGarantie, estAffecter, fournisseur);
		this.marque = marque;
		this.resolution = resolution;
		this.vitesse = vitesse;
	}

	public Imprimante(Imprimante imp) {
		super(imp.getCode(), imp.getDateLiv(), imp.getDureeGarantie(), imp.isEstAffecter(), imp.getFournisseur());
		this.marque = imp.getMarque();
		this.resolution = imp.getResolution();
		this.vitesse = imp.getVitesse();
	}

}
