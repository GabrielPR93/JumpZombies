package com.example.jumpzombies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;

public class Escena1 extends Escena {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    public int numEscena=1;
    /**
     * txt es el tipo de letra utilizado para el titulo del juego
     */
    public Typeface txt;
    /**
     * fondo es el fondo del menú
     */
    public Fondo fondo;
    /**
     * btnPlay es el botón del play para jugar
     */
    public Botones btnPlay;
    /**
     * btnPj es el botón de selección de escenario
     */
    public Botones btnPj;
    /**
     * btnOpciones es el botón de configuración del juego
     */
    public Botones btnOpciones;
    /**
     * btnPuntuaciones es el botón para ver las puntuaciones
     */
    public Botones btnPuntuaciones;
    /**
     * btnTutorial es el botón para ver el tutorial del juego
     */
    public Botones btnTutorial;
    /**
     * btnCreditos es el botón para ver los créditos del juego
     */
    public Botones btnCreditos;


    /**
     * Constructor donde se inicializan varios atributos y se definen el tipo de letra y las propiedades del pincel
     * También se cargan las imagenes correspondientes para los diferentes botones y fondo
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encotramos
     */
    public Escena1(Context context, int anchoPantalla, int altoPantalla, int numEscena) {
        super(context, anchoPantalla, altoPantalla, numEscena);
        this.numEscena=numEscena;
        txt=Typeface.createFromAsset(context.getAssets(),"font/zombiecontrol.ttf");
        p.setTypeface(txt);
        p.setColor(Color.WHITE);
        p.setTextSize(200);
        cargaRecursos();
    }

    /**
     * Método encargado de cargar los recursos necesarios y escalarlos
     */
    public void cargaRecursos(){
        fondo=new Fondo(escalaAnchura(getBitmapFromAssets("Fondo/fondoMenu/fondomenu.png"),anchoPantalla,altoPantalla),0,0,0,-1);
        btnPlay=new Botones(escalaAnchura(getBitmapFromAssets("Botones/play.png"),anchoPantalla/10,anchoPantalla/10),anchoPantalla/2-anchoPantalla*1/10+50,altoPantalla/2-altoPantalla*2/10);
        btnPj=new Botones(escalaAnchura(getBitmapFromAssets("Botones/seleccion.png"),anchoPantalla/15,anchoPantalla/15),anchoPantalla/15,altoPantalla-altoPantalla/4);
        btnOpciones=new Botones(escalaAnchura(getBitmapFromAssets("Botones/opciones.png"),anchoPantalla/15,anchoPantalla/15),anchoPantalla/5,altoPantalla-altoPantalla/4);
        btnPuntuaciones=new Botones(escalaAnchura(getBitmapFromAssets("Botones/puntuaciones.png"),anchoPantalla/15,anchoPantalla/15),btnPlay.getPosicionX(),altoPantalla-altoPantalla/4);
        btnTutorial=new Botones(escalaAnchura(getBitmapFromAssets("Botones/tutorial.png"),anchoPantalla/15,anchoPantalla/15),btnPuntuaciones.getPosicionX()+(int)(anchoPantalla*3.1/10),altoPantalla-altoPantalla/4);
        btnCreditos=new Botones(escalaAnchura(getBitmapFromAssets("Botones/creditos.png"),anchoPantalla/15,anchoPantalla/15),anchoPantalla-(int)(anchoPantalla*1.3/10),altoPantalla-altoPantalla/4);

    }

    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c){

        super.dibuja(c);
        fondo.dibuja(c);
        btnPlay.dibuja(c);
        btnPj.dibuja(c);
        btnOpciones.dibuja(c);
        btnPuntuaciones.dibuja(c);
        btnTutorial.dibuja(c);
        btnCreditos.dibuja(c);
        c.drawText(" JumpZombies ",anchoPantalla/2, altoPantalla/5,p);

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

        if (x>btnPlay.getPosicionX() && x<btnPlay.getPosicionX()+ btnPlay.getImagen().getWidth() && y>btnPlay.getPosicionY() && y<btnPlay.getPosicionY()+btnPlay.getImagen().getHeight())return 10;
        if (x>btnPj.getPosicionX() && x<btnPj.getPosicionX()+ btnPj.getImagen().getWidth() && y>btnPj.getPosicionY() && y<btnPj.getPosicionY()+btnPj.getImagen().getHeight())return 2;
        if (x>btnOpciones.getPosicionX() && x<btnOpciones.getPosicionX()+ btnOpciones.getImagen().getWidth() && y>btnOpciones.getPosicionY() && y<btnOpciones.getPosicionY()+btnOpciones.getImagen().getHeight())return 3;
        if (x>btnPuntuaciones.getPosicionX() && x<btnPuntuaciones.getPosicionX()+ btnPuntuaciones.getImagen().getWidth() && y>btnPuntuaciones.getPosicionY() && y<btnPuntuaciones.getPosicionY()+btnPuntuaciones.getImagen().getHeight())return 4;
        if (x>btnTutorial.getPosicionX() && x<btnTutorial.getPosicionX()+ btnTutorial.getImagen().getWidth() && y>btnTutorial.getPosicionY() && y<btnTutorial.getPosicionY()+btnTutorial.getImagen().getHeight())return 5;
        if (x>btnCreditos.getPosicionX() && x<btnCreditos.getPosicionX()+ btnCreditos.getImagen().getWidth() && y>btnCreditos.getPosicionY() && y<btnCreditos.getPosicionY()+btnCreditos.getImagen().getHeight())return 6;
        return this.numEscena;
    }
}
