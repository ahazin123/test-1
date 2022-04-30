package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.Ressource_personnels;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface AffectationRepository extends JpaRepository<Ressource_personnels, String>{
	
	@Query("SELECT R FROM Ressource_personnels R WHERE R.login_p= :x and R.code_r= :y")
	public Ressource_personnels getAffectation(@Param("x")String login_p,@Param("y")String code_r);

	@Transactional
	@Modifying
	@Query("delete from Ressource_personnels where login_p= :x and code_r= :y")
	public void deleteAff(@Param("x")String login_p,@Param("y")String code_r);



}
