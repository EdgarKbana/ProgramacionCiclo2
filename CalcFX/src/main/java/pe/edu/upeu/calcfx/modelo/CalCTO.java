package pe.edu.upeu.calcfx.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity(name ="calculadora" )
@Data
public class CalCTO{
    @Id
    Long id;
    String num1;
    String num2;
    String operador;
    String resultado;
}