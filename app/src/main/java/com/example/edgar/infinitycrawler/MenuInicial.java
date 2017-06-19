package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;




/**
 * <p>Clase que define la escena concreta del menú  inicial, hereda de EscenaGenerica y sobreescribe alguno de sus métodos
 * para definir sus propias características</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class MenuInicial extends EscenaGenerica {
    /**
     * Bitmaps creadas para definir los elementos concretos de la escena.
     */
    Bitmap boton, boton2, boton3, boton4, boton5, btnopc;
    /**
     * TextPaint definido para escribir los textos que hay en la escena.
     */
    TextPaint textoNuevaPartida;
    Paint p;

    public MenuInicial(Context context, Surface vista, Paint p) {
        super(context, vista, p);
        this.p = p;
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
        actual[1].onDraw(c);
        actual[2].onDraw(c);
        actual[3].onDraw(c);
        actual[4].onDraw(c);
        actual[5].onDraw(c);
        actual[6].onDraw(c);
        this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
        this.p.setColor(Color.WHITE);
        this.p.setTextAlign(Paint.Align.LEFT);
        c.drawText(context.getString(R.string.nuevapartida),(int)(vista.getWidth() / 2.48),(int) (vista.getHeight() / 3.9), this.p);
        c.drawText(context.getString(R.string.cargarpartida), (int)(vista.getWidth() / 2.52),(int) (vista.getHeight() / 2.35), this.p);
        c.drawText(context.getString(R.string.ayuda), (int)(vista.getWidth() / 2.23),(int) (vista.getHeight() / 1.7), this.p);
        c.drawText(context.getString(R.string.credi), (int)(vista.getWidth() / 2.3),(int) (vista.getHeight() / 1.32), this.p);
        c.drawText(context.getString(R.string.salirMenuInicial), (int)(vista.getWidth() / 2.49),(int) (vista.getHeight() / 1.08), this.p);
        this.p.setColor(Color.BLACK);
        this.p.setTextSize(dpTopx(context, R.dimen.tamaniletratitulo));
        this.p.setTextAlign(Paint.Align.CENTER);
        c.drawText(context.getString(R.string.app_name), actual[1].posicion.x + (int) (boton.getWidth() / 4), actual[1].posicion.y + (int) (boton.getHeight() - boton.getHeight() / 0.9), this.p);
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
        actual[0].imagen = Bitmap.createScaledBitmap(actual[0].imagen, vista.getWidth(), vista.getHeight(), true);
        actual[1].imagen = Bitmap.createScaledBitmap(actual[1].imagen, (int) (actual[1].imagen.getWidth() * coefreduc), (int) (actual[1].imagen.getHeight() * coefreduc), true);
        actual[2].imagen = Bitmap.createScaledBitmap(actual[2].imagen, (int) (actual[2].imagen.getWidth() * coefreduc), (int) (actual[2].imagen.getHeight() * coefreduc), true);
        actual[3].imagen = Bitmap.createScaledBitmap(actual[3].imagen, (int) (actual[3].imagen.getWidth() * coefreduc), (int) (actual[3].imagen.getHeight() * coefreduc), true);
        actual[4].imagen = Bitmap.createScaledBitmap(actual[4].imagen, (int) (actual[4].imagen.getWidth() * coefreduc), (int) (actual[4].imagen.getHeight() * coefreduc), true);
        actual[5].imagen = Bitmap.createScaledBitmap(actual[5].imagen, (int) (actual[5].imagen.getWidth() * coefreduc), (int) (actual[5].imagen.getHeight() * coefreduc), true);
    }

    /**
     * Sobreescritura del método inicializacionDeEscena definido en EscenaGenerica en la que se guardan los elementos concretos de la escena
     * dentro de el array actual tambié definido en EscenaGenerica.
     */
    @Override
    public void inicializacionDeEscena() {
        actual = new Imagen[]
                {
                        new Imagen(fondo = BitmapFactory.decodeResource(vista.getResources(), R.drawable.fortress), 0, 0),
                        new Imagen(boton = BitmapFactory.decodeResource(vista.getResources(), R.drawable.botoninicio), vista.getWidth() / 3, vista.getHeight() / 6),
                        new Imagen(boton2 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.botoninicio), vista.getWidth() / 3, vista.getHeight() / 3),
                        new Imagen(boton3 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.botoninicio), vista.getWidth() / 3, vista.getHeight() / 2),
                        new Imagen(boton4 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.botoninicio), vista.getWidth() / 3, (int) (vista.getHeight() / 1.5)),
                        new Imagen(boton5 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.botoninicio), vista.getWidth() / 3, (int) (vista.getHeight() / 1.2)),
                        new Imagen(btnopc = BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark21), vista.getWidth()/20,(int) (vista.getHeight()/1.2))
                };
        textoNuevaPartida = new TextPaint();
        textoNuevaPartida.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * Funcion definida para comprobar si se ha producido colisión con el botón de nueva partida.
     *
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean nuevaPartidaPulsado(float x, float y) {
        vista.sonido.pulsadoBoton.start();
        if (actual[1].colisiona(x, y)) {

            return true;
        } else {
            return false;
        }
    }

    /**
     * Detección que hace que se pase a la primera escena de ayuda.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean pulsarBotonAyuda(float x, float y) {
        vista.sonido.pulsadoBoton.start();
        if (actual[3].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Detección de la pulsación del botón que hace que se pasa a la pantalla de créditos.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean pulsarBotonCreditos(float x, float y) {
        vista.sonido.pulsadoBoton.start();
        if (actual[4].colisiona(x, y)) {
            actual[4].dibujarRectangulo();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Detección de la pulsación del botón de cargar partida.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean cargarPartida(float x, float y) {
        vista.sonido.pulsadoBoton.start();
        if (actual[2].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Detección de la pulsación del botón de salir.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean botonSalir(float x, float y) {
        if (actual[5].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Detección de la pulsación del botón de opciones.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean pulsarBotonOpciones(float x, float y){
        if(actual[6].colisiona(x,y)){
            return true;
        }else{
            return false;
        }
    }
}