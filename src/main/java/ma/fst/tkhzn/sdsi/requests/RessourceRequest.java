package ma.fst.tkhzn.sdsi.requests;

import lombok.Data;
import ma.fst.tkhzn.sdsi.entities.Fournisseur;
import ma.fst.tkhzn.sdsi.entities.Imprimante;
import ma.fst.tkhzn.sdsi.entities.Ordinateur;


@Data
public class RessourceRequest {
	Ordinateur ordi;
	Imprimante imp;
	String type;
	Fournisseur fournisseur;
	int qte;
	boolean nouv; //1 nouv 0:anc

}
