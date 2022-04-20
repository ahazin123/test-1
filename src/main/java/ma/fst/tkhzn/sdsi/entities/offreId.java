package ma.fst.tkhzn.sdsi.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class offreId implements Serializable {
    @NotNull
    private String fournisseur;
    @NotNull
    private String ressource;

    public String getFournisseur() {
        return fournisseur;
    }

    public String getRessource() {
        return ressource;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }
}
