package com.example.edgar.infinitycrawler;

/**
 * <p>Clase heredada de Personaje que se encarga de gestionar los atributos del bárbaro</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Barbaro extends Personaje {
    /**
     * Variable que establece la vida máxima del bárbaro
     */
    int VIDABARBARO=250;
    /**
     * Variable que acumula la experiencia que va adquiriendo el bárbaro
     */
    int experiencia;
    /**
     * El nivel inicial del personaje, que va aumentando a medida que se gana experiencia.
     */
    int nivel=1;
    public Barbaro(String nombre, int fuerza, int vida) {
        super(nombre, fuerza, vida);
        this.vida=VIDABARBARO;
        this.nivel=1;
    }

    /**
     * Función usada en combates para reducir la vida del bárbaro cuando el enemigo ataca.
     * @param vida cantidad de daño producida por el enemigo.
     */
    public void dano(int vida) {
        this.vida -= vida;

    }

    /**
     * Función que se encarga de hacer que si la vida es menor a cero la iguala a cero.
     * @return la vida del personaje.
     */
    public int getVida() {
        if (vida < 0) {
            vida = 0;
        }
        return vida;
    }

    /**
     * Función que se encarga de gestionar la experiencia del bárbaro.
     * @param cantidadDeExperiencia int que representa la experiencia dada por el enemigo.
     * @return devuelve true en caso de que se cumpla alguna de las condiciones establecidas.
     */
    public boolean ganarExperiencia(int cantidadDeExperiencia){
        experiencia+=cantidadDeExperiencia;
        if(experiencia==1500){
            VIDABARBARO+=50;
            nivel+=1;
            return true;
        }else if(experiencia==2500){
            VIDABARBARO+=50;
            nivel+=1;
            return true;
        }else if(experiencia==4500){
            VIDABARBARO+=100;
            nivel+=1;
            return true;
        }else if(experiencia==6500){
            VIDABARBARO+=100;
            nivel+=1;
            return true;
        }else if(experiencia==10500){
            VIDABARBARO+=150;
            nivel+=1;
            return true;
        }else{
            return false;
        }

    }

    /**
     * Función que actualiza los datos del personaje en caso de usar la cura.
     * @param nombre nombre del personaje.
     * @param fuerza fuerza del personaje
     */
    public void actualiza(String nombre, int fuerza) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.vida = VIDABARBARO;
    }

    /**
     * Función utilizada para poner en su punto inicial los parámetros del personaje
     */
    @Override
    public void reiniciarPersonaje(){
        this.VIDABARBARO=250;
        this.vida=VIDABARBARO;
        this.experiencia=0;
        this.nivel=1;
    }
}
