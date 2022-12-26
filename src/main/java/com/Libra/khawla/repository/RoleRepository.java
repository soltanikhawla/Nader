package com.Libra.khawla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Libra.khawla.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByLibelle(String libelle);
	

}
