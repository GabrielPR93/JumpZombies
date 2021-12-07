package com.example.jumpzombies;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Escena8 extends Escena {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    public int numEscena=8;
    /**
     * fondo es el fondo de la escena
     */
    public Fondo fondo;
    /**
     * btnMenu es el botón para volver al menú
     */
    public Botones btnMenu;
    /**
     * btnRestart es el botón para volver a jugar
     */
    public Botones btnRestart;
    /**
     * puntos es un acumulador para almacenar los puntos leídos
     */
    public int puntos;

    /**
     * Constructor donde se inicializan varios atributos y se cargan las imagenes correspondientes para los botones y fondo
     * También lee la puntuación guardada y define propiedades del pincel p2
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encontramos
     */
    public Escena8(Context context, int anchoPantalla, int altoPantalla, int numEscena) {
        super(context, anchoPantalla, altoPantalla, numEscena);
        p2.setTextAlign(Paint.Align.RIGHT);
        fondo=new Fondo(escalaAnchura(getBitmapFromAssets("Fondo/fondoMenu/menuFinal.png"),anchoPantalla-anchoPantalla*2/10,altoPantalla-(altoPantalla*1/10)),anchoPantalla/8,altoPantalla/14,0,-1);
        btnMenu=new Botones(escalaAnchura(getBitmapFromAssets("Botones/menu.png"),anchoPantalla/12,anchoPantalla/12),(int)(anchoPantalla/1.8),(int)(altoPantalla/1.5));
        btnRestart=new Botones(escalaAnchura(getBitmapFromAssets("Botones/restart.png"),anchoPantalla/12,anchoPantalla/12),(int)(anchoPantalla/1.4),(int)(altoPantalla/1.5));
        leerPuntuacion();
    }

    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    @Override
    public void dibuja(Canvas c) {

        super.dibuja(c);
        fondo.dibuja(c);
        c.drawText("Victoria !!",(int)(anchoPantalla/1.7), altoPantalla/3,p2);
        c.drawText("Puntos Conseguidos: "+puntos,(int)(anchoPantalla/1.15), (int)(altoPantalla/1.6),p2);
        btnMenu.dibuja(c);
        btnRestart.dibuja(c);
    }

    /**
     * Método encargado de leer la puntuación
     */
    public void leerPuntuacion(){
        SharedPreferences preferencias = context.getSharedPreferences("puntuacion",Context.MODE_PRIVATE);
        puntos=preferencias.getInt("puntos",0);
    }

    /**
     * Método encargado de gestionar las pulsaciones en la escena
     * @param event evento
     * @return número de escena
     */
    @Override
    int onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        int aux=super.onTouchEvent(event);

        if (aux!=this.numEscena && aux!=-1) return aux;
        if(x>btnMenu.getPosicionX() && x<btnMenu.getPosicionX()+btnMenu.getImagen().getWidth() && y>btnMenu.getPosicionY() && y<btnMenu.getPosicionY()+btnMenu.getImagen().getHeight()){

            return 1;
        }
        if(x>btnRestart.getPosicionX() && x<btnRestart.getPosicionX()+btnRestart.getImagen().getWidth() && y>btnRestart.getPosicionY() && y<btnRestart.getPosicionY()+btnRestart.getImagen().getHeight()){

            return 10;
        }

        return this.numEscena;
    }
}
