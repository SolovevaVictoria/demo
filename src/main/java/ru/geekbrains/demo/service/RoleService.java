package ru.geekbrains.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import ru.geekbrains.demo.model.Role;
import ru.geekbrains.demo.model.User;
//import ru.geekbrains.demo.repository.RoleRepository;
import ru.geekbrains.demo.repository.UserRepository;

//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
////    @Service
//    public class RoleService {
//
//        private final RoleRepository roleRepository;
//
//        @Autowired
//        public RoleService(RoleRepository roleRepository) {
//            this.roleRepository = roleRepository;
//        }
//
//        public Role getById(Long id) {
//            Optional<Role> readerOptional = roleRepository.findById(id);
//            return readerOptional.orElseThrow(() ->
//                    new NoSuchElementException("Не найдена роль с идентификатором \"" + id + "\""));
//        }
//
//        public Role addRole(Role role) {
//            return roleRepository.save(role);
//        }
//
//        public void deleteRole(Long id) {
//            roleRepository.deleteById(id);
//        }
//
//        public List<Role> getAllRoles() {
//            return roleRepository.findAll();
//        }
//    }
//
