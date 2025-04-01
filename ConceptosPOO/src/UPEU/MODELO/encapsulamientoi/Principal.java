package UPEU.MODELO.encapsulamientoi;

public class Principal{
    public static void main(String[] args) {
        Animal a=new Loro();
        a.comer(); a.emitirSonido(); a.dormir();
        System.out.println(a.peso());

        a=new Crow();
        a.comer(); a.emitirSonido(); a.dormir();
        System.out.println(a.peso());
    }
}
