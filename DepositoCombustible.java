import greenfoot.Color;
import greenfoot.GreenfootImage;

public class DepositoCombustible extends Asteroide {
    /**
     * Es el tamaño inicial con el que se creó el DepositoCombustible
     */
    protected final int tamañoInicial;
    protected int aura;

    /**
     * Inicializa un DepositoCombustible con un tamaño de 80 puntos
     */
    public DepositoCombustible() {
        this(1);  
    }

    /**
     * Inicializa un DepositoCombustible con tamaño arbitrario,
     * se le pasa por parametro el tamañoInical del deposito
     */
    public DepositoCombustible(int tamañoInicial) {
        this.tamañoInicial = tamañoInicial;
        this.tamaño = tamañoInicial;  
    }

    /**
     * @return la cantidad de combustible de la que dispone
     */
    public int consultarCombustibleDisponible() {
        return this.tamaño;
    }

    /**
     * post: recibe daño del atacante que se le pasa por parametro
     */
    public void recibirDañoDe(Atacante atacante) {
        int daño = atacante.obtenerDaño();
        this.tamaño -= daño;
        actualizarImagen();
        Explosion.en(getWorld(), this.getX(), this.getY(), false);
        if (this.tamaño <= 0) {
            MundoBOSS mundo = (MundoBOSS) getWorld();
            mundo.destruirDepositoManual();
            CombustibleBoss item = new CombustibleBoss();
            getWorld().addObject(item, getX(), getY());
            getWorld().removeObject(this);

        }
    }

    /**
     * @return el tamaño inicial del DepositoCombustible
     */
    protected int obtenerTamañoMaximo() {
        return this.tamañoInicial;
    }
    
    public Color getAura() {
        return MyGreenfootImage.AURAS[5];
    }
    
    GreenfootImage imagenOriginal = new GreenfootImage(getImage());
    
    public void actualizarImagenAura() {
        int tamCelda = getWorld().getCellSize();
        setImage(imagenOriginal);
        MyGreenfootImage nuevaImagen = new MyGreenfootImage(getImage()) {
                public void configurar() {
                    highlight(getAura());
                }
            };
        nuevaImagen.scale((int) (tamCelda), (int) (tamCelda + 10));
        setImage(nuevaImagen);
    }
}
