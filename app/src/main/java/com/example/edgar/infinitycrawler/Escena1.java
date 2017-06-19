package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * <p>Clase que define la escena número uno, heredando de EscenaGenerica.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Escena1 extends EscenaGenerica {
    /**
     * Elementos de la escena.
     */
    Bitmap sala, prota, puerta1, puerta2, decorado, decorado2, decorado3;

    public Escena1(Context context, Surface vista, Paint p) {
        super(context, vista, p);
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
        actual[2].cambiarPosicion(actual[0].imagen.getWidth() / 2, actual[0].imagen.getHeight() - actual[0].imagen.getHeight());
        actual[3].cambiarPosicion(actual[0].imagen.getWidth() / 3, actual[0].imagen.getHeight() - actual[0].imagen.getHeight() / 12);
        actual[4].cambiarPosicion(actual[0].imagen.getWidth()/2, actual[0].imagen.getHeight()/2);
        actual[5].cambiarPosicion(actual[0].imagen.getWidth()/8, actual[0].imagen.getHeight()/3);
        actual[6].cambiarPosicion((int)(actual[0].imagen.getWidth()/1.2), actual[0].imagen.getHeight()/3);
    }

    /**
     * Sobreescritura del método inicializacionDeEscena definido en EscenaGenerica en la que se guardan los elementos concretos de la escena
     * dentro de el array actual tambié definido en EscenaGenerica.
     */
    @Override
    public void inicializacionDeEscena() {
        actual = new Imagen[]{
                new Imagen(sala = BitmapFactory.decodeResource(vista.getResources(), R.drawable.fondo), 0, 0),
                new Imagen(prota = BitmapFactory.decodeResource(vista.getResources(), R.drawable.prota9), 50, 50),
                new Imagen(puerta1 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.puerta), dpTopx(context, R.dimen.posicionpuerta1Escena1), dpTopx(context, R.dimen.posicionpuerta2Escena1)),
                new Imagen(puerta2 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.puertaab), dpTopx(context, R.dimen.posicionpuerta2), dpTopx(context, R.dimen.posicionpuerta22)),
                new Imagen(decorado = BitmapFactory.decodeResource(vista.getResources(), R.drawable.columna), 300, 300),
                new Imagen(decorado2 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.columna), 400, 400),
                new Imagen(decorado3 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.estatua), 500, 500)
    };
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
    /**
     * Función que detecta la colisión con algunos elementos de la escena.
     * @return true, en caso de producirse la colisión.
     */
   @Override
    public boolean colisiones(){
       if(this.getPersonaje().colision(vista.escena.getPersonaje(), actual[5])||this.getPersonaje().colision(vista.escena.getPersonaje(), actual[4])||this.getPersonaje().colision(vista.escena.getPersonaje(), actual[6])){
           return true;
       }else{
           return false;
       }
   }
}