import greenfoot.*;

/**
 * Define el comportamiento de las Naves de Ataque Enemigas
 */
public class NaveBOSS extends NaveEnemigaBOSS implements Atacante {

    /**
     * post: Inicializa la naveBOSS mirando hacia el SUR
     */
    public NaveBOSS() {
        super(Direccion.SUR);
    }

    /**
     * pre: se le pasa como parametro la direccion y la salud deseada
     * post: incializa una NaveBOSS con la orientación deseada y la salud
     * inicial definida
     */
    public NaveBOSS(Direccion direccion, int salud) {
        super(direccion);
        this.salud = salud;
    }

    /**
     * pre: se le pasa como parametro el atacante
     * post: recibe daño del atacante
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
     * post: retorna el daño que hace la naveBOSS que es 15
     */
    @Override
    public int obtenerDaño() {
        return 15;
    }

    /**
     * post: retorna la salud de la naveBOSS
     */
    public int obtenerSalud() {
        return this.salud;
    }

    /**
     * pre: se le pasa por parametro la direccion hacia donde atacara
     * post: el objetivo en dicha direccion recibe un ataque
     */
    private void atacar(Dañable objetivo) {
        objetivo.recibirDañoDe(this);
    }

    /**
     * Ataca en una dirección determinada
     * pre: Se le pasa como parametro la direcion del ataque
     * post: Atacara hacia dicha direccion
     */
    public void atacarHacia(Direccion direccion) {
        this.direccion = direccion;
        actualizarImagen();
        setRotation(direccion.rotacion);
        Greenfoot.delay(20);

        Dañable objetivo = (Dañable) getOneObjectAtOffset(this.direccion.dx * 10, this.direccion.dy * 10, Actor.class);
        if (objetivo != null) {
            atacar(objetivo);
        }
    }
    
    
}


