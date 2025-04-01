package UPEU.MODELO.polimorfabstrack;

public class Loro extends Animal {
    @Override
    public void emitirSonido() { //Polimorfismo
        System.out.println("Espero que puedas aprender!!...no estes jugando!!");
    }
    @Override
    public void comer() {
        System.out.println("Tengo hambr...que quiero comer algo");
    }
}
