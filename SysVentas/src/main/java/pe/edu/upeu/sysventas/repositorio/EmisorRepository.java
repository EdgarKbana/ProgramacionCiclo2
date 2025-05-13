package pe.edu.upeu.sysventas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysventas.modelo.Emisor;
import pe.edu.upeu.sysventas.modelo.Producto;

@Repository
public interface EmisorRepository extends JpaRepository<Emisor, Long> {
}
