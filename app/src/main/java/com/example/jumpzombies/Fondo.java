package com.example.jumpzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Fondo {
    /**
     * x indica las coordenadas del eje x donde se situara el fondo
     */
    public int x;
    /**
     * y indica las coordenadas del eje y donde se situara el fondo
     */
    public int y;
    /**
     * velx es la velocidad a la que se moverá el fondo
     */
    public int velx;
    /**
     * img es el bitmap del fondo que vamos a dibujar
     */
    public Bitmap img;
    /**
     * direccion nos indica la dirección en la que se moverá el fondo que será a la izquierda
     */
    public int direccion=-1;

    /**
     * Constructor donde se inicializan los atributos
     * @param img es el bitmap del fondo
     * @param x la coordenada del eje x
     * @param y la coordenada del eje y
     * @param velx la velocidad del eje x
     * @param direccion la dirección en la que se moverá el fondo
     */
    public Fondo(Bitmap img, int x,int y, int velx, int direccion) {
        this.x = x;
        this.y=y;
        this.velx = velx;
        this.img = img;
        this.direccion=direccion;

    }
    /**
     * Metodo que dibuja el fondo en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c){

        c.drawBitmap(img,x,y,null);
    }

    /**
     * Metodo que desplaza el fondo hacia un lado a una determinada velocidad
     */
    public void mueve(){

        this.x+=velx*direccion;
    }

    /**
     * Getter para obtener el valor de x
     * @return el valor de x
     */
    public int getX() {
        return x;
    }

    /**
     * Setter que pasando un entero modifica el valor de x
     * @param x valor entero
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Getter para obtener el valor de y
     * @return el valor de y
     */
    public int getY() {
        return y;
    }
    /**
     * Setter que pasando un entero modifica el valor de y
     * @param y valor entero
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Getter para obtener el valor de velx
     * @return el valor de velx
     */
    public int getVelx() {
        return velx;
    }
    /**
     * Setter que pasando un entero modifica el valor de velx
     * @param velx valor entero
     */
    public void setVelx(int velx) {
        this.velx = velx;

    }

}
