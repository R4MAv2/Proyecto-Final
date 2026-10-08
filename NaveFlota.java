import greenfoot.*;

/**
 * Define las características y comportamiento base de una NaveFlota. Las
 * Naves de esta facción no funcionan con combustible, sino con gravitrones. Es
 * por ello que no tienen tanque sino un estado de salud
 */
public abstract class NaveFlota extends NaveBase {
    /**
     * {@value #SALUD_INICIAL}
     */
    private static final int SALUD_INICIAL = 1;

    /**
     * Es la salud de la NaveFlota
     */
    protected int salud = SALUD_INICIAL;

    /**
     * post: se inicia una NaveFlota en la dirección deseada
     * 
     * @param direccion la dirección en la que apuntará la NaveFlota
     */
    public NaveFlota(Direccion direccion) {
        super(direccion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double obtenerProporcionDeBarraIndicadora() {
        return 1.0 * this.salud / 100;
    }

    /**
     * post: la NaveFlota reducirá su {@link #salud} conforme la potencia del
     * ataque. <br>
     * post: la NaveFlota será eliminada del tablero si su {@link #salud} llega a
     * 0. <br>
     * 
     * @see Dañable#recibirDañoDe(Atacante)
     */
    @Override
    public void recibirDañoDe(Atacante atacante) {

        int daño = atacante.obtenerDaño();
        this.salud -= daño;
        actualizarImagen();
        Explosion.en(getWorld(), this.getX(), this.getY(), false);
        if (this.salud <= 0) {
            MundoBOSS mundo = (MundoBOSS) getWorld();
            if (mundo.obtenerContadorMinionLider() == 0){
                Item item = new Item();
                getWorld().addObject(item, getX() + 1, getY());
                Item item1 = new Item();
                getWorld().addObject(item1, getX() - 1, getY());
                mundo.incrementarContadorMinionLider();
            } else {
                Item item2 = new Item();
                getWorld().addObject(item2, getX(), getY());
            }
            int[][] posiciones = {
                    {0, 9},
                    {1, 9},
                    {2, 9},
                    {16, 9},
                    {17, 9},
                    {18, 9}
                };

            for (int i = 0; i < posiciones.length; i++) {
                int x = posiciones[i][0] - this.getX();
                int y = posiciones[i][1] - this.getY();
                Actor asteroide = getOneObjectAtOffset(x, y, AsteroideInvisible.class);
                if (asteroide != null) {
                    getWorld().removeObject(asteroide);
                }
            }
            for (int i = 0; i < getWorld().getObjects(DepositoCombustible.class).size(); i++) {
                DepositoCombustible deposito = (DepositoCombustible) getWorld().getObjects(DepositoCombustible.class).get(i);
                if (deposito != null) {
                    deposito.actualizarImagenAura();
                }
            }

            getWorld().removeObject(this);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Color obtenerColorDeBarraIndicadora() {
        return Color.RED;
    }

    /**
     * Una NaveFlota no tiene restricciones de combustible, por lo que puede
     * actuar siempre
     * 
     * @return {@code true}
     */
    @Override
    protected boolean puedeActuar() {
        return true;
    }

}
