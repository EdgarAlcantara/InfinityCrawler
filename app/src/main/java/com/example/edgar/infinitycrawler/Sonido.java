package com.example.edgar.infinitycrawler;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * <p>Clase específica que se encarga de gestionar el sonido a lo largo del juego.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Sonido {
    /**
     * Array de MediaPlayer en el que se carga la musica al comenzar la aplicación.
     */
    MediaPlayer[] players;
    /**
     * Variable MediaPlayer que se usa específicamente para el efecto de sonido que corresponde al pulsado de un botón.
     */
    MediaPlayer pulsadoBoton;
    /**
     * Sonido que se reproduce cuando ataque un personaje.
     */
    MediaPlayer ataquearma;
    /**
     * Sonido de ataque de fuego
     */
    MediaPlayer ataqueFuego;
    /**
     * Sonido de ataque de hielo
     */
    MediaPlayer ataquehielo;
    /**
     * Objectos Surface y Context que se cargan en el contructor con los valores del Surface principal.
     */
    Surface surface;
    /**
     * El contexto del SurfaceView.
     */
    Context context;
    /**
     * Sonido de cuando se sube de nivel
     */
    MediaPlayer subirNivel;
    public Sonido(Surface surface, Context context) {
        this.surface = surface;
        this.context = context;
        pulsadoBoton = MediaPlayer.create(context, R.raw.clic10);
        ataquearma=MediaPlayer.create(context,R.raw.knifesharpener1);
        ataquehielo=MediaPlayer.create(context,R.raw.freeze);
        ataqueFuego=MediaPlayer.create(context,R.raw.boladefuego);
        subirNivel=MediaPlayer.create(context,R.raw.snare);
        arrayDeMusica();
    }

    /**
     * Método en el que se inicializa el array "player".
     */
    public void arrayDeMusica() {
        players = new MediaPlayer[]{
                MediaPlayer.create(context, R.raw.littletownorchestalmidi),
                MediaPlayer.create(context, R.raw.qubodup),
                MediaPlayer.create(context, R.raw.darkambienceloop)
        };
    }

    /**
     * Metodo que se llama en el onDraw del surface y que reproduce la musica que corresponde con la escena en la que nos encontramos.
     */
    public void gestionDeSonido() {
        if (surface.escenas == 0) {
            if (surface.sonido.players[1].isPlaying()) {
                surface.sonido.players[1].pause();
                surface.sonido.players[0].start();
            } else {
                surface.sonido.players[0].start();
            }

        } else if (surface.escenas >= 1 && surface.escenas < 21 && surface.escenas != 8) {
            if (!surface.sonido.players[1].isPlaying() || surface.sonido.players[2].isPlaying()) {
                surface.sonido.players[2].pause();
                surface.sonido.players[0].pause();
                surface.sonido.players[1].start();

            }else if(surface.sonido.players[0].isPlaying()){
                surface.sonido.players[0].pause();
                surface.sonido.players[1].start();

            }else{
                if(!surface.sonido.players[1].isPlaying()) {
                    surface.sonido.players[1].start();
                }
            }
        } else if (surface.escena instanceof Batalla || surface.escena instanceof BatallaFinal) {
            /*if (!surface.sonido.players[2].isPlaying()) {
                surface.sonido.players[1].pause();

                surface.sonido.players[2] = new MediaPlayer();
                try {
                    surface.sonido.players[2] = MediaPlayer.create(surface.getContext(), R.raw.darkambienceloop);
                } catch (NullPointerException e) {

                }
                surface.sonido.players[2].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer mp) {
                        surface.sonido.players[2].start();
                    }
                });
            }*/
            if(surface.sonido.players[1].isPlaying()&&!surface.sonido.players[2].isPlaying()){
                surface.sonido.players[1].pause();
                surface.sonido.players[2] = new MediaPlayer();
                try {
                    surface.sonido.players[2] = MediaPlayer.create(surface.getContext(), R.raw.darkambienceloop);
                } catch (NullPointerException e) {

                }
                surface.sonido.players[2].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer mp) {
                        surface.sonido.players[2].start();
                    }
                });
            }else if(!surface.sonido.players[2].isPlaying()){
                surface.sonido.players[2].start();
            }

        }
    }


}
