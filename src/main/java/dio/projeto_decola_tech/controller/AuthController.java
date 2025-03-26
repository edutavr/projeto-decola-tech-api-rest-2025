package dio.projeto_decola_tech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dio.projeto_decola_tech.models.Users;
import dio.projeto_decola_tech.repository.UserRepository;
import dio.projeto_decola_tech.services.AuthenticationService;

@RestController
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationService authService, 
                        UserRepository userRepository,
                        PasswordEncoder passwordEncoder) {
        this.authenticationService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest request) {
        return authenticationService.authenticate(
            request.username(),
            request.password()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().body("Username já existe");
        }

        Users newUser = new Users();
        newUser.setUsername(request.username());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(Users.Role.valueOf(request.role())); // Converte String para Enum
        
        userRepository.save(newUser);
        
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }


    // Record para o DTO de autenticação
    public record AuthRequest(String username, String password) {}
    public record RegisterRequest(String username, String password, String role) {}
}