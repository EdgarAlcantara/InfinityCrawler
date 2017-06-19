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
 * <p>Clase que define la escena que se carga de ganar la partida.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Escena10 extends EscenaGenerica {
    /**
     * Paint utilizado para escribir el texto.
     */
    Paint p;
    Bitmap botoncontinuar, botonaux;
    /**
     * TextPaint que guarda las características del paint pasado como parámetro en el constructor y que se
     * utiliza en el StaticLayout.
     */
    StaticLayout recuadroTextoFinal;
    /**
     * TextPaint que guarda las características del paint pasado como parámetro en el constructor y que se
     * utiliza en el StaticLayout.
     */
    TextPaint tpaint;

    public Escena10(Context contexto, Surface vista, Paint p) {
        super(contexto, vista, p);
        this.p = p;
        inicializacionDeEscena();
        escalado();
    }

    /**
     * Sobreescritura del dibujado de EscenaGenérica para dibujar los elementos concretos de la escena.
     * Se le pasa como parámetro el canvas del Surface.
     * @param c
     */
    @Override
    public void dibujado(Canvas c) {
        actual[0].onDraw(c);
        this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra3));
        this.p.setColor(Color.BLACK);
        this.p.setTextAlign(Paint.Align.LEFT);
        tpaint = new TextPaint();
        tpaint.set(this.p);
        recuadroTextoFinal = new StaticLayout(context.getString(R.string.textofinal2), tpaint, vista.getWidth() * 7, Layout.Alignment.ALIGN_NORMAL, 1, 1, true);
        recuadroTextoFinal.draw(c);
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
        actual[0].imagen = Bitmap.createScaledBitmap(actual[0].imagen, (int) (actual[0].imagen.getWidth() * coefreduc), (int) (actual[0].imagen.getHeight() * coefreduc), true);

    }

    /**
     * Sobreescritura del método inicializacionDeEscena definido en EscenaGenerica en la que se guardan los elementos concretos de la escena
     * dentro de el array actual tambié definido en EscenaGenerica.
     */
    @Override
    public void inicializacionDeEscena() {
        actual = new Imagen[]{
                new Imagen(botoncontinuar = BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark20), vista.getWidth() - vista.getWidth() / 7, vista.getHeight() - vista.getHeight() / 4),
                new Imagen(botonaux = BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark17), vista.getWidth() - vista.getWidth() / 7, vista.getHeight() - vista.getHeight() / 4)
        };
    }

    /**
     * Función que comprueba si se ha pusado el botón continuar de la escena10.
     *
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de que se produzca la colisión y false en caso contrario.
     */
    public boolean tocarBotonContinuar(float x, float y) {
        if (actual[0].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }
}
