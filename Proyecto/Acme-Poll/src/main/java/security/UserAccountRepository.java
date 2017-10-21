/*
 * UserAccountRepository.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	@Query("select ua from UserAccount ua where ua.username = ?1")
	UserAccount findByUsername(String username);

	@Query("select c from Actor c where c.userAccount.username = ?1")
	Actor findActorByUsername(String username);

	@Query("select c from Actor c where c.userAccount.id = ?1")
	Actor findActorByUsernameId(Integer username);

}
