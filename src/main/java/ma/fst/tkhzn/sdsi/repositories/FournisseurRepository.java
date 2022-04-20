package ma.fst.tkhzn.sdsi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fst.tkhzn.sdsi.entities.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, String>{

	Fournisseur findByLogin(String fournisseur);

}
