package ma.fst.tkhzn.sdsi.business;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.fst.tkhzn.sdsi.config.JWTTokenHelper;
import ma.fst.tkhzn.sdsi.entities.Utilisateur;
import ma.fst.tkhzn.sdsi.requests.AuthenticationRequest;
import ma.fst.tkhzn.sdsi.responses.LoginResponse;
import ma.fst.tkhzn.sdsi.responses.UserInfo;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenHelper jWTTokenHelper;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
		System.err.println(authenticationRequest.getLogin() + " methode login Dans la classe authentificationcontroller");
		
		final Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
					authenticationRequest.getLogin(), 
					authenticationRequest.getPassword()
			)
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Utilisateur user=(Utilisateur)authentication.getPrincipal();
		
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());
		
		LoginResponse response = new LoginResponse();
		response.setToken(jwtToken);
		response.setRole(user.getRole().toUpperCase());
		return ResponseEntity.ok(response);
	}
	
}
