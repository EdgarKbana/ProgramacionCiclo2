package pe.edu.upeu.sysventas.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.sysventas.modelo.CarritoVenta;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.modelo.Usuario;
import pe.edu.upeu.sysventas.repositorio.MarcaRepository;
import pe.edu.upeu.sysventas.repositorio.UsuarioRepository;
import pe.edu.upeu.sysventas.repositorio.VentCarritoRepository;

import java.util.List;

@Service
public class CarritoVentaService {
    @Autowired
    private VentCarritoRepository ventCarritoRepository;


    // Create
    public CarritoVenta guardarEntidad(CarritoVenta to) {return ventCarritoRepository.save(to);}
    // Report
    public List<CarritoVenta> listarEntidad() {return ventCarritoRepository.findAll();}
    // Update
    public CarritoVenta actualizarEntidad(CarritoVenta to) {return ventCarritoRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {ventCarritoRepository.deleteById(id);}
    // Buscar por ID
    public CarritoVenta buscarEntidad(Long id) {return ventCarritoRepository.findById(id).orElse(null);}

    public List<CarritoVenta> listaCarritoCliente(String dni) {return ventCarritoRepository.listaCarritoCliente(dni);}

    @Transactional
    public void deleteCarAll(String dniruc) {
        ventCarritoRepository.deleteByDniruc(dniruc);
    }
}
