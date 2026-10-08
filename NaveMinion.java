import greenfoot.*;

/**
 * Define el comportamiento de las Naves de Ataque Enemigas
 */
public class NaveMinion extends NaveEnemiga implements Atacante {

    /**
     * pre: se le pasa por parametro la direccion donde apuntara.
     * post: crea una nueva NaveMinion.
     */
    public NaveMinion(Direccion direccion) {
        super(direccion);
    }

    /**
     * post: incializa una NaveMinion con la orientación deseada y la salud
     * inicial definida.
     * Se le pasa por parametro la direccion y la salud inicial.
     */
    public NaveMinion(Direccion direccion, int salud) {
        super(direccion);
        this.salud = salud;
    }

    /**
     * post: recibe daño de atacante.
     */
    public void recibirDañoDe(Atacante atacante) {
        super.recibirDañoDe(atacante);
        Actor actor = (Actor) atacante;
        if (this.salud > 0) {
            Direccion direccion = Direccion.flecha(this.getX(), this.getY(), actor.getX(), actor.getY());
            atacarHacia(direccion);
        }
    }

    /**
     * post: retorna el daño que provoca la naveMinion que es 15
     */
    @Override
    public int obtenerDaño() {
        return 15;
    }

    /**
     * post: retorna la salud actual de la naveMinion.
     */
    public int obtenerSalud() {
        return this.salud;
    }

    /**
     * pre: se le pasa por parametro el objetivo a atacar.
     * post: ataca el objetivo.
     */
    private void atacar(Dañable objetivo) {
        objetivo.recibirDañoDe(this);
    }

    /**
     * pre: se le pasa por parametro la direccion hacia donde atacar.
     * post: ataca la nave hacia la direccion indicada.
     */
    public void atacarHacia(Direccion direccion) {
        this.direccion = direccion;
        actualizarImagen();
        setRotation(direccion.rotacion);
        Greenfoot.delay(20);

        Dañable objetivo = (Dañable) getOneObjectAtOffset(this.direccion.dx, this.direccion.dy, Actor.class);
        if (objetivo != null) {
            atacar(objetivo);
        }
    }

}

