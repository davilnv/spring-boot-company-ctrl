package br.com.davilnv.companyctrl.api.controller;

import br.com.davilnv.companyctrl.api.dto.UserGetDto;
import br.com.davilnv.companyctrl.api.dto.UserRegisterDto;
import br.com.davilnv.companyctrl.api.exception.RolesNotFoundException;
import br.com.davilnv.companyctrl.api.exception.UserNotFoundException;
import br.com.davilnv.companyctrl.api.models.roles.RoleModel;
import br.com.davilnv.companyctrl.api.service.role.RoleService;
import br.com.davilnv.companyctrl.api.service.user.UserService;
import br.com.davilnv.companyctrl.api.models.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, RoleService roleService, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserRegisterDto userDto) {

        if (userService.existsByUsername(userDto.getUsername())) {
            return new ResponseEntity<>("Nome de usuário já está sendo utilizado", HttpStatus.BAD_REQUEST);
        }

        try {
            userDto.setPassword(encoder.encode(userDto.getPassword()));
            UserModel user = new UserModel(userDto);
            Optional<RoleModel> roleDB;
            try {
                roleDB = roleService.findByName("USER");

            } catch (RolesNotFoundException e) {
                RoleModel role = new RoleModel();
                role.setName("USER");
                roleService.saveRole(role);
                roleDB = roleService.findByName("USER");
            }

            user.setRoles(Collections.singletonList(roleDB.get()));
            userService.saveUser(user);
            user = userService.getUserByUsername(user.getUsername()).get();
            return new ResponseEntity<>(new UserGetDto(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> listAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserModel user) {

        try {
            if (user.getUsername() == null || user.getPassword() == null) {
                throw new UserNotFoundException("Usuário ou Senha estão vazios!");
            }
            Optional<UserModel> userData = userService.getUserByUsername(user.getUsername());

            if (userData.isEmpty()) {
                throw new UserNotFoundException("Usuário ou Senha inválido");
            }

            boolean valid = encoder.matches(user.getPassword(), userData.get().getPassword());

            if (valid) {
                return new ResponseEntity<>("Usuário existe!", HttpStatus.OK);
            } else {
                throw new UserNotFoundException("Usuário ou Senha inválido");
            }

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

}
