package ma.fst.tkhzn.sdsi.requests;


import lombok.Data;
import ma.fst.tkhzn.sdsi.entities.Enseignant;
import ma.fst.tkhzn.sdsi.entities.Imprimante_d;
import ma.fst.tkhzn.sdsi.entities.Ordinateur_d;
@Data
public class DemandeRequest {
    private Ordinateur_d ordinateur_d;
    private Imprimante_d imprimante_d;
    private String type;

}
