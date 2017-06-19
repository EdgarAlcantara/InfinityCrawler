package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Map;
import java.util.Set;
/**
 * <p>Clase que hereda de SurfaceView y que implemente SurfaceHolder.Callback.
 * En ella se definen una serie de atributos genéricos usados en todas las clases que
 * componen el juego y en la que se ponen en funcionamiento las mecánicas definidas en
 * otras clases, como en EscenaGenérica y en Sonido recurriendo al hilo definido en la clase Hilo.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Surface extends SurfaceView implements SurfaceHolder.Callback {
    /**
     * Inicialización de personajes estándar
     */
    public Guerrero guerrero = new Guerrero("Padd", 15, 200);
    public Mago mago = new Mago("Eknido", 5, 10);
    public Barbaro barbaro = new Barbaro("Wrehuf", 30,30);
    /**
     * Variable imagen que representa la imagen del enemigo
     */
    public Bitmap enemi;
    /**
     * Vriable de tipo SurfaceHolder
     */
    public SurfaceHolder holder;
    /**
     * Variable de tipo Hilo que tiene por objeto
     * poner en funcionamiento la mecánica definida en dicha clase.
     */
    public Hilo hilo;
    /**
     * Variable que se encarga de capturar el contexto en el que se
     * desarrolla la aplicación.
     */
    public Context context;
    /**
     * Variable que da la señal para cambiar de escena y para que se proceda al dibujado
     * cuando está a true, y una vez dibujada la escena se pone a false.
     */
    public boolean cambiar = false;
    /**
     * Variable que señala si se ha cambiado de escena estando en la escena
     * Grupo y que determinada de estar a true que el personaje recupere
     * la posición anterior a abrir el menú y lo mismo con la sala.
     */
    public boolean deMenu = false;
    /**
     * Variable que realiza lo mismo que deMenu, pero en este caso a la hora de
     * volver a la escena en la que se inició el combate.
     */
    public boolean deBatalla = false;
    /**
     * Realiza lo mismo que deMenu pero en este caso si antes hemos estado
     * en MenuGuardar.
     */
    public boolean deGuardado = false;
    /**
     * Variable que determina de estar a true que se dibujen los mensajes de batalla
     * en el caso de atacar uno de los personajes.
     */
    public boolean atacar = false;
    /**
     * Variable que establece si esta a true que se dibuje la escena de batalla
     * y estando a false que se vuelva a la escena anterior dibujandola.
     */
    public boolean comenzarBatalla = false;

    //public boolean finalizada = false;
    /**
     * Realiza la misma tarea que atacar sólo que en este caso
     * de producirse el ataque del enemigo.
     */
    public boolean atacanemigo = false;

    /**
     * Variable que junto a cambiar se encarga del cambio de escena y dependiendo de su valor
     * se carga una escena u otra. Por defecto se encuentra a 0, que es el valor que está definido para dibujar
     * MenuIncial.
     */
    public int escenas = 0;
    /**
     * Variable que recorre el array de imágenes del personaje definida en EscenaGenerica y que sirve para cambiar
     * la imagen del personaje a medida que este se mueve.
     */
    public int cont = 0;
    /**
     * Variable que hace lo mismo que cont pero esta vez con un array de imagenes de un enemigo para crear efecto de movimiento
     * durante la batalla.
     */
    public int cont2 = 0;
    /**
     * Variable que de estar a true señaliza que se está pulsado el botón derecho de los botones direccionales.
     * Y en ese caso se realiza la acción definida en la función moverDerecha, definida en EscenaGenerica.
     */
    public boolean pulsadoDerecho = false;
    /**
     * Variable que de estar a true señaliza que se está pulsado el botón izquierdo de los botones direccionales.
     * Y en ese caso se realiza la acción definida en la función moverIzquierda, definida en EscenaGenerica.
     */
    public boolean pulsadoIzquierdo = false;
    /**
     * Variable que de estar a true señaliza que se está pulsado el botón arriba de los botones direccionales.
     * Y en ese caso se realiza la acción definida en la función moverArriba, definida en EscenaGenerica.
     */
    public boolean pulsadoArriba = false;
    /**
     * Variable que de estar a true señaliza que se está pulsado el botón abajo de los botones direccionales.
     * Y en ese caso se realiza la acción definida en la función moverAbajo, definida en EscenaGenerica.
     */
    public boolean pulsadoAbajo = false;
    /**
     * Variable que guarda el sprite concreto del personaje antes de comenzar una batalla o antes de entrar en un menú,
     * cargando recuperando ese valor una vez que se vuelva de una batalla o de un menú.
     */
    public Bitmap enElCambio;
    /**
     * Variable de tipo EscenaGenerica que dependiendo del valor de escenas cambia su tipo al de la escena identificada
     * con el valor de escenas y carga sus elementos concretos.
     */
    public EscenaGenerica escena;
    /**
     * Variable que guarda el valor de escenas cuando se cambia de escena y en el caso de entrar en algun menú o al entrar en batalla y de volver del mismo recupera el
     * valor de escenas que tenia antes de cambiar al menú o a la batalla preparándo así a dicha variable para cargar la escena específica.
     * Se usa además a la hora de guardar partida y al cargarla.
     */
    public int guardarEscena;
    /**
     * Realizan una función equivalente a la de guardarEscena pero con la posición del personaje posiciony guarda la posición y del mismo
     * y posicionx la posición x
     */
    int posiciony, posicionx;
    /**
     * Variables que realizan la misma función que posiciony y posicionx, pero en este caso en guardan la posición del personaje en la escena a la
     * hora de entrar en combate dando dicha posición cuando este se termina.
     */
    int posicionxb, posicionyb;
    /**
     * Variable a la mientras se mueva el personaje se le dará un valor aleatorio de 0 a 100, cambiando a la escena Batalla en el caso de ser menor
     * o igual a 5, este valor le es dado en la clase EscenaGenérica en el método detección usando la función generaNumeroAleatorio.
     */
    public int batalla;
    /**
     * Variable que aumenta de valor a medida que se cambia de escena y que de ser su valor igual a 6 se cambia a la Escena7, en el caso de salir
     * de la partida su valor se restaura a 0 y el el caso de guardar partida se guarda el valor que tenga en el momento de realizar el guardado.
     */
    public int contador = 0;
    /**
     * Variable que guarda el valor del daño producido por los personajes recurriendo a la función atacar de la clase Personaje para posteriormente
     * usar el dato en la función informeDeBatalla tanto de la escena Batalla como de la escena BatallaFinal.
     */
    public int dano = 0;
    /**
     * Variable que hace lo mismo que daño pero en este caso con el daño producido por el enemigo recurriendo tanto a la funcion atacar, como a atacar2
     * de la clase enemigo, para ser usada en informeDeBatalla de las clases Batalla y BatallaFinal.
     */
    public int danoa = 0;
    /**
     * Variable que guarda el nombre del personaje que ha realizado el ataque para usarlo en la función informeDeBatalla tanto de la clase Batalla
     * como de BatallaFinal.
     */
    public String nombre = "";
    /**
     * Se crean dos enemigos estandar para realizar el primer combate tanto de la batalla normal(variable enemigo) como de la batalla final(variable boss).
     */
    public Enemigo enemigo = new Enemigo(400, new Imagen(enemi = BitmapFactory.decodeResource(getResources(), R.drawable.enemigo1), this.getWidth(), this.getHeight()));
    public Enemigo boss = new Enemigo(5000, new Imagen(enemi = BitmapFactory.decodeResource(getResources(), R.drawable.ogro), this.getWidth(), this.getHeight()));
    /**
     * Textos en los que se escribe el informe durante la batalla.
     */
    public String texto, texto1;

    /**
     * Variable tipo paint que se pasa a las diferentes escenas para escribir los textos.
     */
    public Paint p;
    /**
     * Cumple la misma función que la variable cambiar, pero para el caso concreto del MenuIncial.
     */
    public boolean comenzado = true;
    public AudioManager player2;
    /**
     * Variable de la clase Sonido que se usa en el onDraw para gestionar la música.
     */
    Sonido sonido;
    /**
     * Variable que carga la fuente de texto.
     */
    Typeface faw;
    /**
     * Variable vibrador que se pasa al resto de escenas.
     */
    Vibrator vibrador;
    /**
     * Variable que se carga con el valor del canvas del surface y que se pasa a la EscenaGenerica.
     */
    Canvas c;
    /**
     * Variables que se encargar de registrar los valores de la posición en la que se produce la colision del MotionEvent y que se envian al onTouchEvent.
     */
    float y = 0;
    float x = 0;
    /**
     * Variable de la clase Shake que se usa en la Escena2 para registrar la agitación del movil.
     */
    Shake sake;
    /**
     * Variable relacionada con sake, funciona como contador que controla que el shake funcione correctamente.
     */
    int controladorDeAgitacion = 0;
    /**
     * Array de imágenes que componen el fondo con scroll de las nubes.
     */
    Imagen[] fondo1 = new Imagen[2];
    /**
     * Array de imágenes que componen el fondo del cielo estrellado.
     */
    Imagen[] fondo2 = new Imagen[2];
    /**
     * Variable booleana que se encarga de controlar el dibujado. Cuando la aplicación se detiene evita que se produzca
     * un NullPointerException.
     */
    boolean dibujar = true;
    /**
     * Variable que establece la cantidad de veces que se puede pulsar el botón curar del menú
     */
    public int cantidadDeCuras = 10;
    /**
     * Variable usada para detectar si el mago ha realizado el ataque y dar el aviso para realizar la animación de hechizos.
     */
    public boolean ataqueDeMago=false;
    /**
     * Variables SharedPreferences para guardar la configuración estandar
     * de la vibración
     */
    SharedPreferences pref;
    SharedPreferences.Editor editpref;
    AudioManager manager;
    /**
     * <p>Constructor al que se le pasa el contexto y en el que se cargan las diferentes clases que tienen que ver con recursos, como el sonido,
     * el saker, la fuente y el vibrador.</p>
     *
     * @param context el contexto de la Activity.
     */
    public Surface(Context context) {
        super(context);
        manager= (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        sake = new Shake(getContext());
        hilo = new Hilo(this);
        holder = getHolder();
        holder.addCallback(this);
        this.context = context;
        sonido = new Sonido(this, getContext());
        faw = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/analecta.ttf");
        p = new Paint();
        p.setTypeface(faw);
        player2 = (AudioManager) getContext().getSystemService(context.AUDIO_SERVICE);
        escena = new EscenaGenerica(this.getContext(), this, p);
        fondo1[0] = new Imagen(BitmapFactory.decodeResource(this.getResources(), R.drawable.tilesetopengamebackground), 0, 0);
        fondo1[1] = new Imagen(BitmapFactory.decodeResource(this.getResources(), R.drawable.tilesetopengamebackground), fondo1[0].posicion.x, 0);
        fondo2[0] = new Imagen(BitmapFactory.decodeResource(this.getResources(), R.drawable.fondoestrellado), 0, 0);
        fondo2[1] = new Imagen(BitmapFactory.decodeResource(this.getResources(), R.drawable.fondoestrellado), fondo2[0].posicion.x, 0);
        pref=context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        editpref=pref.edit();
        if(pref.contains("vibracion")){
           if(pref.getInt("vibracion", 30)==1){
               vibrador = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
           }else{
               vibrador=null;
           }
        }else{
            vibrador = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        }
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        fondo1[0].imagen = Bitmap.createScaledBitmap(fondo1[0].imagen, this.getWidth(), this.getHeight(), true);
        fondo1[1].imagen = Bitmap.createScaledBitmap(fondo1[1].imagen, this.getWidth(), this.getHeight(), true);
        fondo1[1].posicion.x = fondo1[0].posicion.x - fondo1[1].imagen.getWidth();
        fondo2[0].imagen = Bitmap.createScaledBitmap(fondo2[0].imagen, this.getWidth(), this.getHeight(), true);
        fondo2[1].imagen = Bitmap.createScaledBitmap(fondo2[1].imagen, this.getWidth(), this.getHeight(), true);
        fondo2[1].posicion.x = fondo2[0].posicion.x - fondo2[1].imagen.getWidth();
        hilo.setFuncionando(true);
        dibujar = true;
        if (hilo.getState() == Thread.State.NEW) hilo.start();
        if (hilo.getState() == Thread.State.TERMINATED) {
            hilo = new Hilo(this);
            hilo.start();

        }
        resumir();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        dibujar = false;
        boolean retry = true;
        hilo.setFuncionando(false);
        while (retry) {
            try {
                hilo.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }

    }

    /**
     * <p>Función a la que se le pasa el contexto y un numero en dps para hacer un reescalado proporcional al tipo de pantalla
     * devuelve un int con el tamaño en pixeles adecuado.</p>
     *
     * @param context el contexto del SurfaceView.
     * @param dp cantidad en dps a convertir a pixels
     * @return cantidad en pixels
     */
    public int dpTopx(Context context, int dp) {
        return context.getResources().getDimensionPixelSize(dp);

    }

    /**
     * <p>Función onDraw a la que se le pasa el canvas encargado del dibujado y en el se controla la variable controladorDePulsaciones y en la que
     * además se controla que escena se dibuja llamando a la función dibujado de la clase EscenaGenerica.</p>
     *
     * @param c el canvas
     */
    @Override
    synchronized protected void onDraw(Canvas c) {
        if (dibujar) {
            Log.i("Escena mas posicion", "" + escenas + " " + posicionx);
            sonido.gestionDeSonido();
            this.c = c;
            if (controladorDeAgitacion == 100) {
                escenas = 7;
                contador = 6;
                cambiar = true;
                controladorDeAgitacion = 0;
            }

            moverFondo();
            escena.dibujado();

        } else {
            for (int i = 0; i < sonido.players.length; i++) {
                if (sonido.players[i].isPlaying()) {
                    sonido.players[i].pause();
                }
            }
        }
    }

    /**
     * <p>Función que gestiona las pulsaciones y que dependiendo de la escena su comportamiento es uno u otro y cada acción que se produce en cada clase
     * es gestionada en su propia clase.</p>
     *
     * @param event evento detectado
     * @return true en caso de haber registrado alguno o false en caso contrario.
     */
    @Override
    synchronized public boolean onTouchEvent(MotionEvent event) {

        int accion = event.getActionMasked();
        x = event.getX();
        y = event.getY();
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (!(escena instanceof Batalla) && !(escena instanceof BatallaFinal)) {
                    escena.deteccionPulsacionBotones(x, y);
                }
                if (escena instanceof Batalla) {
                    ((Batalla) escena).batalla(x, y);
                } else if (escena instanceof BatallaFinal) {
                    ((BatallaFinal) escena).batalla(x, y);
                }
                break;
            case MotionEvent.ACTION_UP:
                pulsadoDerecho = false;
                pulsadoIzquierdo = false;
                pulsadoArriba = false;
                pulsadoAbajo = false;
                break;
        }

        return true;
    }

    /**
     * Funcién encargada de realizar el efecto de scroll
     */
    public void moverFondo() {
        if (escenas == 1 || escenas == 10 || escenas == 6) {
            if (fondo1[0].posicion.x >= this.getWidth()) {
                fondo1[0].posicion.x = fondo1[1].posicion.x - fondo1[0].imagen.getWidth();
            }
            if (fondo1[1].posicion.x >= this.getWidth()) {
                fondo1[1].posicion.x = fondo1[0].posicion.x - fondo1[1].imagen.getWidth();
            }
            c.drawBitmap(fondo1[0].imagen, fondo1[0].posicion.x, fondo1[0].posicion.y, null);
            c.drawBitmap(fondo1[1].imagen, fondo1[1].posicion.x, fondo1[1].posicion.y, null);
            fondo1[1].mover(1);
            fondo1[0].mover(1);
        }
        if (escenas == 23 || escenas == 24 || escenas == 25 || escenas == 26 || escenas == 27 || escenas == 9) {
            if (fondo2[0].posicion.x >= this.getWidth()) {
                fondo2[0].posicion.x = fondo2[1].posicion.x - fondo2[0].imagen.getWidth();
            }
            if (fondo2[1].posicion.x >= this.getWidth()) {
                fondo2[1].posicion.x = fondo2[0].posicion.x - fondo2[1].imagen.getWidth();
            }
            c.drawBitmap(fondo2[0].imagen, fondo2[0].posicion.x, fondo2[0].posicion.y, null);
            c.drawBitmap(fondo2[1].imagen, fondo2[1].posicion.x, fondo2[1].posicion.y, null);
            fondo2[1].mover(1);
            fondo2[0].mover(1);
        }
    }

    /**
     * Función encargada de pausar el hilo desde el onPause del Main.
     */
    public void pausar() {
        hilo.enPausa();
    }

    /**
     * Función encargada de reiniciar el funcionamiento del hilo desde el onResume del Main.
     */
    public void resumir() {
        hilo.setFuncionando(true);
        hilo.resumir();

    }
}


