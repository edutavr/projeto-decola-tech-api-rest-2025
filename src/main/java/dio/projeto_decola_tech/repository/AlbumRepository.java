package dio.projeto_decola_tech.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import dio.projeto_decola_tech.models.Albuns;
import dio.projeto_decola_tech.models.Users;

public interface AlbumRepository extends JpaRepository<Albuns, Long> {
    
    // Consultas básicas
    List<Albuns> findByUsuario(Users usuario);
    List<Albuns> findByUsuarioAndGenero(Users usuario, String genero);
    
    // Consulta para deleção segura - CORREÇÃO PRINCIPAL
    @EntityGraph(attributePaths = {"usuario"}) // Carrega o usuário junto
    Optional<Albuns> findByIdAndUsuario(Long id, Users usuario);
    
    // Método alternativo para deleção direta (opcional)
    @Modifying
    @Query("DELETE FROM Albuns a WHERE a.id = :id AND a.usuario = :usuario")
    void deleteByIdAndUsuario(@Param("id") Long id, @Param("usuario") Users usuario);
}