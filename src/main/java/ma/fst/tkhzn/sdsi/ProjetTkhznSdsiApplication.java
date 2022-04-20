package ma.fst.tkhzn.sdsi;

import javax.annotation.PostConstruct;

import ma.fst.tkhzn.sdsi.entities.Ressource;
import ma.fst.tkhzn.sdsi.repositories.DepartementRepository;
import ma.fst.tkhzn.sdsi.repositories.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.fst.tkhzn.sdsi.entities.Utilisateur;
import ma.fst.tkhzn.sdsi.repositories.UtilisateurRepository;

import java.util.List;

@SpringBootApplication
public class ProjetTkhznSdsiApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	DepartementRepository departementRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
    @Autowired
	RessourceRepository ressourcerepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetTkhznSdsiApplication.class, args);
	}
}
