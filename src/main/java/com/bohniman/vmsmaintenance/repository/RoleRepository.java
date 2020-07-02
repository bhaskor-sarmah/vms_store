package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByRole(String string);
    
}