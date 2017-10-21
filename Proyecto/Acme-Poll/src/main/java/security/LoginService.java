/*
 * LoginService.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;

@Service
@Transactional
public class LoginService implements UserDetailsService {

	// Managed repository -----------------------------------------------------

	@Autowired
	UserAccountRepository userRepository;


	// Business methods -------------------------------------------------------

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		Assert.notNull(username);

		UserDetails result;

		result = this.userRepository.findByUsername(username);
		Assert.notNull(result);
		// WARNING: The following sentences prevent lazy initialisation problems!
		Assert.notNull(result.getAuthorities());
		result.getAuthorities().size();

		return result;
	}

	public static boolean isAnyAuthenticated() {
		try {
			SecurityContext context;
			Authentication authentication;
			Object principal;

			context = SecurityContextHolder.getContext();
			authentication = context.getAuthentication();
			principal = authentication.getPrincipal();

			return principal instanceof UserAccount;
		} catch (final Throwable t) {
			return false;
		}
	}

	private static UserAccount getAuthenticated() {
		try {
			UserAccount result;
			SecurityContext context;
			Authentication authentication;
			Object principal;

			context = SecurityContextHolder.getContext();
			authentication = context.getAuthentication();
			principal = authentication.getPrincipal();
			result = (UserAccount) principal;

			return result;
		} catch (final Throwable t) {
			return null;
		}
	}

	public static boolean hasRole(final String role) {
		final UserAccount account = LoginService.getAuthenticated();

		if (account == null)
			return false;

		for (final Authority e : account.getAuthorities())
			if (e.getAuthority().equalsIgnoreCase(role))
				return true;

		return false;
	}

	public Actor findActorByUsername(final String username) {
		return this.userRepository.findActorByUsername(LoginService.getPrincipal().getUsername());
	}

	public Actor findActorByUsername(final Integer id) {
		return this.userRepository.findActorByUsernameId(id);
	}

	public static UserAccount getPrincipal() {
		UserAccount result;
		SecurityContext context;
		Authentication authentication;
		Object principal;

		// If the asserts in this method fail, then you're
		// likely to have your Tomcat's working directory
		// corrupt. Please, clear your browser's cache, stop
		// Tomcat, update your Maven's project configuration,
		// clean your project, clean Tomcat's working directory,
		// republish your project, and start it over.

		context = SecurityContextHolder.getContext();
		Assert.notNull(context);
		authentication = context.getAuthentication();
		Assert.notNull(authentication);
		principal = authentication.getPrincipal();
		Assert.isTrue(principal instanceof UserAccount);
		result = (UserAccount) principal;
		Assert.notNull(result);
		Assert.isTrue(result.getId() != 0);

		return result;
	}

	public boolean exists(final Integer id) {
		return this.userRepository.exists(id);
	}

	public UserAccount findOne(final Integer id) {
		return this.userRepository.findOne(id);
	}

	public Actor selectSelf() {
		return userRepository.findActorByUsernameId(LoginService.getPrincipal().getId());
	}
}
