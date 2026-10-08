import greenfoot.*;

/**
 * Define características y comportamientos comunes a todas las Naves de la
 * Batalla Espacial.
 */
public abstract class NaveBaseBOSS extends ActorBase implements Dañable {
    protected double ESCALA_X = 10;
    protected double ESCALA_Y = 10;

    /**
     * La dirección en la que apunta la Nave
     */
    protected Direccion direccion;

    /**
     * Inicializa una Nave apuntando hacia el Direccion.NORTE
     */
    public NaveBaseBOSS() {
        this(Direccion.NORTE);
    }

    /**
     * pre: Se le pasa como parametro la direccion donde apuntara la naveBOSS
     * post: Inicializa la naveBOSS apuntando hacia la direccion
     */
    public NaveBaseBOSS(Direccion direccion) {
        setDireccion(direccion);
        imagenBase = getImage();
    }

    /**
     * pre: Se le pasa por parametro la direccion
     * post: rota la nave en la direccion dentro del mismo cuadrado
     */
    protected void setDireccion(Direccion direccion) {
        this.direccion = direccion;
        setRotation(this.direccion.rotacion);
    }

    /**
     * post: Actualiza la imagen de la Nave. Agrega la barra indicadora debajo.
     */
    @Override
    protected void actualizarImagen() {
        int tamCelda = getWorld().getCellSize();
        GreenfootImage image = getImage();
        image.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
        setImage(image);
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
        nuevaImagen.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
        setImage(nuevaImagen);
    }
    
}
