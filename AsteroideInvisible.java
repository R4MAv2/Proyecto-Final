import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AsteroideInvisible extends Asteroide implements Dañable {
    
    /**
     * Inicializa el TAMAÑO_MAXIMO_DE_ASTEROIDE en 1000000
     * y no se actualiza
     */

    protected int tamaño;
    
    /**
     * Genera el asteroide con el tamaño en 999999
     */
    public AsteroideInvisible() {
        this(999999);
    }
    
    /**
     * Actualiza la imagen del asteroide
     */
    public AsteroideInvisible(int tamañoInicial) {
        this.tamaño = 999999;
    }

    public void recibirDañoDe(Atacante atacante) {
        int daño = atacante.obtenerDaño();
        this.tamaño -= daño;
        actualizarImagen();
        Explosion.en(getWorld(), this.getX(), this.getY(), false);
        if (this.tamaño <= 0) {
            getWorld().removeObject(this);
        }
    }

    /**
     * Utiliza el tamaño para actualizar la imagen
     * del asteroide
     */
    @Override
    public void actualizarImagen() {
        int tamCelda = getWorld().getCellSize();
        int ancho = Math.max(30, (tamCelda));
        GreenfootImage image = getImage();
        if (this.tamaño <= 0)
            image.setTransparency(0);
        image.scale(ancho, ancho);
        setImage(image);
    }

    /**
     * retorna el tamaño actual del asteroide
     */
    public int obtenerTamaño() {
        return this.tamaño;
    }
}

