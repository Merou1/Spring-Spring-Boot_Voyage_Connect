package com.VoyageConnect.AgenceDeVoyage.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.VoyageConnect.AgenceDeVoyage.entity.Role;
import com.VoyageConnect.AgenceDeVoyage.entity.User;
import com.VoyageConnect.AgenceDeVoyage.repository.RoleRepository;
import com.VoyageConnect.AgenceDeVoyage.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
    private  PasswordEncoder passwordEncoder;

	public void registerUser(String username, String password, String roleName) {
	    Optional<User> existingUser = userRepository.findByUsername(username);
	    if (existingUser.isPresent()) {
	        throw new IllegalArgumentException("Username already taken: " + username);
	    }

	    Role role = roleRepository.findByName(roleName)
	            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));

	    User user = new User();
	    user.setUsername(username);
	    user.setPassword(passwordEncoder.encode(password));  // Encoding the password before saving
	    user.setRoles(Set.of(role));  // Assigning the role

	    userRepository.save(user);
	}

}