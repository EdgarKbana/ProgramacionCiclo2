package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.Emisor;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.repositorio.EmisorRepository;
import pe.edu.upeu.sysventas.repositorio.MarcaRepository;

import java.util.List;

@Service
public class EmisorService {
    @Autowired
    private EmisorRepository emisorRepository;
    // Create
    public Emisor guardarEntidad(Emisor to) {return emisorRepository.save(to);}
    // Report
    public List<Emisor> listarEntidad() {return emisorRepository.findAll();}
    // Update
    public Emisor actualizarEntidad(Emisor to) {return emisorRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {emisorRepository.deleteById(id);}
    // Buscar por ID
    public Emisor buscarEntidad(Long id) {return emisorRepository.findById(id).orElse(null);}
}
