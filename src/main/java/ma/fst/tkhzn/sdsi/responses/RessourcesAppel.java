package ma.fst.tkhzn.sdsi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RessourcesAppel implements Serializable{

        private OrdinateurR ord;
        private ImprimanteR imp;

}
