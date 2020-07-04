package com.bohniman.vmsmaintenance.service;

import java.util.List;
import java.util.Optional;

import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.Role;
import com.bohniman.vmsmaintenance.model.User;
import com.bohniman.vmsmaintenance.repository.MasterVehicleRepository;
import com.bohniman.vmsmaintenance.repository.RoleRepository;
import com.bohniman.vmsmaintenance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {

        @Autowired
        UserRepository userRepository;

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        MasterVehicleRepository masterVehicleRepository;

        public Optional<User> findUserByUsername(String username) {
                return userRepository.findByUsername(username);
        }

        public void save(User user) {
                userRepository.save(user);
        }

        public Role findRoleByRole(String string) {
                return roleRepository.findByRole(string);
        }

        public List<MasterVehicle> getVehicleList(String vehicleNo) {
                return masterVehicleRepository.findAllByVehicleRegistrationNo(vehicleNo);
        }

}