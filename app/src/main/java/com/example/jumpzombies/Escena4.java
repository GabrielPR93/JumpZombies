package com.example.jumpzombies;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Escena4 extends Escena {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    public int numEscena=4;
    /**
     * f es el fondo de la escena
     */
    public Fondo f;
    /**
     * puntos es un acumulador que almacena los puntos leídos
     */
    public int puntos;

    /**
     * Constructor donde se inicializan varios atributos y se establece la imagen del fondo
     * También lee la puntuación guardada
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encontramos
     */
    public Escena4(Context context, int anchoPantalla, int altoPantalla, int numEscena) {
        super(context, anchoPantalla, altoPantalla, numEscena);
        f=new Fondo(getBitmapFromAssets("Fondo/fondoPrincipal.png"),0,0,0,-1);
        leerPuntuacion();

    }

    /**
     * Método encargado de leer la puntuación
     */
    public void leerPuntuacion(){
        SharedPreferences preferencias = context.getSharedPreferences("puntuacion",Context.MODE_PRIVATE);
        puntos=preferencias.getInt("puntos",0);
    }

    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c) {
        f.dibuja(c);
        super.dibuja(c);

        c.drawText("Puntuacion", anchoPantalla / 2, altoPantalla / 4, p);
        c.drawText("Registro de puntos conseguidos en la última partida",(int)(anchoPantalla / 4.65), (int)(altoPantalla / 1.4) , p2);
        c.drawText("Puntos Conseguidos: " + puntos, (int)(anchoPantalla /3), (int)(altoPantalla / 2.1), p2);



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
