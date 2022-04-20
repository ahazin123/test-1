package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.fst.tkhzn.sdsi.entities.Utilisateur;

import java.util.List;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{

	public Utilisateur findByLogin(String Login);
	public Utilisateur findByRessetPasswordToken(String ressetPasswordToken);

	@Query("SELECT U FROM Utilisateur U WHERE U.departement= :x")
	public List<Utilisateur> findBydepartement(@Param("x") Departement dep);

}
