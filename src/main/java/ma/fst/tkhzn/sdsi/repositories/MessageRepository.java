package ma.fst.tkhzn.sdsi.repositories;

import ma.fst.tkhzn.sdsi.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select M.* from message M where M.receivername=:x order by date desc", nativeQuery = true)
    List<Message> messageRecus(@Param("x")String receivername);
//    @Query("select m from Message m where m.receivername=:x order by date desc")
//    List<Message> findMessageByReceivername(@Param("x")String receivername) ;
//    @Query("select o from Offre o group by o.id.fournisseur ")
//    List<Offre> getOffreByFournisseur();
}
