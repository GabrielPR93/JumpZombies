package com.example.jumpzombies;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class Vidas {
    /**
     * posicionX indica las coordenadas del eje x donde se situara la vida
     */
    public int posicionX;
    /**
     * posicionY indica las coordenadas del eje y donde se situara la vida
     */
    public int posicionY;
    /**
     * imagen es el bitmap de la vida que vamos a dibujar
     */
    public Bitmap imagen;
    /**
     * Constructor donde se inicializan los atributos
     * @param imagen bitmap de la vida
     * @param posicionX coordenadas del eje x
     * @param posicionY coordenadas del eje y
     */
    public Vidas(Bitmap imagen,int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.imagen = imagen;
    }

    /**
     * Método encargado de dibujar en el lienzo
     * @param c el lienzo
     */
    public void dibuja(Canvas c){

        c.drawBitmap(imagen,posicionX,posicionY,null);
    }

    /**
     * Getter para obtener el valor de posicionX
     * @return el valor de posicionX
     */
    public int getPosicionX() {
        return posicionX;
    }
    /**
     * Setter que pasando un entero modifica el valor de posicionX
     * @param posicionX un valor entero
     */
    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }
    /**
     * Getter para obtener el valor de posicionY
     * @return el valor de posicionY
     */
    public int getPosicionY() {
        return posicionY;
    }
    /**
     * Setter que pasando un entero modifica el valor de posicionY
     * @param posicionY un valor entero
     */
    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
    /**
     * Getter para obtener el valor de la imagen
     * @return la imagen
     */
    public Bitmap getImagen() {
        return imagen;
    }
    /**
     * Setter que pasando un Bitmap modifica la imagen de imagen
     * @param imagen un Bitmap
     */
    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
