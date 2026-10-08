/**
 * Define cómo se efectuarán los daños en la Batalla
 */
public interface Dañable {
    /**
     * pre: Se le pasa por parametro el atacante
     * post: El dañable recibe daño del atacante asignado
     */
    public void recibirDañoDe(Atacante atacante);
}
