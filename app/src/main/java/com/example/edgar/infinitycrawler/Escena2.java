package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ConcurrentModificationException;

/**
 * <p>Clase que define la escena número dos, que hereda de EscenaGenerica.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */

public class Escena2 extends EscenaGenerica {
    Surface vista;
    Bitmap sala, prota, puerta1, puerta2, antorcha, antorcha2, antorcha3;
    /**
     * Variable que define el tiempo en el que se produce un cambio en la antorcha.
     */
    int tiempoFrameAntorcha = 90;
    /**
     * El tiempo en el que se produce el cambio del frame.
     */
    long tickAntorcha;
    int cont2=0;
    public Escena2(Context context, final Surface vista, Paint p) {
        super(context, vista, p);
        this.vista = vista;

        inicializacionDeEscena();
        escalado();
        comprobacionSaker();
        Log.i("Const", "creo contructo 222");
    }

    /**
     * Función que comprueba si se está agitando el teléfono y suma 10 al controlDePulsaciones
     */
    public void comprobacionSaker() {
        vista.sake.setOnShakeListener(new Shake.OnShakeListener() {
            public void onShake() {
                if (vista.escena instanceof Escena2) {
                    try {
                        vista.controladorDeAgitacion += 10;
                    } catch (ConcurrentModificationException a) {
                    }
                }
            }
        });

    }

    /**
     * Sobreescritura del dibujado de EscenaGenérica para dibujar los elementos concretos de la escena.
     * Se le pasa como parámetro el canvas del Surface.
     *
     * @param c el canvas del SurfaceView.
     */
    @Override
    public void dibujado(Canvas c) {
        actual[0].onDraw(c);
        actual[2].onDraw(c);
        actual[3].onDraw(c);
        actual[4].onDraw(c);
        actual[5].onDraw(c);
        actual[6].onDraw(c);
        actual[1].onDraw(c);


    }

    /**
     * Sobreescrituda del método escalado definido en EscenaGenerica que realiza un escalado de las imágenes adaptándolas a la pantalla
     */
    @Override
    public void escalado() {
        int altoPantalla;
        int altoImagen;
        double coefreduc;

        altoImagen = actual[0].imagen.getHeight();

        altoPantalla = vista.getHeight();
        coefreduc = (double) altoPantalla / (double) altoImagen;
        actual[0].imagen = Bitmap.createScaledBitmap(actual[0].imagen, (int) (actual[0].imagen.getWidth() * coefreduc), (int) (actual[0].imagen.getHeight() * coefreduc), true);
        actual[2].cambiarPosicion(actual[0].imagen.getWidth() / 2, actual[0].imagen.getHeight() / 2);
        actual[3].cambiarPosicion(actual[0].imagen.getWidth() / 3, actual[0].imagen.getHeight() - actual[0].imagen.getHeight() / 12);
        actual[4].cambiarPosicion(actual[0].imagen.getWidth()/4, actual[0].imagen.getHeight()/4);
        actual[5].cambiarPosicion(actual[0].imagen.getWidth()/2, actual[0].imagen.getHeight()/4);
        actual[6].cambiarPosicion((int)(actual[0].imagen.getWidth()/1.3), actual[0].imagen.getHeight()/4);
    }

    /**
     * Sobreescritura del método inicializacionDeEscena definido en EscenaGenerica en la que se guardan los elementos concretos de la escena
     * dentro de el array actual tambié definido en EscenaGenerica.
     */
    @Override
    public void inicializacionDeEscena() {
        actual = new Imagen[]{new Imagen(sala = BitmapFactory.decodeResource(vista.getResources(), R.drawable.sala2), 0, 0),
                new Imagen(prota = BitmapFactory.decodeResource(vista.getResources(), R.drawable.prota2), 310, 300),
                new Imagen(puerta1 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.escalerassub), 70, 70),
                new Imagen(puerta2 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.puertaab), 310, 440),
                new Imagen (antorcha=BitmapFactory.decodeResource(vista.getResources(),R.drawable.antorcha1), 500,500),
                new Imagen(antorcha2=BitmapFactory.decodeResource(vista.getResources(), R.drawable.antorcha1), 600,600),
                new Imagen(antorcha3=BitmapFactory.decodeResource(vista.getResources(), R.drawable.antorcha1), 700,700)};
    }

    /**
     * Función que devuelve la imagen de la puerta número 1.
     *
     * @return la imagen de la puerta número 1.
     */
    @Override
    public Imagen getPuerta1() {
        return actual[2];
    }
    /**
     * Función que devuelve la imagen de la puerta número 2
     * @return la imagen de la puerta número 2.
     */
    @Override
    public Imagen getPuerta2() {
        return actual[3];
    }

    public void cambiarSprite() {
        if (cont2 < secuenciaAntorcha.length - 1 && this instanceof Escena2) {
            if ((System.currentTimeMillis() - tickAntorcha) > tiempoFrameAntorcha) {
                cont2++;
                cambiarSpriteAntorcha(cont2);
                cambiarSpriteAntorcha2(cont2);
                cambiarSpriteAntorcha3(cont2);
                tickAntorcha = System.currentTimeMillis();
            }
        } else {
            cont2 = 0;
        }
    }

    /**
     * Funciónes encargadas de generar el movimiento de las antorchas
     * @param cont variable que recorre el array secuenciaAntorcha.
     * @return la nueva imagen que corresponde a la posición indicada por el contador.
     */
    synchronized public Bitmap cambiarSpriteAntorcha(int cont){
        return actual[4].imagen=BitmapFactory.decodeResource(vista.getResources(), secuenciaAntorcha[cont]);
    }
    synchronized public Bitmap cambiarSpriteAntorcha2(int cont){
        return actual[5].imagen=BitmapFactory.decodeResource(vista.getResources(), secuenciaAntorcha[cont]);
    }
    synchronized public Bitmap cambiarSpriteAntorcha3(int cont){
        return actual[6].imagen=BitmapFactory.decodeResource(vista.getResources(), secuenciaAntorcha[cont]);
    }

    /**
     * Función que detecta la colisión con algunos elementos de la escena.
     * @return true, en caso de producirse la colisión.
     */
    @Override
    public boolean colisiones(){
        if(this.getPersonaje().colision(vista.escena.getPersonaje(), actual[5])||this.getPersonaje().colision(vista.escena.getPersonaje(), actual[4])){
            return true;
        }else{
            return false;
        }
    }

}