package com.example.jumpzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Corazon extends Vidas{
    /**
     * hitbox representa un rectángulo creado por cuatro coordenadas
     */
    Rect hitbox;
    /**
     * p es un pincel con el que se va a dibujar el hitbox
     */
    Paint p;

    /**
     * Constructor donde se inicializan varios atributos y se establecen las propiedades del pincel
     * @param imagen bitmap del corazón
     * @param posicionX coordenadas del eje x
     * @param posicionY coordenadas del eje y
     */
    public Corazon(Bitmap imagen, int posicionX, int posicionY) {
        super(imagen, posicionX, posicionY);
        this.setRectangulo();
        p =new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
    }

    /**
     * Metodo encargado de definir las coordenadas y tamaño de una región de la pantalla dependiendo del frame correspondiente
     */
  public void setRectangulo(){
        hitbox=new Rect(posicionX,posicionY,posicionX+imagen.getWidth(),posicionY+imagen.getHeight());
  }

    /**
     * Metodo encargado de dibujar en el lienzo
     * @param c el lienzo
     */
  public void dibuja(Canvas c){
        c.drawBitmap(imagen,posicionX,posicionY,null);
        //c.drawRect(hitbox, p);
  }

    /**
     * Metodo que gestiona el movimiento del corazon pasandole un parametro
     * @param velx parametro que se le pasa para definir la velocidad
     */
    public void mueve(int velx){
        this.posicionX += velx*(-1);
        this.setRectangulo();
    }
    /**
     * Metodo que pasandole un rect detecta si se ha producido una intersección entre dos objectos
     * @param hitbox El rect
     * @return devuelve true si se produce una intersección y false si no
     */
    public boolean colisionCorazon(Rect hitbox){
        return this.hitbox.intersect(hitbox);
    }
    /**
     * Getter para obtener el hitbox
     * @return el hitbox
     */
    public Rect getHitbox() {
        return hitbox;
    }

    /**
     *  Setter que pasando un Rect modifica el valor de hitbox
     * @param hitbox
     */
    public void setHitbox(Rect hitbox) {
        this.hitbox = hitbox;
    }
}
