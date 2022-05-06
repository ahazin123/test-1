package ma.fst.tkhzn.sdsi.requests;

import lombok.Data;

import java.io.Serializable;
@Data
public class OffreRequest implements Serializable {
    private Long groupe;
    private String fournisseur;
    private Float prix_unit;
    private int duree_garantie;

}
