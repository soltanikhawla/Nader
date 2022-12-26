package com.Libra.khawla.ConfigSecurity;
import com.Libra.khawla.entities.Utilisateur;
import com.Libra.khawla.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	Utilisateur utilisateur = utilisateurRepository.findByLogin(username);
 
        return utilisateur;
    }


}