package com.yukado.greenraffle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yukado.greenraffle.dto.UserDto;
import com.yukado.greenraffle.model.User;
import com.yukado.greenraffle.repo.UserRepo;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public User save(UserDto userDto) {
		// Mapping UserDto -> User
		User user = new User();
		user.setEmail(userDto.getEmail());
		// Stellen Sie sicher, dass der PasswortEncoder eingerichtet ist.
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole(userDto.getRole());
		user.setVorname(userDto.getVorname());
		user.setNachname(userDto.getNachname());
		user.setGebdatum(userDto.getGebdatum());
		user.setCountry(userDto.getCountry());
		user.setIban(userDto.getIban());

		return userRepo.save(user);
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepo.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findPaginatedUsers(String search, Pageable pageable) {
		return userRepo.findByEmailContainingIgnoreCase(search, pageable);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public User findById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public Optional <User> findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}

	@Override
	public boolean updateUser(User user) {
		Optional<User> existingOpt = userRepo.findById(user.getId());
		if (existingOpt.isPresent()) {
			User existingUser = existingOpt.get();
			// Aktualisiere die einzelnen Felder
			existingUser.setEmail(user.getEmail());
			// Nur bei einer Eingabe wird das Passwort aktualisiert
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				String encodedPassword = passwordEncoder.encode(user.getPassword());
				existingUser.setPassword(encodedPassword);
			}
			existingUser.setVorname(user.getVorname());
			existingUser.setNachname(user.getNachname());
			existingUser.setGebdatum(user.getGebdatum());
			existingUser.setCountry(user.getCountry());
			existingUser.setIban(user.getIban());

			userRepo.save(existingUser);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(Long id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
