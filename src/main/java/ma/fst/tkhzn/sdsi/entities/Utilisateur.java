package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ma.fst.tkhzn.sdsi.entities.Authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("unused")
@Entity
@Data @NoArgsConstructor 

@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @Column(length = 150)
    private String login;
	private String nom;
	private String prenom;
	private String pwd;
	private Boolean active;	
	private String role;
	private String ressetPasswordToken;

//	@ManyToMany(mappedBy = "personnels")
//	private Collection<Ressource> ressources=new ArrayList<>();
//
	//constructeur
	public Utilisateur(String login,  String nom, String prenom, String pwd,Boolean act,String role) {
		super();
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.pwd = pwd;
		this.active=act;
		this.role=role;
	}

	@ManyToOne
	@JoinColumn(name="idDep", insertable=false,updatable=false )
	private Departement departement;

	public String getLogin() {
		return login;
	}

	public Utilisateur(Utilisateur user){
		this.login=user.getLogin();
		this.nom=user.getNom();
		this.departement=user.getDepartement();
		this.ressource_ds=new ArrayList<>();
	}
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	private List<Ressource_d> ressource_ds ;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<Authority> authorities = new ArrayList<>();
		authorities.add(new Authority(role));
		return authorities;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pwd;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return active;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return active;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return active;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}


	@Override
	public String toString() {
		return "Utilisateur [login=" + login + ", nom=" + nom + ", prenom=" + prenom + ", pwd=" + pwd + ", active="
				+ active + ", role=" + role + ", ressetPasswordToken=" + ressetPasswordToken + ", departement="
				+ departement + "]";
	}
	

	
	
	
}
