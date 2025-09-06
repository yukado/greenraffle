package com.yukado.greenraffle.service;

import com.yukado.greenraffle.dto.UserDto;
import com.yukado.greenraffle.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
	User save(UserDto userDto); // für Registrierung
	User save(User user);       // für Updates
	User findByEmail(String email);
	Page<User> findPaginatedUsers(String search, Pageable pageable);
	void deleteById(Long id);   // void statt User
	Optional <User> findUserByEmail(String email);

	/**
	 * Aktualisiert die Benutzerdaten.
	 *
	 * Falls ein neues Passwort eingegeben wurde (nicht leer), wird dieses mittels BCrypt verschlüsselt.
	 *
	 * @param user Der User mit den neuen Daten.
	 * @return true, wenn die Aktualisierung erfolgreich war, sonst false.
	 */
	boolean updateUser(User user);

	/**
	 * Löscht den User anhand der ID.
	 *
	 * @param id Die User-ID.
	 * @return true, wenn der Löschvorgang erfolgreich war, sonst false.
	 */
	boolean deleteUser(Long id);

	User findById(Long id);
}
