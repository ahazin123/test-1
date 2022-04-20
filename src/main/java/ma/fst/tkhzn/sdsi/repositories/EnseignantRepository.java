package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.Enseignant;
import ma.fst.tkhzn.sdsi.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, String>{

public Enseignant findByLogin(String login);

}
