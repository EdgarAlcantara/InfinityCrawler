package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * <p>Clase que define la escena número siete, que hereda de EscenaGenerica.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */

public class Escena7 extends EscenaGenerica {
    /**
     * Elementos de la escena
     */
    Bitmap sala, prota, escalera, puerta;
    public Escena7(Context contexto, Surface vista, Paint p) {
        super(contexto, vista, p);
        inicializacionDeEscena();
        escalado();
    }

    /**
     * Sobreescritura del dibujado de EscenaGenérica para dibujar los elementos concretos de la escena.
     * Se le pasa como parámetro el canvas del Surface.
     *
     * @param c el canvas del SurfaceView.
     */
    @Override
    public void dibujado(Canvas c) {
        actual[2].onDraw(c);
        actual[3].onDraw(c);
        actual[0].onDraw(c);
        actual[2].onDraw(c);
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
    }

    /**
     * Sobreescritura del método inicializacionDeEscena definido en EscenaGenerica en la que se guardan los elementos concretos de la escena
     * dentro de el array actual tambié definido en EscenaGenerica.
     */
    @Override
    public void inicializacionDeEscena() {
        actual = new Imagen[]{
                new Imagen(sala = BitmapFactory.decodeResource(vista.getResources(), R.drawable.sala7), 0, 0),
                new Imagen(prota = BitmapFactory.decodeResource(vista.getResources(), R.drawable.prota4), 100, 100),
                new Imagen(escalera = BitmapFactory.decodeResource(vista.getResources(), R.drawable.ogroescenario), 520, 300),
                new Imagen(puerta = BitmapFactory.decodeResource(vista.getResources(), R.drawable.escalerasbaj), 200, 200),
        };
    }

    /**
     * Función que devuelve el Bitmap del fondo.
     *
     * @return el bitmap del fondo.
     */
    @Override
    public Bitmap getFondo() {
        return actual[0].imagen;
    }

    /**
     * Sobreescritura del método detección para ajustarlo a los requisitos de la escena7.
     */
    @Override
    public void deteccion() {
        try {
            vista.hilo.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.getPersonaje().setRectangulos().intersect(this.getBoss().setRectangulos())) {
            vista.escenas = 8;
            vista.cambiar = true;
        }
        cambiarImagenPersonaje();
    }

    /**
     * Funciones que devuelven las varialbe tipo Imagen del boss para detectar en la función deteccion (sobreescrita de EscenaGenerica)
     * la colisión.
     *
     * @return la imagen asociada al boss.
     */
    public Imagen getBoss() {
        return actual[2];
    }

}
