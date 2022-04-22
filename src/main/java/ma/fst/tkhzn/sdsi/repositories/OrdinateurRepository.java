package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.Ordinateur;
import ma.fst.tkhzn.sdsi.entities.Ordinateur_d;
import ma.fst.tkhzn.sdsi.entities.Ressource_d;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdinateurRepository extends JpaRepository<Ordinateur, Long>{
}
