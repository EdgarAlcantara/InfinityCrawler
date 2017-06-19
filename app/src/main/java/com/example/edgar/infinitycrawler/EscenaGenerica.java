package com.example.edgar.infinitycrawler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import android.media.AudioManager;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>
 *     Clase generica que gestiona la escena que toca cargar en el momento dependiendo del valor de la variable escenas de la clase Surface,
 *     además de tener en cuenta otras variables como por ejemplo la variable cambiar que se usa para dar la señal del cambio. Además, por
 *     ser la clase padre del resto, se usa para generalizar algunas mecánicas comunes.
 * </p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class EscenaGenerica {
    /**
     * Variable indica durante los combates si es el turno del guerrero
     * y en ese caso permite realizar las acciones definidas en Batalla y en BatallaFinal.
     */
    public boolean turnopersonaje1 = true;
    /**
     * Variable indica durante los combates si es el turno del mago
     * y en ese caso permite realizar las acciones definidas en Batalla y en BatallaFinal.
     */
    public boolean turnopersonaje2 = false;
    /**
     * Variable indica durante los combates si es el turno del bárbaro
     * y en ese caso permite realizar las acciones definidas en Batalla y en BatallaFinal.
     */
    public boolean turnopersonaje3 = false;

    /**
     * Variable indica durante los combates si es el turno del enemigo
     * y en ese caso permite realizar las acciones definidas en Batalla y en BatallaFinal.
     */
    public boolean turnoenemigo = false;
    /**
     * Varialbes constantes que representan el valor de las escena, se usan principalmente para evaluar el valor de escenas y para tomarlas
     * como referencia a la hora de producir los cambios. Se crean para aclarar el código
     */
    final int ESCENA10 = 10;
    final int OPCIONES = 28;
    final int MENUINICIAL = 0;
    final int ESCENA0 = 1;
    final int ESCENA7 = 7;
    final int BATALLAFINAL = 8;
    final int GRUPO = 20;
    final int BATALLA = 21;
    final int MENUGUARDAR = 22;
    final int AYUDA = 23;
    final int AYUDA2 = 24;
    final int CREDITOS = 25;
    final int ESCENA1 = 2;
    final int ESCENA2 = 3;
    final int ESCENA3 = 4;
    final int ESCENA4 = 5;
    final int ESCENA6 = 6;
    final int AYUDA3 = 26;
    final int AYUDA4 = 27;
    final int TEXTOFINAL = 9;
    /**
     * Variables definidas para representar algunos elementos comunes entre las escenas, aunque muchas veces estas variables son sustituidas
     * por otras.
     */
    Bitmap prota, sala, puerta, escalera, fondo;
    /**
     * Variable del tipo surface que se encarga de guardar las características del Surface para posteriormente usarlo en las diferentes
     * acciones. Principalmente se usa para acceder a las variables definidas en la clase Surface.
     */
    Surface vista;
    /**
     * Variable de tipo Context cuya función es similar a la de la variable vista, pero en este caso con las características del contexto.
     */
    Context context;
    /**
     * Variables de tipo Imagen que se usan para definir los borones en la función botones para dibujarlos en las escenas en las que aparecen.
     */
    public Imagen botonderecho, botonizquierdo, botonarriba, botonabajo, grupo, guardar, salir;
    /**
     * Array de imagenes que se sobreeescribe en cada una de las escenas para gestionar las imagenes que corresponden a esa escena como un
     * conjunto. Se usa principalmente en la función inicializacionDeEscena.
     */
    public Imagen[] actual;
    /**
     * Arrays de secuencias de imágenes definidos para producir el efecto de movimiento del personaje y del enemigo durante la partida.
     * Estos arrays se recorren, en el caso del personaje, con la variable cont definida en la clase Surface y, en el caso de la antorcha,
     * con la variable cont2, también definida en la clase Escena7.
     */
    int[] secuenciArriba = {R.drawable.prota1, R.drawable.prota1, R.drawable.prota2, R.drawable.prota2, R.drawable.prota3, R.drawable.prota3};
    int[] secuenciaDerecha = {R.drawable.prota4, R.drawable.prota4, R.drawable.prota5, R.drawable.prota5, R.drawable.prota6, R.drawable.prota6};
    int[] secuenciaIzquierda = {R.drawable.prota10, R.drawable.prota10, R.drawable.prota11, R.drawable.prota11, R.drawable.prota12, R.drawable.prota12};
    int[] secuenciAbajo = {R.drawable.prota7, R.drawable.prota7, R.drawable.prota8, R.drawable.prota8, R.drawable.prota9, R.drawable.prota9};
    int[] secuenciaEnemigo = {R.drawable.enemigo1, R.drawable.enemigo1, R.drawable.enemigo2, R.drawable.enemigo2, R.drawable.enemigo3, R.drawable.enemigo3};
    int[] secuenciaEnemigo2 = {R.drawable.enemigo21, R.drawable.enemigo21, R.drawable.enemigo22, R.drawable.enemigo22, R.drawable.enemigo23, R.drawable.enemigo23};
    int[] secuenciaEnemigo3 = {R.drawable.enemigo31, R.drawable.enemigo31, R.drawable.enemigo32, R.drawable.enemigo32, R.drawable.enemigo33, R.drawable.enemigo33};
    int[] secuenciaBoss = {R.drawable.ogro1, R.drawable.ogro1, R.drawable.ogro2, R.drawable.ogro2, R.drawable.ogro3, R.drawable.ogro3};
    int [] ataquefuego={R.drawable.fuego1, R.drawable.fuego1, R.drawable.fuego2, R.drawable.fuego2, R.drawable.fuego1, R.drawable.fuego2};
    int [] ataqueHielo={R.drawable.hielo1,R.drawable.hielo1,R.drawable.hielo2,R.drawable.hielo2,R.drawable.hielo1,R.drawable.hielo1};
    int [] secuenciaAntorcha={R.drawable.antorcha1,R.drawable.antorcha1,R.drawable.antorcha2,R.drawable.antorcha2,R.drawable.antorcha3,R.drawable.antorcha3};
    /**
     * Variable en la que se guarda el Paint definido en la clase Surface para usarlo en las distintas escenas.
     */
    Paint p;
    /**
     * Variable AlerDialog encargada de pedir confirmación a la hora de cerrar el juego.
     */
    AlertDialog.Builder alerta;
    /**
     * Variables de tipo Barbaro, Guerrero y Mago que se definen para ser usadas posteriormente en las escenas Batalla y BatallaFinal
     * y realizar las funciones definidas en esas clases teniendo en cuenta sus valores.
     */
    Barbaro barbaro;
    Mago mago;
    Guerrero guerrero;
    /**
     * Variable que determina a qué personaje atacará el enemigo.
     */
    int personaje;
    /**
     * Variable que determina qué hechizo ejecuta el mago.
     */
    int quehechizo;
    /**
     * Constructor definido para ser usado en las distintas escenas, menos en la escena Batalla y en la escena BatallaFinal, en el
     * que se da el valor a vista de la clase Surface para poder usar las variables definidas en esa clase.
     * Además se define un mensaje de alerta para el caso en el que se haya pulsado el botón salir de MenuIncial.
     *
     * @param contexto el contexto del SurfaceView.
     * @param vista el SurfaceView.
     * @param p el paint definido en el SurfaceView.
     */
    public EscenaGenerica(Context contexto, Surface vista, Paint p) {

        this.p = p;
        this.vista = vista;
        this.context = contexto;
        alerta = new AlertDialog.Builder(vista.getContext());
        alerta.setMessage("¿Está seguro de querer salir?");
        alerta.setTitle("Salir");
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Runtime.getRuntime().exit(1);
                dialog.cancel();
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

    }

    /**
     * Constructor definido para el caso en el que la escena es igual a Batalla o Batalla final ya que se le pasa la lista de
     * personajes definidos en la clase Surface y pasados a la clase EscenaGenerica a través de la variable escena definida en
     * la clase Surface.
     * @param contexto el contexto del SurfaceView
     * @param vista el SurfaceView
     * @param guerrero el guerrero definido en el SurfaceView
     * @param mago el mago definido en el SurfaceView
     * @param barbaro el bárbaro definido en el SurfaceView
     * @param p la variable de tipo Paint definida en el SurfaceView
     */
    public EscenaGenerica(Context contexto, Surface vista, Guerrero guerrero, Mago mago, Barbaro barbaro, Paint p) {

        this(contexto, vista, p);
        this.mago = mago;
        this.guerrero = guerrero;
        this.barbaro = barbaro;
        inicializacionDeEscena();
        escalado();

    }

    /**
     * Función que se sobreescribe en cada una de las escenas para dibujar los elementos contretos de de cada una.
     *
     * @param c el canvas del SurfaceView.
     */
    synchronized public void dibujado(Canvas c) {

    }

    /**
     * Función que al igual que dibujado se sobreescibe en cada una de las escenas con sus elementos concretos y cuya función
     * consiste en escalar las imagenes proporcionandolas al dispositivo.
     */
    synchronized public void escalado() {

    }

    /**
     * Función que inicializa la variable "actual" con los valores concretos que tiene en la escena.
     */
    synchronized public void inicializacionDeEscena() {

    }

    /**
     * <p>Función a la que se le pasa el contexto y un numero en dps para hacer un reescalado proporcional al tipo de pantalla
     * devuelve un int con el tamaño en pixeles adecuado.</p>
     *
     * @param context el contexto del SurfaceView
     * @param dp cantidad en dps
     * @return la cantidad en pixels
     */
    synchronized public int dpTopx(Context context, int dp) {
        return context.getResources().getDimensionPixelSize(dp);

    }

    /**
     * Función que se encarga de cambiar el sprite del personaje recorriendo el array de sprites secuenciaArriba cuando se pulsa el
     * botón direccional correspondiente. La variable cont está definida en la clase Surface.
     *
     * @param cont la variable que recorre el array.
     * @return la imagen que ocupa la posición en el array con el valor de cont
     */
    synchronized public Bitmap cambiarSpritearr(int cont) {
        return actual[1].imagen = BitmapFactory.decodeResource(vista.getResources(), secuenciArriba[cont]);
    }

    /**
     * Función que se encarga de cambiar el sprite del personaje recorriendo el array de sprites secuenciaDerecha cuando se pulsa el
     * botón direccional correspondiente. La variable cont está definida en la clase Surface.
     *
     * @param cont la variable que recorre el array.
     * @return la imagen que ocupa la posición en el array con el valor de cont
     */
    synchronized public Bitmap cambiarSpriteder(int cont) {
        return actual[1].imagen = BitmapFactory.decodeResource(vista.getResources(), secuenciaDerecha[cont]);
    }

    /**
     * Función que se encarga de cambiar el sprite del personaje recorriendo el array de sprites secuenciaIzquierda cuando se pulsa el
     * botón direccional correspondiente. La variable cont está definida en la clase Surface.
     *
     * @param cont la variable que recorre el array.
     * @return la imagen que ocupa la posición en el array con el valor de cont
     */
    synchronized public Bitmap cambiarSpriteiz(int cont) {
        return actual[1].imagen = BitmapFactory.decodeResource(vista.getResources(), secuenciaIzquierda[cont]);
    }
    /**
     * Función que se encarga de cambiar el sprite del personaje recorriendo el array de sprites secuenciaAbajo cuando se pulsa el
     * botón direccional correspondiente. La variable cont está definida en la clase Surface.
     *
     * @param cont la variable que recorre el array.
     * @return la imagen que ocupa la posición en el array con el valor de cont
     */
    synchronized public Bitmap cambiarSpriteab(int cont) {
        return actual[1].imagen = BitmapFactory.decodeResource(vista.getResources(), secuenciAbajo[cont]);
    }

    /**
     * Función que se encarga de cambiar el sprite del enemigo recorriendo el array de sprites secuenciaEnemigo durante
     * los combates.
     * @param cont variable que recorre el array.
     * @return el bitmap que se coloca en la imagen del enemigo.
     */
    synchronized public Bitmap cambiarSpriteEnemigo(int cont) {
        return actual[4].imagen = BitmapFactory.decodeResource(vista.getResources(), secuenciaEnemigo[cont]);
    }

    /**
     * Función que recorre cambia el sprite del enemigo recorriendo el array de sprites secuenciaEnemigo2
     * durante los combates.
     * @param cont variable que recorre el array.
     * @return el bitmap que se coloca en la imagen del enemigo.
     */
    synchronized public Bitmap cambiarSpriteEnemigo2(int cont) {
        return actual[4].imagen = BitmapFactory.decodeResource(vista.getResources(), secuenciaEnemigo2[cont]);
    }
    /**
     * Función que recorre cambia el sprite del enemigo recorriendo el array de sprites secuenciaEnemigo3
     * durante los combates.
     * @param cont variable que recorre el array.
     * @return el bitmap que se coloca en la imagen del enemigo.
     */
    synchronized public Bitmap cambiarSpriteEnemigo3(int cont) {
        return actual[4].imagen = BitmapFactory.decodeResource(vista.getResources(), secuenciaEnemigo3[cont]);
    }
    /**
     * Función que recorre cambia el sprite del enemigo recorriendo el array de sprites secuenciaBoss
     * durante la batalla final.
     * @param cont variable que recorre el array.
     * @return el bitmap que se coloca en la imagen del enemigo.
     */
    synchronized public Bitmap cambiarSpriteBoss(int cont) {
        return actual[4].imagen = BitmapFactory.decodeResource(vista.getResources(), secuenciaBoss[cont]);
    }

    /**
     * Función que sirve para crear el efecto de ataque mágico de fuego
     * @param cont variable que recorre el array.
     * @return el Bitmap que ocupa la posición en el array igual a cont.
     */
    synchronized public Bitmap cambiarSpriteHechizoFuego(int cont){
        return actual[4].imagen=BitmapFactory.decodeResource(vista.getResources(), ataquefuego[cont]);
    }
    /**
     * Función que sirve para crear el efecto de ataque mágico de hielo.
     * @param cont variable que recorre el array.
     * @return el Bitmap que ocupa la posición en el array igual a cont.
     */
    synchronized public Bitmap cambiarSpriteHechizoHielo(int cont){
        return actual[4].imagen=BitmapFactory.decodeResource(vista.getResources(), ataqueHielo[cont]);
    }
    /**
     * Variable devuelve un objeto tipo imagen sacado del array actual, que se corresponde con una de las imagenes o bien de una
     * puerta o bien de una escalera.
     *
     * @return devuelve los datos de la puerta número 1
     */
    public Imagen getPuerta1() {
        return actual[2];
    }

    /**
     * Hace lo mismo que getPuerta1 pero con el otro elemento de la sala que sirve para cambiar de escena.
     *
     * @return devuelve los datos de la imagen de la puerta nº dos
     */
    public Imagen getPuerta2() {
        return actual[3];
    }

    /**
     * Función que sirve para capturar la variable Imagen del personaje para realizar cambios sobre la misma tanto para el movimiento
     * como para las colisiones.
     *
     * @return los datos de la imagen del personaje
     */
    public Imagen getPersonaje() {
        return actual[1];
    }

    /**
     * Función que recoge el sprite de la sala para trabajar con sus propiedades en distintas funciones.
     *
     * @return el bitmap de la sala.
     */
    public Bitmap getFondo() {
        return actual[0].imagen;
    }

    /**
     * Función que genera un número aleatorio entre un rango dado por las variables minimo y maximo.
     *
     * @param minimo valor mínimo del rango.
     * @param maximo valor máximo del rango.
     * @return número producido por el Math.random.
     */
    public int generaNumeroAleatorio(int minimo, int maximo) {

        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }
    public boolean colisiones(){
        return false;
    }

    /**
     * Función que se encarga de gestionar el cambio de posición del sprite del personaje y de detectar
     * las colisiones de los rectángulos de las distintas imagenes dentro de la escena, recurriendo a las funciones definidas en la clase Imagen.
     * En el caso de producirse una colisión prepara las variables para realizar el cambio y en el caso de producirse la colisión
     * realiza una vibración del dispositivo. Y además se realiza la comprobación aleatoria
     * de si se entra en combate o no.
     */
    synchronized public void deteccion() {
        try {
            vista.hilo.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vista.batalla = generaNumeroAleatorio(1, 200);
        if (vista.batalla <= 10) {
            vista.guardarEscena = vista.escenas;
            vista.posicionxb = (int) vista.escena.actual[1].posicion.x;
            vista.posicionyb = (int) vista.escena.actual[1].posicion.y;
            vista.enElCambio = vista.escena.actual[1].imagen;
            vista.cambiar = true;
            vista.escenas = BATALLA;
        } else {
            if (vista.pulsadoDerecho) {
                if (this instanceof Escena1 &&colisiones()||this instanceof Escena3 &&colisiones()||this instanceof Escena4 && colisiones()||this instanceof Escena2 && colisiones()){
                    vista.escena.getPersonaje().posicion.x -= dpTopx(vista.getContext(), R.dimen.moderpersonaje);
                    vista.pulsadoDerecho = false;
                }

            } else if (vista.pulsadoIzquierdo) {
                if (this instanceof Escena1 &&colisiones()||this instanceof Escena3 &&colisiones()||this instanceof Escena4 && colisiones()||this instanceof Escena2 && colisiones()) {
                    vista.escena.getPersonaje().posicion.x += dpTopx(vista.getContext(), R.dimen.moderpersonaje);
                    vista.pulsadoIzquierdo = false;
                }

            } else if (vista.pulsadoAbajo) {
                if (this instanceof Escena1 &&colisiones()||this instanceof Escena3 &&colisiones()||this instanceof Escena4 && colisiones()||this instanceof Escena2 && colisiones()) {
                    vista.escena.getPersonaje().posicion.y -= dpTopx(vista.getContext(), R.dimen.moderpersonaje);
                    vista.pulsadoAbajo = false;
                }

            } else if (vista.pulsadoArriba) {
                if (this instanceof Escena1 &&colisiones()||this instanceof Escena3 &&colisiones()||this instanceof Escena4 && colisiones()||this instanceof Escena2 && colisiones()) {
                    vista.escena.getPersonaje().posicion.y += dpTopx(vista.getContext(), R.dimen.moderpersonaje);
                    vista.pulsadoArriba = false;
                }

            }
            if (this.getPersonaje().setRectangulos().intersect(this.getPuerta1().setRectangulos())
                    || this.getPersonaje().setRectangulos().intersect(this.getPuerta2().setRectangulos())) {
                if (this instanceof Escena1) {
                    vista.escenas = generaNumeroAleatorio(4, 5);

                } else if (this instanceof Escena2) {
                    vista.escenas = generaNumeroAleatorio(1, 3);
                    if (vista.escenas == ESCENA0) {
                        vista.escenas = ESCENA1;
                    } else if (vista.escenas == ESCENA1) {
                        vista.escenas = ESCENA3;
                    } else if (vista.escenas == ESCENA2) {
                        vista.escenas = ESCENA4;
                    }
                } else if (this instanceof Escena3) {
                    vista.escenas = generaNumeroAleatorio(1, 3);

                    if (vista.escenas == ESCENA0) {
                        vista.escenas = ESCENA2;
                    } else if (vista.escenas == ESCENA1) {
                        vista.escenas = ESCENA1;
                    } else if (vista.escenas == ESCENA2) {
                        vista.escenas = ESCENA4;
                    }
                } else if (this instanceof Escena4) {
                    vista.escenas = generaNumeroAleatorio(2, 4);

                }
                if(vista.vibrador!=null) {
                    vista.vibrador.vibrate(300);
                }
                vista.contador++;
                vista.pulsadoDerecho = false;
                vista.cambiar = true;

            }


        }
        cambiarImagenPersonaje();

    }

    /**
     * Función usada durante los combates y que se encarga de dibujar el rectángulo rojo definido en la función dibujarRectangulo de la
     * clase Imagen alrededor del botón de ataque del personaje sobre el que está el turno.
     */
    public void dibujarCuadradosDeTurnos() {

        if ( turnopersonaje1 && vista.guerrero.getVida() != 0 && this instanceof Batalla ||  turnopersonaje1 && vista.guerrero.getVida() != 0 && this instanceof BatallaFinal) {

            Paint p = new Paint();
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(10);

            vista.c.drawRect(actual[1].dibujarRectangulo(), p);


        } else if ( turnopersonaje2 && vista.mago.getVida() != 0 && this instanceof Batalla ||  turnopersonaje2 && vista.mago.getVida() != 0 && this instanceof BatallaFinal) {
            Paint p = new Paint();
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(10);

            vista.c.drawRect(actual[2].dibujarRectangulo(), p);
        } else if ( turnopersonaje3 && vista.barbaro.getVida() != 0 && this instanceof Batalla ||  turnopersonaje3 && vista.barbaro.getVida() != 0 && this instanceof BatallaFinal) {
            Paint p = new Paint();
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(10);

            vista.c.drawRect(actual[3].dibujarRectangulo(), p);
        }
    }

    /**
     * Función que se encarga de guardar la partida escribiendo en un archivo de texto las variables.
     */
    public void guardadoDePartida() {
        String fichero = vista.getContext().getFilesDir().getPath().toString() + "/guardado3.txt";
        try {
            File f = new File(fichero);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream salida = new FileOutputStream(f);
            ObjectOutputStream guardado = new ObjectOutputStream(salida);
            guardado.writeInt(vista.guardarEscena);
            guardado.writeInt(vista.posicionx);
            guardado.writeInt(vista.posiciony);
            guardado.writeInt(vista.guerrero.vida);
            guardado.writeInt(vista.guerrero.VIDAGUERRERO);
            guardado.writeInt(vista.guerrero.experiencia);
            guardado.writeInt(vista.guerrero.nivel);
            guardado.writeInt(vista.mago.vida);
            guardado.writeInt(vista.mago.VIDAMAGO);
            guardado.writeInt(vista.mago.experiencia);
            guardado.writeInt(vista.mago.nivel);
            guardado.writeInt(vista.barbaro.vida);
            guardado.writeInt(vista.barbaro.VIDABARBARO);
            guardado.writeInt(vista.barbaro.experiencia);
            guardado.writeInt(vista.barbaro.nivel);
            guardado.writeInt(vista.cantidadDeCuras);
            guardado.close();
            salida.close();
            Log.i("guardado", "guardado");
            Toast.makeText(vista.getContext(), "Partida guardada", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Función encargada de cargar la partida leyendo las varialbes guardadas en la función guardadoDePartida.
     */
    public void cargadoDePartida() {
        String fichero = vista.getContext().getFilesDir().getPath().toString() + "/guardado3.txt";
        try {

            File f = new File(fichero);
            if (!f.exists()) {
                Toast.makeText(vista.getContext(), "No se ha guardado ninguna partida", Toast.LENGTH_SHORT).show();
            } else if (f.length() == 0) {
                Toast.makeText(vista.getContext(), "No se ha guardado ninguna partida", Toast.LENGTH_SHORT).show();
            } else {

                FileInputStream salida = new FileInputStream(f);
                ObjectInputStream guardado = new ObjectInputStream(salida);
                vista.escenas = guardado.readInt();
                vista.posicionx = guardado.readInt();
                vista.posiciony = guardado.readInt();
                vista.guerrero.vida=guardado.readInt();
                vista.guerrero.VIDAGUERRERO = guardado.readInt();
                vista.guerrero.experiencia=guardado.readInt();
                vista.guerrero.nivel=guardado.readInt();
                vista.mago.vida=guardado.readInt();
                vista.mago.VIDAMAGO = guardado.readInt();
                vista.mago.experiencia=guardado.readInt();
                vista.mago.nivel=guardado.readInt();
                vista.barbaro.vida=guardado.readInt();
                vista.barbaro.VIDABARBARO = guardado.readInt();
                vista.barbaro.experiencia=guardado.readInt();
                vista.barbaro.nivel=guardado.readInt();
                vista.cantidadDeCuras = guardado.readInt();
                guardado.close();
                salida.close();
                Log.i("guardado", "guardado");

                vista.deGuardado = true;
                vista.cambiar = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Cambia el sprite del personaje dependiendo del botón direccional que se pulse, se usan las funciones de cambiarSpritexxxx
     */
    public void cambiarImagenPersonaje() {

        if (vista.cont < this.secuenciaDerecha.length && vista.pulsadoDerecho) {
            vista.escena.getPersonaje().imagen = cambiarSpriteder(vista.cont);
            vista.cont++;
        } else if (vista.cont < this.secuenciaIzquierda.length && vista.pulsadoIzquierdo) {
            vista.escena.getPersonaje().imagen = cambiarSpriteiz(vista.cont);
            vista.cont++;
        } else if (vista.cont < this.secuenciArriba.length && vista.pulsadoArriba) {
            vista.escena.getPersonaje().imagen = cambiarSpritearr(vista.cont);
            vista.cont++;
        } else if (vista.cont < this.secuenciAbajo.length && vista.pulsadoAbajo) {
            vista.escena.getPersonaje().imagen = cambiarSpriteab(vista.cont);
            vista.cont++;
        } else {
            vista.cont = 0;
        }


    }

    /**
     * Función que se encarga de detectar la pulsación de botones durante la partida usando para ello los valores x e y que se
     * le pasan en la función onTouchEvent del Surface y llamando a los métidos concretos definidos en cada clase.
     *
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     */
    public void deteccionPulsacionBotones(float x, float y) {
        vista.comenzarBatalla = false;
        switch (vista.escenas) {
            case MENUINICIAL:
                if (((MenuInicial) (this)).nuevaPartidaPulsado(x, y)) {
                    vista.escenas++;
                    vista.guerrero.reiniciarPersonaje();
                    vista.mago.reiniciarPersonaje();
                    vista.barbaro.reiniciarPersonaje();
                    vista.cambiar = true;
                } else if (((MenuInicial) (this)).pulsarBotonAyuda(x, y)) {
                    vista.escenas = AYUDA;
                    vista.cambiar = true;
                } else if (((MenuInicial) (this)).pulsarBotonCreditos(x, y)) {
                    vista.escenas = CREDITOS;
                    vista.cambiar = true;
                } else if (((MenuInicial) (this)).cargarPartida(x, y)) {
                    this.cargadoDePartida();
                } else if (((MenuInicial) (this)).botonSalir(x, y)) {
                    alerta.show();
                    alerta.create();
                }else if(((MenuInicial)(this)).pulsarBotonOpciones(x,y)){
                    vista.escenas=OPCIONES;
                    vista.cambiar=true;
                }
                break;

            case ESCENA0:

                if (((Escena0) (this)).tocarBotonContinuar(x, y)) {
                    vista.escenas = ESCENA1;
                    vista.cambiar = true;
                }
                break;
            case ESCENA6:
                if (((Escena6) (this)).pulsarBotonSiguienteEscena6(x, y)) {
                    vista.escenas++;
                    vista.cambiar = true;
                }
                break;
            case GRUPO:
                if (((Grupo) (this)).pulsarBotonVolver(x, y)) {
                    vista.escenas = vista.guardarEscena;
                    vista.cambiar = true;
                    vista.deMenu = true;
                } else if (((Grupo) (this)).pulsarBotonRecuperar(x, y)) {
                    if (vista.cantidadDeCuras > 0) {
                        vista.guerrero.actualiza("Padd", 15);
                        vista.mago.actualiza("Eknido", 5);
                        vista.barbaro.actualiza("Wrehuf", 30);
                        vista.cantidadDeCuras -= 1;
                        Toast.makeText(vista.getContext(), "Curas restantes:" + vista.cantidadDeCuras, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(vista.getContext(), "No te quedan más curas", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case BATALLA:
                break;
            case MENUGUARDAR:
                if (((MenuGuardar) (this)).pulsarBotonGuardarDeGuardar(x, y)) {
                    guardadoDePartida();
                } else if (((MenuGuardar) (this)).cargarPartidaDeGuardar(x, y)) {
                    cargadoDePartida();
                } else if (((MenuGuardar) (this)).pulsarBotonSalirDeGuardar(x, y)) {
                    vista.escenas = MENUINICIAL;
                    vista.cantidadDeCuras = 10;
                    vista.contador = 0;
                    vista.guerrero.actualiza("Padd", 15, 200);
                    vista.mago.actualiza("Eknido", 5,100);
                    vista.barbaro.actualiza("Wrehuf", 30, 250);
                    vista.cambiar = false;
                    vista.comenzado = true;
                } else if (((MenuGuardar) (this)).pulsarBotonVolverDeGuardar(x, y)) {
                    vista.escenas = vista.guardarEscena;
                    getPersonaje().posicion.x = vista.posicionx;
                    getPersonaje().posicion.y = vista.posiciony;
                    getPersonaje().imagen = vista.enElCambio;
                    vista.deGuardado = true;
                    vista.cambiar = true;
                }
                break;
            case AYUDA:
                if (((Ayuda) (this)).pulsarBotonSalirDeAyuda(x, y)) {
                    vista.escenas = MENUINICIAL;

                    vista.comenzado = true;
                } else if (((Ayuda) (this)).pulsarBotonSiguienteDeAyuda(x, y)) {
                    vista.escenas = AYUDA2;
                    vista.cambiar = true;
                }
                break;
            case AYUDA2:
                if (((Ayuda2) (this)).pulsarBotonSalirDeAyuda2(x, y)) {
                    vista.escenas = MENUINICIAL;
                    vista.comenzado = true;

                } else if (((Ayuda2) (this)).pulsarBotonSiguienteDeAyuda2(x, y)) {
                    vista.escenas = AYUDA3;
                    vista.cambiar = true;
                }
                break;
            case AYUDA3:
                if (((Ayuda3) (this)).pulsarBotonSalirDeAyuda3(x, y)) {
                    vista.escenas = MENUINICIAL;
                    vista.comenzado = true;

                } else if (((Ayuda3) (this)).pulsarBotonSiguienteDeAyuda3(x, y)) {
                    vista.escenas = AYUDA4;
                    vista.cambiar = true;
                }
                break;
            case AYUDA4:
                if (((Ayuda4) (this)).pulsarBotonSalirDeAyuda4(x, y)) {
                    vista.escenas = MENUINICIAL;
                    vista.comenzado = true;

                }
                break;
            case CREDITOS:
                if (((Creditos) (this)).pulsarBotonSalirDeCreditos(x, y)) {
                    vista.escenas = MENUINICIAL;
                    vista.comenzado = true;
                }
                break;
            case TEXTOFINAL:

                if (((Escena9) (this)).tocarBotonContinuar(x, y)) {
                    vista.escenas = MENUINICIAL;
                    vista.contador = 0;
                    vista.cantidadDeCuras = 10;
                    vista.controladorDeAgitacion = 0;
                    vista.comenzado = true;
                    vista.guerrero.actualiza("Padd", 15, 200);
                    vista.mago.actualiza("Eknido", 5);
                    vista.barbaro.actualiza("Wrehuf", 30, 250);
                }
                break;
            case ESCENA10:
                if (((Escena10) (this)).tocarBotonContinuar(x, y)) {
                    vista.escenas = MENUINICIAL;
                    vista.contador = 0;
                    vista.cantidadDeCuras = 10;
                    vista.controladorDeAgitacion = 0;
                    vista.comenzado = true;
                    vista.guerrero.actualiza("Padd", 15, 200);
                    vista.mago.actualiza("Eknido", 5, 100);
                    vista.barbaro.actualiza("Wrehuf", 30, 250);
                }
                break;
            case BATALLAFINAL:

                break;
            case OPCIONES:
                if(((Opciones)(this)).pulsarBotonAumentarVol(x,y)){

                    vista.manager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_VIBRATE);
                }else if(((Opciones)(this)).pulsarBotonBajarVol(x,y)){

                    vista.manager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_VIBRATE);

                }else if(((Opciones)(this)).volverAMenu(x,y)){
                    vista.escenas = MENUINICIAL;
                    vista.comenzado = true;
                }else if(((Opciones)(this)).botonQuitarVibracion(x,y)){
                    if(vista.vibrador!=null) {
                        Toast.makeText(vista.getContext(), "Se ha desactivado la vibración", Toast.LENGTH_SHORT).show();
                        vista.vibrador = null;
                        vista.editpref.putInt("vibracion",2);
                        vista.editpref.commit();
                    }else{
                        Toast.makeText(vista.getContext(), "La vibración ya está desactivada", Toast.LENGTH_SHORT).show();
                    }
                }else if(((Opciones)(this)).botonPonerVibracion(x,y)){
                    if(vista.vibrador==null) {
                        Toast.makeText(vista.getContext(), "Se ha activado la vibración", Toast.LENGTH_SHORT).show();
                        vista.vibrador = (Vibrator) vista.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vista.editpref.putInt("vibracion",1);
                        vista.editpref.commit();
                    }else{
                        Toast.makeText(vista.getContext(), "La vibración ya está activada", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:

                deLaUnoAlaSiete(x, y);//Botones comunes de todas las escenas, menos las de menú


        }


    }

    /**
     * Detección de pulsación de botones que se encuentran a partir de la escena1 hasta la 7 para cambiar el valor de las
     * varialbes definidas en el Surface. Se llama en el default del switch de la función deteteccionPulsacionesBotones.
     *
     * @param x posición x del evento registrado en el onTouchEvent.
     * @param y posición y del evento registrado en el onTouchEvent.
     */
    public void deLaUnoAlaSiete(float x, float y) {
        if (botonizquierdo.colisiona(x, y)) {
            vista.pulsadoIzquierdo = true;
        } else if (botonderecho.colisiona(x, y)) {
            vista.pulsadoDerecho = true;
        } else if (botonarriba.colisiona(x, y)) {
            vista.pulsadoArriba = true;
        } else if (botonabajo.colisiona(x, y)) {
            vista.pulsadoAbajo = true;
        } else if (grupo.colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            vista.sonido.pulsadoBoton.start();
            vista.guardarEscena = vista.escenas;
            vista.posicionx = (int) vista.escena.getPersonaje().posicion.x;
            vista.posiciony = (int) vista.escena.getPersonaje().posicion.y;
            vista.enElCambio = getPersonaje().imagen;
            vista.escenas = GRUPO;
            vista.cambiar = true;
        } else if (guardar.colisiona(x, y)) {
            vista.sonido.pulsadoBoton.start();
            vista.guardarEscena = vista.escenas;
            vista.posicionx = (int) vista.escena.getPersonaje().posicion.x;
            vista.posiciony = (int) vista.escena.getPersonaje().posicion.y;
            vista.enElCambio = getPersonaje().imagen;
            vista.cambiar = true;
            vista.escenas = MENUGUARDAR;

        }else if(salir.colisiona(x,y)){
            vista.escenas = MENUINICIAL;
            vista.comenzado = true;
        }
    }

    /**
     * Función que mueve la imagen del personaje a la derecha.
     */
    public void moverDerecha() {
        vista.batalla = 0;
        if (vista.pulsadoDerecho && !vista.cambiar) {
            if (this.getPersonaje().posicion.x < vista.escena.actual[0].imagen.getWidth() - dpTopx(vista.getContext(), R.dimen.colision)) {

                this.getPersonaje().posicion.x += dpTopx(vista.getContext(), R.dimen.moderpersonaje);

                deteccion();
            }else{
                vista.pulsadoDerecho=false;
            }

        }
    }
    /**
     * Función que mueve la imagen del personaje hacia arriba.
     */
    public void moverArriba() {
        vista.batalla = 0;
        if (vista.pulsadoArriba && !vista.cambiar) {
            if (vista.escena.actual[1].posicion.y > vista.escena.getFondo().getHeight() - vista.escena.getFondo().getHeight() * 0.95) {

                this.getPersonaje().posicion.y -= dpTopx(vista.getContext(), R.dimen.moderpersonaje);

                deteccion();
            } else {
                vista.pulsadoArriba = false;
            }

        }
    }
    /**
     * Función que mueve la imagen del personaje hacia la izquierda.
     */
    public void moverIzquierda() {
        vista.batalla = 0;
        if (vista.pulsadoIzquierdo && !vista.cambiar) {
            if (vista.escena.actual[1].posicion.x > 25) {

                this.getPersonaje().posicion.x -= dpTopx(vista.getContext(), R.dimen.moderpersonaje);

                deteccion();
            } else {
                vista.pulsadoIzquierdo = false;
            }
        }
    }
    /**
     * Función que mueve la imagen del personaje hacia abajo.
     */
    public void moverAbajo() {
        vista.batalla = 0;
        if (vista.pulsadoAbajo && !vista.cambiar) {
            if (vista.escena.actual[1].posicion.y < vista.escena.actual[0].imagen.getHeight() - dpTopx(vista.getContext(), R.dimen.colision)) {

                this.getPersonaje().posicion.y += dpTopx(vista.getContext(), R.dimen.moderpersonaje);

                deteccion();
            } else {
                vista.pulsadoAbajo = false;
            }
        }
    }

    /**
     * Función en la que se comprueba si se han cumplido las condiciones para cambiar de escena y en caso de ser así las dibuja
     * recurriendo para ellos a la funcion cambiarAxxxxx correspondiente y además dibuja los cambios de posición de la imagen del personaje
     * usando las funciones moverxxxx.
     */
    public void dibujado() {
        Canvas c = vista.c;

        switch (vista.escenas) {//Se comprueba el valor de la variable escenas definida en el Surface
            case MENUINICIAL:
                cambiarAMenuInicial();
                break;
            case ESCENA0:
                cambiarAescenaCero();
                break;
            case ESCENA1:
                cambiarAescenaUno();
                botones(c);
                break;
            case ESCENA2:
                cambiarAescenaDos();
                ((Escena2)(vista.escena)).cambiarSprite();
                botones(c);
                break;
            case ESCENA3:
                cambiarAescenaTres();
                botones(c);
                break;
            case ESCENA4:
                cambiarAescenaCuatro();
                botones(c);
                break;
            case ESCENA6:
                cambiarAescenaSeis();
                break;
            case ESCENA7:
                cambiarAEscenaSiete();
                botones(c);
                break;
            case GRUPO:
                cambiarAGrupo();
                break;
            case BATALLA:
                cambiaraBatalla();
                break;
            case BATALLAFINAL:
                batallaFinal();
                break;
            case TEXTOFINAL:
                cambiarAEscenaFinal();
                break;
            case ESCENA10:
                cambiarAEscenaDiez();
                break;
            case MENUGUARDAR:
                cambiarAMenuGuardar();
                break;
            case AYUDA:
                cambiarAAyuda();
                break;
            case AYUDA2:
                cambiarAAyuda2();
                break;
            case AYUDA3:
                cambiarAAyuda3();
                break;
            case AYUDA4:
                cambiarAAyuda4();
                break;
            case CREDITOS:
                cambiarACreditos();
                break;
            case OPCIONES:
                cambiarAOpciones();
                break;
        }
        if (vista.pulsadoDerecho) {
            vista.escena.moverDerecha();
        }
        if (vista.pulsadoIzquierdo) {
            vista.escena.moverIzquierda();
        }
        if (vista.pulsadoArriba) {
            vista.escena.moverArriba();
        }
        if (vista.pulsadoAbajo) {
            vista.escena.moverAbajo();
        }
        if (vista.contador < 6 || vista.contador == 7) {
            vista.escena.dibujado(c);
        } else if (vista.contador == 6) {
            cambiarAescenaSeis();
            vista.escena.dibujado(c);
        }
        vista.escena.dibujado(c);
        if (this instanceof Batalla || this instanceof BatallaFinal) {
            dibujarCuadradosDeTurnos();
            informeDuranteLaBatalla(c);
            cambiarSpriteDuranteBatalla();
        }

    }

    /**
     * Funciones que se encargan de gestionar el cambio a escenas concretas
     */
    public void cambiarAescenaCero() {
        if (vista.cambiar && vista.contador <= 6) {
            vista.escena = new Escena0(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
        }
    }

    public void cambiarAescenaUno() {
        vista.comenzarBatalla = false;
        vista.guardarEscena = vista.escenas;
        vista.c.drawColor(Color.BLACK);
        if (vista.cambiar && vista.contador < 6) {
            vista.escena = new Escena1(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
            botones(vista.c);
        }
        regresoDeMenusYdeBatalla();
    }

    public void cambiarACreditos() {
        if (vista.cambiar) {
            vista.escena = new Creditos(vista.getContext(), vista, p);
            vista.cambiar = false;
        }
    }
    public void cambiarAOpciones(){
        if(vista.cambiar){
            vista.escena=new Opciones(vista.getContext(), vista,p);
            vista.cambiar=false;
        }
    }
    public void cambiarAescenaDos() {
        vista.guardarEscena = vista.escenas;
        vista.c.drawColor(Color.BLACK);
        if (vista.cambiar && vista.contador < 6) {
            vista.escena = new Escena2(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
            botones(vista.c);
        }
        regresoDeMenusYdeBatalla();
    }

    public void cambiarAescenaTres() {
        vista.comenzarBatalla = false;
        vista.guardarEscena = vista.escenas;
        vista.c.drawColor(Color.BLACK);
        if (vista.cambiar && vista.contador < 6) {
            vista.escena = new Escena3(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
        }
        regresoDeMenusYdeBatalla();
        botones(vista.c);
    }

    public void cambiarAescenaCuatro() {
        vista.comenzarBatalla = false;
        vista.guardarEscena = vista.escenas;
        vista.c.drawColor(Color.BLACK);
        if (vista.cambiar && vista.contador < 6) {
            vista.escena = new Escena4(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
        }
        regresoDeMenusYdeBatalla();
        botones(vista.c);
    }

    public void cambiarAescenaSeis() {
        vista.comenzarBatalla = false;
        vista.guardarEscena = vista.escenas;
        if (vista.cambiar && vista.contador == ESCENA6) {
            vista.escena = new Escena6(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
            vista.escenas = 6;
        }
        regresoDeMenusYdeBatalla();
    }

    public void cambiaraBatalla() {
        if (vista.escenas == BATALLA) {
            Log.i("Vida enemigo:", "" + vista.enemigo.vida);
            //Se pone la señal de la pulsación de los botones direccionales a false para que no haya ninguna interferencia con
            //con los botones de la batalla.
            vista.pulsadoDerecho = false;
            vista.pulsadoIzquierdo = false;
            vista.pulsadoArriba = false;
            vista.pulsadoAbajo = false;
            if (vista.cambiar) {
                vista.escena = new Batalla(vista.getContext(), vista, vista.enemigo, vista.p);
                vista.deBatalla = true;
                vista.cambiar = false;
            }
        }
    }

    public void batallaFinal() {
        if (vista.escenas == BATALLAFINAL) {
            //Se pone la señal de la pulsación de los botones direccionales a false para que no haya ninguna interferencia con
            //con los botones de la batalla.
            vista.pulsadoDerecho = false;
            vista.pulsadoIzquierdo = false;
            vista.pulsadoArriba = false;
            vista.pulsadoAbajo = false;
            vista.deBatalla = false;
            if (vista.cambiar) {
                Log.i("cambio a batalla", "cambio a batalla");
                vista.escena = new BatallaFinal(vista.getContext(), vista, vista.boss, vista.p);
                vista.cambiar = false;
                vista.deBatalla = true;
            }
        }
    }

    public void cambiarAMenuGuardar() {
        if (vista.cambiar) {
            vista.escena = new MenuGuardar(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
        }
    }

    public void cambiarAGrupo() {
        if (!vista.comenzarBatalla && vista.cambiar) {
            vista.escena = new Grupo(vista.getContext(), vista, guerrero, mago, barbaro, vista.p);
            vista.cambiar = false;
        }
    }

    public void cambiarAAyuda() {
        if (vista.cambiar) {
            vista.escena = new Ayuda(vista.getContext(), vista, p);
            vista.cambiar = false;
        }
    }

    public void cambiarAAyuda2() {
        if (vista.cambiar) {
            vista.escena = new Ayuda2(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
        }
    }

    public void cambiarAAyuda3() {
        if (vista.cambiar) {
            vista.escena = new Ayuda3(vista.getContext(), vista, p);
            vista.cambiar = false;
        }
    }

    public void cambiarAAyuda4() {
        if (vista.cambiar) {
            vista.escena = new Ayuda4(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
        }
    }

    public void cambiarAMenuInicial() {
        if (vista.comenzado) {
            vista.escena = new MenuInicial(vista.getContext(), vista, vista.p);
            vista.comenzado = false;
        }

    }

    public void cambiarAEscenaSiete() {
        vista.comenzarBatalla = false;
        vista.guardarEscena = vista.escenas;
        vista.c.drawColor(Color.BLACK);
        if (vista.cambiar) {
            vista.contador++;
            vista.escena = new Escena7(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
        }
        botones(vista.c);
        regresoDeMenusYdeBatalla();
    }

    public void cambiarAEscenaFinal() {
        if (vista.cambiar && vista.escenas == 9) {

            vista.escena = new Escena9(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
        }
    }

    public void cambiarAEscenaDiez() {
        if (vista.cambiar && vista.escenas == 10) {
            vista.escena = new Escena10(vista.getContext(), vista, vista.p);
            vista.cambiar = false;
        }
    }

    /**
     * Función que Da el valor anterior al ser pulsados los botones de menu, grupo y al volver de una batalla, a las variables de posición
     * y de escena.
     */
    public void regresoDeMenusYdeBatalla() {

        if (vista.deMenu) {//Se da la señal de que se vuelve del menú.
            vista.escena.getPersonaje().posicion = new PointF(vista.posicionx, vista.posiciony);//Se carga la posición anterior a entrar el menú
            vista.escena.getPersonaje().imagen = vista.enElCambio;//Se recarla la escena anterior a entrar en el menú
            vista.deMenu = false;//Una vez producido el cambio se evita que se vuelva a entrar en el if
        }
        if (vista.deBatalla) {//Se comprueba que se sale del combate
            vista.escena.getPersonaje().posicion = new PointF(vista.posicionxb, vista.posicionyb);//Se recarga la posición del personaje anterior a la entrada en el combate
            vista.escena.getPersonaje().imagen = vista.enElCambio;//Se recarga la escena anterior a la entrada en el combate
            vista.deBatalla = false;//Una vez producido el cambio se evita volver a entrar en el if
            //Nos aseguramos de vaciar los valores de los textos que se escriben en el informe de batalla.
            vista.texto = "";
            vista.texto1 = "";

        }
        if (vista.deGuardado) {//Una vez dada la señal de que se regresa del menú de guardado
            vista.escena.getPersonaje().posicion = new PointF(vista.posicionx, vista.posiciony);//se recarga la posición del personaje anterior a la entrada en el menú de guardado
            vista.deGuardado = false;//nos aseguramos de que no se vuelva a entrar en este if
        }

    }

    /**
     * Función sobreescrita en Batalla y en BatallaFinal que se encarga de escribir el texto que informa al usuario
     * de el daño producido por el personaje que haya realizado el ataque. Se le pasa el canvas del Surface, que se encarga
     * de escribir el texto.
     *
     * @param c el canvas del SurfaceView.
     */
    synchronized public void informeDuranteLaBatalla(Canvas c) {
    }

    /**
     * Función usada en Batalla y BatallaFinal para determinar qué sucede una vez terminado el combate,
     * se sobreescribe en esas clases
     */
    public void trasElCombate() {

    }

    /**
     * Función que se encarga de cambiar la imagen del enemigo durante los combates y que se sobreescribe en Batalla y en BatallaFinal.
     */
    public void cambiarSpriteDuranteBatalla() {

    }
    /**
     * Función que se encarga de dibujar los botones direccionales y los de menu de grupo y menu de guardado. Se
     * le pasa el canvas del surface para que se encargue del dibujado de los mismos.
     *
     * @param c el canvas del SurfaceView.
     */
    synchronized public void botones(Canvas c) {

        botonderecho = new Imagen(BitmapFactory.decodeResource(vista.getResources(), R.drawable.botonderecho), vista.escena.getFondo().getWidth() + (int) (vista.escena.getFondo().getWidth() / 4.5), vista.escena.getFondo().getHeight() - (int) (vista.escena.getFondo().getHeight() * 0.57));
        botonizquierdo = new Imagen(BitmapFactory.decodeResource(vista.getResources(), R.drawable.botonizquierdo), vista.escena.getFondo().getWidth() + vista.escena.getFondo().getWidth() / 470, vista.escena.getFondo().getHeight() - (int) (vista.escena.getFondo().getHeight() * 0.57));
        botonabajo = new Imagen(BitmapFactory.decodeResource(vista.getResources(), R.drawable.btnabajo),vista.escena.getFondo().getWidth() + vista.escena.getFondo().getWidth() / 9, vista.escena.getFondo().getHeight() - vista.escena.getFondo().getHeight() / 3);
        botonarriba = new Imagen(BitmapFactory.decodeResource(vista.getResources(), R.drawable.btnarr), vista.escena.getFondo().getWidth() + vista.escena.getFondo().getWidth() / 9, vista.escena.getFondo().getHeight() - (int) (vista.escena.getFondo().getHeight() * 0.8));
        grupo = new Imagen(BitmapFactory.decodeResource(vista.getResources(), R.drawable.entrarengrupo), vista.escena.getFondo().getWidth() - vista.escena.getFondo().getWidth() / 650, vista.escena.getFondo().getHeight() / 50);
        salir =new Imagen(BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark20), vista.escena.getFondo().getWidth() + vista.escena.getFondo().getWidth() / 3, vista.escena.getFondo().getHeight() / 50);
        botonderecho.onDraw(c);
        botonizquierdo.onDraw(c);
        botonabajo.onDraw(c);
        botonarriba.onDraw(c);
        grupo.onDraw(c);
        salir.onDraw(c);
        guardar = new Imagen(BitmapFactory.decodeResource(vista.getResources(), R.drawable.flatdark33), vista.escena.getFondo().getWidth() + vista.escena.getFondo().getWidth() / 6, vista.escena.getFondo().getHeight() / 50);
        guardar.onDraw(c);
    }

    /**
     * Función genérica que se encarga de gestionar los turnos durante los combates, se usa tanto en Batalla como en BatallaFinal.
     * @param x la posición x del evento registrado en el onTouchEvent.
     * @param y la posición y del evento registrado en el onTouchEvent.
     * @param enemigo El enemigo que se le pasa, en el caso de Batalla se le pasa el enemigo genérico, y en el caso de ser la BatallaFinal se le pasa el boss.
     */
    public void gestionDeTurnos(float x, float y, Enemigo enemigo) {

        if (turnopersonaje1) {//En el caso de ser el turno del personaje guerrero.
            Log.i("turnopersonaje1", "1");
            if (this instanceof Batalla ? ((Batalla) (this)).tocarBotonAtaquePersonaje1(x, y) : ((BatallaFinal) (this)).tocarBotonAtaquePersonaje1(x, y)) {//Si se pulsa el botón de ataque del guerrero tanto de Batalla como de BatallaFinal.
                vista.ataqueDeMago=false;
                if (enemigo.vida > 0) {//Se la vida es superior a 0 se realiza el ataque
                    vista.sonido.ataquearma.start();
                    vista.dano = vista.guerrero.atacar();//Se introduce el valor del ataque del guerrero dentro de la variable daño del Surface, para su uso en el informe de batalla.
                    enemigo.vida -= vista.dano;//Se utiliza el valor de la variable daño con el valor capturado de la función ataque del guerrero y se le resta a la vida del enemigo
                    vista.atacar = true;//Se da el aviso para que se escriba el informe de batalla.
                    turnopersonaje1 = false;//Se pone el turno del guerrero a false.
                    if (vista.barbaro.vida == 0 && vista.mago.vida == 0) {//Si la vida del mago y del guerrero es 0 el turno pasa directamente al enemigo.
                        turnoenemigo = true;
                    } else if (vista.mago.vida > 0) {//Si la vida del mago es mayor a 0 el turno pasa al mago
                        turnopersonaje2 = true;
                    } else if (vista.barbaro.vida > 0 && vista.mago.vida == 0) {// Si la vida del mago es 0 y la del bárbaro mayor a 0, el turno pasa al bárbaro.
                        turnopersonaje3 = true;
                    }
                }
            }
        }
        if (turnopersonaje2) {//Si es el turno del mago.
            vista.ataqueDeMago=false;
            if (this instanceof Batalla ? ((Batalla) (this)).tocarBotonAtaquePersonaje2(x, y) : ((BatallaFinal) (this)).tocarBotonAtaquePersonaje2(x, y)) {//Se comprueba que se ha pulsado el botón de ataque del mago tanto de Batalla como de BatallFinal.
                if (enemigo.vida > 0) {//Lo mismo que en el caso del guerrero.
                    vista.dano = vista.mago.atacar();
                    enemigo.vida -= vista.dano;
                    vista.atacar = true;
                    turnopersonaje2 = false;
                    vista.ataqueDeMago=true;
                    quehechizo=generaNumeroAleatorio(1,2);
                    if(quehechizo==2){
                        vista.sonido.ataquehielo.start();
                    }else if(quehechizo==1){
                        vista.sonido.ataqueFuego.start();
                    }
                    if (vista.barbaro.vida == 0 && vista.guerrero.vida == 0) {//Si la vida del bárbaro y del guerrero son igual a cero el turno pasa al enemigo.
                        turnoenemigo = true;
                    } else if (vista.barbaro.vida == 0 && vista.guerrero.vida > 0) {//Si la vida del bárbaro es igual a cero pasa el turno al enemigo.
                        turnoenemigo = true;
                    } else {//Si el bárbaro está vivo pasa pasa a él el turno.
                        turnopersonaje3 = true;
                    }
                }
            }
        }
        if (turnopersonaje3) {//Si es el turno del guerrero.

            if (this instanceof Batalla ? ((Batalla) (this)).tocarBotonAtaquePersonaje3(x, y) : ((BatallaFinal) (this)).tocarBotonAtaquePersonaje3(x, y)) {//Se comprueba que se ha pulsado el botón de ataque del bárbaro tanto dentro de Batalla como de BatallaFinal.

                vista.ataqueDeMago=false;
                if (enemigo.vida > 0) {//Lo mismo que en el caso del guerrero.
                    vista.sonido.ataquearma.start();
                    vista.dano = vista.barbaro.atacar();
                    enemigo.vida -= vista.dano;
                    vista.atacar = true;
                    turnopersonaje3 = false;
                    turnoenemigo = true;
                    if (enemigo.vida == 0 && vista.guerrero.vida > 0) {
                        turnopersonaje1 = true;
                    } else if (enemigo.vida == 0 && vista.mago.vida > 0) {
                        turnopersonaje2 = true;
                    }
                }

            }
        }
        if (enemigo.vida > 0 && turnoenemigo) {//Este es el turno del enemigo.
            vista.ataqueDeMago=false;
            personaje = generaNumeroAleatorio(1, 3);//Se genera un número aleatorio entre 1 y 3 que determina a que personaje atacará el enemigo.
            if (vista.guerrero.vida == 0 && vista.barbaro.vida == 0) {//Si la vida del bárbaro y del guerrero son igual a cero se atacará unicamente al mago
                personaje = 2;
            } else if (vista.mago.vida == 0 && vista.barbaro.vida == 0) {//Si la vida del bárbaro y del mago son igual a cero se ataca al guerrero
                personaje = 1;
            } else if (vista.mago.vida == 0 && vista.guerrero.vida == 0) {//Si la vida del mago y del guerrero son igual a cero se ataca al bárbaro
                personaje = 3;
            } else if (vista.barbaro.vida == 0) {//Si únicamente la vida del bárbaro es igual a cero se genera un número aleatorio entre 1 y 2 (guerrero, mago).
                personaje = generaNumeroAleatorio(1, 2);
            } else if (vista.mago.vida == 0) {//Si la vida del mago es igual a cero se genera un numero entre 4 y 5(guerrero, bárbaro).
                personaje = generaNumeroAleatorio(4, 5);
            }
            if (personaje == 1 || personaje == 4 && vista.guerrero.vida > 0 && vista.enemigo.vida > 0) {//si el número es uno o cuatro se procede a restarle la vida al guerrero.
                vista.nombre = vista.guerrero.nombre;//Se guarda en la variable nombre de la vista el nombre del guerrero para usarlo en el informe de batalla.
                if (this instanceof Batalla) {//Si la escena es instancia de Batalla se realiza el ataque del enemigo común.
                    vista.danoa = enemigo.atacar();
                } else if (this instanceof BatallaFinal) {//Si lo es de BatallaFinal se realiza el ataque del boss.
                    vista.danoa = enemigo.atacar2();
                }
                vista.guerrero.dano(vista.danoa);//Se le quita la vida al guerrero.
            } else if (personaje == 2 && vista.mago.vida > 0 && vista.enemigo.vida > 0) {//Igual que con el guerrero
                vista.nombre = vista.mago.nombre;
                if (this instanceof Batalla) {
                    vista.danoa = enemigo.atacar();
                } else if (this instanceof BatallaFinal) {
                    vista.danoa = enemigo.atacar2();
                }
                vista.mago.dano(vista.danoa);

            } else if (personaje == 3 || personaje == 5 && vista.barbaro.vida > 0 && vista.enemigo.vida > 0) {//Igual que con el guerrero.
                vista.nombre = vista.barbaro.nombre;
                if (this instanceof Batalla) {
                    vista.danoa = enemigo.atacar();
                } else if (this instanceof BatallaFinal) {
                    vista.danoa = enemigo.atacar2();
                }
                vista.barbaro.dano(vista.danoa);
            }
            vista.atacanemigo = true;//Se pone la variable ataque enemigo a true para que se escriba su ataque en el informe de batalla.
            if (vista.guerrero.vida > 0) {//Si la vida del guerrero es superior a cero pasa a él el turno
                turnopersonaje1 = true;
            } else if (vista.mago.vida > 0) {//Si resulta que la vida del guerrero es igual a cero y la vida del mago es mayor a cero pasa el turno al mago.
                turnopersonaje2 = true;
            } else if (vista.barbaro.vida > 0) {//Si por el contrario la vida del guerrero y del mago son igual a cero pasa el turno al bárbaro.
                turnopersonaje3 = true;
            }
            turnoenemigo = false;//Se pone el turno del enemigo a false, para evitar que realice ningún ataque.
        }
    }
}
