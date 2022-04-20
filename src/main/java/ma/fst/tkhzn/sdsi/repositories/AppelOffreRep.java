package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.AppelOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppelOffreRep  extends JpaRepository<AppelOffre,Integer> {

    @Query("select max (o.id) from AppelOffre o")
    public int getId();
}
