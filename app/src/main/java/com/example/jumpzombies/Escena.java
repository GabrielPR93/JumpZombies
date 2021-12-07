package com.example.jumpzombies;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Escena {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    public int numEscena=-1;
    /**
     * altoPantalla nos indica el tamaño del alto de la pantalla
     */
    public int altoPantalla;
    /**
     * anchoPantalla nos indica el tamaño del ancho de la pantalla
     */
    public int anchoPantalla;
    /**
     * btnMenu es el botón de volver al menú
     */
    public Botones btnMenu;
    /**
     * context es el contexto de la aplicación
     */
    public Context context;
    /**
     * p es el pincel utilizado para escribir el título de las escenas
     */
    public Paint p;
    /**
     * p2 es el pincel utilizado para escribir el contenido de las escenas
     */
    public Paint p2;
    /**
     * txt es el tipo de letra utilizado por el pincel p
     */
    public Typeface txt;
    /**
     * txt2 es el tipo de letra utilizado por el pincel p2
     */
    public Typeface txt2;
    /**
     * base es el fondo de la escena
     */
    public Fondo base;
    /**
     * fondo es el fondo que utilizamos por encima del fondo base
     */
    public Fondo fondo;
    /**
     * mediaplayer2 es el reproductor que utilizamos para la musica del menú
     */
    public MediaPlayer mediaPlayer2;
    /**
     *flagMusicaParada nos indica si la música está parada
     */
    public boolean flagMusicaParada=false;
    /**
     * personajePrincipal es él personaje principal del juego
     */
    public Personaje personajePrincipal;
    /**
     * vida1 es la vida número uno
     */
    public Vidas vida1;
    /**
     * vida2 es la vida número dos
     */
    public Vidas vida2;
    /**
     * vida3 es la vida número tres
     */
    public Vidas vida3;
    /**
     * enemigoV1 es el enemigo de tipo uno
     */
    public Enemigo enemigoV1;
    /**
     * enemigoV2 es el enemigo de tipo dos
     */
    public Enemigo enemigoV2;
    /**
     * enemigoV3 es el enemigo de tipo tres
     */
    public Enemigo enemigoV3;
    /**
     * enemigosV1 es un arraylist de los enemigos de tipo uno
     */
    public ArrayList<Enemigo> enemigosV1;
    /**
     * enemigosV2 es un arraylist de los enemigos de tipo dos
     */
    public ArrayList<Enemigo> enemigosV2;
    /**
     * enemigosV3 es un arraylist de los enemigos de tipo tres
     */
    public ArrayList<Enemigo> enemigosV3;
    /**
     * contPuntos es un contador de puntos
     */
    public int contPuntos;
    /**
     * cambiaEscenaDerrota nos indica cuando se cambia de escena al perder
     */
    public boolean cambiaEscenaDerrota =false;
    /**
     * cambiaEscenaVictoria nos indica cuando se cambia de escena al ganar
     */
    public boolean cambiaEscenaVictoria =false;

    /**
     * Constructor donde se inicializan varios atributos y se definen las propiedades tanto de los pinceles como del reproductor
     * También se establecen las imagenes del fondo y del boton del menú
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encontramos
     */
    public Escena(Context context, int anchoPantalla, int altoPantalla, int numEscena) {

        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;
        this.context = context;
        this.numEscena=numEscena;
        p=new Paint();
        p2=new Paint();
        txt= Typeface.createFromAsset(context.getAssets(),"font/zombiecontrol.ttf");
        txt2= Typeface.createFromAsset(context.getAssets(),"font/stranger.ttf");
        p.setTextAlign(Paint.Align.CENTER);
        p.setTypeface(txt);
        p2.setTypeface(txt2);
        p.setColor(Color.BLACK);
        p2.setColor(Color.BLACK);
        p2.setTextSize(180);
        p.setTextSize(100);
        mediaPlayer2=MediaPlayer.create(context,R.raw.inicial);
        mediaPlayer2.setVolume(50,50);
        mediaPlayer2.setLooping(true);
        base=new Fondo(escalaAnchura(getBitmapFromAssets("Botones/base.png"),anchoPantalla-anchoPantalla*2/10,altoPantalla-(altoPantalla*1/10)),anchoPantalla/8,altoPantalla/14,0,-1);
        fondo=new Fondo(escalaAnchura(getBitmapFromAssets("Fondo/fondoMenu/fondomenu.png"),anchoPantalla,altoPantalla),0,0,0,-1);
        btnMenu=new Botones(escalaAnchura(getBitmapFromAssets("Botones/menu.png"),anchoPantalla/15,anchoPantalla/15),anchoPantalla/30,altoPantalla/30);

    }

    /**
     * Método que se le pasa una ruta y te devuelve el bitmap de dicha ruta
     * @param fichero La ruta
     * @return Un bitmap
     */
    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is=context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * Método que se le pasa un bitmap y dos enteros y redimensiona la imagen a ese ancho y alto
     * @param bitmapAux El bitmap
     * @param nuevoAncho Nuevo ancho
     * @param alto  Nuevo alto
     * @return devuelve el bitmap redimensionado
     */
    public Bitmap escalaAnchura(Bitmap bitmapAux, int nuevoAncho, int alto) {
        if (nuevoAncho==bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, alto, true);
    }

    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c){
        fondo.dibuja(c);
        if (numEscena!=-1 && numEscena!=10 && numEscena!=7 && numEscena!=8){
            btnMenu.dibuja(c);
            base.dibuja(c);

        }
    }

    /**
     * Metodo donde se actualizan los valores de los diferentes elementos en las escenas
     */
    public void actualizaFisica(){

    }

    /**
     * Método encargado de gestionar las pulsaciones en la escena
     * @param event evento
     * @return número de escena
     */
    int onTouchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();

        if (numEscena!=1 && numEscena!=7 &&numEscena!=10)  {
            if (x>btnMenu.getPosicionX() && x<btnMenu.getPosicionX()+ btnMenu.getImagen().getWidth() && y>btnMenu.getPosicionY() && y<btnMenu.getPosicionY()+btnMenu.getImagen().getHeight())return 1;
        }

        return -1;
    }
}
