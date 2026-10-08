import greenfoot.*;

public class MundoBOSS extends MundoBase {
    private int depositosDestruidos;
    private int contadorMinionLider = 0;

    /**
     * post: Genera el mapa del MundoBOSS
     */
    public MundoBOSS() {
        super(19, 20, 45);
        // Genera los asteroides que rodean a la nave BOSS.
        generarAsteroidesBOSS();

        // Genera los asteorides que protejen a los depositos de combustible.
        generarAsteroidesEscudo();

        // Genera el deposito de combustibel.
        generarDespositosCombustible();

        // Contador.
        depositosDestruidos = 0;
        
        
    }

    /**
     * post: Genera las naves: NaveMinion, NaveDeAtaque y NaveBOSS
     */
    protected void generarNaves() {
        agregar(new NaveDeAtaque(Direccion.NORTE, 109), 9, 18);
        agregar(new NaveDeAtaque(Direccion.SUR, 0), 10, 4);
        agregar(new NaveMinionLider(Direccion.SUR), 9, 10);
        agregar(new NaveMinionLider(Direccion.SUR), 9, 4);
        agregar(new NaveMinion(Direccion.SUR), 8, 13);
        agregar(new NaveMinion(Direccion.SUR), 7, 11);
        agregar(new NaveMinion(Direccion.SUR), 10, 13);
        agregar(new NaveMinion(Direccion.SUR), 11, 11);
        agregar(new NaveBOSS(), 9, 3);
    }

    /**
     * post: Destruye los depositos de manera manual
     */
    public void destruirDepositoManual() {
        depositosDestruidos++;
        if (depositosDestruidos == 2) {
            eliminarAsteroides();
        }
    }

    /**
     * post: elimina los asteroides
     */
    protected void eliminarAsteroides(){
        while (!getObjects(AsteroideInvisible.class).isEmpty()) {
            Actor asteroideinvisible = getObjects(AsteroideInvisible.class).get(0); 
            removeObject(asteroideinvisible);  
        }
        NaveBaseBOSS nave = (NaveBaseBOSS) getObjects(NaveBaseBOSS.class).get(0);
        if (nave != null) {
            nave.actualizarImagenAura();

        }
    }

    /**
     * post: Genera el mundo
     */
    protected void generarPOIs() {
        marcarCelda(9, 18, new Color(200, 0, 0, 150));
    }

    /**
     * post: Genera los asteroides que rodean a la NaveBOSS
     */
    protected void generarAsteroidesBOSS() {
        int x = ((getWidth() / 2) + 6);
        int y = ((getHeight() / 2) - 10);
        for (int i = 0; i < 9; i++){
            agregar(new AsteroideInvisible(), x, y);
            y++;
        }
        for (int j = 0; j < 12; j++){
            agregar(new AsteroideInvisible(), x, y);
            x--;
        }
        for (int k = 0; k < 10; k++){
            agregar(new AsteroideInvisible(), x, y);
            y--;
        }
    }

    /**
     * post: Genera los asteroides que protejen los barriles
     */
    protected void generarAsteroidesEscudo() {
        int x = ((getWidth() / 2) + 7);
        int y = ((getHeight() / 2)-1);
        for (int i = 0; i < 3; i++){
            agregar(new AsteroideInvisible(), x, y);
            x++;
        }
        x = ((getWidth() / 2) - 7);
        y = ((getHeight() / 2)-1);
        for (int i = 0; i < 3; i++){
            agregar(new AsteroideInvisible(), x, y);
            x--;
        }
    }

    /**
     * post: Genera los DepositoCombustible
     */
    protected void generarDespositosCombustible() {
        agregar(new DepositoCombustible(), 1, 3);
        agregar(new DepositoCombustible(), 17, 3);
    }
    
    public void incrementarContadorMinionLider() {
        contadorMinionLider++;
    }
    
    public int obtenerContadorMinionLider(){
        return contadorMinionLider;
    }
}
