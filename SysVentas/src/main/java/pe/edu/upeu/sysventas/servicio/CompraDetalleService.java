package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.CompraDetalle;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.repositorio.CompraDetalleRepository;
import pe.edu.upeu.sysventas.repositorio.MarcaRepository;

import java.util.List;

@Service
public class CompraDetalleService {
    @Autowired
    private CompraDetalleRepository compraDetalleRepository;
    // Create
    public CompraDetalle guardarEntidad(CompraDetalle to) {return compraDetalleRepository.save(to);}
    // Report
    public List<CompraDetalle> listarEntidad() {return compraDetalleRepository.findAll();}
    // Update
    public CompraDetalle actualizarEntidad(CompraDetalle to) {return compraDetalleRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {compraDetalleRepository.deleteById(id);}
    // Buscar por ID
    public CompraDetalle buscarEntidad(Long id) {return compraDetalleRepository.findById(id).orElse(null);}
}
