package pe.edu.upeu.sysventas.modelo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "upeu_perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perfil", nullable = false)
    private Long idPerfil;
    //@BatchSize(max = 20)
    private String nombre;
    //@BatchSize(max = 6)
    private String codigo;
}