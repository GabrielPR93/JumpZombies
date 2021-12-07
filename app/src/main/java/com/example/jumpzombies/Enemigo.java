package com.example.jumpzombies;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Enemigo extends Personaje{
    /**
     * colision nos indica si un enemigo colisiono con algún elemento
     */
    boolean colision=true;

    /**
     * Constructor donde se inicializan varios atributos y se define una región de la pantalla
     * @param x coordenadas del eje x
     * @param y coordenadas del eje y
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param frames array de bitmaps
     */
    public Enemigo(int x, int y, int anchoPantalla, int altoPantalla, Bitmap[] frames) {
        super(x, y, anchoPantalla, altoPantalla, frames);
        setRectangulosEnemigo();
    }

    /**
     * Metodo que mueve al enemigo
     */
    public void mueveEnemigo(){
        this.x+=vx;
        this.y+=vy;

        setRectangulosEnemigo();
    }

    /**
     * Metodo donde se gestiona el cambio de frames del enemigo y su tiempo
     */
    public void cambiaFrameEnemigo(){
        if (System.currentTimeMillis()-tFrame>tickFrame){
            numframe++;
            tFrame=System.currentTimeMillis();
            if (numframe>=this.frames.length){
                numframe=0;
            }
            setRectangulosEnemigo();
        }
    }

    /**
     * Metodo encargado de definir las coordenadas y tamaño de una región de la pantalla dependiendo del frame correspondiente
     */
    public void setRectangulosEnemigo(){

        //hitbox=new Rect(x,y,x+frames[getNumframe()].getWidth()-frames[getNumframe()].getWidth()*3/10,y+frames[getNumframe()].getHeight());
        hitbox=new Rect(x+frames[getNumframe()].getWidth()-(int)(frames[getNumframe()].getWidth()*8/10),y,x+frames[getNumframe()].getWidth()-(int)(frames[getNumframe()].getWidth()*3.5/10),y+frames[getNumframe()].getHeight()-(int)(altoPantalla/6));//cabeza
        hitbox2=new Rect(x,y+frames[getNumframe()].getWidth()-(int)(frames[getNumframe()].getWidth()*4/10),x+frames[getNumframe()].getWidth()-frames[getNumframe()].getWidth()*2/10,y+frames[getNumframe()].getHeight()); //cuerpo

    }

    /**
     * Getter para obtener la colision
     * @return la colision
     */
    public boolean isColision() {
        return colision;
    }

    /**
     *  Setter que pasando un boolean modifica el valor de colision
     * @param colision parametro boolean
     */
    public void setColision(boolean colision) {
        this.colision = colision;
    }
}
