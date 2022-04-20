package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.Imprimante_d;
import ma.fst.tkhzn.sdsi.entities.Ordinateur_d;
import ma.fst.tkhzn.sdsi.entities.Ressource_d;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Ordinateur_dRepository extends JpaRepository<Ordinateur_d,String> {

    @Query("select R from Ordinateur_d R where R.code = :x")
    public Ordinateur_d findByCode(@Param("x")Long code);

    @Query("select o.code from Ordinateur_d o where o.cpu=:x and o.disque_d=:y and o.ecran=:z and o.marque=:t and o.ram=:u")
    public String getCodeOrd(@Param("x") String cpu,@Param("y") Integer disque_d,@Param("z") Float ecran,@Param("t") String marque,@Param("u") Integer ram);

    @Query("select R from Ordinateur_d R where R.user.login = :x")
    public List<Ordinateur_d> findOrd_d(@Param("x")String login);

    @Query("select r from Ordinateur_d r where r.id_demande=:x and r.checked=1")
    public List<Ordinateur_d> listerRess_d(@Param("x") int id_demande);
}
