package dio.projeto_decola_tech.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import dio.projeto_decola_tech.models.Users;
import dio.projeto_decola_tech.repository.UserRepository;

@Service
public class UserDetailsServices implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        return user;
    }
}