//package ru.geekbrains.demo.api;
//
//
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.geekbrains.demo.model.Role;
//import ru.geekbrains.demo.model.User;
//import ru.geekbrains.demo.service.RoleService;
//
////@RestController
////@RequestMapping("/roles")
//public class RoleController {
//    @Autowired
//    private RoleService service;
//
//    @PostMapping
//    public ResponseEntity<Role> createUser(@RequestBody Role role) {
//        Role newRole = service.addRole(role);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Role> getUserById(@PathVariable Long id) {
//        try {
//            return ResponseEntity.ok(service.getById(id));
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
//        try {
//            Role role = service.getById(id);
//            role.setName(roleDetails.getName());
//            Role updatedRole = service.addRole(role);
//            return ResponseEntity.ok(updatedRole);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//}
//}
