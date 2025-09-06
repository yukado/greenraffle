package com.yukado.greenraffle.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		String role = authentication.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.findFirst()
				.orElse("");

		switch (role) {
			case "ADMIN" -> response.sendRedirect("/admin/admin");
			case "USER"  -> response.sendRedirect("/user/indexu"); // Absoluter Pfad
			default      -> response.sendRedirect("/error");
		}
	}
}

