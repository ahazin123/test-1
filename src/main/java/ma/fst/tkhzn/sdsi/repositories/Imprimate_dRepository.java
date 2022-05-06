package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.Imprimante_d;
import ma.fst.tkhzn.sdsi.entities.Ressource_d;
import ma.fst.tkhzn.sdsi.responses.ImprimanteR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface Imprimate_dRepository extends JpaRepository<Imprimante_d,String> {
   @Query("select R from Imprimante_d R where R.dep = :x")
   public List<Imprimante_d> findRess_dep(@Param("x")Long dep);

   /* @Query("select o.code from Imprimante_d o where o.resolution=:x and o.vitesse=:y  and o.marque=:t ")
    public String getCodeImp(@Param("x") Float resolution, @Param("y") Float vitesse, @Param("t") String marque);*/
   @Query("select R from Imprimante_d R where R.user.login = :x")
   public List<Imprimante_d> findRess_d(@Param("x")String login);

   @Query("select R from Imprimante_d R where R.code = :x")
   public Imprimante_d findByCode(@Param("x")Long code);

   @Query("select r from Imprimante_d r where r.id_demande=:x and r.checked=1")
   public List<Imprimante_d> listerRess_d(@Param("x") int id_demande);

   @Transactional
   @Modifying
   @Query("update Imprimante_d d set d.groupe=:x where d.code=:y")
   public void setGroupe(@Param("x")Long groupe,@Param("y")Long code);
}
