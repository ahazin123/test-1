package ma.fst.tkhzn.sdsi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fst.tkhzn.sdsi.entities.Fournisseur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffreResponse implements Serializable {

        private Long groupe;
        private float prix_unit;
        private int duree_garantie;


}
