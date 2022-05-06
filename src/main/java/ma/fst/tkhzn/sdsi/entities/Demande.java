package ma.fst.tkhzn.sdsi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Demande implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer valide;
    private Integer id_appel;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
