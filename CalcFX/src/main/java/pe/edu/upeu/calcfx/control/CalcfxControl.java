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

    private double num1 = 0;
    private String operador = "";
    private boolean nuevaOperacion = true;

    private void escribirNumero(String numero) {
        if (nuevaOperacion) {
            txtResultado.setText(numero);
            nuevaOperacion = false;
        } else {
            txtResultado.appendText(numero);
        }
    }

    private void agregarPunto() {
        if (nuevaOperacion) {
            txtResultado.setText("0.");
            nuevaOperacion = false;
        } else if (!txtResultado.getText().contains(".")) {
            txtResultado.appendText(".");
        }
    }

    private void agregarOperador(String op) {
        if (!txtResultado.getText().isEmpty()) {
            try {
                num1 = Double.parseDouble(txtResultado.getText());
                operador = op;
                nuevaOperacion = true;
            } catch (NumberFormatException e) {
                txtResultado.setText("Error de formato");
            }
        }
    }

    private void calcularResultado() {
        if (!txtResultado.getText().isEmpty() && !operador.isEmpty()) {
            try {
                double num2 = Double.parseDouble(txtResultado.getText());
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
                    case "^":
                        resultado = Math.pow(num1, num2);
                        break;
                    case "√":
                        if (num1 >= 0) {
                            resultado = Math.sqrt(num1);
                        } else {
                            txtResultado.setText("Error: Raíz negativa");
                            return;
                        }
                        break;
                    case "1/":
                        if (num1 != 0) {
                            resultado = 1.0 / num1;
                        } else {
                            txtResultado.setText("Error: Div/0");
                            return;
                        }
                        break;
                    case "π":
                        resultado = Math.PI * num2; // Suponiendo multiplicación por Pi
                        break;
                    case "Bin":
                        try {
                            int decimal = (int) num1;
                            txtResultado.setText(Integer.toBinaryString(decimal));
                            nuevaOperacion = true; // Reset para la siguiente operación
                            operador = "";
                            return; // Salir para no aplicar el resultado doblemente
                        } catch (ClassCastException e) {
                            txtResultado.setText("Error: No entero");
                            nuevaOperacion = true;
                            operador = "";
                            return;
                        }
                }
                txtResultado.setText(String.valueOf(resultado));
                num1 = resultado; // Para encadenar operaciones
                nuevaOperacion = true;
                operador = "";
            } catch (NumberFormatException e) {
                txtResultado.setText("Error de formato");
            }
        } else if (operador.equals("Bin") && !txtResultado.getText().isEmpty()) {
            try {
                num1 = Double.parseDouble(txtResultado.getText());
                int decimal = (int) num1;
                txtResultado.setText(Integer.toBinaryString(decimal));
                nuevaOperacion = true;
                operador = "";
            } catch (NumberFormatException e) {
                txtResultado.setText("Error de formato");
            } catch (ClassCastException e) {
                txtResultado.setText("Error: No entero");
            }
        }
    }

    @FXML
    private void controlClick(ActionEvent event) {
        Button boton = (Button) event.getSource();
        switch (boton.getId()) {
            case "btn0", "btn1", "btn2", "btn3", "btn4", "btn5", "btn6", "btn7", "btn8", "btn9": {
                escribirNumero(boton.getText());
            }
            break;
            case "btnSum", "btnDiv", "btnRes", "btnMulti", "btnPot", "btnRaiz", "btnUno", "btnPi", "btnBin": {
                agregarOperador(boton.getText());
            }
            break;
            case "btnPunto": {
                agregarPunto();
            }
            break;
            case "btnBorrar": {
                txtResultado.setText("");
                num1 = 0;
                operador = "";
                nuevaOperacion = true;
            }
            break;
            case "btnIgual": {
                calcularResultado();
            }
            break;
            default: {
            }
            break;
        }
    }
}