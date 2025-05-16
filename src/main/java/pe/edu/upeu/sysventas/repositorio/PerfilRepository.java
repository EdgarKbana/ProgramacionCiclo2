package pe.edu.upeu.sysventas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysventas.modelo.Perfil;
import pe.edu.upeu.sysventas.modelo.Producto;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
