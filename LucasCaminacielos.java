public class LucasCaminacielos extends PilotoBase {
    @Override
    public void subirse(NaveDeAtaque nave) {
        super.subirse(nave);
    }
    
    @Override
    public void bajarse() {
        super.bajarse();
    }

    private void despegar() {
        navePilotada.encenderMotores();
    }

    private void avanzarAlNortePor(int casilleros) {
        for (int pasos = 0; pasos < casilleros; pasos++) {
            navePilotada.avanzarHacia(Direccion.NORTE);
        }
    }
    
    public void atacarHaciaPor(int cantidad, Direccion direccion){
        for(int i = 0; i < cantidad; i++){
            navePilotada.atacarHacia(direccion);
        }
    }
    
    private void destruirNaveEnemigaHacia(Direccion direccion){
        while (navePilotada.hayNaveHacia(direccion)) {
            navePilotada.atacarHacia(direccion);
        }
    }
    
    private int destruirAsteroideHacia(Direccion direccion) {
        int ataques = 0;
        while (navePilotada.hayAsteroideHacia(direccion)) {
            navePilotada.atacarHacia(direccion);
            ataques++;
        }
        return ataques;
    }
    
    private void avanzarAlOestePor(int casilleros) {
        for (int pasos = 0; pasos < casilleros; pasos++) {
            navePilotada.avanzarHacia(Direccion.OESTE);        
        }
    }
    
    private void avanzarAlSurPor(int casilleros) {
        for (int pasos = 0; pasos < casilleros; pasos++) {
            navePilotada.avanzarHacia(Direccion.SUR);        
        }
    }
    
    private void avanzarAlEstePor(int casilleros) {
        for (int pasos = 0; pasos < casilleros; pasos++) {
            navePilotada.avanzarHacia(Direccion.ESTE);        
        }
    }
    
    private void arranque(NaveDeAtaque nave){
        subirse(nave);
        despegar();
    }
    
    private void llegarAAsteroide1(){
        avanzarAlEstePor(8);
        avanzarAlNortePor(8);
    }
    
    private void llegarAAsteroide2(){
        avanzarAlSurPor(7);
        avanzarAlOestePor(16);
        avanzarAlNortePor(6);
    }
     
    public void destruirAsteroide1(){
        llegarAAsteroide1();
        destruirAsteroideHacia(Direccion.NORTE);
        avanzarAlNortePor(1);
    }
    
    private void destruirAsteroide2(){
        llegarAAsteroide2();
        destruirAsteroideHacia(Direccion.NORTE);
        avanzarAlNortePor(1);
    }
    
    //Mision y Fases
    
    public void MisionComando(NaveDeAtaque nave){
        faseUno(nave);
        faseDos();
        faseTres();
    }
    
    public void faseUno(NaveDeAtaque nave){
        arranque(nave);
        avanzarAlNortePor(5);
        
        //Primera naveMinion
        destruirNaveEnemigaHacia(Direccion.OESTE);
        avanzarAlOestePor(1);
        
        //Primer ataque a naveMinionLider
        avanzarAlEstePor(1);
        avanzarAlNortePor(2);
        atacarHaciaPor(2, Direccion.NORTE);
        
        //Segunda naveMinion
        avanzarAlOestePor(1);
        destruirNaveEnemigaHacia(Direccion.OESTE);
        avanzarAlOestePor(1);
        arranque(nave);
        
        //Segundo ataque a naveMinionLider
        avanzarAlEstePor(2);
        atacarHaciaPor(2, Direccion.NORTE);
        
        //Tercera naveMinion
        avanzarAlEstePor(1);
        destruirNaveEnemigaHacia(Direccion.ESTE);
        avanzarAlEstePor(1);
        
        //Tercer ataque a naveMinionLider
        avanzarAlOestePor(2);
        atacarHaciaPor(2, Direccion.NORTE);
        
        //Cuarta naveMinion
        avanzarAlSurPor(2);
        destruirNaveEnemigaHacia(Direccion.ESTE);
        avanzarAlEstePor(1);
        
        //Ataque final a naveMinionLider
        avanzarAlOestePor(1);
        avanzarAlNortePor(2);
        destruirNaveEnemigaHacia(Direccion.NORTE);
        avanzarAlNortePor(1);
    }
    
    public void faseDos(){
        destruirAsteroide1();
        destruirAsteroide2();
    }
    
    public void faseTres(){
        avanzarAlEstePor(3);
        atacarHaciaPor(1, Direccion.ESTE);
        avanzarAlEstePor(4);
        avanzarAlSurPor(1);
        atacarHaciaPor(1, Direccion.ESTE);
        avanzarAlEstePor(1);
    }
}
