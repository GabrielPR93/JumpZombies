package com.example.jumpzombies;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;


public class Game extends SurfaceView implements SurfaceHolder.Callback {
    /**
     * surfaceHolder es el objeto que gestiona la superficie
     */
    public SurfaceHolder surfaceHolder;
    /**
     * altoPantalla es el tamaño del alto de la pantalla
     */
    public static int altoPantalla;
    /**
     * anchoPantalla es el tamaño del ancho de la pantalla
     */
    public static int anchoPantalla;
    /**
     * hilo es un hilo encargado de dibujar y actualizar la física
     */
    public Hilo hilo;
    /**
     * context es el contexto de la aplicación
     */
    public Context context;
    /**
     * escenaActual es el numero de escena actual
     */
    public Escena escenaActual;
    /**
     * nuevaEscena es el numero de la nueva escena
     */
    public int  nuevaEscena;
    /**
     * funcionando nos indica si el hilo está funcionando
     */
    public boolean funcionando=true;
    /**
     * mediaPlayer es el reproductor de la música del juego
     */
    public MediaPlayer mediaPlayer;
    /**
     * mediaPlayer2 es el reproductor de la música del menú
     */
    public MediaPlayer mediaPlayer2;
    /**
     * posicionMusica es la posición de la música del juego cuando se pausa
     */
    public int posicionMusica;
    /**
     * posicionMusica2 es la posición de la música del menú cuando se pausa
     */
    public int posicionMusica2;
    /**
     * flagSonido nos indica si los sonidos están activados o no
     */
    public boolean flagSonido;
    /**
     * flagMusica nos indica si las músicas están activadas o no
     */
    public boolean flagMusica;
    /**
     * flagVibracion nos indica si la vibración está activada o no
     */
    public boolean flagVibracion;
    /**
     * flagIdioma nos indica si el español está activado o no
     */
    public boolean flagIdioma;
    /**
     * flagMusicaParada nos indica si la música está parada
     */
    public boolean flagMusicaParada=true;
    /**
     * audioManager es el reproductor de sonidos
     */
    public AudioManager audioManager;


    /**
     * Constructor donde se inicializan varios atributos y se instacia el hilo
     * También se lee la configuración y se inicia la música
     * Inicializa el audioManager y el mediaPlayer
     * @param context el contexto de la aplicación
     */
    public Game(Context context) {
        super(context);
        this.surfaceHolder=getHolder();
        this.context=context;
        this.surfaceHolder.addCallback(this);
        hilo = new Hilo();
        audioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer=MediaPlayer.create(context,R.raw.musica);
        int v= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer.setVolume(v/2,v/2);
        mediaPlayer.setLooping(true);
        posicionMusica=0;

        mediaPlayer2=MediaPlayer.create(context,R.raw.inicial);
        mediaPlayer2.setVolume(v/2,v/2);
        mediaPlayer2.setLooping(true);
        posicionMusica2=0;
        leerConfiguracion();
        musica();
    }
    /**
     * Metodo que gestiona el play/pause de la musica
     */
    public void musica(){
        if (flagMusica) {
         mediaPlayer2.start();
        } else {
            mediaPlayer2.pause();
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    /**
     * Método que se ejecuta inmediatamente después de que la superficie de dibujo tenga cambios o bien de tamaño o bien de forma
     * @param holder holder
     * @param format formato
     * @param width Ancho
     * @param height Alto
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.altoPantalla=height;
        this.anchoPantalla=width;
        escenaActual=new Escena1(context,anchoPantalla,altoPantalla,1);
        cambioEscena(nuevaEscena);

        if (hilo.getState() == Thread.State.NEW) hilo.start();
        if (hilo.getState() == Thread.State.TERMINATED) {
            hilo=new Hilo();
            hilo.start();
        }
    }

    /**
     * Metodo que gestiona si la música suena o no dependiendo de la configuracion guardada
     */
    public void actualizaFisica() {
        leerConfiguracion();
            if(flagMusicaParada){
                if(flagMusica){
                    if(!mediaPlayer2.isPlaying()){
                        mediaPlayer2.start();
                    }
                }else{
                    if(mediaPlayer2.isPlaying()){
                        mediaPlayer2.pause();
                    }
                }
            }else{
                mediaPlayer2.pause();
            }
    }

    /**
     *  Método que se ejecuta inmediatamente antes de la destrucción de la superficie de dibujo
     * @param holder el holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.funcionando=false;
        mediaPlayer2.stop();
        try {
            hilo.join();

        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo que gestiona el cambio de escena dependiendo en cual pulsemos
     * @param event
     * @return true al gestionar la pulsacion
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
            nuevaEscena=escenaActual.onTouchEvent(event);
            cambioEscena(nuevaEscena);

    return  true;
    }
    /**
     * Metodo que lee la configuracion guardada
     */
    public void leerConfiguracion(){
        SharedPreferences preferencias = context.getSharedPreferences("configuracion",Context.MODE_PRIVATE);

        flagMusica=preferencias.getBoolean("musica",true);
        flagSonido=preferencias.getBoolean("sonido",true);
        flagVibracion=preferencias.getBoolean("vibracion",true);
        flagIdioma=preferencias.getBoolean("idioma",true);
    }

    /**
     * Metodo que pasandole un numero de escena te lleva a la escena correspondiente
     * @param cambiaEscena numero de escena
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cambioEscena(int cambiaEscena){
        if (escenaActual.numEscena!=nuevaEscena){
            switch (nuevaEscena){
                case 1: escenaActual=new Escena1(context, anchoPantalla, altoPantalla,1);
                if(!mediaPlayer2.isPlaying()){
                    if(flagSonido){
                        flagMusicaParada=true;
                        mediaPlayer2.start();
                    }
                }
                break;
                case 2: escenaActual=new Escena2(this.context, anchoPantalla, altoPantalla,2);break;
                case 3: escenaActual=new Escena3(this.context, anchoPantalla, altoPantalla,3); break;
                case 4: escenaActual=new Escena4(this.context, anchoPantalla, altoPantalla,4); break;
                case 5: escenaActual=new Escena5(this.context, anchoPantalla, altoPantalla,5); break;
                case 6: escenaActual=new Escena6(this.context, anchoPantalla, altoPantalla,6); break;
                case 7: escenaActual=new Escena7(this.context, anchoPantalla, altoPantalla,7);

                break;
                case 8: escenaActual=new Escena8(this.context, anchoPantalla, altoPantalla,8);
                break;
                case 10:escenaActual=new Escena10(this.context, anchoPantalla, altoPantalla,10);
                flagMusicaParada=false;
                mediaPlayer2.pause();

                break;
                case 11: escenaActual=new Escena11(this.context, anchoPantalla, altoPantalla,11); break;

            }
        }
    }


    public class Hilo extends Thread{
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void run() {
        super.run();
        Canvas c=null;
        while (funcionando){
            c=null;
            try {
                if (!surfaceHolder.getSurface().isValid()) continue;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    c = surfaceHolder.lockHardwareCanvas();
                }
                if (c == null) c = surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {
                        actualizaFisica();
                        escenaActual.actualizaFisica();
                        if(escenaActual.cambiaEscenaDerrota){
                            escenaActual=new Escena7(context, anchoPantalla, altoPantalla,7);
                        }else if(escenaActual.cambiaEscenaVictoria){
                            escenaActual=new Escena8(context, anchoPantalla, altoPantalla,8);
                        }
                        escenaActual.dibuja(c);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try {
                    surfaceHolder.unlockCanvasAndPost(c);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    }


}

