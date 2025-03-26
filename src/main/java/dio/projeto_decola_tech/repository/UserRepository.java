package dio.projeto_decola_tech.repository;
import dio.projeto_decola_tech.models.Users;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);

}
