package com.example.jumpzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Scroll {
    /**
     * fondos es un arrayList de fondos
     */
    public ArrayList<Fondo> fondos;
    /**
     * velx nos indica la velocidad del eje x
     */
    public int velx;
    /**
     * direccion nos indica la dirección en la que se desplaza el scroll
     */
    public int direccion=-1;

    /**
     * Constructor donde se inicializan varios atributos
     * También se inicializa y añade los fondos correspondientes en el arrayList de fondos
     * @param img array de bitmaps
     * @param velx velocidad del eje x
     * @param direccion dirección en la que se desplazaran los fondos
     */
    public Scroll(Bitmap[] img, int velx, int direccion) {
        this.velx = velx;
        this.direccion = direccion;
        fondos=new ArrayList<>();
        fondos.add(new Fondo(img[0],0,0,velx,direccion));
        for (int i = 1; i <img.length ; i++) {
            fondos.add(new Fondo(img[i],fondos.get(i-1).x+fondos.get(i-1).img.getWidth(),0,velx,direccion));
        }
    }

    /**
     * Metodo que desplaza las imagenes hacia un lado y las borra una vez salieron de la pantalla
     */
    public void mover(){
        for (int i = 0; i <fondos.size() ; i++) {
            fondos.get(i).mueve();
        }
        if (fondos.get(0).x+fondos.get(0).img.getWidth()<0){
            Fondo aux=fondos.get(0);
            aux.x=fondos.get(fondos.size()-1).x+fondos.get(fondos.size()-1).img.getWidth();
            fondos.remove(0);
            fondos.add(aux);

        }
    }

    /**
     * Metodo que dibuja todos los fondos sobre el lienzo
     * @param c El lienzo
     */
    public void dibuja(Canvas c){
        for (Fondo f:fondos){
            f.dibuja(c);
        }
    }

    /**
     * Getter para obtener el valor de velx
     * @return el valor de velx
     */
    public int getVelx() {
        return velx;
    }

    /**
     *  Setter que pasando un entero modifica el valor de velx
     *  También modifica velx en los respectivos fondos
     * @param velx valor entero
     */
    public void setVelx(int velx) {
        this.velx = velx;
        for (Fondo f:fondos) {
            f.setVelx(velx);
        }
    }
}
