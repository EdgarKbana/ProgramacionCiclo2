package pe.edu.upeu.tictactoe.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import pe.edu.upeu.tictactoe.modelo.ResultadoTicTacToe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicfxControl {

    @FXML
    private TextField txtNombreJugador1;
    @FXML
    private TextField txtNombreJugador2;
    @FXML
    private Label labelTurno;
    @FXML
    private Label labelPuntajeJugador1;
    @FXML
    private Label labelPuntajeJugador2;
    @FXML
    private Label labelResultado; // Para mostrar el resultado
    @FXML
    private Button btnIniciar;
    @FXML
    private Button btnAnular;
    @FXML
    private GridPane tableroGrid;
    @FXML
    private Button boton00;
    @FXML
    private Button boton01;
    @FXML
    private Button boton02;
    @FXML
    private Button boton10;
    @FXML
    private Button boton11;
    @FXML
    private Button boton12;
    @FXML
    private Button boton20;
    @FXML
    private Button boton21;
    @FXML
    private Button boton22;
    @FXML
    private TableView<ResultadoTicTacToe> tablaPuntajes;
    @FXML
    private TableColumn<ResultadoTicTacToe, String> nombrePartidaColumna;
    @FXML
    private TableColumn<ResultadoTicTacToe, String> nombreJugador1Columna;
    @FXML
    private TableColumn<ResultadoTicTacToe, String> nombreJugador2Columna;
    @FXML
    private TableColumn<ResultadoTicTacToe, String> nombreGanadorColumna;
    @FXML
    private TableColumn<ResultadoTicTacToe, Integer> puntajeColumna;
    @FXML
    private TableColumn<ResultadoTicTacToe, String> estadoColumna;

    private Button[][] botones;
    private String jugadorActual = "X";
    private String jugador1Simbolo = "X";
    private String jugador2Simbolo = "O";
    private String nombreJugador1;
    private String nombreJugador2;
    private int puntajeJugador1 = 0;
    private int puntajeJugador2 = 0;
    private String[][] tablero = {{"", "", ""}, {"", "", ""}, {"", "", ""}};
    private boolean juegoActivo = false;
    private List<ResultadoTicTacToe> resultados = new ArrayList<>();
    private ObservableList<ResultadoTicTacToe> resultadosObservable;
    private int partidaActualIndex = -1;

    public void initialize() {
        botones = new Button[][]{
                {boton00, boton01, boton02},
                {boton10, boton11, boton12},
                {boton20, boton21, boton22}
        };
        nombrePartidaColumna.setCellValueFactory(new PropertyValueFactory<>("nombrePartida"));
        nombreJugador1Columna.setCellValueFactory(new PropertyValueFactory<>("nombreJugador1"));
        nombreJugador2Columna.setCellValueFactory(new PropertyValueFactory<>("nombreJugador2"));
        nombreGanadorColumna.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        puntajeColumna.setCellValueFactory(new PropertyValueFactory<>("punto"));
        estadoColumna.setCellValueFactory(new PropertyValueFactory<>("estado"));
        resultadosObservable = FXCollections.observableArrayList(resultados);
        tablaPuntajes.setItems(resultadosObservable);
        labelTurno.setText("-");
        labelPuntajeJugador1.setText("0");
        labelPuntajeJugador2.setText("0");
        labelResultado.setText(""); // Inicializa el label de resultado
        btnAnular.setDisable(true);
    }

    @FXML
    public void iniciarJuego(ActionEvent event) {
        if (!juegoActivo) {
            juegoActivo = true;
            btnIniciar.setDisable(true);
            btnAnular.setDisable(false);
            tableroGrid.setDisable(false);
            labelResultado.setText("");
            nombreJugador1 = txtNombreJugador1.getText();
            nombreJugador2 = txtNombreJugador2.getText();
            jugadorActual = jugador1Simbolo;
            actualizarLabelTurno();
            limpiarTablero();
            habilitarBotonesTablero();

            ResultadoTicTacToe nuevaPartida = new ResultadoTicTacToe(
                    "Partida " + (resultados.size() + 1),
                    nombreJugador1,
                    nombreJugador2,
                    "",
                    0,
                    "Jugando"
            );
            resultados.add(nuevaPartida);
            resultadosObservable.add(nuevaPartida);
            partidaActualIndex = resultados.size() - 1;
        }
    }

    @FXML
    public void handleButtonClick(ActionEvent event) {
        if (juegoActivo) {
            Button botonClicado = (Button) event.getSource();
            String idBoton = botonClicado.getId();
            int fila = Character.getNumericValue(idBoton.charAt(5));
            int columna = Character.getNumericValue(idBoton.charAt(6));

            if (tablero[fila][columna].equals("")) {
                tablero[fila][columna] = jugadorActual;
                botonClicado.setText(jugadorActual);
                botonClicado.setDisable(true);

                if (verificarGanador()) {
                    String ganador = (jugadorActual.equals(jugador1Simbolo)) ? nombreJugador1 : nombreJugador2;
                    labelResultado.setText("¡Ganador: " + ganador + "!");
                    if (jugadorActual.equals(jugador1Simbolo)) {
                        puntajeJugador1++;
                        actualizarResultadoPartida(ganador, 1);
                    } else {
                        puntajeJugador2++;
                        actualizarResultadoPartida(ganador, 1);
                    }
                    actualizarLabelPuntajes();
                    juegoActivo = false;
                    btnIniciar.setDisable(false);
                    btnAnular.setDisable(true);
                    deshabilitarTablero();
                } else if (tableroLleno()) {
                    labelResultado.setText("¡Empate!");
                    actualizarResultadoPartida("Empate", 0);
                    juegoActivo = false;
                    btnIniciar.setDisable(false);
                    btnAnular.setDisable(true);
                    deshabilitarTablero();
                } else {
                    cambiarJugador();
                    actualizarLabelTurno();
                }
            }
        }
    }

    @FXML
    public void anularJuego(ActionEvent event) {
        if (juegoActivo) {
            juegoActivo = false;
            btnIniciar.setDisable(false);
            btnAnular.setDisable(true);
            tableroGrid.setDisable(true);
            labelResultado.setText("Partida Anulada");
            actualizarEstadoPartida("Anulado");
            limpiarTablero();
            deshabilitarTablero();
        }
    }

    private void limpiarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = "";
                botones[i][j].setText("");
                botones[i][j].setDisable(true);
            }
        }
    }

    private void habilitarBotonesTablero() {
        for (Button[] filaBotones : botones) {
            for (Button boton : filaBotones) {
                boton.setDisable(false);
            }
        }
    }

    private void cambiarJugador() {
        jugadorActual = (jugadorActual.equals(jugador1Simbolo)) ? jugador2Simbolo : jugador1Simbolo;
    }

    private void actualizarLabelTurno() {
        labelTurno.setText((jugadorActual.equals(jugador1Simbolo)) ? nombreJugador1 + " (" + jugador1Simbolo + ")" : nombreJugador2 + " (" + jugador2Simbolo + ")");
    }

    private void actualizarLabelPuntajes() {
        labelPuntajeJugador1.setText(String.valueOf(puntajeJugador1));
        labelPuntajeJugador2.setText(String.valueOf(puntajeJugador2));
    }

    private boolean verificarGanador() {
        String[] lines = new String[8];
        for (int i = 0; i < 3; i++) lines[i] = tablero[i][0] + tablero[i][1] + tablero[i][2];
        for (int i = 0; i < 3; i++) lines[i + 3] = tablero[0][i] + tablero[1][i] + tablero[2][i];
        lines[6] = tablero[0][0] + tablero[1][1] + tablero[2][2];
        lines[7] = tablero[0][2] + tablero[1][1] + tablero[2][0];

        for (String line : lines) {
            if (line.equals(jugador1Simbolo + jugador1Simbolo + jugador1Simbolo) || line.equals(jugador2Simbolo + jugador2Simbolo + jugador2Simbolo)) {
                return true;
            }
        }
        return false;
    }

    private boolean tableroLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void deshabilitarTablero() {
        for (Button[] filaBotones : botones) {
            for (Button boton : filaBotones) {
                boton.setDisable(true);
            }
        }
    }

    private void actualizarEstadoPartida(String estado) {
        if (partidaActualIndex != -1 && partidaActualIndex < resultados.size()) {
            ResultadoTicTacToe partidaActual = resultados.get(partidaActualIndex);
            partidaActual.setEstado(estado);
            resultadosObservable.set(partidaActualIndex, partidaActual);
            tablaPuntajes.refresh();
        }
    }

    private void actualizarResultadoPartida(String ganador, int punto) {
        if (partidaActualIndex != -1 && partidaActualIndex < resultados.size()) {
            ResultadoTicTacToe partidaActual = resultados.get(partidaActualIndex);
            partidaActual.setGanador(ganador);
            partidaActual.setPunto(punto);
            partidaActual.setEstado("Terminado");
            resultadosObservable.set(partidaActualIndex, partidaActual);
            tablaPuntajes.refresh();
        }
    }


    private Connection conectarDB() throws SQLException {

        return null; // Temporalmente retorna null
    }

    private void guardarResultadoEnDB(ResultadoTicTacToe resultado) {
        String sql = "INSERT INTO resultados (nombre_partida, nombre_jugador1, nombre_jugador2, ganador, punto, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectarDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn != null) {
                pstmt.setString(1, resultado.getNombrePartida());
                pstmt.setString(2, resultado.getNombreJugador1());
                pstmt.setString(3, resultado.getNombreJugador2());
                pstmt.setString(4, resultado.getGanador());
                pstmt.setInt(5, resultado.getPunto());
                pstmt.setString(6, resultado.getEstado());
                pstmt.executeUpdate();
            } else {
                System.err.println("No se pudo conectar a la base de datos para guardar el resultado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar el resultado en la base de datos: " + e.getMessage());
        }
    }
}