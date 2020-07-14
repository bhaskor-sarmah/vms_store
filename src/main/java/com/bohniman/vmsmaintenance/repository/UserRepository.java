package com.bohniman.vmsmaintenance.repository;

import java.util.List;
import java.util.Optional;

import com.bohniman.vmsmaintenance.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TransSuspectRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

	List<User> findByUsernameContaining(String searchText);

	List<User> findByUsernameContainingAndRoles_role(String searchText, String string);

}