package projeto1.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto1.application.database.FeraDatabase;

import java.util.Optional;

public interface FeraRepository extends JpaRepository<FeraDatabase, Long> {

    Optional<FeraDatabase> findByUsuario(String usuario);
    Optional<FeraDatabase> findByNomeCompleto(String nomeCompleto);
}
