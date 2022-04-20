package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
public class Enseignant extends PersonneDep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomLab;

	public String getNomLab() {
		return nomLab;
	}

	public void setNomLab(String nomLab) {
		nomLab = nomLab;
	}

	public Enseignant(String login, String nom, String prenom, String pwd, Boolean act, String role, Departement departement, String nomLab) {
		super(login, nom, prenom, pwd, act, role, departement);
		nomLab = nomLab;
	}

	public Enseignant(String nomLab) {
		nomLab = nomLab;
	}
}
