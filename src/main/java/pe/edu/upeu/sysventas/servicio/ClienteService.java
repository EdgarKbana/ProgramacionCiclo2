package pe.edu.upeu.sysventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.modelo.Cliente;
import pe.edu.upeu.sysventas.modelo.Marca;
import pe.edu.upeu.sysventas.repositorio.ClientRepository;
import pe.edu.upeu.sysventas.repositorio.MarcaRepository;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClientRepository clientRepository;
    // Create
    public Cliente guardarEntidad(Cliente to) {return clientRepository.save(to);}
    // Report
    public List<Cliente> listarEntidad() {return clientRepository.findAll();}
    // Update
    public Cliente actualizarEntidad(Cliente to) {return clientRepository.save(to);}
    // Delete
    public void eliminarRegEntidad(Long id) {clientRepository.deleteById(id);}
    // Buscar por ID
    public Cliente buscarEntidad(Long id) {return clientRepository.findById(id).orElse(null);}
}
