package ma.fst.tkhzn.sdsi.business;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.fst.tkhzn.sdsi.entities.Enseignant;
import ma.fst.tkhzn.sdsi.entities.Fournisseur;
import ma.fst.tkhzn.sdsi.repositories.EnseignantRepository;
import ma.fst.tkhzn.sdsi.repositories.FournisseurRepository;
import ma.fst.tkhzn.sdsi.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import ma.fst.tkhzn.sdsi.entities.Utilisateur;
import ma.fst.tkhzn.sdsi.repositories.UtilisateurRepository;
import ma.fst.tkhzn.sdsi.requests.ChangePasswordRequest;
import ma.fst.tkhzn.sdsi.responses.UserInfo;



@RestController
@RequestMapping(value = {"api/userservice"})
@CrossOrigin("*")
public class UserService {
	@Autowired
	private UtilisateurRepository iUserRepository;

	@Autowired
	private EnseignantRepository enseignantRepository;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private FournisseurRepository fournisseurRepository;
	
	@GetMapping("/fournisseurs")
	public List<Fournisseur> fournisseurs() {
		return fournisseurRepository.findAll();
	}
	
	@GetMapping("/userInfo")
	public ResponseEntity<?> getUserInfo(Principal user){
		Utilisateur userObj=(Utilisateur) userDetailsService.loadUserByUsername(user.getName());
		
		UserInfo userInfo=new UserInfo();
		userInfo.setFirstName(userObj.getNom());
		userInfo.setLastName(userObj.getPrenom());
		userInfo.setUserName(userObj.getLogin());
		userInfo.setRoles(userObj.getAuthorities().toArray());
		
		return ResponseEntity.ok(userInfo);
	}
	
	@RequestMapping(path = "/editPwd", method = RequestMethod.POST)
	public Boolean editPsswd(@RequestBody ChangePasswordRequest loggedUser) {
		Utilisateur user= iUserRepository.findByLogin(loggedUser.getLogin());		
		System.err.println(user);
		System.out.println(loggedUser);
		if (user != null && checkIfValidOldPassword(user, loggedUser.getOrdPassword())) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(loggedUser.getNewPassword());
			user.setPwd(encodedPassword);
			user.setActive(true);
			iUserRepository.save(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(path = "/editInfo", method = RequestMethod.GET)
	public ResponseEntity<?> editInfo(HttpServletRequest req,@AuthenticationPrincipal UserDetails loggedUser) {
		 return ResponseEntity.ok(iUserRepository.findByLogin(loggedUser.getUsername()));
	}

	@RequestMapping(path = "/editInfoPerso", method = RequestMethod.GET)
	public void editInfoPerso(Utilisateur user) {
		user.setActive(true);
		iUserRepository.save(user);
	}

	@RequestMapping(path = "/editPwd", method = RequestMethod.GET)
	public ResponseEntity<?> editPwd(HttpServletRequest req,@AuthenticationPrincipal UserDetails loggedUser) {
		return ResponseEntity.ok(iUserRepository.findByLogin(loggedUser.getUsername()));
	}

	public boolean checkIfValidOldPassword(Utilisateur user,String ordPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(ordPassword, user.getPwd());
	}

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public List<Utilisateur> users() {
		return iUserRepository.findAll();
	}


	@RequestMapping(path = "/adduser", method = RequestMethod.POST)
	public void addUser(UserRequest userReq) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		ens.setPwd(encoder.encode(ens.getPwd()));
//		ens.setActive(true);
//		ens.setNomLab(ens.getNomLab());
//		enseignantRepository.save(ens);
		userReq.getUser().setPwd(encoder.encode(userReq.getUser().getPwd()));
		userReq.getUser().setActive(true);
		if(userReq.getUser().getRole().equals("Enseignant")) {
			enseignantRepository.save(userReq.getEnseignant());
		}
		iUserRepository.save(userReq.getUser());


	}

	@RequestMapping(path = "/editusers", method = RequestMethod.GET)
	public ResponseEntity<?> editUser(String login) {
		Utilisateur user=iUserRepository.findByLogin(login);
		Enseignant ens = enseignantRepository.findByLogin(login);
        if(user.getRole().equals("Enseignant")){
			return ResponseEntity.ok(ens);
		}
		return ResponseEntity.ok(user);
	}

	@RequestMapping(path = "/edituser", method = RequestMethod.POST)
	public void editUser(UserRequest userReq) {

		if(userReq.getUser().getRole().equals("Enseignant")) {
			enseignantRepository.save(userReq.getEnseignant());
		}
		iUserRepository.save(userReq.getUser());
	}


	@RequestMapping(path = "/deleteuser", method = RequestMethod.GET)
	public void deleteUser(String login) {
		Utilisateur user=iUserRepository.findByLogin(login);
        iUserRepository.delete(user);
	}

	public Boolean updateRessetPassword(String token,String email){
		Utilisateur user = iUserRepository.findByLogin(email);

		if(user != null){
			user.setRessetPasswordToken(token);
			iUserRepository.save(user);
			return true;
		}else{
			return false;
		}
	}
}
