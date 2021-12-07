package com.example.jumpzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

public class Personaje {
    /**
     * vidas es el número de vidas que tiene el pj
     */
    public int vidas=3;
    /**
     * x indica las coordenadas del eje x donde se situara el pj
     */
    public int x;
    /**
     * y indica las coordenadas del eje y donde se situara el pj
     */
    public int y;
    /**
     * vx indica la velocidad del eje x
     */
    public int vx=0;
    /**
     * vy indica la velocidad del eje y
     */
    public int vy=0;
    /**
     * tickFrame es el tick de cada cuanto tiempo se va a cambiar de frame
     */
    public int tickFrame=150;
    /**
     * tFrame es el tiempo en milisegundos
     */
    public long tFrame;
    /**
     * anchoPantalla es el tamaño del ancho de la pantalla
     */
    public int anchoPantalla;
    /**
     * altoPantalla es el tamaño del alto de la pantalla
     */
    public int altoPantalla;
    /**
     * numFrame nos indica un numero de frame
     */
    public int numframe=0;
    /**
     * frames es un array de bitmaps
     */
    public Bitmap[] frames;
    /**
     * pincel es un pincel con el que pintar el hitbox
     */
    public Paint pincel;
    /**
     * hitbox representa un rectángulo creado por cuatro coordenadas para el tamaño de la cabeza del pj
     */
    public Rect hitbox;
    /**
     * hitbox representa un rectángulo creado por cuatro coordenadas para el tamaño del cuerpo del pj
     */
    public Rect hitbox2;
    /**
     * sube nos indica si el personaje está subiendo debido al salto
     */
    public boolean sube=false;
    /**
     * baja nos indica si el personaje está bajando debido al salto
     */
    public boolean baja=false;
    /**
     * altMax es la altura máxima de la pantalla
     */
    public int altMax;
    /**
     * altMin es la altura mínima donde debe quedar el pj después del salto
     */
    public int altMin;
    /**
     * topeSalto nos indica el tope de la altura que alcanza el pj al saltar
     */
    public int topeSalto;

    /**
     * Constructor donde se inicializan varios atributos y se define una región de la pantalla
     * También se definen varias propiedades del pincel
     * @param x coordenadas del eje x
     * @param y coordenadas del eje y
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param frames array de bitmaps
     */
    public Personaje(int x, int y, int anchoPantalla, int altoPantalla, Bitmap[] frames) {
        this.x = x;
        this.y = y;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.frames = frames;
        tFrame=System.currentTimeMillis();
        altMax=altoPantalla;
        altMin=altoPantalla - (int)(altoPantalla * 4.2) / 10;
        topeSalto=30;//altopantalla/11
        pincel=new Paint();
        pincel.setColor(Color.RED);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(5);
        setRectangulos();
    }

    /**
     * Metodo donde se gestiona el cambio de frames del personaje y su tiempo
     */
    public void cambiaFrame(){
        if (System.currentTimeMillis()-tFrame>tickFrame){
            numframe++;
            tFrame=System.currentTimeMillis();
            if (numframe>=this.frames.length){
               numframe=0;

            }
            setRectangulos();
        }
    }

    /**
     * Metodo que genera el salto del personaje modificando las cordenadas
     */
    public void salta(){

        if(sube){

            if(this.y<altMax && !baja){
                this.y-=15;
                this.x+=2;

                if(this.y<topeSalto){
                    baja=true;
                }
                this.setRectangulos();
            }
            if(this.y <= altMin && baja){
                this.y+=15;
                this.x-=2;

                this.setRectangulos();
            }else if(this.y >= altMin && baja){
                baja=false;
                sube=false;
            }

        }
    }


    /**
     * Metodo que dibuja al personaje en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c){

        c.drawBitmap(frames[getNumframe()],x,y,null);
//        c.drawRect(hitbox,pincel);
//        c.drawRect(hitbox2,pincel);

    }

    /**
     * Metodo encargado de definir las coordenadas y tamaño de una región de la pantalla dependiendo del frame correspondiente
     */
    public void setRectangulos(){

        hitbox=new Rect(x,y,x+frames[getNumframe()].getWidth(),y+frames[getNumframe()].getHeight()-(altoPantalla/5));
        hitbox2=new Rect(x,y,x+frames[getNumframe()].getWidth()-frames[getNumframe()].getWidth()*3/10,y+frames[getNumframe()].getHeight());

    }



    /**
     * Metodo que pasandole un rect detecta si se ha producido una intersección entre dos objectos
     * @param hitbox El rect
     * @return devuelve true si se produce una intersección y false si no
     */
    public boolean colision(Rect hitbox){
        return this.hitbox.intersect(hitbox);
    }

    /**
     * Metodo que impide que se salga del array los frames
     * @return el numero de frame
     */
    public int getNumframe(){
        if(numframe>=frames.length){
            numframe=0;;
        }
        return numframe;
    }

    /**
     * Getter para obtener el hitbox
     * @return el hitbox
     */
    public Rect getHitbox() {
        return hitbox;
    }
    /**
     * Getter para obtener el valor de x
     * @return el valor de x
     */
    public float getX() {
        return x;
    }

    /**
     * Setter que pasando un entero modifica el valor de x
     * @param x un valor entero
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Getter para obtener el valor de y
     * @return el valor de y
     */
    public float getY() {
        return y;
    }
    /**
     * Setter que pasando un entero modifica el valor de y
     * @param y un valor entero
     */
    public void setY(int y) {

        this.y = y;
    }
    /**
     * Getter para obtener el valor de vx
     * @return el valor de vx
     */
    public int getVx() {
        return vx;
    }
    /**
     * Setter que pasando un entero modifica el valor de vx
     * @param vx un valor entero
     */
    public void setVx(int vx) {
        this.vx = vx;
    }
    /**
     * Getter para obtener el valor de tickFrame
     * @return el valor de tickFrame
     */
    public int getTickFrame() {
        return tickFrame;
    }
    /**
     * Setter que pasando un entero modifica el valor de tickFrame
     * @param tickFrame un valor entero
     */
    public void setTickFrame(int tickFrame) {
        this.tickFrame = tickFrame;
    }
    /**
     * Getter para obtener el hitbox2
     * @return el hitbox2
     */
    public Rect getHitbox2() {
        return hitbox2;
    }

}
