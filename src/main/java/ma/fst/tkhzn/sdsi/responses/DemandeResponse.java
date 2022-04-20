package ma.fst.tkhzn.sdsi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fst.tkhzn.sdsi.entities.Ressource_d;
import ma.fst.tkhzn.sdsi.entities.Utilisateur;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DemandeResponse {
    private List<Ressource_d> ressource_ds;
    private Utilisateur usr;
}
