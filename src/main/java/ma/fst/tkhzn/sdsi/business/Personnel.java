package ma.fst.tkhzn.sdsi.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Personnel {
    private String login;
    private String nom;
    private int qte;
}
