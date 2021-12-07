package com.example.jumpzombies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class Escena5 extends Escena {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    public int numEscena=5;
    /**
     * pj es el botón del personaje
     */
    public Botones pj;
    /**
     * v1 es el botón del enemigoV1
     */
    public Botones v1;
    /**
     * v2 es el botón del enemigoV2
     */
    public Botones v2;
    /**
     * v3 es el botón del enemigoV3
     */
    public Botones v3;
    /**
     * btnsiguiente es el botón para ir a la siguiente escena
     */
    public Botones btnsiguiente;

    /**
     * Constructor donde se inicializan varios atributos y se cargan las imagenes correspondientes para los botones
     * También se definen propiedades del pincel p2
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encontramos
     */
    public Escena5(Context context, int anchoPantalla, int altoPantalla, int numEscena) {
        super(context, anchoPantalla, altoPantalla, numEscena);
        //base=new Fondo(escalaAnchura(getBitmapFromAssets("Tutorial/baseTutorial1.png"),anchoPantalla-anchoPantalla*2/10,altoPantalla-(altoPantalla*1/10)),anchoPantalla/8,altoPantalla/14,0,-1);
        pj=new Botones(escalaAnchura(getBitmapFromAssets("Tutorial/pj.png"),anchoPantalla/14,anchoPantalla/12),(int)(anchoPantalla/3.8),(int)(altoPantalla/3.5));
        v1=new Botones(escalaAnchura(getBitmapFromAssets("Tutorial/v1.png"),anchoPantalla/17,anchoPantalla/13),(int)(anchoPantalla/3.8),(int)(altoPantalla/1.55));
        v2=new Botones(escalaAnchura(getBitmapFromAssets("Tutorial/v2.png"),anchoPantalla/16,anchoPantalla/13),(int)(anchoPantalla/2.3),(int)(altoPantalla/1.55));
        v3=new Botones(escalaAnchura(getBitmapFromAssets("Tutorial/v3.png"),anchoPantalla/16,anchoPantalla/13),(int)(anchoPantalla/1.6),(int)(altoPantalla/1.55));
        btnsiguiente=new Botones(escalaAnchura(getBitmapFromAssets("Tutorial/next.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/1.17),(int)(altoPantalla/2.1));

        p2.setTextSize(120);
    }

    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c){

        super.dibuja(c);
        pj.dibuja(c);
        v1.dibuja(c);
        v2.dibuja(c);
        v3.dibuja(c);
        c.drawText("Tutorial",anchoPantalla/2, altoPantalla/4,p);
        c.drawText("Este es Jhon nuestro personaje, con él cual tendrémos que",(int)(anchoPantalla/2.8), (int)(altoPantalla/2.8),p2);
        c.drawText("recorrer un largo camino saltando diferentes enemigos",(int)(anchoPantalla/2.8), (int)(altoPantalla/2.4),p2);
        c.drawText("en busca de la planta curativa.",(int)(anchoPantalla/2.8), (int)(altoPantalla/2.1),p2);
        c.drawText("Estos son los diferentes enemigos que no podras tocar o te restaran vidas",(int)(anchoPantalla/4.1), (int)(altoPantalla/1.7),p2);
        c.drawText("-1 vida",(int)(anchoPantalla/2.9), (int)(altoPantalla/1.3),p2);
        c.drawText("-2 vidas",(int)(anchoPantalla/1.9), (int)(altoPantalla/1.3),p2);
        c.drawText("-3 vidas",(int)(anchoPantalla/1.4), (int)(altoPantalla/1.3),p2);
        c.drawText("´",(int)(anchoPantalla/1.62), (int)(altoPantalla/1.70),p2);
        c.drawText("´",(int)(anchoPantalla/1.30), (int)(altoPantalla/1.70),p2);
        btnsiguiente.dibuja(c);


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
        if (x>btnsiguiente.getPosicionX() && x<btnsiguiente.getPosicionX()+ btnsiguiente.getImagen().getWidth() && y>btnsiguiente.getPosicionY() && y<btnsiguiente.getPosicionY()+btnsiguiente.getImagen().getHeight())return 11;

        return this.numEscena;
    }
}
