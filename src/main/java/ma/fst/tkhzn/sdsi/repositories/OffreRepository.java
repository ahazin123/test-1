package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.Offre;
import ma.fst.tkhzn.sdsi.entities.offreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, offreId> {
    @Query("select o from Offre o where o.id=:x ")
    public Offre getOffre(@Param("x") offreId id) ;
    @Query("select o from Offre o group by o.id.fournisseur ")
    public List<Offre> getOffreByFournisseur();
}
