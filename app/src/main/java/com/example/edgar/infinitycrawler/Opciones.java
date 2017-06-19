package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * <p>Clase encargada de las opciones del juego</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Opciones extends EscenaGenerica {
    /**
     * Elementos de la escena.
     */
    Bitmap sonido, sonido2, regreso, quitarVibracion, activarVibracion;
    /**
     * Paint de la escena.
     */
    Paint p;
    public Opciones(Context contexto, Surface vista, Paint p) {
        super(contexto, vista, p);
        this.p = p;
        inicializacionDeEscena();
        escalado();
    }

    /**
     * Función encargada del dibujado.
     * @param c el canvas del SurfaceView.
     */
    @Override
    public void dibujado(Canvas c) {
        actual[0].onDraw(c);
        actual[1].onDraw(c);
        actual[2].onDraw(c);
        actual[3].onDraw(c);
        actual[4].onDraw(c);
        actual[5].onDraw(c);
        this.p.setColor(Color.WHITE);
        this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
        c.drawText(context.getString(R.string.subirvolumen),vista.getWidth()/7, (int)(vista.getHeight()/5.4), this.p);
        c.drawText(context.getString(R.string.bajarvolumen), vista.getWidth()/7, (int)(vista.getHeight()/2.1), this.p);
        c.drawText("Quitar vibración", vista.getWidth()/7,(int)(vista.getHeight()/1.35), this.p);
        c.drawText("Poner vibración", (int)(vista.getWidth()/2.3),(int)(vista.getHeight()/1.35), this.p);
    }

    /**
     * Función que se encarga de escalar las imágenes y de recolocarlar.
     */
    @Override
    public void escalado() {

        actual[0].imagen = Bitmap.createScaledBitmap(actual[0].imagen, vista.getWidth(), vista.getHeight(), true);
        actual[1].cambiarPosicion(vista.getWidth()/9, vista.getHeight()/5);
        actual[2].cambiarPosicion(vista.getWidth()/9, vista.getHeight()/2);
        actual[3].cambiarPosicion((int)(vista.getWidth()/1.2),(int)(vista.getHeight()/1.2));
        actual[4].cambiarPosicion(vista.getWidth()/9, (int)(vista.getHeight()/1.3));
        actual[5].cambiarPosicion((int)(vista.getWidth()/2.5), (int)(vista.getHeight()/1.3));

    }

    /**
     * Función que inicializa los elementos de la escena.
     */
    @Override
    public void inicializacionDeEscena() {
        actual = new Imagen[]{
                new Imagen(fondo = BitmapFactory.decodeResource(vista.getResources(), R.drawable.uisplitbackground), 0, 0),
                new Imagen(sonido =BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark12), vista.getWidth()/6,(int)( vista.getHeight()/1.5)),
                new Imagen(sonido2=BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark14), vista.getWidth()/6, (int)( vista.getHeight()/1.5)),
                new Imagen(regreso=BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark20), vista.getWidth()/6, (int)( vista.getHeight()/1.5)),
                new Imagen(quitarVibracion=BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark34), vista.getWidth()/6, (int) (vista.getHeight()/1.5)),
                new Imagen(activarVibracion=BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark22),vista.getWidth()/6, (int) (vista.getHeight()/1.5))
        };


    }

    /**
     * Deteccion de la pulsación del botón de aumentar volumen
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean pulsarBotonAumentarVol(float x, float y) {
        if (actual[1].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }

    }
    /**
     * Deteccion de la pulsación de bajar volumen.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean pulsarBotonBajarVol(float x, float y){
        if (actual[2].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Deteccion de la pulsación del botón de volver al menú.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean volverAMenu(float x, float y){
        if (actual[3].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Deteccion de la pulsación quitar vibración.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean botonQuitarVibracion(float x, float y){
        if (actual[4].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Deteccion de la pulsación poner vibración.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean botonPonerVibracion(float x, float y){
        if (actual[5].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }
    }
}
