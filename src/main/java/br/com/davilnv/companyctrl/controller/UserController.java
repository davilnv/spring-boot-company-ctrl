package br.com.davilnv.companyctrl.controller;

import br.com.davilnv.companyctrl.models.user.User;
import br.com.davilnv.companyctrl.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUser() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        return ResponseEntity.ok(repository.save(user));
    }

    @GetMapping("/validation")
    public ResponseEntity<Boolean> passwordValidation(@RequestParam String username, @RequestParam String password) {


        Optional<User> optionalUser = repository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = encoder.matches(password, optionalUser.get().getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }

}
