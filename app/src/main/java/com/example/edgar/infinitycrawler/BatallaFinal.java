package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.Log;
import android.widget.Toast;

/**
 * <p>Clase que gestiona la escena en la que se desarrolla la batalla final, además de gestionar la batalla en sí.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class BatallaFinal extends EscenaGenerica {
    /**
     * Bitmaps que se cargan con las imágenes definidas en la función inicializacionDeEscena.
     */
    Bitmap atacar, atacar2, atacar3, enemi, caraguerrero, caramago, carabarbaro, fondo, cura;
    /**
     * Variable de la clase Enemigo que representa al boss de la mazmorra.
     */
    Enemigo enemigo;
    /**
     * TextPaint que dibuja el texto de los botones.
     */
    TextPaint ataque;
    /**
     * Variable que durante el combate indica a qué personaje atacará el enemigo.
     */
    int personaje;
    /**
     * Variable que define el tiempo en el que se produce un cambio de frame del enemigo.
     */
    int tiempoFrameEnemigo = 90;
    /**
     * El tiempo en el que se produce el cambio del frame.
     */
    long tickEnemigo;
    Paint p;

    public BatallaFinal(Context contexto, Surface vista, Enemigo enemigo, Paint p) {
        super(contexto, vista, p);
        this.p = p;
        this.p.setTextAlign(Paint.Align.CENTER);
        inicializacionDeEscenas(enemigo);
        escalado();
    }

    /**
     * Sobreescritura del dibujado de EscenaGenérica para dibujar los elementos concretos de la escena.
     * Se le pasa como parámetro el canvas del Surface.
     *
     * @param c
     */
    @Override
    public void dibujado(Canvas c) {
        actual[0].onDraw(c);
        actual[1].onDraw(c);
        actual[2].onDraw(c);
        actual[3].onDraw(c);
        actual[4].onDraw(c);
        if (vista.guerrero.vida > 0) {
            actual[5].imagen = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cara1);
            actual[5].onDraw(c);
        } else {
            actual[5].imagen = toGrayscale(actual[5].imagen);
            actual[5].onDraw(c);
        }
        if (vista.mago.vida > 0) {
            actual[6].imagen = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cara2);
            actual[6].onDraw(c);
        } else {
            actual[6].imagen = toGrayscale(actual[6].imagen);
            actual[6].onDraw(c);
        }
        if (vista.barbaro.vida > 0) {
            actual[7].imagen = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cara3);
            actual[7].onDraw(c);
        } else {
            actual[7].imagen = toGrayscale(actual[7].imagen);
            actual[7].onDraw(c);
        }
        actual[8].onDraw(c);


        this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
        this.p.setColor(Color.BLACK);
        c.drawText(context.getString(R.string.ataqueguerrero), actual[1].posicion.x + atacar.getWidth() / 2, actual[1].posicion.y + atacar.getHeight() / 2, this.p);
        c.drawText(context.getString(R.string.ataquemago), actual[2].posicion.x + atacar2.getWidth() / 2, actual[2].posicion.y + atacar2.getHeight() / 2, this.p);
        c.drawText(context.getString(R.string.ataquebarbaro), actual[3].posicion.x + atacar3.getWidth() / 2, actual[3].posicion.y + atacar3.getHeight() / 2, this.p);
        p.setColor(Color.WHITE);
        c.drawText(context.getString(R.string.vida) + vista.guerrero.getVida(), actual[1].posicion.x + atacar.getWidth() / 2, actual[2].imagen.getHeight() + dpTopx(context, R.dimen.distancia), this.p);
        c.drawText(context.getString(R.string.vida) + vista.mago.getVida(), actual[2].posicion.x + atacar2.getWidth() / 2, actual[2].imagen.getHeight() + dpTopx(context, R.dimen.distancia), this.p);
        c.drawText(context.getString(R.string.vida) + vista.barbaro.getVida(), actual[3].posicion.x + atacar3.getWidth() / 2, actual[3].imagen.getHeight() + dpTopx(context, R.dimen.distancia), this.p);
    }

    /**
     * Sobreescrituda del método escalado definido en EscenaGenerica que realiza un escalado de las imágenes adaptándolas a la pantalla
     */
    @Override
    public void escalado() {


        actual[0].imagen = Bitmap.createScaledBitmap(actual[0].imagen, vista.getWidth(), vista.getHeight(), true);
        int altoPantalla;
        int altoImagen;
        double coefreduc;
        altoImagen = actual[0].imagen.getHeight();
        altoImagen = actual[1].imagen.getHeight();
        coefreduc = (double) altoImagen / (double) altoImagen;
        Paint p = new Paint();
        ataque = new TextPaint();
        ataque.setTextAlign(Paint.Align.CENTER);
        p.setTextSize(13);
        p.setColor(Color.BLACK);
        actual[1].imagen = Bitmap.createScaledBitmap(actual[1].imagen, (int) (actual[1].imagen.getWidth() * coefreduc), (int) (actual[1].imagen.getHeight() * coefreduc), true);
        actual[2].imagen = Bitmap.createScaledBitmap(actual[2].imagen, (int) (actual[1].imagen.getWidth() * coefreduc), (int) (actual[2].imagen.getHeight() * coefreduc), true);
        actual[3].imagen = Bitmap.createScaledBitmap(actual[2].imagen, (int) (actual[2].imagen.getWidth() * coefreduc), (int) (actual[2].imagen.getHeight() * coefreduc), true);

    }

    /**
     * Sobreescritura del método inicializacionDeEscena definido en EscenaGenerica en la que se guardan los elementos concretos de la escena
     * dentro de el array actual tambié definido en EscenaGenerica.
     */
    public void inicializacionDeEscenas(Enemigo enemigo) {
        enemigo.imagen = new Imagen(enemi = BitmapFactory.decodeResource(vista.getResources(), R.drawable.ogro), vista.getWidth() / 3, vista.getHeight() / 5);
        actual = new Imagen[]{
                new Imagen(fondo = BitmapFactory.decodeResource(vista.getResources(), R.drawable.keepouter), 0, 0),
                new Imagen(atacar = BitmapFactory.decodeResource(vista.getResources(), R.drawable.btnataque), vista.getWidth() / 25, (int) (vista.getHeight() / 1.3))
                , new Imagen(atacar2 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.btnataque), (int) (vista.getWidth() / 2.6), (int) (vista.getHeight() / 1.3))
                , new Imagen(atacar3 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.btnataque), (int) (vista.getWidth() / 1.4), (int) (vista.getHeight() / 1.3))
                , enemigo.imagen,
                new Imagen(caraguerrero = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cara1), vista.getWidth() / 9, (int) (vista.getHeight() / 1.12))
                , new Imagen(caramago = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cara2), (int) (vista.getWidth() / 2.1), (int) (vista.getHeight() / 1.12))
                , new Imagen(carabarbaro = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cara3), (int) (vista.getWidth() / 1.25), (int) (vista.getHeight() / 1.12)),
                new Imagen(cura = BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark17), vista.escena.getFondo().getWidth() + vista.escena.getFondo().getWidth() / 4, vista.escena.getFondo().getHeight() / 50)
        };


    }

    /**
     * Funciones definidas para detectar la pulsación de botones.
     * @param x posición x del evento registrado por el onTouchEvent.
     * @param y posición y del evento registrado por el onTouchEvent.
     * @return true en caso de haber pulsado el botón y false en caso de que no se haya pulsado.
     */
    public boolean tocarBotonAtaquePersonaje1(float x, float y) {
        if (actual[1].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Se detecta si se ha pulsado el botón de ataque del mago.
     * @param x posición x del evento registrado por el onTouchEvent.
     * @param y posición y del evento registrado por el onTouchEvent.
     * @return true en caso de haber pulsado el botón y false en caso de que no se haya pulsado.
     */
    public boolean tocarBotonAtaquePersonaje2(float x, float y) {
        if (actual[2].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Se detecta si se ha pulsado el botón de ataque del bárbaro.
     * @param x posición x del evento registrado por el onTouchEvent.
     * @param y posición y del evento registrado por el onTouchEvent.
     * @return true en caso de haber pulsado el botón y false en caso de que no se haya pulsado.
     */
    public boolean tocarBotonAtaquePersonaje3(float x, float y) {
        if (actual[3].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Se detecta si se ha pulsado el botón de cura que únicamente estará disponible en la batalla final.
     * @param x posición x del evento registrado por el onTouchEvent.
     * @param y posición y del evento registrado por el onTouchEvent.
     * @return true en caso de haber pulsado el botón y false en caso de que no se haya pulsado.
     */
    public boolean tocarBotonCurar(float x, float y) {
        if (actual[8].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Función que gestiona el desarrollo del combate.
     * @param x posición x del evento registrado por el onTouchEvent.
     * @param y posición y del evento registrado por el onTouchEvent.
     */
    public void batalla(float x, float y) {
        if (tocarBotonCurar(x, y)) {//Se comprueba si se ha pulsado el botón curar.
            if (vista.cantidadDeCuras > 0) {
                vista.guerrero.actualiza("Padd", 15);
                vista.mago.actualiza("Eknido", 5);
                vista.barbaro.actualiza("Wrehuf", 30);
                vista.cantidadDeCuras -= 1;
                Toast.makeText(vista.getContext(), "Curas restantes:" + vista.cantidadDeCuras, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(vista.getContext(), "No te quedan más curas", Toast.LENGTH_SHORT).show();
            }
        } else {
            gestionDeTurnos(x, y, vista.boss);
        }
        trasElCombate();
    }

    /**
     * Función que determina qué pasa una vez terminado el combate a partir de su resultado.
     */
    @Override
    public void trasElCombate() {
        if (vista.boss.vida < 0) {//Si se ha derrotado al enemigo.
            vista.atacar = false;//se borra del informe de batalla el ataque de los personajes.
            vista.contador++;//Se suma uno al contador para que no vuelva a cargar la sala del boss
            vista.boss.actualizaenemigo(10000, new Imagen(enemi = BitmapFactory.decodeResource(vista.getResources(), R.drawable.ogro), vista.getWidth(), vista.getHeight()));//Se actualiza el boss, esta vez con más vida.
            vista.atacanemigo = false;//Se borra del informe de batalla el ataque del enemigo
            //vista.comenzarBatalla = false;
            vista.mago.reiniciarPersonaje();//Se reinician los personajes
            vista.barbaro.reiniciarPersonaje();
            vista.guerrero.reiniciarPersonaje();
            vista.cambiar = true;//Se da señal para que se cargue una nueva escena
            vista.ataqueDeMago=false;
            vista.cont=0;
            vista.escenas = ESCENA10;//Y se da señal para que se cargue la escena 10 (La de haber ganado)
        } else if (vista.mago.getVida() == 0 && vista.guerrero.getVida() == 0 && vista.barbaro.getVida() == 0) {//Si se pierde el combate
            vista.escenas = TEXTOFINAL;//Se da señal para que se dibuje la escena 9 (La de haber perdido).
            vista.cambiar = true;//Se da señal para cargar la nueva escena.
            vista.mago.reiniciarPersonaje();//Se reinician los personajes
            vista.guerrero.reiniciarPersonaje();
            vista.barbaro.reiniciarPersonaje();
            vista.ataqueDeMago=false;
            vista.atacanemigo = false;//Se borra del informe de batalla el daño hecho por el enemigo
            vista.cont=0;
            vista.atacar = false;//Se borra del informe de batalla el daño de los personajes
            turnopersonaje1 = true;//Se pone el turno del guerrero a true, por si se inicia una nueva partida.
        }
    }

    /**
     * Función que gestiona los mensajes de informe durante la batalla.
     * @param c se pasa el canvas del SurfaceView.
     */
    @Override
    public void informeDuranteLaBatalla(Canvas c) {
        if (vista.atacar) {
            vista.texto1 = "Daño producido:" + vista.dano;
            this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
            this.p.setColor(Color.WHITE);
            c.drawText(vista.texto1, vista.getWidth() / 5, vista.getHeight() / 15, this.p);
        }
        if (vista.atacanemigo) {
            this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
            this.p.setColor(Color.WHITE);
            vista.texto = vista.nombre + " ha recibido " + vista.danoa + " de daño";
            c.drawText(vista.texto, vista.getWidth() / 4, vista.getHeight() / 10, this.p);
        }
    }

    /**
     * Comvierte una imagen a escala de grises
     *
     * @param imgOriginal Imagen original
     * @return Imagen convertida a escala de grises
     */
    public Bitmap toGrayscale(Bitmap imgOriginal) {
        Bitmap bmpGrayscale = Bitmap.createBitmap(imgOriginal.getWidth(), imgOriginal.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(imgOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    /**
     * Función que sobreescribe la homónima definina en EscenaGenerica y que se encarga de crear la animación de movimiento
     * del enemigo.
     */
    @Override
    public void cambiarSpriteDuranteBatalla() {
        if (vista.cont < secuenciaBoss.length - 1 && this instanceof BatallaFinal) {
            if ((System.currentTimeMillis() - tickEnemigo) > tiempoFrameEnemigo) {
                Log.i("Esprite:", "conta" + vista.cont);
                vista.cont++;
                if(!vista.ataqueDeMago) {
                    cambiarSpriteBoss(vista.cont);
                }else if(vista.ataqueDeMago&&quehechizo==1){
                    cambiarSpriteHechizoFuego(vista.cont);
                }else if(vista.ataqueDeMago&&quehechizo==2){
                    cambiarSpriteHechizoHielo(vista.cont);
                }
                tickEnemigo = System.currentTimeMillis();
            }
        } else {
            vista.cont = 0;
        }
    }

}
