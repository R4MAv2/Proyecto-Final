import greenfoot.*;

/**
 * Define el comportamiento de las Naves de Ataque Enemigas
 */
public class NaveMinionLider extends NaveFlota implements Atacante {
    protected int aura;
    

    /**
     * pre: se le pasa por parametro la direccion hacia donde apuntara.
     * post: crea una nueva naveMinionLider.
     */
    public NaveMinionLider(Direccion direccion) {
        super(direccion);
        salud = 300;

    }

    /**
     * pre: se le pasa por parametro la nave atacante
     * post: recibe daño de la nave atacante
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
     * post: retorna el daño que hace la naveMinionLider que es 15
     */
    @Override
    public int obtenerDaño() {
        return 15;
    }

    /**
     * post: retorna la salud actual de la naveMinionLider
     */
    public int obtenerSalud() {
        return this.salud;
    }

    /**
     * pre: se le pasa por parametro el objetivo
     * post: ataca el objetivo
     */
    private void atacar(Dañable objetivo) {
        objetivo.recibirDañoDe(this);
    }

    /**
     * pre: se le pasa por parametro la direccion
     * post: ataca hacia la direccion
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
    
    /**
     * post: retorna el color del aura
     */
    public Color getAura() {
        return MyGreenfootImage.AURAS[5];
    }

    GreenfootImage imagenOriginal = new GreenfootImage(getImage());
    
    /**
     * post: actualiza la imagen
     */
    public void actualizarImagen() {
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

