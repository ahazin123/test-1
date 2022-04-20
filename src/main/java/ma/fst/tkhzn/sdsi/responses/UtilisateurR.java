package ma.fst.tkhzn.sdsi.responses;

import lombok.ToString;
import ma.fst.tkhzn.sdsi.entities.Utilisateur;

import java.io.Serializable;
import java.util.ArrayList;

@ToString
public class UtilisateurR implements Serializable {
    private String login;
    private String nom;
    private String prenom;
    private String pwd;
    private Boolean active;
    private String role;

    public UtilisateurR() {

    }

    public UtilisateurR(Utilisateur user){
        this.login=user.getLogin();
        this.nom=user.getNom();
    }

    public UtilisateurR(String login,  String nom, String prenom, String pwd,Boolean act,String role) {
        super();
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.active=act;
        this.role=role;
    }
}
