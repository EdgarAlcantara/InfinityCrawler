package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * <p>Clase que define la escena concreta del menú de guardado, hereda de EscenaGenerica y sobreescribe alguno de sus métodos
 * para definir sus propias características</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class MenuGuardar extends EscenaGenerica {
    /**
     * Bitmaps creadas para definir los elementos concretos de la escena.
     */
    Bitmap sala, boton, boton2, boton3, boton4;
    Paint p;

    public MenuGuardar(Context contexto, Surface vista, Paint p) {
        super(contexto, vista, p);
        this.p = p;
        this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
        this.p.setColor(Color.WHITE);
        this.p.setTextAlign(Paint.Align.LEFT);
        inicializacionDeEscena();
        escalado();
    }

    /**
     * Sobreescritura del dibujado de EscenaGenérica para dibujar los elementos concretos de la escena.
     * Se le pasa como parámetro el canvas del Surface.
     * @param c el canvas del SurfaceView.
     */
    @Override
    public void dibujado(Canvas c) {


        actual[0].onDraw(c);
        actual[1].onDraw(c);
        actual[2].onDraw(c);
        actual[3].onDraw(c);
        actual[4].onDraw(c);
        c.drawText(context.getString(R.string.guardarpartida), (int)(vista.getWidth() / 2.56),(int) (vista.getHeight() / 3.9), p);
        c.drawText(context.getString(R.string.cargarpartida), (int)(vista.getWidth() / 2.52),(int) (vista.getHeight() / 2.35), p);
        c.drawText(context.getString(R.string.salir),(int)(vista.getWidth() / 2.2),(int) (vista.getHeight() / 1.7), p);
        c.drawText(context.getString(R.string.volver),(int)(vista.getWidth() / 2.25),(int) (vista.getHeight() /  1.32), p);
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
    }

    /**
     * Sobreescritura del método inicializacionDeEscena definido en EscenaGenerica en la que se guardan los elementos concretos de la escena
     * dentro de el array actual tambié definido en EscenaGenerica.
     */
    @Override
    public void inicializacionDeEscena() {
        actual = new Imagen[]{
                new Imagen(sala = BitmapFactory.decodeResource(context.getResources(), R.drawable.wizardtower1), 0, 0),
                new Imagen(boton = BitmapFactory.decodeResource(vista.getResources(), R.drawable.botoninicio), vista.getWidth() / 3, vista.getHeight() / 6),
                new Imagen(boton2 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.botoninicio), vista.getWidth() / 3, vista.getHeight() / 3),
                new Imagen(boton3 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.botoninicio), vista.getWidth() / 3, vista.getHeight() / 2),
                new Imagen(boton4 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.botoninicio), vista.getWidth() / 3, (int) (vista.getHeight() / 1.5))

        };
    }

    /**
     * Funcion definid para comprobar si se ha producido la colisión el el botón guardar.
     *
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean pulsarBotonGuardarDeGuardar(float x, float y) {
        if (actual[1].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Se detecta si se ha pulsado el botón de cargar partida del menú de guardar.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean cargarPartidaDeGuardar(float x, float y) {
        if (actual[2].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Se detecta si se ha pulsado el botón volver del menu guardar
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean pulsarBotonVolverDeGuardar(float x, float y) {
        if (actual[4].colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            return true;
        } else {
            return false;
        }

    }

    /**
     * Se detecta si se ha pulsado el botón salir del menú de guardado.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de haberse producido la colisión, false en caso contrario.
     */
    public boolean pulsarBotonSalirDeGuardar(float x, float y) {
        if (actual[3].colisiona(x, y)) {

            vista.sonido.pulsadoBoton.start();

            return true;
        } else {
            return false;
        }
    }
}