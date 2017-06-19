package com.example.edgar.infinitycrawler;


/**
 * <p>Clase genérica que gestiona la creación de personajes y que les da el estado inicial a sus atributos principales.</p>
 * @author edgar
 * @version 1.0
 */
public class Personaje {
    /**
     * Propiedad en la que se establece el nombre del personaje.
     */
    public String nombre;
    int fuerza, vida, nivel;

    /**
     * Función que devuelve la vida del personaje
     * @return la vida del personaje.
     */
    public int getVida() {
        return vida;
    }

    /**
     * Función que pone la vida del personaje
     * @param vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Constructor en el que se inicializa un personaje
     * @param nombre
     * @param fuerza
     */
    public Personaje(String nombre, int fuerza, int vida) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.vida=vida;
    }

    /**
     * Función que actualiza los datos el personaje
     * @param nombre el nombre del personaje.
     * @param fuerza la fuerza del personaje
     * @param vida la vida del personaje
     */
    public void actualiza(String nombre, int fuerza, int vida) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.vida = vida;
    }

    /**
     * Función que reinicia los parámetros del personaje en caso de terminar la partida.
     */
    public void reiniciarPersonaje(){
    }

    /**
     * Función que genera un número aleatorio entre un rango dado por las variables minimo y maximo.
     *
     * @param minimo cantidad mínima del rango.
     * @param maximo cantidad máxima del rango.
     * @return número generado.
     */
    public int generaNumeroAleatorio(int minimo, int maximo) {

        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

    /**
     * Función que determina el daño producido por el personaje dependiendo de tu fuerza
     * @return cantidad de daño producida
     */
    public int atacar() {
        int dano = 0;
        if (fuerza > 1 && fuerza <= 10) {
            dano = generaNumeroAleatorio(10,30);
        } else if (fuerza > 10 && fuerza <= 20) {
            dano = generaNumeroAleatorio(30,70);
        } else if (fuerza > 20 && fuerza <= 40) {
            dano = generaNumeroAleatorio(70,100);
        }
        return dano;
    }
}
