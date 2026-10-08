import greenfoot.*;

/**
 * Define el comportamiento de una Explosion
 */
import greenfoot.*;

/**
 * Define el comportamiento de una Explosion
 */
public class Explosion extends ActorBase {
    GifImage gif = new GifImage("explo.gif");
    private int escala = 140;
    private boolean esExplosionBoss;

    /**
     * Constructor para decidir si esta es una explosión de jefe
     * 
     * @param esBoss indica si es una explosión de jefe
     */
    public Explosion(boolean esBoss) {
        this.esExplosionBoss = esBoss;
    }

    /**
     * Ejecuta la animación completa
     */
    public void animar() {
        for (int i = 0; i < 35; i++) {
            if (esExplosionBoss) {
                actualizarImagenBoss();
            } else {
                actualizarImagen();
            }
            Greenfoot.delay(1);
        }
    }

    /**
     * post: Genera una explosión en unas coordenadas del mundo
     * 
     * @param mundo es el mundo donde aparecerá la explosión
     * @param x     la coordenada x de la explosión
     * @param y     la coordenada y de la explosión
     * @param esBoss indica si es una explosión de jefe (más grande)
     */
    public static void en(World mundo, int x, int y, boolean esBoss) {
        Explosion explosion = new Explosion(esBoss);
        mundo.addObject(explosion, x, y);
        Greenfoot.playSound("explosion.wav");
        explosion.animar();
        mundo.removeObject(explosion);
    }

    /**
     * Avanza un fotograma en la animación de la explosión estándar
     */
    protected void actualizarImagen() {
        setImage(gif.getCurrentImage());
    }

    /**
     * Avanza un fotograma en la animación de una explosión más grande
     */
    protected void actualizarImagenBoss() {
        GreenfootImage imagen = gif.getCurrentImage();
        imagen.scale(imagen.getWidth() * escala / 100, imagen.getHeight() * escala / 100);
        setImage(imagen);
    }
}
