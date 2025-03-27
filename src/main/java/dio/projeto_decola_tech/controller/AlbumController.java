package dio.projeto_decola_tech.controller;

import dio.projeto_decola_tech.models.Albuns;
import dio.projeto_decola_tech.models.Users;
import dio.projeto_decola_tech.services.AlbumService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albuns")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public Albuns criarAlbum(@RequestBody Albuns albuns, 
                          @AuthenticationPrincipal Users usuario) {
        return albumService.criarAlbum(albuns, usuario);
    }

    @GetMapping
    public List<Albuns> listarAlbuns(@AuthenticationPrincipal Users usuario) {
        return albumService.listarAlbunsDoUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarAlbum(@PathVariable Long id,
                           @AuthenticationPrincipal Users usuario) {
        albumService.deletarAlbum(id, usuario);
    }
}
