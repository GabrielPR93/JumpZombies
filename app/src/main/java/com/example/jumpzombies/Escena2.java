package com.example.jumpzombies;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

public class Escena2 extends Escena {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    public int numEscena=2;
    /**
     * f es el fondo de la escena
     */
    public Fondo f;
    /**
     * btnEscena1 es el botón de selección de escena uno
     */
    public Botones btnEscena1;
    /**
     * btnEscena2 es el botón de selección de escena dos
     */
    public Botones btnEscena2;
    /**
     * btnOn1 es el botón que indica que la escena uno está seleccionada
     */
    public Botones btnOn1;
    /**
     * btnOff1 es el botón que indica que la escena uno no está seleccionada
     */
    public Botones btnOff1;
    /**
     * btnOn2 es el botón que indica que la escena dos está seleccionada
     */
    public Botones btnOn2;
    /**
     * btnOff2 es el botón que indica que la escena dos no está seleccionada
     */
    public Botones btnOff2;
    /**
     * flagBtnOn nos indica que escenario está seleccionado
     */
    public boolean flagBtnOn;

    /**
     * Constructor donde se inicializan varios atributos y se cargan las imagenes correspondientes para los botones y fondo
     * También lee la selección de escena guardada
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encontramos
     */
    public Escena2(Context context, int anchoPantalla, int altoPantalla, int numEscena) {
        super(context, anchoPantalla, altoPantalla, numEscena);
        f=new Fondo(getBitmapFromAssets("Fondo/fondoPrincipal.png"),0,0,0,-1);
        leerSeleccionBosque();
        cargaRecursos();

    }

    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c){

        f.dibuja(c);
        super.dibuja(c);
        c.drawText("Escenarios",anchoPantalla/2, altoPantalla/4,p);

        if(flagBtnOn){
            btnOn1.dibuja(c);
            btnOff1.dibuja(c);
        }else{
            btnOn2.dibuja(c);
            btnOff2.dibuja(c);
        }

        btnEscena1.dibuja(c);
        btnEscena2.dibuja(c);
        c.drawText("Bosque Ancestral",(int)(anchoPantalla/4.2), (int)(altoPantalla/1.2),p2);
        c.drawText("Bosque de Fargorn",(int)(anchoPantalla/1.8), (int)(altoPantalla/1.2),p2);

    }


    /**
     * Método encargado de cargar los recursos necesarios y escalarlos
     */
    public void cargaRecursos(){
        btnEscena1=new Botones(escalaAnchura(getBitmapFromAssets("Botones/Escenas/escena1.png"),anchoPantalla/4,anchoPantalla/6),(int)(anchoPantalla/4.4),(int)(altoPantalla/3.3));
        btnEscena2=new Botones(escalaAnchura(getBitmapFromAssets("Botones/Escenas/escena2.png"),anchoPantalla/4,anchoPantalla/6),(int)(anchoPantalla/1.87),(int)(altoPantalla/3.3));
        btnOn1 =new Botones(escalaAnchura(getBitmapFromAssets("Botones/Escenas/seleccionado1.png"),anchoPantalla/30,anchoPantalla/30),(int)(anchoPantalla/3.1),(int)(altoPantalla/1.5));
        btnOff1 =new Botones(escalaAnchura(getBitmapFromAssets("Botones/Escenas/noSeleccionado1.png"),anchoPantalla/30,anchoPantalla/30),(int)(anchoPantalla/1.55),(int)(altoPantalla/1.5));
        btnOn2 =new Botones(escalaAnchura(getBitmapFromAssets("Botones/Escenas/noSeleccionado1.png"),anchoPantalla/30,anchoPantalla/30),(int)(anchoPantalla/3.1),(int)(altoPantalla/1.5));
        btnOff2 =new Botones(escalaAnchura(getBitmapFromAssets("Botones/Escenas/seleccionado1.png"),anchoPantalla/30,anchoPantalla/30),(int)(anchoPantalla/1.55),(int)(altoPantalla/1.5));

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
    switch (accion){
        case MotionEvent.ACTION_DOWN:
            if(x>btnOn1.getPosicionX() && x<btnOn1.getPosicionX()+btnOn1.getImagen().getWidth() && y>btnOn1.getPosicionY() && y<btnOn1.getPosicionY()+btnOn1.getImagen().getHeight() || x>btnEscena1.getPosicionX() && x<btnEscena1.getPosicionX()+btnEscena1.getImagen().getWidth() && y>btnEscena1.getPosicionY() && y<btnEscena1.getPosicionY()+btnEscena1.getImagen().getHeight() ){
                flagBtnOn=!flagBtnOn;
            }
            if(x> btnOff1.getPosicionX() && x< btnOff1.getPosicionX()+ btnOff1.getImagen().getWidth() && y> btnOff1.getPosicionY() && y< btnOff1.getPosicionY()+ btnOff1.getImagen().getHeight() || x>btnEscena2.getPosicionX() && x<btnEscena2.getPosicionX()+btnEscena2.getImagen().getWidth() && y>btnEscena2.getPosicionY() && y<btnEscena2.getPosicionY()+btnEscena2.getImagen().getHeight() ){
                flagBtnOn=!flagBtnOn;
            }
            guardarSeleccionBosque();
            break;
    }

        return this.numEscena;
    }

    /**
     * Método encargado de guardar la seleccion del bosque
     */
    public void guardarSeleccionBosque(){
        SharedPreferences preferencias = context.getSharedPreferences("SeleccionBosque",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=preferencias.edit();
       editor.putBoolean("mapa",flagBtnOn);
        editor.commit();

    }

    /**
     * Método encargado de leer la seleccion del bosque
     */
    public void leerSeleccionBosque(){
        SharedPreferences preferencias = context.getSharedPreferences("SeleccionBosque",Context.MODE_PRIVATE);
        flagBtnOn=preferencias.getBoolean("mapa",true);
    }
}
