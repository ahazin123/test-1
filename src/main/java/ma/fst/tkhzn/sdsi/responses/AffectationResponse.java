package ma.fst.tkhzn.sdsi.responses;

import lombok.Data;
import ma.fst.tkhzn.sdsi.entities.Departement;
import ma.fst.tkhzn.sdsi.entities.PersonneDep;
import ma.fst.tkhzn.sdsi.entities.Ressource_personnels;
import ma.fst.tkhzn.sdsi.entities.Utilisateur;

import java.util.List;

@Data
public class AffectationResponse {

    private Ressource_personnels affectation;
    private List<Departement> departements;
    private List<Utilisateur> personnels;
}
