package com.Libra.khawla.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.Libra.khawla.entities.Utilisateur;


import com.Libra.khawla.service.UtilisateurService;

@CrossOrigin("http://localhost:8081/")
@RestController
@RequestMapping("/api")
public class UtilisateurController {
	
    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("/Utilisateur")
    @ResponseBody
    public List<Utilisateur>getAllUtilisateur()
    {
    	List<Utilisateur> list= utilisateurService.getAllUtilisateur();
    	return list;
    }
    @PostMapping("/AjouterUtilisateur")
    @ResponseBody
    public Utilisateur AjouterUtilisateur(@RequestBody Utilisateur u)
    {
	    return utilisateurService.saveUtilisateur(u);
    }
 // Delete User  
 	@GetMapping("/delete/{id}")
     public ResponseEntity<?> deleteUtilisateurById(@PathVariable Long id) {
 		utilisateurService.deleteUtilisateurById(id);
         return ResponseEntity.ok().body(id);
     }}