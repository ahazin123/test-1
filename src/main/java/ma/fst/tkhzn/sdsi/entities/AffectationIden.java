package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AffectationIden implements Serializable{
	

	private static final long serialVersionUID = 1L;

	    @NotNull
	    private String Personne_dep;

	    @NotNull
	    private String code_ressource;


	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        AffectationIden inscriptionIdent  = (AffectationIden) o;
	        return Personne_dep.equals(inscriptionIdent.Personne_dep) &&
	        	   code_ressource.equals(inscriptionIdent.code_ressource);
	    }

	    
	    @Override
	    public int hashCode() {
	        return Objects.hash(Personne_dep, code_ressource);
	    }


}
