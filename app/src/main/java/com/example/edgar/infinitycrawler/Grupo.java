package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

/**
 * <p>Clase que define la escena concreta del menú de grupo, hereda de EscenaGenerica y sobreescribe alguno de sus métodos
 * para definir sus propias características</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Grupo extends EscenaGenerica {
    /**
     * Definición de un bárbaro que cargara las características de el definido en la clase Surface.
     */
    Barbaro barbaro;
    /**
     * Definición de un mago que cargara las características de el definido en la clase Surface.
     */
    Mago mago;
    /**
     * Definición de un guerrero que cargara las características de el definido en la clase Surface.
     */
    Guerrero guerrero;
    /**
     * Bitmaps creadas para definir los elementos concretos de la escena.
     */
    Bitmap personaje1, personaje2, personaje3, regreso, fondo;

    Paint p;

    public Grupo(Context contexto, Surface vista, Guerrero guerrero, Mago mago, Barbaro barbaro, Paint p) {
        super(contexto, vista, p);
        this.p = p;

        this.mago = mago;
        this.guerrero = guerrero;
        this.barbaro = barbaro;
        inicializacionDeEscena();
        escalado();

    }

    /**
     * Sobreescritura del dibujado de EscenaGenérica para dibujar los elementos concretos de la escena.
     * Se le pasa como parámetro el canvas del Surface.
     *
     * @param c canvas del SurfaceView.
     */
    @Override
    public void dibujado(Canvas c) {
        actual[0].onDraw(c);
        actual[1].onDraw(c);
        actual[2].onDraw(c);
        actual[3].onDraw(c);
        actual[4].onDraw(c);
        actual[5].onDraw(c);
        this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra2));
        this.p.setTextAlign(Paint.Align.CENTER);
        this.p.setColor(Color.WHITE);

        c.drawText(context.getString(R.string.nombregrupo) + vista.guerrero.nombre, actual[1].posicion.x + actual[1].imagen.getWidth() / 2, actual[1].posicion.y + actual[1].imagen.getHeight() / 3, this.p);
        c.drawText(context.getString(R.string.vidagrupo)+ vista.guerrero.vida+"/"+vista.guerrero.VIDAGUERRERO, actual[1].posicion.x + actual[1].imagen.getWidth() / 2, actual[1].posicion.y + actual[1].imagen.getHeight() / 2, this.p);
        c.drawText(context.getString(R.string.nivelgrupo)+vista.guerrero.nivel,  actual[1].posicion.x + actual[1].imagen.getWidth() / 2, actual[1].posicion.y + (int)(actual[1].imagen.getHeight() / 1.5), this.p);
        c.drawText(context.getString(R.string.nombregrupo) + vista.mago.nombre,  actual[2].posicion.x + actual[2].imagen.getWidth() / 2, actual[2].posicion.y + actual[2].imagen.getHeight() / 3, this.p);
        c.drawText(context.getString(R.string.vidagrupo) + vista.mago.vida+"/"+vista.mago.VIDAMAGO, actual[2].posicion.x + actual[2].imagen.getWidth() / 2, actual[2].posicion.y + actual[2].imagen.getHeight() / 2, this.p);
        c.drawText(context.getString(R.string.nivelgrupo)+vista.mago.nivel, actual[2].posicion.x + actual[2].imagen.getWidth() / 2, actual[2].posicion.y + (int)(actual[2].imagen.getHeight() / 1.5), this.p);
        c.drawText(context.getString(R.string.nombregrupo) + vista.barbaro.nombre,actual[3].posicion.x + actual[3].imagen.getWidth() / 2, actual[3].posicion.y + actual[3].imagen.getHeight() / 3, this.p);
        c.drawText(context.getString(R.string.vidagrupo) + vista.barbaro.vida+"/"+vista.barbaro.VIDABARBARO,actual[3].posicion.x + actual[3].imagen.getWidth() / 2, actual[3].posicion.y + actual[3].imagen.getHeight() / 2, this.p);
        c.drawText(context.getString(R.string.nivelgrupo)+vista.barbaro.nivel, actual[3].posicion.x + actual[3].imagen.getWidth() / 2, actual[3].posicion.y + (int)(actual[3].imagen.getHeight() / 1.5), this.p);
    }

    /**
     * Sobreescrituda del método escalado definido en EscenaGenerica que realiza un escalado de las imágenes adaptándolas a la pantalla
     */
    @Override
    public void escalado() {

        actual[0].imagen = Bitmap.createScaledBitmap(actual[0].imagen, vista.getWidth(), vista.getHeight(), true);
        actual[1].cambiarPosicion(vista.getWidth()/9, vista.getHeight()/5);
        actual[2].cambiarPosicion((int)(vista.getWidth()/1.9), vista.getHeight()/5);
        actual[3].cambiarPosicion(vista.getWidth()/3,(int)( vista.getHeight()/2.2));
        actual[4].cambiarPosicion(vista.getWidth()/4, (int)(vista.getHeight()/1.3));
        actual[5].cambiarPosicion((int)(vista.getWidth()/1.49), (int)(vista.getHeight()/1.3));
    }

    /**
     * Sobreescritura del método inicializacionDeEscena definido en EscenaGenerica en la que se guardan los elementos concretos de la escena
     * dentro de el array actual tambié definido en EscenaGenerica.
     */
    @Override
    public void inicializacionDeEscena() {
        actual = new Imagen[]{
                new Imagen(fondo = BitmapFactory.decodeResource(vista.getResources(), R.drawable.uisplitbackground), 0, 0),
                new Imagen(personaje1 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cuadroguerrero), 100, 100),
                new Imagen(personaje2 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cuadromago), (int) (personaje1.getWidth() * 1.5), 100),
                new Imagen(personaje3 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cuadrobarbaro), personaje1.getWidth(), (int) (personaje1.getHeight() * 1.9))
                , new Imagen(regreso = BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark20), (int) (personaje3.getWidth() * 0.9), (int) (personaje3.getHeight() * 3)),
                new Imagen(regreso = BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark17), (int) (personaje3.getWidth() * 1.9), (int) (personaje3.getHeight() * 3))
        };


    }

    /**
     * Función que detecta la pulsación del botón volver del menú de grupo y devuelve true en caso afirmativo.
     *
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de que se haya pulsado
     */
    public boolean pulsarBotonVolver(float x, float y) {
        if (actual[4].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }

    }

    /**
     * Se detecta que se ha pulsado el botón de cura y se da la señal para proceder a actualizar los datos de los personajes.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de que se haya pulsado.
     */
    public boolean pulsarBotonRecuperar(float x, float y) {
        if (actual[5].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }

    }


}