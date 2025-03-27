package dio.projeto_decola_tech.services;

import java.util.List;
import org.springframework.stereotype.Service;
import dio.projeto_decola_tech.repository.AlbumRepository;
import jakarta.transaction.Transactional;
import dio.projeto_decola_tech.models.Albuns;
import dio.projeto_decola_tech.models.Users;

@Service
@Transactional
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Albuns criarAlbum(Albuns albuns, Users usuario) {
        albuns.setUsuario(usuario);
        return albumRepository.save(albuns);
    }

    public List<Albuns> listarAlbunsDoUsuario(Users usuario) {
        return albumRepository.findByUsuario(usuario);
    }

    public void deletarAlbum(Long id, Users usuarioLogado) {
        // Debug: verifique os IDs antes da operação
        System.out.println("[DEBUG] Usuário logado - ID: " + usuarioLogado.getId() + 
                          ", Nome: " + usuarioLogado.getUsername() + 
                          ", Role: " + usuarioLogado.getRole());
    
        // 1. Busca o álbum com verificação de usuário
        Albuns album = albumRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));
    
        // Debug: verifique o dono do álbum
        System.out.println("[DEBUG] Álbum encontrado - ID Dono: " + album.getUsuario().getId() + 
                          ", Nome Dono: " + album.getUsuario().getUsername());
    
        // 2. Verificação robusta de permissão
        if (!temPermissaoParaDeletar(album, usuarioLogado)) {
            throw new SecurityException("Acesso negado: você não tem permissão para deletar este álbum");
        }
    
        // 3. Deleção segura
        albumRepository.delete(album);
        System.out.println("[SUCESSO] Álbum " + id + " deletado por " + usuarioLogado.getUsername());
    }
    
    private boolean temPermissaoParaDeletar(Albuns album, Users usuario) {
        // Comparação segura usando IDs
        boolean ehDono = album.getUsuario().getId().equals(usuario.getId());
        boolean ehAdmin = usuario.getRole() == Users.Role.ROLE_ADMIN;
        
        return ehDono || ehAdmin;
    }
}
