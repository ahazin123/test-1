package ma.fst.tkhzn.sdsi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fournisseur;
    private Long groupe;
    private float prix_unit;
    private int duree_garantie;
    private int etat;

    public Offre(String fournisseur, Long groupe, float prix_unit, int duree_garantie, int etat) {
        this.fournisseur = fournisseur;
        this.groupe = groupe;
        this.prix_unit = prix_unit;
        this.duree_garantie = duree_garantie;
        this.etat = etat;
    }
}
