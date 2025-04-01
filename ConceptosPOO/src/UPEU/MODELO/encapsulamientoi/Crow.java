package UPEU.MODELO.encapsulamientoi;

public class Crow implements Animal {
    @Override
    public void comer() {
        System.out.println("Cororosh.....Cocorosh...!!!");
    }

    @Override
    public void dormir() {
        System.out.println("Cororosh...zzz..Cocorosh..zzz...!!!");
    }

    @Override
    public void emitirSonido() {
        System.out.println("Co... Couuuu!!! Nice");
    }

    @Override
    public String peso() {
        return "Estoy pesando 2 kilos";

    }
}
