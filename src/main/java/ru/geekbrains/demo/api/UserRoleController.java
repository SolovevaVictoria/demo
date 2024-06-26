//package ru.geekbrains.demo.api;
//
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.geekbrains.demo.model.Role;
//import ru.geekbrains.demo.model.User;
//import ru.geekbrains.demo.service.RoleService;
//import ru.geekbrains.demo.service.UserService;
//
//@RestController
//@RequestMapping("/userRole")
//@AllArgsConstructor
//public class UserRoleController {
//    private final UserService userService;
//    private final RoleService roleService;
//
//    @PutMapping("/{userId}/addRole/{roleId}")
//    public ResponseEntity<User> addUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
//        try {
//            User user = userService.getById(userId);
//            Role role = roleService.getById(roleId);
//            user.getRoles().add(role);
//            role.getUsers().add(user);
//            return ResponseEntity.ok(user);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//}


