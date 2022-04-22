package ma.fst.tkhzn.sdsi.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.fst.tkhzn.sdsi.entities.Departement;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Livraison {
    private Long code;
    private String nom;
    private String type;
    private Integer qte;
    private List<Departement_> departements;
}
