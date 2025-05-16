package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.modelo.UnidadMedida;
import pe.edu.upeu.sysventas.repositorio.MarcaRepository;
import pe.edu.upeu.sysventas.repositorio.UnidadMedidaRepository;

import java.util.List;

@Service
public class UnidadMedidaService {
    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;
    // Create
    public UnidadMedida guardarEntidad(UnidadMedida to) {return unidadMedidaRepository.save(to);}
    // Report
    public List<UnidadMedida> listarEntidad() {return unidadMedidaRepository.findAll();}
    // Update
    public UnidadMedida actualizarEntidad(UnidadMedida to) {return unidadMedidaRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {unidadMedidaRepository.deleteById(id);}
    // Buscar por ID
    public UnidadMedida buscarEntidad(Long id) {return unidadMedidaRepository.findById(id).orElse(null);}
}
