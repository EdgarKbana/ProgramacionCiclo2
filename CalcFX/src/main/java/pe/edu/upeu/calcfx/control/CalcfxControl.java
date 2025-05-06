package pe.edu.upeu.calcfx.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.calcfx.modelo.CalCTO;
import pe.edu.upeu.calcfx.servicio.CalcRepoSql;
import pe.edu.upeu.calcfx.servicio.CalcServicioI;

import javax.swing.*;
import java.util.List;

@Controller
public class CalcfxControl {
    @Autowired
    CalcServicioI servicioI;

    @FXML
    TableView<CalCTO> tableView;
    private ObservableList<CalCTO> datos;
    List<CalCTO> listar;
    @FXML
    TableColumn<CalCTO, String> num1x;
    @FXML
    TableColumn<CalCTO, String> num2x;
    @FXML
    TableColumn<CalCTO, Character> operp;
    @FXML
    TableColumn<CalCTO, String> resultx;
    @FXML
    TableColumn<CalCTO,Void> opcionesx;
    @FXML
    private TextField txtResultado;

    int indexID=-1;
    int idx=0;

    @Autowired
    CalcRepoSql calcRepoSql;

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



                CalCTO to = new CalCTO();
                to.setNum1(String.valueOf(num1));
                to.setNum2(String.valueOf(num2));
                to.setOperador(String.valueOf(operador.charAt(0)));
                to.setResultado(String.valueOf(resultado));
                if(indexID!=-1){
                    servicioI.update(to, Long.valueOf(indexID));
                }else{

                    servicioI.save(to);
                }

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
        listar();

    }


    public void listar(){
        listar=servicioI.findAll();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        num1x.setCellValueFactory(new PropertyValueFactory<CalCTO,String>("num1"));
        num1x.setCellFactory(TextFieldTableCell.<CalCTO>forTableColumn());

        num2x.setCellValueFactory(new PropertyValueFactory<CalCTO,String>("num2"));
        num2x.setCellFactory(TextFieldTableCell.<CalCTO>forTableColumn());

        operp.setCellValueFactory(new PropertyValueFactory<CalCTO, Character>("operador"));
        operp.setCellFactory(ComboBoxTableCell.<CalCTO, Character>forTableColumn('+',  '-' , '/' , '*'));

        addActionButtonsToTable();
        resultx.setCellValueFactory(new PropertyValueFactory<CalCTO,String>("resultado"));
        resultx.setCellFactory(TextFieldTableCell.<CalCTO>forTableColumn());
        //datos = FXCollections.observableArrayList(listar);
        datos = FXCollections.observableArrayList(calcRepoSql.listarEntidad());
        tableView.setItems(datos);

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
    private void addActionButtonsToTable() {
        Callback<TableColumn<CalCTO, Void>, TableCell<CalCTO, Void>>
                cellFactory = param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            {
                editButton.getStyleClass().setAll("btn", "btn-success");
                editButton.setOnAction(event -> {
                    CalCTO cal = getTableView().getItems().get(getIndex());
                    editOperCalc(cal, getIndex());
                });
                deleteButton.getStyleClass().setAll("btn", "btn-danger");
                deleteButton.setOnAction(event -> {
                    CalCTO cal = getTableView().getItems().get(getIndex());
                    deleteOperCalc(cal); getIndex();
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(editButton, deleteButton);
                    buttons.setSpacing(10);
                    setGraphic(buttons);
                }
            }
        };
        opcionesx.setCellFactory(cellFactory);
    }
    public void editOperCalc(CalCTO to, int index){
        txtResultado.setText(String.valueOf(to.getNum1()+" "+to.getOperador()+" "+to.getNum2()));
        System.out.println(index);
        indexID=index;
    }

    public void deleteOperCalc(CalCTO to){
        servicioI.delete(to);
        calcRepoSql.eliminarEntidad(to);
        listar();
    }
}