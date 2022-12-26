package com.Libra.khawla.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
public class Utilisateur implements Serializable, UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String Nom;
	private String Prenom;
	private String Login;
	private String Password;
	
	public Utilisateur() {
		super();
	}
	public Utilisateur(Long id, String nom, String prenom, String login, String password) {
		super();
		this.id = id;
		Nom = nom;
		Prenom = prenom;
		Login = login;
		Password = password;
	}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn( name = "id_role", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Role role;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Login=" + Login + ", Password="
				+ Password + "]";
	}
	
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getUsername() {
		// TODO Auto-generated method stub
		return Login;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
