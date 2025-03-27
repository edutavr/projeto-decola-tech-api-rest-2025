package dio.projeto_decola_tech.models;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="albuns")
public class Albuns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String artista;
    @Column(nullable = false)
    private Integer anoDeLancamento;
    @Column(nullable = false)
    private String genero;
    @CreationTimestamp
    private LocalDateTime dataCriacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Users usuario;

    public Albuns() {}

    public Albuns(String titulo, String artista, Integer anoLancamento, String genero, Users usuario) {
        this.titulo = titulo;
        this.artista = artista;
        this.anoDeLancamento = anoLancamento;
        this.genero = genero;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public Integer getAnoDeLancamento() {
        return anoDeLancamento;
    }
    public void setAnoDeLancamento(Integer anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public Users getUsuario() {
        return usuario;
    }
    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }
}
