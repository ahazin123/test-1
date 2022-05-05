package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @ToString @Data
public class Enseignant extends PersonneDep implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomLab;

	public Enseignant() {
	}

	public String getNomLab() {
		return nomLab;
	}

	public Enseignant(String login, String nom, String prenom, String pwd, Boolean act, String role, Departement departement, String nomLab) {
		super(login, nom, prenom, pwd, act, role, departement);
		this.nomLab = nomLab;
	}

	public Enseignant(Enseignant user) {
		super(user.getLogin(), user.getNom(), user.getPrenom(), user.getPwd(), user.getActive(), user.getRole(), null);
		this.nomLab = user.getNomLab();
	}

	public Enseignant(String nomLab){
		this.nomLab = nomLab;
	}

	public void setNomLab(String nomLab) {
		this.nomLab = nomLab;
	}
}
