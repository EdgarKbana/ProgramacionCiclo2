package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.Compra;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.repositorio.CompraRepository;
import pe.edu.upeu.sysventas.repositorio.MarcaRepository;

import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;
    // Create
    public Compra guardarEntidad(Compra to) {return compraRepository.save(to);}
    // Report
    public List<Compra> listarEntidad() {return compraRepository.findAll();}
    // Update
    public Compra actualizarEntidad(Compra to) {return compraRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {compraRepository.deleteById(id);}
    // Buscar por ID
    public Compra buscarEntidad(Long id) {return compraRepository.findById(id).orElse(null);}
}
