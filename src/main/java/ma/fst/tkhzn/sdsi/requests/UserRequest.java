package ma.fst.tkhzn.sdsi.requests;

import lombok.Data;
import ma.fst.tkhzn.sdsi.entities.Enseignant;
import ma.fst.tkhzn.sdsi.entities.Fournisseur;
import ma.fst.tkhzn.sdsi.entities.Utilisateur;

@Data
public class UserRequest {
    private String role;
    private Utilisateur user;
    private Enseignant enseignant;
    private Fournisseur fournisseur;
}
