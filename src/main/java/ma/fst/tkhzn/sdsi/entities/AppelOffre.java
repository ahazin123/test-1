package ma.fst.tkhzn.sdsi.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppelOffre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

/*private ArrayList <Demande> listeDemande;

    public boolean addDemande(Demande demande) {
        if (listeDemande.isEmpty())
            listeDemande.add(demande);
        else {
            for (Demande d : listeDemande) {
                if (d.equals(demande))
                    return false;
                else {
                    listeDemande.add(demande);
                    return true;
                }
            }
        }
        return false;
    }
        public boolean deleteDemande (Demande demande) {
            if (listeDemande.isEmpty())
                return false;
            else {
                for (Demande d : listeDemande) {
                    if (d.equals(demande)) {
                        listeDemande.remove(demande);
                        return true;
                    }
                    else
                        return false;
                }
            }
            return false;
        }
*/
    }