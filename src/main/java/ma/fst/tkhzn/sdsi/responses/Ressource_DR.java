package ma.fst.tkhzn.sdsi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data @NoArgsConstructor @ToString
public class Ressource_DR implements Serializable {
    private Long code;
//    private UtilisateurR user;
    private int qteD;
    private int id_demande;

    public Ressource_DR(Long code, int qteD, int id_demande) {
        this.code = code;
        this.qteD = qteD;
        this.id_demande = id_demande;
    }
}
