package com.Libra.khawla.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Libra.khawla.entities.Utilisateur;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	Utilisateur findByLogin(String login);
	public Optional<Utilisateur> findUserByLogin(String login);
	Boolean existsByLogin(String login);
	
	List <Utilisateur> findByNom(String nom);
}