package UPEU.MODELO.claseinterna;

public class Principal {
    public static void main(String[] args) {
        Madre madre=new Madre();
        madre.nombre="Brigina";
        madre.edad=30;
        madre.peso=70;
        madre.apellido="Apaza";

        Madre.Hijo hijo = madre.new Hijo();
        hijo.nombre="Raul";
        hijo.apellido= madre.apellido;
        hijo.peso=4;
        System.out.println("Nombre: "+madre.nombre+"\nApellido: "+madre.apellido+"\nPeso: "+madre.peso);
        System.out.println("Madre con Hijo:");
        System.out.println("Nombre: "+madre.nombre+"\nApellido: "+madre.apellido+"\nPeso: "+madre.peso);

        System.out.println("Datos del hijo:");
        System.out.println("Nombre:"+hijo.nombre+"\nApellido: "+hijo.apellido+"\nPeso: "+hijo.peso);
    }
}
