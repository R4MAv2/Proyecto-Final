import greenfoot.*;

/**
 * Define las características y comportamiento base de una NaveBOSS. Las
 * Naves de esta facción no funcionan con combustible, sino con gravitrones. Es
 * por ello que no tienen tanque sino un estado de salud
 */
public abstract class NaveEnemigaBOSS extends NaveBaseBOSS {

    private static final int SALUD_INICIAL = 1000;

    /**
     * Es la salud de la NaveBOSS
     */
    protected int salud = SALUD_INICIAL;

    /**
     * pre: se le pasa por parametro la direccion
     * post: se inicia una NaveEnemiga en la dirección deseada
     */
    public NaveEnemigaBOSS(Direccion direccion) {
        super(direccion);
    }

    /**
     * pre: se le pasa por parametro el atacante
     * post: la naveBOSS reducira su vida hasta ser eliminida
     */
    @Override
    public void recibirDañoDe(Atacante atacante) {
        int daño = atacante.obtenerDaño();
        this.salud -= daño;
        actualizarImagen();
        Explosion.en(getWorld(), this.getX(), this.getY(), true);
        if (this.salud <= 0) {
            getWorld().removeObject(this);
        }
    }

}
