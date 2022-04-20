package ma.fst.tkhzn.sdsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.fst.tkhzn.sdsi.entities.Utilisateur;
import ma.fst.tkhzn.sdsi.repositories.UtilisateurRepository;


@Service
public class CustomUserService implements UserDetailsService {
	
	@Autowired
	UtilisateurRepository utilisateurDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Utilisateur user=utilisateurDetailsRepository.findByLogin(username);
		System.err.println(user);
		if(null==user) {
			throw new UsernameNotFoundException("User Not Found with userName "+username);
		}
		return user;
	}

}
