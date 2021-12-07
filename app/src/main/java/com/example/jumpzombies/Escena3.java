package com.example.jumpzombies;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

public class Escena3 extends Escena {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    public int numEscena=3;
    /**
     * btnMusica es el botón que nos indica que la música está activada
     */
    public Botones btnMusica;
    /**
     * btnMusicaOff es el botón que nos indica que la música está desactivada
     */
    public Botones btnMusicaOff;
    /**
     * btnVibracion es el botón que nos indica que la vibración está activada
     */
    public Botones btnVibracion;
    /**
     * btnVibracionOff es el botón que nos indica que la vibración está desactivada
     */
    public Botones btnVibracionOff;
    /**
     * btnSonido es el botón que nos indica que los sonidos del juego están activados
     */
    public Botones btnSonido;
    /**
     * btnSonidoOff es el botón que nos indica que los sonidos del juego están desactivados
     */
    public Botones btnSonidoOff;
    /**
     * btnIdioma es el botón que nos indica que el idioma español está activado
     */
    public Botones btnIdioma;
    /**
     * btnIdiomaOff es el botón que nos indica que el idioma español está desactivado
     */
    public Botones btnIdiomaOff;
    /**
     * flagSonido nos indica si los sonidos están activados o no
     */
    public boolean flagSonido;
    /**
     * flagMusica nos indica si la música está activada o no
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
     * Constructor donde se inicializan varios atributos y se cargan las imagenes correspondientes para los botones
     * También lee la configuración guardada
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encontramos
     */
    public Escena3(Context context, int anchoPantalla, int altoPantalla, int numEscena) {
        super(context, anchoPantalla, altoPantalla, numEscena);
        this.numEscena=numEscena;
        cargaRecursos();
        leerConfiguracion();

    }
    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c){

        super.dibuja(c);
        if(flagMusica){
            btnMusica.dibuja(c);
        }else{
            btnMusicaOff.dibuja(c);
        }
        if(flagSonido){
            btnSonido.dibuja(c);
        }else{
            btnSonidoOff.dibuja(c);
        }
        if(flagVibracion){
            btnVibracion.dibuja(c);
        }else{
            btnVibracionOff.dibuja(c);
        }
        if(flagIdioma){
            btnIdioma.dibuja(c);
        }else{
            btnIdioma.dibuja(c);
        }
        c.drawText("Configuracion ",anchoPantalla/2, altoPantalla/4,p);
        c.drawText("Música ",(int)(anchoPantalla/3.5), (int)(altoPantalla/2.7),p2);
        c.drawText("Sonido ",(int)(anchoPantalla/1.5), (int)(altoPantalla/2.7),p2);
        c.drawText("Vibración ",(int)(anchoPantalla/3.5), (int)(altoPantalla/1.5),p2);
        c.drawText("Idioma ",(int)(anchoPantalla/1.5), (int)(altoPantalla/1.5),p2);
        c.drawText("´",(int)(anchoPantalla/1.7), (int)(altoPantalla/4.5),p2);

    }

    /**
     * Método encargado de cargar los recursos necesarios y escalarlos
     */
    public void cargaRecursos(){

        btnMusica=new Botones(escalaAnchura(getBitmapFromAssets("Botones/sound.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/3.5),(int)(altoPantalla/2.7));
        btnMusicaOff=new Botones(escalaAnchura(getBitmapFromAssets("Botones/sound_off.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/3.5),(int)(altoPantalla/2.7));
        btnSonido=new Botones(escalaAnchura(getBitmapFromAssets("Botones/music.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/1.5),(int)(altoPantalla/2.7));
        btnSonidoOff=new Botones(escalaAnchura(getBitmapFromAssets("Botones/music_off.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/1.5),(int)(altoPantalla/2.7));
        btnVibracion=new Botones(escalaAnchura(getBitmapFromAssets("Botones/movil.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/3.5),(int)(altoPantalla/1.5));
        btnVibracionOff=new Botones(escalaAnchura(getBitmapFromAssets("Botones/movilSinVibracion.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/3.5),(int)(altoPantalla/1.5));
        btnIdioma=new Botones(escalaAnchura(getBitmapFromAssets("Botones/español.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/1.5),(int)(altoPantalla/1.5));
        btnIdiomaOff=new Botones(escalaAnchura(getBitmapFromAssets("Botones/ingles.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/1.5),(int)(altoPantalla/1.5));

    }


    /**
     * Método encargado de gestionar las pulsaciones en la escena
     * @param event evento
     * @return número de escena
     */
    int onTouchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();
        int aux=super.onTouchEvent(event);
        if (aux!=this.numEscena && aux!=-1) return aux;
        int accion=event.getAction();

        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (x > btnMusica.getPosicionX() && x < btnMusica.getPosicionX() + btnMusica.getImagen().getWidth() && y > btnMusica.getPosicionY() && y < btnMusica.getPosicionY() + btnMusica.getImagen().getHeight()) {
                    flagMusica = !flagMusica;
                }
                if (x > btnSonido.getPosicionX() && x < btnSonido.getPosicionX() + btnSonido.getImagen().getWidth() && y > btnSonido.getPosicionY() && y < btnSonido.getPosicionY() + btnSonido.getImagen().getHeight()) {
                    flagSonido = !flagSonido;
                }
                if (x > btnVibracion.getPosicionX() && x < btnVibracion.getPosicionX() + btnVibracion.getImagen().getWidth() && y > btnVibracion.getPosicionY() && y < btnVibracion.getPosicionY() + btnVibracion.getImagen().getHeight()) {
                    flagVibracion = !flagVibracion;
                }
                if (x > btnIdioma.getPosicionX() && x < btnIdioma.getPosicionX() + btnIdioma.getImagen().getWidth() && y > btnIdioma.getPosicionY() && y < btnIdioma.getPosicionY() + btnIdioma.getImagen().getHeight()) {
                    flagIdioma = !flagIdioma;
                }

                guardarConfiguracion();
        }

        return this.numEscena;

    }

    /**
     * Método encargado de guardar la configuración
     */
    public void guardarConfiguracion(){
        SharedPreferences preferencias = context.getSharedPreferences("configuracion",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=preferencias.edit();
        editor.putBoolean("musica",flagMusica);
        editor.putBoolean("sonido",flagSonido);
        editor.putBoolean("vibracion",flagVibracion);
        editor.putBoolean("idioma",flagIdioma);
        editor.commit();

    }

    /**
     * Método encargado de leer la configuración
     */
    public void leerConfiguracion(){
        SharedPreferences preferencias = context.getSharedPreferences("configuracion",Context.MODE_PRIVATE);

        flagMusica=preferencias.getBoolean("musica",true);
        flagSonido=preferencias.getBoolean("sonido",true);
        flagVibracion=preferencias.getBoolean("vibracion",true);
        flagIdioma=preferencias.getBoolean("idioma",true);
    }
}
