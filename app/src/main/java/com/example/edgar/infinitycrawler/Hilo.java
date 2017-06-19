package com.example.edgar.infinitycrawler;

import android.graphics.Canvas;

/**
 * <p>Clase de que hereda de Thread que mantiene la rutina de dibujado en funcionamiento.</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class Hilo extends Thread {
    /**
     * Variable encargada de poner en pausa el hilo cuando el Main está en su estado onPause.
     */
    private boolean esperar;
    /**
     * Variable de tipo Surface encargada de cargar los elementos del lienzo definido para
     * poner en marcha su función de dibujado.
     */
    private Surface view;
    /**
     * Variable encargada de gestionar el funcionamiento del hilo.
     */
    private boolean funcionando = false;


    public Hilo(Surface view) {

        this.view = view;
    }

    /**
     * Función encargada de poner en funcionamiento el hilo desde el SurfaceView.
     *
     * @param funcionando variable que pone en funcionamiento el hilo en caso de estar a true.
     */
    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }


    @Override

    public void run() {
        if (esperar) {
            while (esperar) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            while (funcionando) {
                Canvas c = null;
                try {
                    c = view.getHolder().lockCanvas();
                    synchronized (view.getHolder()) {
                        view.onDraw(c);
                    }

                } finally {
                    if (c != null) {
                        view.getHolder().unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }

    /**
     * Función que se encarga, al ser llamada desde el Main a través del SurfaceView de pausar el hilo.
     */
    public void enPausa() {
        synchronized (this) {
            esperar = true;
        }
    }

    /**
     * Función que pone en reactiva el hilo una vez que se vuelve a usar la aplicación, su funcionamiento es similar
     * al de la función enPausa, solo que esta vez en el Main se la llama desde el onResume.
     */
    public void resumir() {
        synchronized (this) {
            esperar = false;
        }
    }

}
