package dio.projeto_decola_tech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dio.projeto_decola_tech.models.Users;

@RestController
@RequestMapping("/private")
public class PrivateController {

    @GetMapping
    public String welcome(@AuthenticationPrincipal Users user) {
    // Agora user não será mais null
        return "Bem-vindo, " + user.getUsername(); 
    }
}