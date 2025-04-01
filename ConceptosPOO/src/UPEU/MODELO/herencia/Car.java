package UPEU.MODELO.herencia;

public class Car extends Vehicle { //Concepto herencia
    protected String modelo;

    public void mostrarInformacion(){

        tipo="Electrico";
        setMarca("Toyota");
        modelo="Hilux";
        sonido();
        System.out.println("Modelo:"+modelo);
    }
    public static void main(String[] args) {
        new Car().mostrarInformacion();

    }
}
