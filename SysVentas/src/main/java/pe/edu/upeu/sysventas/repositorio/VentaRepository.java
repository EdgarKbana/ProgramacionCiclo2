package pe.edu.upeu.sysventas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysventas.modelo.Producto;
import pe.edu.upeu.sysventas.modelo.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
