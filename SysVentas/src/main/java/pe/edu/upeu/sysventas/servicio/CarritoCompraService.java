package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.CarritoCompra;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.repositorio.CompCarritoRepository;

import java.util.List;

@Service
public class CarritoCompraService {
    @Autowired
    private CompCarritoRepository compCarritoRepository ;
    // Create
    public CarritoCompra guardarEntidad(CarritoCompra to) {return compCarritoRepository.save(to);}
    // Report
    public List<CarritoCompra> listarEntidad() {return compCarritoRepository.findAll();}
    // Update
    public CarritoCompra actualizarEntidad(CarritoCompra to) {return compCarritoRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {compCarritoRepository.deleteById(id);}
    // Buscar por ID
    public CarritoCompra buscarEntidad(Long id) {return compCarritoRepository.findById(id).orElse(null);}
}
