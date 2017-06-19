package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

/**
 * <p>Clase que define la escena concreta de la página 3 del menu de ayuda, hereda de EscenaGenerica y sobreescribe alguno de sus métodos
 * para definir sus propias características</p>
 * @author Edgar Alcántara
 * @version 1.0
 */

public class Ayuda3 extends EscenaGenerica {
    /**
     * Elementos presentes en la escena
     */
    Bitmap boton, boton2, explicacion;
    /**
     * TextPaint que guarda las características del paint pasado como parámetro en el constructor y que se
     * utiliza en el StaticLayout.
     */
    TextPaint tpaint;
    /**
     * StaticLayout en el que se introduce el texto
     */
    StaticLayout recuadroTexto;

    public Ayuda3(Context contexto, Surface vista, Paint p) {
        super(contexto, vista, p);
        inicializacionDeEscena();
        escalado();
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
     * Sobreescritura del dibujado de EscenaGenérica para dibujar los elementos concretos de la escena.
     * Se le pasa como parámetro el canvas del Surface.
     *
     * @param c el canvas del SurfaceView.
     */
    @Override
    public void dibujado(Canvas c) {

        actual[1].onDraw(c);
        actual[0].onDraw(c);
        p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
        p.setColor(Color.WHITE);
        p.setTextAlign(Paint.Align.LEFT);
        tpaint = new TextPaint();
        tpaint.setTextSize(dpTopx(context, R.dimen.tamanoletra));
        tpaint.setTextAlign(Paint.Align.LEFT);
        tpaint.setColor(Color.WHITE);
        tpaint.set(p);


        recuadroTexto = new StaticLayout(context.getString(R.string.textoayuda2), tpaint, vista.getWidth() * 7, Layout.Alignment.ALIGN_NORMAL, 1, 1, true);
        recuadroTexto.draw(c);

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
                , new Imagen(explicacion = BitmapFactory.decodeResource(vista.getResources(), R.drawable.ayuda), vista.getWidth() / 16, vista.getHeight() / 16)
        };
    }

    /**
     * Funciones definidas para comprobar si se ha producido colisión con el botón salir de ayuda.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haber pulsado el botón y false en caso de que no se haya pulsado.
     */
    public boolean pulsarBotonSalirDeAyuda3(float x, float y) {
        if (actual[1].colisiona(x, y)) {

            return true;
        } else {
            return false;
        }
    }
    /**
     * Funciones definidas para comprobar si se ha producido colisión con el botón de pasar a la siguiente escena.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haber pulsado el botón y false en caso de que no se haya pulsado.
     */
    public boolean pulsarBotonSiguienteDeAyuda3(float x, float y) {
        if (actual[0].colisiona(x, y)) {

            return true;
        } else {
            return false;
        }
    }
}
