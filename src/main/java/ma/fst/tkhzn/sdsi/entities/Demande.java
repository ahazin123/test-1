package ma.fst.tkhzn.sdsi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Demande {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int valide;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Utilisateur user_dem;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int id_appel;

    public Demande(Utilisateur user_dem) {
        this.user_dem = user_dem;
    }
}
