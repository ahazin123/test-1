package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {
    @Query("select o from Offre o where o.etat=0")
    public List<Offre> getOffreByEtat();

    @Query("select o from Offre o where o.fournisseur = :x")
    public List<Offre> getOffreByFournisseur(@Param("x")String fournisseur);

    @Transactional
    @Modifying
    @Query("update Offre o set o.etat = :x where o.id = :y")
    public void setEtat(@Param("x")int etat,@Param("y")Long id);

}
