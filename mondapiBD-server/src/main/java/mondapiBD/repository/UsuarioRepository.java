package mondapiBD.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import mondapiBD.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    public Optional<Usuario> findByUsername(String username);
    public boolean existsByUsername(String username);
}
