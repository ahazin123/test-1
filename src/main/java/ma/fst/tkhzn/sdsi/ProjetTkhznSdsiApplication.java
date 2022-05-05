package ma.fst.tkhzn.sdsi;

import javax.annotation.PostConstruct;

import ma.fst.tkhzn.sdsi.entities.Message;
import ma.fst.tkhzn.sdsi.entities.Ressource;
import ma.fst.tkhzn.sdsi.repositories.DepartementRepository;
import ma.fst.tkhzn.sdsi.repositories.MessageRepository;
import ma.fst.tkhzn.sdsi.repositories.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.fst.tkhzn.sdsi.entities.Utilisateur;
import ma.fst.tkhzn.sdsi.repositories.UtilisateurRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ProjetTkhznSdsiApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	DepartementRepository departementRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
    @Autowired
	RessourceRepository ressourcerepository;
	@Autowired
	private MessageRepository messageRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetTkhznSdsiApplication.class, args);
	}

	@GetMapping("/test")
	public void test() {
		Message m1 = new Message();
		messageRepository.save(new Message(2678400L * 5));
		messageRepository.save(new Message(32140800L * 3));
		messageRepository.save(new Message(64281600L * 10));
	}
}
