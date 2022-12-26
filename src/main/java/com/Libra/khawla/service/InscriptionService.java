package com.Libra.khawla.service;


import java.util.Optional;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.Libra.khawla.entities.Utilisateur;
import com.Libra.khawla.repository.RoleRepository;
import com.Libra.khawla.repository.UtilisateurRepository;
import com.Libra.khawla.entities.Role;

@Service
public class InscriptionService {
	
	final private UtilisateurRepository utilisateurRepository;
	@Autowired
	private final BCryptPasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	
	
	public InscriptionService(UtilisateurRepository utilisateurRepository, BCryptPasswordEncoder passwordEncoder,
			RoleRepository roleRepository) {
		this.utilisateurRepository = utilisateurRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Transactional
	public Optional<Utilisateur> findUserByLogin(String login){
		return utilisateurRepository.findUserByLogin(login);
	}
	
	public boolean userExists(String login) {
		return findUserByLogin(login).isPresent();
	}
	
	@Transactional
	public Utilisateur save(Utilisateur utilisateur) {
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		return utilisateurRepository.save(utilisateur);
	}


	public Utilisateur inscription(Utilisateur utilisateur) { 
		//password encryption
		String password = passwordEncoder.encode(utilisateur.getPassword());
		utilisateur.setPassword(password);
		Role role = roleRepository.findByLibelle("Admin");
		utilisateur.setRole(role);
		return utilisateurRepository.save(utilisateur);
	}



}