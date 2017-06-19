package com.example.edgar.infinitycrawler;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;


/**
 * <p>Clase que define los rectángulos, posiciones y demás propiedades de las imágenes que las relacionan con otras.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Imagen {
    Bitmap imagen;
    /**
     * Variable tipo Rect que se usa para definir el rectángulo de colisión de la imagen.
     */
    Rect colis;
    /**
     * Variable PointF en la que se guarda la posición de la imagen.
     */
    public PointF posicion;

    public Imagen(Bitmap imagen, float x, float y) {

        this.imagen = imagen;
        this.posicion = new PointF(x, y);
        this.setRectangulos();


    }

    /**
     * Función que se usa para dibujar un rectángulo rojo alrededor de los botones de ataque durante la batalla.
     *
     * @return deuelve el rectángulo de turno
     */
    synchronized public Rect dibujarRectangulo() {
        float x = posicion.x;
        float y = posicion.y;
        return colis = new Rect((int) (x - 0.02 * imagen.getWidth()), (int) (y + 0.95 * imagen.getHeight()), (int) (x + 1.03 * imagen.getWidth()), (int) (y + 0.03 * imagen.getHeight()));
    }

    /**
     * Función que se usa para definir los rectángulos de colisión en las imágenes.
     *
     * @return devuelve el rectángulo de colisión.
     */
    synchronized public Rect setRectangulos() {
        float x = posicion.x;
        float y = posicion.y;
        return colis = new Rect((int) (x + 0.2 * imagen.getWidth()), (int) (y + 0.2 * imagen.getHeight()), (int) (x + 0.8 * imagen.getWidth()), (int) (y + 0.8 * imagen.getHeight()));

    }

    /**
     * Función usada para definir una nueva posición de la imagen en pantalla.
     *
     * @param x nuevo valor de la posición x de la imagen.
     * @param y nuevo valor de la posición y de la imagen.
     */
    synchronized public void cambiarPosicion(float x, float y) {
        this.posicion = new PointF(x, y);
    }

    /**
     * Función que se usa para detectar la colisión con los botones que se muestran por pantalla.
     *
     * @param x2 valor x de la imagen en cuestión
     * @param y2 valor y de la imagen en cuestión
     * @return true en caso de que haya colisión
     */
    synchronized public boolean colisiona(float x2, float y2) {

        return x2 > posicion.x && x2 < posicion.x + imagen.getWidth() && y2 > posicion.y && y2 < posicion.y + imagen.getHeight();

    }

    /**
     * Función que detecta la colisión entre dos imágenes
     * @param i una de las imagenes cuya colisión se desea comprobar
     * @param i2 la otra imagen
     * @return true en caso de que haya colisión.
     */
    public boolean colision(Imagen i, Imagen i2){
        return i.posicion.x+i.imagen.getWidth()>i2.posicion.x&&i.posicion.x<i2.posicion.x+i2.imagen.getWidth()&&i.posicion.y+i.imagen.getHeight()>i2.posicion.y&&i.posicion.y<i2.posicion.y+i2.imagen.getHeight();
    }

    /**
     * Funcion usada para dibujar la imagen en particular dentro de cada escena.
     *
     * @param canvas el canvas del surface
     */
    synchronized public void onDraw(Canvas canvas) {


        canvas.drawBitmap(imagen, (int) posicion.x, (int) posicion.y, null);
        this.setRectangulos();


    }

    /**
     * Función que permite realizar el efecto scroll.
     *
     * @param velocidad la velocidad a la que queremos que se mueva el fondo
     */
    public void mover(int velocidad) {
        posicion.x += velocidad;

    }
}
