package com.example.edgar.infinitycrawler;

/**
 * <p>Clase que hereda de Personaje y que define las características particulares del personaje guerrero.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Guerrero extends Personaje {
    int VIDAGUERRERO=200;
    int experiencia;
    public Guerrero(String nombre, int fuerza, int vida) {
        super(nombre, fuerza, vida);
        this.vida=VIDAGUERRERO;
        this.nivel=1;
    }

    /**
     * Función usada en combates para reducir la vida del guerrero cuando el enemigo ataca.
     *
     * @param vida
     */
    public void dano(int vida) {
        this.vida -= vida;

    }
    public boolean ganarExperiencia(int cantidadDeExperiencia) {
        experiencia += cantidadDeExperiencia;
        if (experiencia == 1000) {
            VIDAGUERRERO += 50;
            nivel+=1;
            return true;
        } else if (experiencia == 2000) {
            VIDAGUERRERO += 50;
            nivel+=1;
            return true;
        } else if (experiencia == 4000) {
            VIDAGUERRERO += 100;
            nivel+=1;
            return true;
        }else if(experiencia==6000){
            VIDAGUERRERO+=50;
            nivel+=1;
            return true;
        }else if(experiencia==10000){
            VIDAGUERRERO+=50;
            nivel+=1;
            return true;
        }else {
            return false;
        }
    }
    /**
     * Función que se encarga de hacer que si la vida es menor a cero la iguala a cero.
     *
     * @return devuelve la vida del guerrero.
     */
    public int getVida() {
        if (vida < 0) {
            vida = 0;
        }
        return vida;
    }
    /**
     * Función que actualiza los datos del personaje en caso de usar la cura.
     * @param nombre nombre del personaje.
     * @param fuerza fuerza del personaje.
     */
    public void actualiza(String nombre, int fuerza) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.vida = VIDAGUERRERO;
    }
    /**
     * Función utilizada para poner en su punto inicial los parámetros del personaje
     */
    @Override
    public void reiniciarPersonaje(){
        this.VIDAGUERRERO=200;
        this.vida=VIDAGUERRERO;
        this.experiencia=0;
        this.nivel=1;
    }
}
