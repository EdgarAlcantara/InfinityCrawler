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
 * <p>Clase que define la escena de batalla.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Batalla extends EscenaGenerica {
    /**
     * Bitmaps creadas para definir los elementos concretos de la escena.
     */
    Bitmap atacar, atacar2, atacar3, enemi, caraguerrero, caramago, carabarbaro, fondo, botonRetirada;
    /**
     * TextPaint usado para escribir los textos.
     */
    TextPaint ataque;
    /**
     * Variable que define el tiempo en el que se produce un cambio de frame del enemigo.
     */
    int tiempoFrameEnemigo = 90;
    /**
     * El tiempo en el que se produce el cambio del frame.
     */
    long tickEnemigo;
    /**
     * Variable que determina, dentro de la función batalla al personaje al que atacará el enemigo.
     */
    Paint p;
    /**
     * Variable que determina que sprites del enemigo se cargan.
     */
    int queEnemigo;
    int hechizo;
    public Batalla(Context contexto, Surface vista, Enemigo enemigo, Paint p) {
        super(contexto, vista, p);
        this.p = p;
        queEnemigo = generaNumeroAleatorio(1, 3);
        tickEnemigo = System.currentTimeMillis();
        inicializacionDeEscenas(enemigo);
        escalado();
        p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
        this.p.setTextAlign(Paint.Align.CENTER);
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
        if (vista.guerrero.vida > 0) {
            actual[5].onDraw(c);
        } else {
            actual[5].imagen = toGrayscale(actual[5].imagen);
            actual[5].onDraw(c);
        }
        if (vista.mago.vida > 0) {
            actual[6].onDraw(c);
        } else {
            actual[6].imagen = toGrayscale(actual[6].imagen);
            actual[6].onDraw(c);
        }
        if (vista.barbaro.vida > 0) {
            actual[7].onDraw(c);
        } else {
            actual[7].imagen = toGrayscale(actual[7].imagen);
            actual[7].onDraw(c);
        }
        actual[8].onDraw(c);
        this.p.setColor(Color.BLACK);
        c.drawText(context.getString(R.string.ataqueguerrero), actual[1].posicion.x + atacar.getWidth() / 2, actual[1].posicion.y + atacar.getHeight() / 2, this.p);
        c.drawText(context.getString(R.string.ataquemago), actual[2].posicion.x + atacar2.getWidth() / 2, actual[2].posicion.y + atacar2.getHeight() / 2, this.p);
        c.drawText(context.getString(R.string.ataquebarbaro), actual[3].posicion.x + atacar3.getWidth() / 2, actual[3].posicion.y + atacar3.getHeight() / 2, this.p);
        this.p.setColor(Color.WHITE);
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
        if (queEnemigo == 1) {
            enemigo.imagen = new Imagen(enemi = BitmapFactory.decodeResource(vista.getResources(), R.drawable.enemigo1), vista.getWidth() / 3, vista.getHeight() / 5);

        } else if (queEnemigo == 2) {
            enemigo.imagen = new Imagen(enemi = BitmapFactory.decodeResource(vista.getResources(), R.drawable.enemigo21), vista.getWidth() / 3, vista.getHeight() / 5);
        } else if (queEnemigo == 3) {
            enemigo.imagen = new Imagen(enemi = BitmapFactory.decodeResource(vista.getResources(), R.drawable.enemigo31), vista.getWidth() / 3, vista.getHeight() / 5);
        }
        actual = new Imagen[]{
                new Imagen(fondo = BitmapFactory.decodeResource(vista.getResources(), R.drawable.keep), 0, 0),
                new Imagen(atacar = BitmapFactory.decodeResource(vista.getResources(), R.drawable.btnataque), vista.getWidth() / 25, (int) (vista.getHeight() / 1.3))
                , new Imagen(atacar2 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.btnataque), (int) (vista.getWidth() / 2.6), (int) (vista.getHeight() / 1.3))
                , new Imagen(atacar3 = BitmapFactory.decodeResource(vista.getResources(), R.drawable.btnataque), (int) (vista.getWidth() / 1.4), (int) (vista.getHeight() / 1.3))
                , enemigo.imagen,
                new Imagen(caraguerrero = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cara1), vista.getWidth() / 9, (int) (vista.getHeight() / 1.12))
                , new Imagen(caramago = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cara2), (int) (vista.getWidth() / 2.1), (int) (vista.getHeight() / 1.12))
                , new Imagen(carabarbaro = BitmapFactory.decodeResource(vista.getResources(), R.drawable.cara3), (int) (vista.getWidth() / 1.25), (int) (vista.getHeight() / 1.12)),
                new Imagen(botonRetirada = BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark15), vista.escena.getFondo().getWidth() + vista.escena.getFondo().getWidth() / 4, vista.escena.getFondo().getHeight() / 50)
        };


    }

    /**
     * Funcion definida para comprobar si se ha producido colisión con el botón de ataque del guerrero
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de que se haya pulsado y false en caso contrario.
     */
    public boolean tocarBotonAtaquePersonaje1(float x, float y) {
        if (actual[1].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Funcion definida para comprobar si se ha producido colisión con el botón de ataque del guerrero.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de que se haya pulsado y false en caso contrario.
     */
    public boolean tocarBotonAtaquePersonaje2(float x, float y) {
        if (actual[2].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Funcion definida para comprobar si se ha producido colisión con el botón de ataque del bárbaro.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de que se haya pulsado y false en caso contrario.
     */
    public boolean tocarBotonAtaquePersonaje3(float x, float y) {
        if (actual[3].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Funcion definida para comprobar si se ha producido colisión con el botón de retirada.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     * @return true en caso de que se haya pulsado y false en caso contrario.
     */
    public boolean tocarBotonRetirada(float x, float y) {
        if (actual[8].colisiona(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Función que establece la mecánica del combate, tanto la sucesión de los turnos como actualizar el valor de determinadas variables
     * definidas en la clase Surface.
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     */
    public void batalla(float x, float y) {
        if (tocarBotonRetirada(x, y)) {//Se gestiona la pulsación del botón de retirada
            int num = generaNumeroAleatorio(1, 100);//Se genera un número aleatorio
            if (num <= 60) {//Si el numero es menor o igual a 60 se produce la retirada
                vista.atacar = false;//Se borra del informe de batalla el daño hecho por los personajes
                vista.enemigo.actualizaenemigo(400, new Imagen(vista.enemi = BitmapFactory.decodeResource(vista.getResources(), R.drawable.enemigo1), vista.getWidth(), vista.getHeight()));//se actualiza la información del enemigo
                vista.atacanemigo = false;//se borra del informe de batalla el ataque del enemigo
                vista.cambiar = true;//Se da la señal de cambiar de escena
                vista.deBatalla = true;//se da la señal para recargar la posicion del personaje anterior a la batalla
                vista.escenas = vista.guardarEscena;//Se notifica que escenas pasa a tener el mismo valor que tenia antes de iniciarse el combate, para proceder a cargar los datos que hay en la escena con ese valor
                Toast.makeText(vista.getContext(), "Escapaste del combare", Toast.LENGTH_SHORT).show();//Se informa al usuario.
            }
        } else {
            gestionDeTurnos(x, y, vista.enemigo);
        }
        trasElCombate();
    }





    /**
     * Función que evalúa qué hacer en el caso de que mueran los personajes o en el caso de que
     * muera el enemigo.
     */
    @Override
    public void trasElCombate() {
        if (vista.enemigo.vida <= 0) {//Si la vida del enemigo es menor a cero.
            if(vista.guerrero.getVida()>0) {//Si la vida del guerrero es mayor a cero tiene el turno en el siguiente combate.
                turnopersonaje1 = true;
            }else if(vista.mago.getVida()>0){//Si resulta que el guerrero está muerto tiene el turno el mago.
                turnopersonaje2=true;
            }else if(vista.barbaro.getVida()>0){//Si resulta que el guerrero y el mago están muertos tiene el turno el bárbaro
                turnopersonaje3=true;
            }
            vista.atacar = false;//Se borra el informe de batalla que corresponde al daño producido por los personajes.
            vista.enemigo.actualizaenemigo(400, new Imagen(vista.enemi = BitmapFactory.decodeResource(vista.getResources(), R.drawable.enemigo1), vista.getWidth(), vista.getHeight()));//Se crea un nuevo enemigo para el próximo combate.
            vista.atacanemigo = false;//Se borra el informe de batalla del daño producido por el enemigo.
            vista.comenzarBatalla = false;//Se da la señal de que ha terminado el combate.
            vista.cambiar = true;//Se da la señal para que se dibuje la siguiente escena programada.
            vista.deBatalla = true;//Se da señal para se cargue la posición del sprite anterior al combate.
            vista.cont=0;
            vista.ataqueDeMago=false;
            if(vista.mago.getVida()>0) {//Si la vida del mago es superior a 0 se comprueba si sube de nivel.
                if (vista.mago.ganarExperiencia(vista.enemigo.darExperiencia())) {//Si se cumple que llega a la experiencia exigida para subir nivel.
                    Toast.makeText(vista.getContext(), vista.getContext().getString(R.string.subirnivelmago), Toast.LENGTH_SHORT).show();//Se notifica que que se ha subido nivel al usuario.
                    vista.sonido.subirNivel.start();
                }
            }
            if(vista.barbaro.getVida()>0) {
                if (vista.barbaro.ganarExperiencia(vista.enemigo.darExperiencia())) {
                    Toast.makeText(vista.getContext(), vista.getContext().getString(R.string.subirnivelbarbaro), Toast.LENGTH_SHORT).show();
                    vista.sonido.subirNivel.start();
                }
            }
            if(vista.barbaro.getVida()>0){
                if(vista.guerrero.ganarExperiencia(vista.enemigo.darExperiencia())) {
                    Toast.makeText(vista.getContext(), vista.getContext().getString(R.string.subirnivelguerrero), Toast.LENGTH_SHORT).show();
                    vista.sonido.subirNivel.start();
                }
            }
            vista.escenas = vista.guardarEscena;//Se da el valor a escenas del valor que tenia antes del combate.
        } else if (vista.mago.getVida() <= 0 && vista.guerrero.getVida() <= 0 && vista.barbaro.getVida() <= 0) {//Si todos los personajes están muertos.
            vista.escenas = TEXTOFINAL;//Se da el valor a escenas del número asociado a la escena que tiene que aparecer cuando se pierde la partida.
            vista.cambiar = true;//Se da la señal para cambiar de escena.
            vista.mago.reiniciarPersonaje();//Se reinician los personajes
            vista.guerrero.reiniciarPersonaje();
            vista.barbaro.reiniciarPersonaje();
            vista.cont=0;
            vista.ataqueDeMago=false;
            vista.enemigo.actualizaenemigo(400, new Imagen(vista.enemi = BitmapFactory.decodeResource(vista.getResources(), R.drawable.enemigo1), vista.getWidth(), vista.getHeight()));//Se actualiza la información del enemigo.
            vista.atacanemigo = false;//Se borra el informe de batalla del daño producido por el enemigo.
            vista.comenzarBatalla = false;//Se da la señal de que ha terminado el combate.
            vista.atacar = false;//Se borra el informe de batalla que corresponde al daño producido por los personajes.
            turnopersonaje1 = true;//Se pone el turno del guerrero a true.
        }

    }

    /**
     * Función que dibuja en pantalla el daño realizado tanto por los personajes como por el enemigo. Sobreesbribe al
     * método informeDuranteLaBatalla definido en EscenaGenerica.
     * @param c el canvas del SurfaceView
     */
    @Override
    public void informeDuranteLaBatalla(Canvas c) {
        if (vista.atacar) {
            vista.texto1 = vista.getContext().getString(R.string.dano) + vista.dano;
            this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
            this.p.setColor(Color.WHITE);
            c.drawText(vista.texto1, vista.getWidth() / 5, vista.getHeight() / 15, this.p);
        }
        if (vista.atacanemigo) {
            this.p.setTextSize(dpTopx(context, R.dimen.tamanoletra));
            this.p.setColor(Color.WHITE);
            vista.texto = vista.nombre+" "+vista.getContext().getString(R.string.recibio) +" "+ vista.danoa +" "+ vista.getContext().getString(R.string.dedano);
            c.drawText(vista.texto, vista.getWidth() / 4, vista.getHeight() / 10, this.p);
        }
    }

    /**
     * Función que sobreescribe la homónima definina en EscenaGenerica y que se encarga de crear la animación de movimiento
     * del enemigo.
     */
    @Override
    public void cambiarSpriteDuranteBatalla() {
        hechizo=ataquefuego.length;
        if (vista.cont < secuenciaEnemigo.length - 1 && this instanceof Batalla) {
            if ((System.currentTimeMillis() - tickEnemigo) > tiempoFrameEnemigo) {
                Log.i("Esprite:", "conta" + vista.cont);
                vista.cont++;
                if (queEnemigo == 1&&!vista.ataqueDeMago) {
                    cambiarSpriteEnemigo(vista.cont);
                } else if (queEnemigo == 2&&!vista.ataqueDeMago) {
                    cambiarSpriteEnemigo2(vista.cont);
                } else if (queEnemigo == 3&&!vista.ataqueDeMago) {
                    cambiarSpriteEnemigo3(vista.cont);
                } else if(vista.ataqueDeMago){
                    if(queEnemigo==1){
                        if(quehechizo==1) {
                            cambiarSpriteHechizoFuego(vista.cont);
                        }else if(quehechizo==2){
                            cambiarSpriteHechizoHielo(vista.cont);
                        }
                        hechizo--;
                        if(hechizo==0){
                            vista.ataqueDeMago=false;
                        }
                    }else if(queEnemigo==2){
                        if(quehechizo==1) {
                            cambiarSpriteHechizoFuego(vista.cont);
                        }else if(quehechizo==2){
                            cambiarSpriteHechizoHielo(vista.cont);
                        }
                        hechizo--;
                        if(hechizo==0){
                            vista.ataqueDeMago=false;
                        }
                    }else if(queEnemigo==3){
                        if(quehechizo==1) {
                            cambiarSpriteHechizoFuego(vista.cont);
                        }else if(quehechizo==2){
                            cambiarSpriteHechizoHielo(vista.cont);
                        }
                        hechizo--;
                        if(hechizo==0){
                            vista.ataqueDeMago=false;
                        }
                    }
                }
                tickEnemigo = System.currentTimeMillis();
            }
        } else {
            vista.cont = 0;
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
}
