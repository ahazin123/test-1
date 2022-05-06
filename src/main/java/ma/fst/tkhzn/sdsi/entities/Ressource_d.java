package ma.fst.tkhzn.sdsi.entities;

        import java.io.Serializable;


        import javax.persistence.*;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Ressource_d implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private Long groupe;
    private int qteD;
    private int checked;
    private int id_demande;
    private Long dep;

    @ManyToOne
    @JoinColumn(name="id_user")
    private Utilisateur user;



}
