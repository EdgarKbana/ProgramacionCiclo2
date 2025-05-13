package pe.edu.upeu.sysventas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.modelo.Producto;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
