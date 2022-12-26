package com.Libra.khawla.controller;



import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Libra.khawla.ConfigSecurity.JwtUtil;
import com.Libra.khawla.ConfigSecurity.MyUserDetailsService;
import com.Libra.khawla.entities.AuthenticationResponse;
import com.Libra.khawla.entities.Utilisateur;

import io.jsonwebtoken.impl.DefaultClaims;

@CrossOrigin("/")
@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private  AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Utilisateur authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}



	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}


}
