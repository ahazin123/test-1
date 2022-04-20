package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@ToString
public class PersonneDep extends Utilisateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="idDep", insertable=false,updatable=false )
	private Departement departement;



	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}




	public PersonneDep(String login, String nom, String prenom, String pwd, Boolean act, String role, Departement departement) {
		super(login, nom, prenom, pwd, act, role);
		this.departement = departement;
	}
}
