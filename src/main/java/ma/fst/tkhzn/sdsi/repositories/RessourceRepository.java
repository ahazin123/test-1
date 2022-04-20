package ma.fst.tkhzn.sdsi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.fst.tkhzn.sdsi.entities.Ressource;

public interface RessourceRepository extends JpaRepository<Ressource, Long>{

	@Query("select R.code from Ressource R")
	public List<String> findLastId();

	@Query("select R from Ressource R where R.estAffecter=true")
    public List<Ressource> findAllDisp();

	@Query("select R from Ressource R where R.code = :x")
	public Ressource findRess(@Param("x")String code);


	//jamais affecter
	@Query("select R from Ressource R where R.estAffecter=false")
    public List<Ressource> findAllLiv();

	@Transactional
	@Modifying
	@Query("delete from Ressource where code = :x")
	public void deleteByCode(@Param("x")String code);
//@Transactional
//@Query("delete R from Ressource* R where R.code= :id")
//public void deleteResource(@Param("id") Long code_ressource);
	
	
}
