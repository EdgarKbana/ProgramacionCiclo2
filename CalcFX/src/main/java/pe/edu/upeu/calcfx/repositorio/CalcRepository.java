package pe.edu.upeu.calcfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.calcfx.modelo.CalCTO;
@Repository
public interface CalcRepository extends JpaRepository<CalCTO, Long> {
}
