package UPEU.MODELO.herencia;

public class Vehicle {
    private String marca; //clave
    protected String tipo;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void sonido(){
        System.out.println("tititi....");
        System.out.println("Mis carcateristicas son:\n"+
                "Marca:"+marca+"\nTipo:"+tipo);
    }
}
