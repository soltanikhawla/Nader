package com.Libra.khawla.entities;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "T_Role")
public class Role {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "libelle")
	private String libelle;
	
	@JsonIgnore()
	@OneToMany(mappedBy = "role")
	private List<Utilisateur>utilisateur;

	
	
	public Role() {
		
	}
	
	public Role(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Utilisateur> getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(List<Utilisateur> utilisateur) {
		this.utilisateur = utilisateur;
	}






	

}