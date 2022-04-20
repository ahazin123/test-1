package ma.fst.tkhzn.sdsi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor @ToString
@IdClass(Ressource_personnels.NN.class)
public class Ressource_personnels implements Serializable {

    @Id
    @Column(name="ressources_code")
    private String code_r;

    @Id
    @Column(name="personnels_login")
    private String login_p;

    private Date date_affectation;

    private Long departement;

    public static class NN implements Serializable
    {
        String code_r;
        String login_p;
    }

    public Ressource_personnels(String code_r, String login_p, Date date_affectation,Long dep) {
        this.code_r = code_r;
        this.login_p = login_p;
        this.date_affectation = date_affectation;
        this.departement=dep;
    }
}
