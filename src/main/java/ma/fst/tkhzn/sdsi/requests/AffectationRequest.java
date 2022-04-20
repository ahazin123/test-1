package ma.fst.tkhzn.sdsi.requests;

import java.util.Date;

import lombok.Data;
import ma.fst.tkhzn.sdsi.entities.Affectation;
import ma.fst.tkhzn.sdsi.entities.Ressource;
import ma.fst.tkhzn.sdsi.entities.Utilisateur;


@Data
public class AffectationRequest {
	String ressource;
	String pers_dep;
	Long dep;
}
