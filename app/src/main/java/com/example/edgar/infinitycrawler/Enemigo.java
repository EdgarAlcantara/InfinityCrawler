package com.example.edgar.infinitycrawler;

import android.content.Context;

/**
 *<p>Clase específica que gestiona las características del enemigo.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Enemigo {
    /**
     * Variable de tipo Surface que captura las características actuales de la clase Surface, para usarla en sus características.
     */
    Surface vista;
    /**
     * Variable Context que captura el estado del contexto del SurfaceView.
     */
    Context context;
    /**
     * Variable asociada a la vida del enemigo.
     */
    int vida;
    /**
     * Imagen del enemigo.
     */
    Imagen imagen;

    /**
     * Función que devuelve un número aleatorio en un rango dado.
     * @param minimo cantidad mínima del rango.
     * @param maximo cantidad máxima del rango.
     * @return el número generado
     */
    public int generaNumeroAleatorio(int minimo, int maximo) {

        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

    public Enemigo(int vida, Imagen imagen) {
        this.vida = vida;
        this.imagen = imagen;
    }

    /**
     * Función encargada de recargar el estado anterior del enemigo tras el combate.
     *
     * @param vida la vida del enemigo.
     * @param imagen la imagen que corresponde el enemigo.
     */
    public void actualizaenemigo(int vida, Imagen imagen) {
        this.vida = vida;
        this.imagen = imagen;
    }

    /**
     * Función específica del enemigo común, que se encarga de gestionar el daño que hace.
     *
     * @return daño que produce el enemigo.
     */
    public int atacar() {
        return generaNumeroAleatorio(60, 70);
    }

    /**
     * Función que realiza lo mismo que la función atacar, sólo que esta vez representa
     * el daño del boss de la mazmorra.
     *
     * @return daño que produce el boss.
     */
    public int atacar2() {
        return generaNumeroAleatorio(50, 200);
    }

    /**
     * Función que devuelve la experiencia que da el enemigo tras el combate
     * @return cantidad de experiencia.
     */
    public int darExperiencia(){
        return 500;
    }
}
