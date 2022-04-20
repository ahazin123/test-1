package ma.fst.tkhzn.sdsi.repositories;


import ma.fst.tkhzn.sdsi.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long>  {

        @Query("select D from Departement D WHERE D.id = :x")
        public Departement getDepartement(@Param("x")Long id);


}
