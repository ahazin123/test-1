package ma.fst.tkhzn.sdsi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import ma.fst.tkhzn.sdsi.entities.Utilisateur;
import ma.fst.tkhzn.sdsi.entities.Ressource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Affectation implements Serializable{

	private static final long serialVersionUID = 1L;
	

    @EmbeddedId
    private AffectationIden affectationIden;

	private Date date_affectation;

    public void setAffectation(String ressource,String pers_dep){
        affectationIden.setPersonne_dep(pers_dep);
        affectationIden.setCode_ressource(ressource);
    }


//    @Id
//	private Long id;

//    @ManyToOne
//	Utilisateur pers_dep;
////	private String login;
//
//    @Id
//    @ManyToOne
//	Ressource code_ressource;
////	private Long code_ress;

//	@ManyToMany






}
