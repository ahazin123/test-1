package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;

import javax.persistence.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
public class Fournisseur extends Utilisateur implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String mail;
	
	private String gerant;
	private String lieu;
	private String nom_socite;
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGerant() {
		return gerant;
	}

	public Fournisseur(String login, String nom, String prenom, String pwd, Boolean act, String role, String mail, String gerant, String lieu, String nom_socite) {
		super(login, nom, prenom, pwd, act, role);
		this.mail = mail;
		this.gerant = gerant;
		this.lieu = lieu;
		this.nom_socite = nom_socite;
	}

	public Fournisseur(String login, String nom, String prenom, String pwd, Boolean act, String role) {
		super(login, nom, prenom, pwd, act, role);
	}

	public void setGerant(String gerant) {
		this.gerant = gerant;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getNomSocite() {
		return nom_socite;
	}
	public void setNomSocite(String nomSocite) {
		this.nom_socite = nomSocite;
	}
	@Override
	public String toString() {
		return "Fournisseur" + super.toString() +" [mail=" + mail + ", gerant=" + gerant + ", lieu=" + lieu + ", nom_socite=" + nom_socite
				+ "]";
	}
	
	

}
