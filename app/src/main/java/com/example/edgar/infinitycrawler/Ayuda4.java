package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * <p>Clase que define la escena concreta de la página 4 del menu de ayuda, hereda de EscenaGenerica y sobreescribe alguno de sus métodos
 * para definir sus propias características</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Ayuda4 extends EscenaGenerica {
    /**
     * Definición de los elementos que se encuentran en esta escena.
     */
    Bitmap boton, boton2, explicacion;

    public Ayuda4(Context contexto, Surface vista, Paint p) {
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

        altoImagen = actual[1].imagen.getHeight();
        coefreduc = (double) altoImagen / (double) altoImagen;
        actual[2].imagen = Bitmap.createScaledBitmap(actual[2].imagen, (int) (actual[2].imagen.getWidth() * coefreduc), (int) (actual[2].imagen.getHeight() * coefreduc), true);
    }

    /**
     * Sobreescritura del método inicializacionDeEscena definido en EscenaGenerica en la que se guardan los elementos concretos de la escena
     * dentro de el array actual tambié definido en EscenaGenerica.
     */
    @Override
    public void inicializacionDeEscena() {
        actual = new Imagen[]{
                new Imagen(boton = BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark20), vista.getWidth() - vista.getWidth() / 7, vista.getHeight() - vista.getHeight() / 4),
                new Imagen(boton2 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark34), vista.getWidth() / 12, vista.getHeight() - vista.getHeight() / 4)
                , new Imagen(explicacion = BitmapFactory.decodeResource(vista.getResources(), R.drawable.ayudabatall), vista.getWidth() / 16, vista.getHeight() / 16)
        };
    }

    /**
     * Funciones definidas para comprobar si se ha producido colisión con el botón salir de ayuda.
     * @param x posición x del event registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haber pulsado el botón y false en caso de que no se haya pulsado.
     */
    public boolean pulsarBotonSalirDeAyuda4(float x, float y) {
        if (actual[1].colisiona(x, y)) {

            return true;
        } else {
            return false;
        }
    }
}
