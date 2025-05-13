package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.CarritoVenta;
import pe.edu.upeu.sysventas.repositorio.CarritoVentaRepository;

import java.util.List;

@Service
public class CarritoVentaService {
    @Autowired
    private CarritoVentaRepository carritoVentaRepository;
    // Create
    public CarritoVenta guardarEntidad(CarritoVenta to) {return carritoVentaRepository.save(to);}
    // Report
    public List<CarritoVenta> listarEntidad() {return carritoVentaRepository.findAll();}
    // Update
    public CarritoVenta actualizarEntidad(CarritoVenta to) {return carritoVentaRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {carritoVentaRepository.deleteById(id);}
    // Buscar por ID
    public CarritoVenta buscarEntidad(Long id) {return carritoVentaRepository.findById(id).orElse(null);}
}