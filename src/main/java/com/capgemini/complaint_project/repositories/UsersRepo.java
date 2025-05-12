package com.capgemini.complaint_project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.complaint_project.entities.User;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	Optional<User> findByNameOrEmail(String name, String email);
	
	
	Optional<User> findByEmail(String email);


	Optional<User> findByName(String name);

	boolean existsByName(String name);

	boolean existsByEmail(String email);
	
}
