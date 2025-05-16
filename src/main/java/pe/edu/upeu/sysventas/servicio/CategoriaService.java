package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.Categoria;
import pe.edu.upeu.sysventas.repositorio.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    // Create
    public Categoria guardarEntidad(Categoria to) {return categoriaRepository.save(to);}
    // Report
    public List<Categoria> listarEntidad() {return categoriaRepository.findAll();}
    // Update
    public Categoria actualizarEntidad(Categoria to) {return categoriaRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {categoriaRepository.deleteById(id);}
    // Buscar por ID
    public Categoria buscarEntidad(Long id) {return categoriaRepository.findById(id).orElse(null);}
}
