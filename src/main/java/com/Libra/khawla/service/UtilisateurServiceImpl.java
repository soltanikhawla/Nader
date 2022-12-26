package com.Libra.khawla.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Libra.khawla.entities.Utilisateur;
import com.Libra.khawla.repository.UtilisateurRepository;





@Service
public class UtilisateurServiceImpl implements UtilisateurService{
    
	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Override
	public Utilisateur saveUtilisateur(Utilisateur p) {
		
		return utilisateurRepository.save(p);
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur u) {
		return utilisateurRepository.save(u);
	}

	@Override
	public void deleteUtilisateur(Utilisateur u) {
		utilisateurRepository.delete(u);
		
	}

	@Override
	public void deleteUtilisateurById(Long id) {
		utilisateurRepository.deleteById(id);
	}
   
	@Override
	public Utilisateur getUtilisateur(Long id) {
		return utilisateurRepository.findById(id).get();
	}

	@Override
	public List<Utilisateur> getAllUtilisateur() {
		return utilisateurRepository.findAll();
		}
	

}