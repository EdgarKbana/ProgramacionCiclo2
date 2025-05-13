package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.CarritoCompra;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.repositorio.CarritoCompraRepository;

import java.util.List;

@Service
public class CarritoCompraService {
    @Autowired
    private CarritoCompraRepository carritoCompraRepository;
    // Create
    public CarritoCompra guardarEntidad(CarritoCompra to) {return carritoCompraRepository.save(to);}
    // Report
    public List<CarritoCompra> listarEntidad() {return carritoCompraRepository.findAll();}
    // Update
    public CarritoCompra actualizarEntidad(CarritoCompra to) {return carritoCompraRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {carritoCompraRepository.deleteById(id);}
    // Buscar por ID
    public CarritoCompra buscarEntidad(Long id) {return carritoCompraRepository.findById(id).orElse(null);}
}