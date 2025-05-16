package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.modelo.Proveedor;
import pe.edu.upeu.sysventas.repositorio.MarcaRepository;
import pe.edu.upeu.sysventas.repositorio.ProveedorRepository;

import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    // Create
    public Proveedor guardarEntidad(Proveedor to) {return proveedorRepository.save(to);}
    // Report
    public List<Proveedor> listarEntidad() {return proveedorRepository.findAll();}
    // Update
    public Proveedor actualizarEntidad(Proveedor to) {return proveedorRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {proveedorRepository.deleteById(id);}
    // Buscar por ID
    public Proveedor buscarEntidad(Long id) {return proveedorRepository.findById(id).orElse(null);}
}
