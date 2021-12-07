package com.example.jumpzombies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Escena6 extends Escena {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    public int numEscena=6;
    /**
     * Constructor donde se inicializan varios atributos y se definen propiedades del pincel p2
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encontramos
     */
    public Escena6(Context context, int anchoPantalla, int altoPantalla, int numEscena) {
        super(context, anchoPantalla, altoPantalla, numEscena);
        p2.setTextAlign(Paint.Align.LEFT);
        p2.setTextSize(150);
    }
    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c){
        c.drawColor(Color.CYAN);
        super.dibuja(c);
        c.drawText("Creditos",anchoPantalla/2, altoPantalla/4,p);
        c.drawText("Desarrollador: Gabriel Pérez Rodríguez",(int)(anchoPantalla/4.5),(int)(altoPantalla/2.8),p2);
        c.drawText("Agradecimientos: Javier, Juanjo, Marcos",(int)(anchoPantalla/4.5),(int)(altoPantalla/2.1),p2);
        c.drawText("Graficos: gameart2d.com, itch.io, iconfinder.com",(int)(anchoPantalla/4.5),(int)(altoPantalla/1.7),p2);
        c.drawText("Fuentes: 1001freefonts.com, iconian.com Daniel Zadorozny",(int)(anchoPantalla/4.5),(int)(altoPantalla/1.4),p2);
        c.drawText("Audio: freesound.org, bensound.com, patrickdearteaga.com ",(int)(anchoPantalla/4.5),(int)(altoPantalla/1.2),p2);
        c.drawText("´",(int)(anchoPantalla/3.82),(int)(altoPantalla/1.7),p2);//graficos
        c.drawText("´",(int)(anchoPantalla/2.1), (int)(altoPantalla/4.5),p2);//creditos

    }

    /**
     * Metodo que actualizafisica de diferentes elementos
     */
    @Override
    public void actualizaFisica() {
        super.actualizaFisica();
    }

    /**
     * Método encargado de gestionar las pulsaciones en la escena
     * @param event evento
     * @return número de escena
     */
    int onTouchEvent(MotionEvent event){

        int aux=super.onTouchEvent(event);
        if (aux!=this.numEscena && aux!=-1) return aux;
        return this.numEscena;
    }
}
