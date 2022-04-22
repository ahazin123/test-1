package ma.fst.tkhzn.sdsi.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Departement_ {
    private Long id;
    private String nom;
    private Integer qte;
    private List<Personnel> personnels;
}
