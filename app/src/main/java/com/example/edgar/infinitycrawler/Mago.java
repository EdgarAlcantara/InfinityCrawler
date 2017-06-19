package com.example.edgar.infinitycrawler;

/**
 * <p>Clase que define el personaje mago.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Mago extends Personaje {

    /**
     * Acumulador de la experiencia del mago.
     */
    int experiencia;
    /**
     * Vida base del mago.
     */
    int VIDAMAGO=100;

    public Mago(String nombre, int fuerza, int vida) {
        super(nombre, fuerza, vida);
        this.vida=VIDAMAGO;
        this.nivel=1;
    }

    /**
     * Función usada en combates para reducir la vida del mago cuando el enemigo ataca.
     *
     * @param vida cantidad de daño producido por el enemigo.
     */
    public void dano(int vida) {

        this.vida -= vida;

    }

    /**
     * Función que se encarga de hacer que si la vida es menor a cero la iguala a cero.
     *
     * @return la vida del personaje.
     */
    public int getVida() {
        if (vida < 0) {
            vida = 0;
        }
        return vida;
    }

    /**
     * Función encargada de subir la experiencia del personaje
     * @param cantidadDeExperiencia cantidad de experiencia recibida del enemigo.
     */
    public boolean ganarExperiencia(int cantidadDeExperiencia){
        experiencia+=cantidadDeExperiencia;
        if(experiencia==1500){
            VIDAMAGO+=50;
            nivel+=1;
            return true;
        }else if(experiencia==2500){
            VIDAMAGO+=50;
            nivel+=1;
            return true;
        }else if(experiencia==3000){
            VIDAMAGO+=50;
            nivel+=1;
            return true;
        }else if(experiencia==7000){
            VIDAMAGO+=50;
            nivel+=1;
            return true;
        }else if(experiencia==11000){
            VIDAMAGO+=50;
            nivel+=1;
            return true;
        }else{
            return false;
        }

    }

    /**
     * Función que ejecuta el ataque del mago.
     * @return el daño producido por el mago.
     */
    @Override
    public int atacar() {
        return generaNumeroAleatorio(80,120);
    }
    /**
     * Función que actualiza los datos del personaje en caso de usar la cura.
     * @param nombre
     * @param fuerza
     */
    public void actualiza(String nombre, int fuerza) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.vida = VIDAMAGO;
    }
    /**
     * Función utilizada para poner en su punto inicial los parámetros del personaje
     */
    @Override
    public void reiniciarPersonaje(){
        this.VIDAMAGO=100;
        this.vida=VIDAMAGO;
        this.experiencia=0;
        this.nivel=1;
    }
}
