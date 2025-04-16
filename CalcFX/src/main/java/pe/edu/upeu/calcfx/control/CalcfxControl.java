package pe.edu.upeu.calcfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class CalcfxControl {

    @FXML
    private TextField txtResultado;

    private void escribirNumero(String numero) {
        txtResultado.appendText(numero);
    }

    private void agregarOperador (String Operador){
        txtResultado.appendText(" " +Operador+ " ");
    }


    private void calcularResultado() {
        try {
            String[] tokens = txtResultado.getText().split(" ");
            if (tokens.length < 3) {
                return;
            }
            double num1 = Double.parseDouble(tokens[0]);
            String operador = tokens[1];
            double num2 = Double.parseDouble(tokens[2]);
            double resultado = 0;
            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        txtResultado.setText("Error: Div/0");
                        return;
                    }
                    break;
                case "^": // Suponiendo que "^" se usa para la potencia
                    resultado = Math.pow(num1, num2);
                    break;
                case "√": // Suponiendo que "sqrt" se usa para la raíz cuadrada, sino calcula poner el numero primero luego la raiz y para que resuelva poner un numero extra al final
                    if (num1 >= 0) {
                        resultado = Math.sqrt(num1);
                    } else {
                        txtResultado.setText("Error: Raíz de número negativo");
                        return;
                    }
                    break;
                case "1/": // Suponiendo que "1/" se usa para la inversa
                    // Para la inversa, el segundo número (num2) no se utiliza directamente en esta lógica
                    if (num1 != 0) {
                        resultado = 1.0 / num1;
                    } else {
                        txtResultado.setText("Error: División por cero");
                        return;
                    }
                    break;
                case "π": // Suponiendo que "pi" se usa para obtener el valor de Pi igual poner un numero al final para calcular
                    resultado = Math.PI;
                    txtResultado.setText(String.valueOf(resultado));
                    return; // Salimos del switch después de mostrar Pi
                default:
                    txtResultado.setText("Operador inválido");
                    return;
            }

            txtResultado.setText(String.valueOf(resultado));
        } catch (Exception e) {
            txtResultado.setText("Error");
            System.out.println(e.getMessage());
        }
    }


    @FXML
    private void controlClick(ActionEvent event) {
        Button boton = (Button) event.getSource();
        switch (boton.getId()) {
            case "btn0", "btn1", "btn2", "btn3", "btn4", "btn5", "btn6", "btn7", "btn8", "btn9": {escribirNumero(boton.getText());} break;
            case "btnSum", "btnDiv", "btnRest", "btnMulti", "btnPot", "btnRaiz", "btnUno", "btnPi" :{ agregarOperador(boton.getText());} break;
            case "btnBorrar":{txtResultado.setText("");} break;
            case "btnIgual":{calcularResultado();} break;

            default: {} break;

        }
    }
}
