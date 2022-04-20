package ma.fst.tkhzn.sdsi.requests;

import lombok.Data;
import ma.fst.tkhzn.sdsi.entities.Enseignant;
import ma.fst.tkhzn.sdsi.entities.Utilisateur;

@Data
public class UserRequest {

    Utilisateur user;
    Enseignant enseignant;
}
