package pe.edu.upeu.tictactoe.modelo;

public class ResultadoTicTacToe {
    private String nombrePartida;
    private String nombreJugador1;
    private String nombreJugador2;
    private String ganador;
    private int punto;
    private String estado;

    public ResultadoTicTacToe(String nombrePartida, String nombreJugador1, String nombreJugador2, String ganador, int punto, String estado) {
        this.nombrePartida = nombrePartida;
        this.nombreJugador1 = nombreJugador1;
        this.nombreJugador2 = nombreJugador2;
        this.ganador = ganador;
        this.punto = punto;
        this.estado = estado;
    }

    public String getNombrePartida() {
        return nombrePartida;
    }

    public String getNombreJugador1() {
        return nombreJugador1;
    }

    public String getNombreJugador2() {
        return nombreJugador2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}