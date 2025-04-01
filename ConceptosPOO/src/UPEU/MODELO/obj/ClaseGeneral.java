package UPEU.MODELO.obj;
import UPEU.MODELO.Persona;

public class ClaseGeneral {
    public static void main(String[] args) {
        Persona persona; //variable de tipo persona
        persona = new Persona(); //persona es un objeto
        persona.setNombre("Jose"); //encapsulamiento
        persona.setEdad(25); //encapsulamiento
        persona.saludo();
    }
}
