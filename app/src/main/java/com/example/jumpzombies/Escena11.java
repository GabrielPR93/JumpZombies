package com.example.jumpzombies;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Escena11 extends Escena {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    public int numEscena=11;
    /**
     * btnAnterior es el botón para ir a la escena anterior
     */
    public Botones btnAnterior;
    /**
     * vidas es el botón de las vidas que tiene el pj
     */
    public Botones vidas;
    /**
     * btnsalto es el botón de saltar
     */
    public Botones btnsalto;
    /**
     * btncorrer1 es el botón de correr
     */
    public Botones btncorrer1;
    /**
     * corazon es el botón de la vida que puede coger el pj
     */
    public Botones corazon;
    /**
     * trebol es el botón del trebol que coge el pj
     */
    public Botones trebol;

    /**
     * Constructor donde se inicializan varios atributos y se cargan las imagenes correspondientes para los botones
     * También define propiedades del pincel p2
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encontramos
     */
    public Escena11(Context context, int anchoPantalla, int altoPantalla, int numEscena) {
        super(context, anchoPantalla, altoPantalla, numEscena);
        btnAnterior=new Botones(escalaAnchura(getBitmapFromAssets("Tutorial/prew.png"),anchoPantalla/15,anchoPantalla/15),(int)(anchoPantalla/8),(int)(altoPantalla/2.1));
        vidas=new Botones(escalaAnchura(getBitmapFromAssets("Vidas/3vidas.png"),anchoPantalla/8,anchoPantalla/14),(int)(anchoPantalla/4.2),(int)(altoPantalla/3.9));
        btncorrer1=new Botones(escalaAnchura(getBitmapFromAssets("Botones/btnDerecha.png"),anchoPantalla/16,anchoPantalla/16),(int)(anchoPantalla/3.7),(int)(altoPantalla/2.6));
        btnsalto=new Botones(escalaAnchura(getBitmapFromAssets("Botones/btnSalto.png"),anchoPantalla/16,anchoPantalla/16),(int)(anchoPantalla/1.9),(int)(altoPantalla/2.6));
        corazon=new Botones(escalaAnchura(getBitmapFromAssets("Vidas/corazon.png"),anchoPantalla/17,anchoPantalla/17),(int)(anchoPantalla/3.7),(int)(altoPantalla/1.8));
        trebol=new Botones(escalaAnchura(getBitmapFromAssets("Vidas/trebol.png"),anchoPantalla/16,anchoPantalla/16),(int)(anchoPantalla/3.7),(int)(altoPantalla/1.4));
        p2.setTextSize(120);
    }

    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    @Override
    public void dibuja(Canvas c) {
        super.dibuja(c);
        btnAnterior.dibuja(c);
        c.drawText("Tutorial",anchoPantalla/2, altoPantalla/4,p);
        vidas.dibuja(c);
        btncorrer1.dibuja(c);
       btnsalto.dibuja(c);
       corazon.dibuja(c);
        trebol.dibuja(c);
        c.drawText("Las vidas de Jhon que aparecen en la parte superior izquierda",(int)(anchoPantalla/2.8), (int)(altoPantalla/2.8),p2);
        c.drawText("Boton de correr",(int)(anchoPantalla/2.8), (int)(altoPantalla/2.0),p2);
        c.drawText("Boton de saltar",(int)(anchoPantalla/1.65), (int)(altoPantalla/2.0),p2);
        c.drawText("Vida que te encuentras a lo largo del juego",(int)(anchoPantalla/2.8), (int)(altoPantalla/1.5),p2);
        c.drawText("Trebol que busca Jhon para lograr la cura del virus",(int)(anchoPantalla/2.8), (int)(altoPantalla/1.25),p2);
    }

    /**
     * Método encargado de gestionar las pulsaciones en la escena
     * @param event evento
     * @return número de escena
     */
    int onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        int aux=super.onTouchEvent(event);
        if (aux!=this.numEscena && aux!=-1) return aux;
        if (x>btnAnterior.getPosicionX() && x<btnAnterior.getPosicionX()+ btnAnterior.getImagen().getWidth() && y>btnAnterior.getPosicionY() && y<btnAnterior.getPosicionY()+btnAnterior.getImagen().getHeight())return 5;

        return this.numEscena;
    }
}
