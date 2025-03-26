package dio.projeto_decola_tech.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import dio.projeto_decola_tech.services.JwtUtil;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String username, String password) {
        System.out.println("Tentativa de login: " + username); 
        try{
           Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("Autenticação bem-sucedida");
            return jwtUtil.generateToken(authentication); 
        } catch (Exception e){
            System.out.println("Falha na autenticação" + e.getMessage());
            throw e;
        }
        
    }
}