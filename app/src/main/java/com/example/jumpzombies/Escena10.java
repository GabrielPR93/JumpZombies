package com.example.jumpzombies;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Escena10 extends Escena implements SensorEventListener {
    /**
     * numEscena nos indica el número de escena en el que estamos
     */
    private int numEscena=10;
    /**
     * parado es un array de bitmaps con los diferentes frames del personaje parado
     */
    private Bitmap[] parado;
    /**
     * corriendo es un array de bitmaps con los diferentes frames del personaje corriendo
     */
    private Bitmap[] corriendo;
    /**
     * saltando es un array de bitmaps con los diferentes frames del personaje saltando
     */
    private Bitmap[] saltando;
    /**
     * muerto es un array de bitmaps con los diferentes frames del personaje muerto
     */
    private Bitmap[] muerto;
    /**
     * fondo1 es un array de bitmaps con las imagenes de uno de los fondos que forman parte del parallax
     */
    private Bitmap[] fondo1;
    /**
     * fondo2 es un array de bitmaps con las imagenes de uno de los fondos que forman parte del parallax
     */
    private Bitmap[] fondo2;
    /**
     * fondo3 es un array de bitmaps con las imagenes de uno de los fondos que forman parte del parallax
     */
    private Bitmap[] fondo3;
    /**
     * fondo4 es un array de bitmaps con las imagenes de uno de los fondos que forman parte del parallax
     */
    private Bitmap[] fondo4;
    /**
     * fondo5 es un array de bitmaps con las imagenes de uno de los fondos que forman parte del parallax
     */
    private Bitmap[] fondo5;
    /**
     * fondo6 es un array de bitmaps con las imagenes de uno de los fondos que forman parte del parallax
     */
    private Bitmap[] fondo6;
    /**
     * fondo7 es un array de bitmaps con las imagenes de uno de los fondos que forman parte del parallax
     */
    private Bitmap[] fondo7;
    /**
     * fondo8 es un array de bitmaps con las imagenes de uno de los fondos que forman parte del parallax
     */
    private Bitmap[] fondo8;
    /**
     * corazones es un arrayList de corazones
     */
    private ArrayList<Corazon> corazones;
    /**
     * treboles es un arrayList de treboles
     */
    private ArrayList<Corazon> treboles;
    /**
     * anhadirVida es el primer corazón que aparece y añade una vida al pj si lo coge
     */
    private Corazon anhadirVida;
    /**
     * anhadirVida2 es el segundo corazón que aparece y añade una vida al pj si lo coge
     */
    private Corazon anhadirVida2;
    /**
     * anhadirVida3 es el último corazón que aparece y añade una vida al pj si lo coge
     */
    private Corazon anhadirVida3;
    /**
     * trebol es el trebol que tiene que coger el pj para ganar
     */
    private Corazon trebol;
    /**
     * btnDerecha es el botón de correr
     */
    private Botones btnDerecha;
    /**
     * btnSalto es el botón de saltar
     */
    private Botones btnSalto;
    /**
     * anchoBotonD es el ancho del botón de correr
     */
    private int anchoBotonD;
    /**
     * altoBotonD es el alto del botón de correr
     */
    private int altoBotonD;
    /**
     * anchoBotonS es el ancho del botón de saltar
     */
    private int anchoBotonS;
    /**
     * altoBotonS es el alto del botón de saltar
     */
    private int altoBotonS;
    /**
     * flagParado nos indica si el personaje está parado o no
     */
    private boolean flagParado=true;
    /**
     * sc1 es el scroll de las imagenes que hay en el array de fondo1
     */
    private Scroll sc1;
    /**
     * sc2 es el scroll de las imagenes que hay en el array de fondo2
     */
    private Scroll sc2;
    /**
     * sc3 es el scroll de las imagenes que hay en el array de fondo3
     */
    private Scroll sc3;
    /**
     * sc4 es el scroll de las imagenes que hay en el array de fondo4
     */
    private Scroll sc4;
    /**
     * sc5 es el scroll de las imagenes que hay en el array de fondo5
     */
    private Scroll sc5;
    /**
     * sc6 es el scroll de las imagenes que hay en el array de fondo6
     */
    private Scroll sc6;
    /**
     * sc7 es el scroll de las imagenes que hay en el array de fondo7
     */
    private Scroll sc7;
    /**
     * sc8 es el scroll de las imagenes que hay en el array de fondo8
     */
    private Scroll sc8;
    /**
     * fondoPrincipal es el fondo principal
     */
    private Fondo fondoPrincipal;
    /**
     * fondoPrincipalNocturno es el fondo principal en modo nocturno
     */
    private Fondo fondoPrincipalNocturno;
    /**
     * fondoPrincipal2 es el fondo principal si está seleccionado el segundo escenario
     */
    private Fondo fondoPrincipal2;
    /**
     * enemigoCorre es un array de bitmaps con los diferentes frames del enemigoV1 corriendo
     */
    private Bitmap[] enemigoCorre;
    /**
     * enemigoCorre2 es un array de bitmaps con los diferentes frames del enemigoV2 corriendo
     */
    private Bitmap[] enemigoCorre2;
    /**
     * enemigoCorre3 es un array de bitmaps con los diferentes frames del enemigoV3 corriendo
     */
    private Bitmap[] enemigoCorre3;
    /**
     * cant indica la posición donde se situarán los enemigosV1
     */
    private int cant;
    /**
     * cant1 indica la posición donde se situarán los enemigosV2
     */
    private int cant1;
    /**
     * cant2 indica la posición donde se situarán los enemigosV3
     */
    private int cant2;
    /**
     * colisionVida indica si el pj colisiona con una vida o no
     */
    private boolean colisionVida=true;
    /**
     * vibrator es el elemento hardware vibrador
     */
    private Vibrator vibrator;
    /**
     * mediaPlayer es el reproductor de música
     */
    private MediaPlayer mediaPlayer;
    /**
     * audioManager es el reproductor de sonidos
     */
    private AudioManager audioManager;
    /**
     * sonidosAmbiente es una colección de sonidos
     */
    private SoundPool sonidosAmbiente;
    /**
     * sonidoDanho es el sonido de daño del personaje al colisionar con un enemigo
     */
    private int sonidoDanho;
    /**
     * sonidoVida es el sonido de coger una vida
     */
    private int sonidoVida;
    /**
     * sonidoVictoria es el sonido de la victoria
     */
    private int sonidoVictoria;
    /**
     * sonidoDerrota es el sonido de la derrota
     */
    private int sonidoDerrota;
    /**
     * volumen es el volumen de los sonidos
     */
    private int volumen;
    /**
     * sensorManager se usa para registrar y anular los registros de los sensores
     */
    private SensorManager sensorManager;
    /**
     * sensorLuz es el sensor de luz ambiental
     */
    private Sensor sensorLuz;
    /**
     * flagLuz nos indica si hay luz ambiental o no
     */
    private boolean flagLuz;
    /**
     * posicion es un punto exacto de la pantalla
     */
    private Point posicion;
    /**
     * pincel es un pincel con el que escribir
     */
    private Paint pincel;
    /**
     * txt es un tipo de letra
     */
    private Typeface txt;
    /**
     * flagSonido nos indica si los sonidos están activados o no
     */
    private boolean flagSonido;
    /**
     * flagSonido nos indica si la música está activada o no
     */
    private boolean flagMusica;
    /**
     * flagVibracion nos indica si la vibración está activada o no
     */
    private boolean flagVibracion;
    /**
     * flagIdioma nos indica si está en español o no
     */
    private boolean flagIdioma;
    /**
     * segundo es un segundo
     */
    private int segundo=1000;
    /**
     * tiempo es el tiempo en milisegundos
     */
    private long tiempo=System.currentTimeMillis();
    /**
     * cambioParallax nos indica que parallax se usa dependiendo de la selección de escenario
     */
    private boolean cambioParallax=true;
    /**
     * posicionMusica es la posicion de la música donde se pausa
     */
    private int posicionMusica;

    /**
     * Constructor donde se inicializan varios atributos y se cargan los recursos necesarios
     * También inicializa los arrays de los diferentes enemigos
     * Registra los diferentes elementos hardware como el sensor de luz y vibrador
     * Inicializa el audioManager y el mediaPlayer
     * Define diferentes propiedades del pincel y tipo de letra
     * @param context contexto de la aplicación
     * @param anchoPantalla tamaño del ancho de la pantalla
     * @param altoPantalla tamaño del alto de la pantalla
     * @param numEscena número de escena en el que nos encontramos
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Escena10(Context context, int anchoPantalla, int altoPantalla, int numEscena) {
        super(context, anchoPantalla, altoPantalla, numEscena);

        enemigosV1=new ArrayList<>();
        enemigosV2=new ArrayList<>();
        enemigosV3=new ArrayList<>();
        corazones=new ArrayList<>();
        treboles=new ArrayList<>();

        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        audioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if ((android.os.Build.VERSION.SDK_INT) >= 21) {
            SoundPool.Builder spb=new SoundPool.Builder();
            spb.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build());
            spb.setMaxStreams(10);
            this.sonidosAmbiente=spb.build();
        }else{
            sonidosAmbiente=new SoundPool(10,AudioManager.STREAM_MUSIC,0);
        }
        audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);

        sonidoDanho =sonidosAmbiente.load(context,R.raw.ouch,1);
        sonidoVida =sonidosAmbiente.load(context,R.raw.vida,1);
        sonidoVictoria =sonidosAmbiente.load(context,R.raw.victoria,1);
        sonidoDerrota =sonidosAmbiente.load(context,R.raw.perder,1);

        mediaPlayer=MediaPlayer.create(context,R.raw.musica);
        mediaPlayer.setVolume(50,50);
        mediaPlayer.setLooping(true);
        posicionMusica=0;

        txt= Typeface.createFromAsset(context.getAssets(),"font/stranger.ttf");
        pincel=new Paint();
        pincel.setTypeface(txt);
        pincel.setColor(Color.WHITE);
        pincel.setTextAlign(Paint.Align.RIGHT);
        pincel.setTextSize(180);

        cargaRecursos();

        if (sensorLuz!=null) {
            sensorManager.registerListener(this, sensorLuz, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }
    /**
     * Metodo que se le pasa una ruta y te devuelve el bitmap de dicha ruta
     * @param fichero La ruta
     * @return Un bitmap
     */
    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is=context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Metodo que se le pasa un bitmap y dos enteros y redimensiona la imagen a ese ancho y alto
     * @param bitmapAux El bitmap
     * @param nuevoAncho Nuevo ancho
     * @param alto  Nuevo alto
     * @return devuelve el bitmap redimensionado
     */
    public Bitmap escalaAnchura(Bitmap bitmapAux, int nuevoAncho, int alto) {
        if (nuevoAncho==bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, alto, true);
    }

    /**
     * Metodo que se le pasa un bitmap y un entero y redimensiona la imagen a esa altura
     * @param bitmapAux El bitmap
     * @param nuevoAlto Nueva altura
     * @return devuelve el bitmap redimensionado
     */
    public Bitmap escalaAltura(Bitmap bitmapAux, int nuevoAlto ) {
        if (nuevoAlto==bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) /
                bitmapAux.getHeight(), nuevoAlto, true);
    }

    /**
     * Metodo que lee la configuracion guardada
     */
    public void leerConfiguracion(){
        SharedPreferences preferencias = context.getSharedPreferences("configuracion",Context.MODE_PRIVATE);

        flagMusica=preferencias.getBoolean("musica",true);
        flagSonido=preferencias.getBoolean("sonido",true);
        flagVibracion=preferencias.getBoolean("vibracion",true);
        flagIdioma=preferencias.getBoolean("idioma",true);
    }

    /**
     * Metodo que lee la Seleccion de bosque guardado
     */
    public void leerSeleccionBosque(){
        SharedPreferences preferencias = context.getSharedPreferences("SeleccionBosque",Context.MODE_PRIVATE);
        cambioParallax=preferencias.getBoolean("mapa",true);
    }
    /**
     * Metodo que incrementa la velocidad de los scroll dependiendo de una booleana
     */
    public void cambiaVelocidadParallax(){

        if(cambioParallax){
            sc1.setVelx(anchoPantalla / 500);
            sc2.setVelx(anchoPantalla / 400);
            sc3.setVelx(anchoPantalla / 300);
            sc4.setVelx(anchoPantalla / 200);
        }else{
            sc5.setVelx(anchoPantalla / 500);
            sc6.setVelx(anchoPantalla / 400);
            sc7.setVelx(anchoPantalla / 300);
            sc8.setVelx(anchoPantalla / 200);
        }
    }

    /**
     * Metodo que restablece a la velocidad por defecto de los scroll dependiendo de una booleana
     */
    public void RestableceVelocidadParallax(){

        if(cambioParallax){
            sc1.setVelx(anchoPantalla / 900);
            sc2.setVelx(anchoPantalla / 800);
            sc3.setVelx(anchoPantalla / 700);
            sc4.setVelx(anchoPantalla / 600);
        }else{
            sc5.setVelx(anchoPantalla / 900);
            sc6.setVelx(anchoPantalla / 700);
            sc7.setVelx(anchoPantalla / 500);
            sc8.setVelx(anchoPantalla / 400);
        }
    }

    /**
     * Metodo encargado de cargar todos los recursos utilizados, así como imagenes de sprites o fondos y redimensionarlos.
     * Tambien genera personajes y otros componentes del juego
     */
    public void cargaRecursos(){

        parado=new Bitmap[15];

        for (int i=0;i<parado.length;i++){

            parado[i]=escalaAltura(getBitmapFromAssets("Personajes/Idle" +(i+1)+".png"),(int)(altoPantalla/2.8));

        }
        corriendo=new Bitmap[14];
        for (int i=0;i<corriendo.length;i++){

            corriendo[i]=escalaAltura(getBitmapFromAssets("Personajes/corriendo" +(i+1)+".png"),(int)(altoPantalla/2.8));

        }
        saltando=new Bitmap[12];
        for (int i=0;i<saltando.length;i++){

            saltando[i]=escalaAltura(getBitmapFromAssets("Personajes/jump" +(i+1)+".png"),(int)(altoPantalla/2.8));

        }
        muerto=new Bitmap[15];
        for (int i=0;i<muerto.length;i++){

            muerto[i]=escalaAltura(getBitmapFromAssets("Personajes/muerte" +(i+1)+".png"),(int)(altoPantalla/2.8));
        }

        personajePrincipal=new Personaje(anchoPantalla/5,altoPantalla - (int)(altoPantalla * 4.2) / 10, anchoPantalla,altoPantalla,parado);
        personajePrincipal.setTickFrame(80);
        personajePrincipal.setVx(-10);
        //---------------------------ENEMIGOS---------------------------------------
        enemigoCorre=new Bitmap[6];
        for (int i=0;i <enemigoCorre.length;i++){

            enemigoCorre[i]=(escalaAltura(getBitmapFromAssets("Enemigos/enemigo1/run"+i+".png"),(int)(altoPantalla/3.3)));
        }


        enemigoCorre2=new Bitmap[6];
        for (int i=0;i <enemigoCorre2.length;i++){

            enemigoCorre2[i]=(escalaAltura(getBitmapFromAssets("Enemigos/enemigo2/run" +i+".png"),(int)(altoPantalla/3.3)));
        }

        enemigoCorre3=new Bitmap[6];
        for (int i=0;i <enemigoCorre3.length;i++){

            enemigoCorre3[i]=(escalaAltura(getBitmapFromAssets("Enemigos/enemigo3/run" +i+".png"),(int)(altoPantalla/3.3)));
        }

        cant=(int)personajePrincipal.getX()+(int)(anchoPantalla);
        Log.i("en", "cargaRecursos: CAnt fuera "+cant);

        for (int i=0; i<6;i++){

            enemigoV1 = new Enemigo(cant, altoPantalla - (int)(altoPantalla * 3.7) / 10, anchoPantalla, altoPantalla, enemigoCorre);
            enemigoV1.setVx(-15);
            enemigoV1.setTickFrame(150);
            enemigosV1.add(enemigoV1);
            cant+=anchoPantalla*2.4;

        }
        cant1=(int)personajePrincipal.getX()+(int)(anchoPantalla*1.8);

        for (int i=0; i<6;i++){

            enemigoV2 = new Enemigo(cant1, altoPantalla - (int)(altoPantalla * 3.7) / 10, anchoPantalla, altoPantalla, enemigoCorre2);
            enemigoV2.setVx(-15);
            enemigoV2.setTickFrame(150);
            enemigosV2.add(enemigoV2);
            cant1+=anchoPantalla*2.4;

        }
        cant2=(int)personajePrincipal.getX()+(int)(anchoPantalla*2.5);
        for (int i=0; i<6;i++){
            enemigoV3 = new Enemigo(cant2, altoPantalla - (int)(altoPantalla * 3.7) / 10, anchoPantalla, altoPantalla, enemigoCorre3);
            enemigoV3.setVx(-15);
            enemigoV3.setTickFrame(150);
            enemigosV3.add(enemigoV3);
            cant2 += anchoPantalla*2.5;
        }


        //---------------------------FONDOS-----------------------------------------
        fondo1=new Bitmap[2];
        for (int i=0;i<fondo1.length;i++){

            fondo1[i]=escalaAnchura(getBitmapFromAssets("Fondo/fondo1/fondo"+i+".png"),anchoPantalla,altoPantalla);
        }

        fondo2=new Bitmap[2];
        for (int i=0;i<fondo2.length;i++){
            fondo2[i]=escalaAnchura(getBitmapFromAssets("Fondo/fondo2/fondo"+i+".png"),anchoPantalla,altoPantalla);

        }


        fondo3=new Bitmap[2];
        for (int i=0;i<fondo3.length;i++){
            fondo3[i]=escalaAnchura(getBitmapFromAssets("Fondo/fondo3/fondo"+i+".png"),anchoPantalla,altoPantalla);

        }
        fondo4=new Bitmap[2];
        for (int i=0;i<fondo4.length;i++){
            fondo4[i]=escalaAnchura(getBitmapFromAssets("Fondo/fondo4/fondo"+i+".png"),anchoPantalla,altoPantalla);

        }

        fondo5=new Bitmap[2];
        for (int i=0;i<fondo5.length;i++){
            fondo5[i]=escalaAnchura(getBitmapFromAssets("Fondo2/fondo1/fondo"+i+".png"),anchoPantalla,altoPantalla);

        }

        fondo6=new Bitmap[2];
        for (int i=0;i<fondo6.length;i++){
            fondo6[i]=escalaAnchura(getBitmapFromAssets("Fondo2/fondo2/fondo"+i+".png"),anchoPantalla,altoPantalla);

        }

        fondo7=new Bitmap[2];
        for (int i=0;i<fondo7.length;i++){
            fondo7[i]=escalaAnchura(getBitmapFromAssets("Fondo2/fondo3/fondo"+i+".png"),anchoPantalla,altoPantalla);

        }

        fondo8=new Bitmap[2];
        for (int i=0;i<fondo8.length;i++){
            fondo8[i]=escalaAnchura(getBitmapFromAssets("Fondo2/fondo4/fondo"+i+".png"),anchoPantalla,altoPantalla);

        }

        fondoPrincipal=new Fondo(escalaAnchura(getBitmapFromAssets("Fondo/fondoPrincipal.png"),anchoPantalla,altoPantalla),0,0,0,-1);
        fondoPrincipal2=new Fondo(escalaAnchura(getBitmapFromAssets("Fondo2/base.png"),anchoPantalla,altoPantalla),0,0,0,-1);

        fondoPrincipalNocturno=new Fondo(escalaAnchura(getBitmapFromAssets("Fondo/fondoPrincipalNocturno.png"),anchoPantalla,altoPantalla),0,0,0,-1);
        sc1=new Scroll(fondo1,anchoPantalla/900,-1);
        sc2=new Scroll(fondo2,anchoPantalla/800,-1);
        sc3=new Scroll(fondo3,anchoPantalla/700,-1);
        sc4=new Scroll(fondo4,anchoPantalla/600,-1);

        sc5=new Scroll(fondo5,anchoPantalla/900,-1);
        sc6=new Scroll(fondo6,anchoPantalla/700,-1);
        sc7=new Scroll(fondo7,anchoPantalla/500,-1);
        sc8=new Scroll(fondo8,anchoPantalla/400,-1);


        //----------VIDAS------
        int altoVidas=altoPantalla/8;
        vida1=new Vidas(escalaAltura(getBitmapFromAssets("Vidas/heart.png"),altoVidas),anchoPantalla/20,altoPantalla/30);
        vida2=new Vidas(escalaAltura(getBitmapFromAssets("Vidas/heart.png"),altoVidas),vida1.getPosicionX()+vida1.getImagen().getWidth(),vida1.getPosicionY());
        vida3=new Vidas(escalaAltura(getBitmapFromAssets("Vidas/heart.png"),altoVidas),vida2.getPosicionX()+vida2.getImagen().getWidth(),vida1.getPosicionY());

        anhadirVida =new Corazon(escalaAltura(getBitmapFromAssets("Vidas/corazon.png"),(int)(altoVidas*1.1)),(int)(personajePrincipal.getX()+anchoPantalla*3.2),altoPantalla-(altoPantalla*2)/10);
        corazones.add(anhadirVida);
        anhadirVida2 =new Corazon(escalaAltura(getBitmapFromAssets("Vidas/corazon.png"),(int)(altoVidas*1.1)),(int)(personajePrincipal.getX()+anchoPantalla*5.2),altoPantalla-(altoPantalla*3)/10);
        corazones.add(anhadirVida2);
        anhadirVida3 =new Corazon(escalaAltura(getBitmapFromAssets("Vidas/corazon.png"),(int)(altoVidas*1.1)),(int)(personajePrincipal.getX()+anchoPantalla*8),altoPantalla-(altoPantalla*3)/10);
        corazones.add(anhadirVida3);

        trebol=new Corazon(escalaAltura(getBitmapFromAssets("Vidas/trebol.png"),(int)(altoVidas*1.1)),(int)(personajePrincipal.getX()+anchoPantalla*9),altoPantalla/2);
        treboles.add(trebol);
        //-------------Botones---------------
        btnDerecha=new Botones(escalaAnchura(getBitmapFromAssets("Botones/btnDerecha.png"),anchoPantalla/10,altoPantalla/6),anchoPantalla/15,altoPantalla-(altoPantalla*3)/10);
        btnSalto=new Botones(escalaAnchura(getBitmapFromAssets("Botones/btnSalto.png"),anchoPantalla/10,altoPantalla/6),anchoPantalla-(anchoPantalla*2)/10,altoPantalla-(altoPantalla*3)/10);
        anchoBotonD=btnDerecha.getPosicionX() + btnDerecha.getImagen().getWidth();
        altoBotonD=btnDerecha.getPosicionY() + btnDerecha.getImagen().getHeight();
        anchoBotonS=btnSalto.getPosicionX() + btnSalto.getImagen().getWidth();
        altoBotonS=btnSalto.getPosicionY() + btnSalto.getImagen().getHeight();

    }
    /**
     * Metodo donde se actualiza la física de los scroll,personajes y otros elementos
     */
    @Override
    public void actualizaFisica()
    {
        super.actualizaFisica();
        leerConfiguracion();
        leerSeleccionBosque();

            if(cambioParallax){
                sc1.mover();
                sc2.mover();
                sc3.mover();
                sc4.mover();
            }else{
                sc5.mover();
                sc6.mover();
                sc7.mover();
                sc8.mover();
            }

            personajePrincipal.cambiaFrame();
            personajePrincipal.salta();

            for (Corazon e : treboles) {
            e.mueve(8);
            }

            for (Corazon e : corazones) {
                e.mueve(8);
            }

            for (Enemigo e : enemigosV1) {
                e.cambiaFrameEnemigo();
                e.mueveEnemigo();
            }
            for (Enemigo e : enemigosV2) {
                e.cambiaFrameEnemigo();
                e.mueveEnemigo();
            }
            for (Enemigo e : enemigosV3) {
                e.cambiaFrameEnemigo();
                e.mueveEnemigo();
            }

            colisiones();
            colisionVida();
            colisionFinal();
            puntuacion();
            musica();


    }

    /**
     * Método encargado de dibujar los diferentes elementos en el lienzo
     * @param c el lienzo
     */
    @Override
    public void dibuja(Canvas c) {

        super.dibuja(c);

            if (flagLuz) {
                if(cambioParallax){
                    fondoPrincipal.dibuja(c);
                }else{
                    fondoPrincipal2.dibuja(c);
                }
            } else {
                fondoPrincipalNocturno.dibuja(c);
            }
            if(cambioParallax){
                sc1.dibuja(c);
                sc2.dibuja(c);
                sc3.dibuja(c);
            }else{
                sc5.dibuja(c);
                sc6.dibuja(c);
                sc7.dibuja(c);
            }
            vida1.dibuja(c);
            vida2.dibuja(c);
            vida3.dibuja(c);
            c.drawText("Puntuacion: "+ contPuntos,anchoPantalla-(int)(anchoPantalla*0.5)/10,altoPantalla/8,pincel);
            personajePrincipal.dibuja(c);

            for (Corazon e : treboles) {
            e.dibuja(c);
            }
            for (Corazon e : corazones) {
                e.dibuja(c);
            }

            for (Enemigo e : enemigosV1) {
                e.dibuja(c);
            }

            for (Enemigo e : enemigosV2) {
                e.dibuja(c);
            }
            for (Enemigo e : enemigosV3) {
                e.dibuja(c);
            }
            if(cambioParallax){
                sc4.dibuja(c);
            }else{
                sc8.dibuja(c);
            }
            btnDerecha.dibuja(c);
            btnSalto.dibuja(c);


    }


    /**
     * Metodo que gestiona las pulsaciones sobre la pantalla del dispositivo
     * @param event parametro donde viene la información sobre el evento
     * @return numero de escena
     */

    public int onTouchEvent(MotionEvent event) {
        int aux=super.onTouchEvent(event);
        int accion = event.getActionMasked();
        int x = (int) event.getX();
        int y = (int) event.getY();
        int pointerIndex = event.getActionIndex();

        switch (accion) {
            case MotionEvent.ACTION_DOWN:

                    if (x > btnDerecha.getPosicionX() && x < anchoBotonD && y > btnDerecha.getPosicionY() && y < altoBotonD) {
                        if (flagParado) {
                            personajePrincipal.frames = corriendo;
                            flagParado = false;

                            cambiaVelocidadParallax();

                        }
                    }

                    if (x > btnSalto.getPosicionX() && x < anchoBotonS && y > btnSalto.getPosicionY() && y < altoBotonS) {
                        if (flagParado) {
                            personajePrincipal.setTickFrame(80);
                            personajePrincipal.frames = saltando;
                            personajePrincipal.sube = true;
                            flagParado = false;
                        }
                    }

                break;
            case MotionEvent.ACTION_POINTER_DOWN:

                posicion = new Point((int) event.getX(pointerIndex), (int) event.getY(pointerIndex));
                if ((x > btnDerecha.getPosicionX() && x < anchoBotonD && y > btnDerecha.getPosicionY() && y < altoBotonD) && (posicion.x > btnSalto.getPosicionX() && posicion.x < anchoBotonS && posicion.y > btnSalto.getPosicionY() && posicion.y < altoBotonS)) {

                    personajePrincipal.setTickFrame(100);
                    personajePrincipal.frames = saltando;
                    personajePrincipal.sube = true;

                }


                break;
            case MotionEvent.ACTION_UP:

                if (!flagParado) {
                    flagParado = true;
                    personajePrincipal.frames = parado;

                    RestableceVelocidadParallax();

                }

                break;
            case MotionEvent.ACTION_POINTER_UP:

                if ((x > btnDerecha.getPosicionX() && x < anchoBotonD && y > btnDerecha.getPosicionY() && y < altoBotonD) && (posicion.x > btnSalto.getPosicionX() && posicion.x < anchoBotonS && posicion.y > btnSalto.getPosicionY() && posicion.y < altoBotonS)) {
                    flagParado = false;
                    personajePrincipal.frames = corriendo;
                }

                break;
        }
        return this.numEscena;

    }



    /**
     * Metodo que genera una puntuación por el tiempo transcurrido
     */
    public void puntuacion(){
        if(System.currentTimeMillis()-tiempo>segundo){
            contPuntos +=1;
            tiempo=System.currentTimeMillis();

        }
    }
    /**
     * Metodo que gestiona el play/pause de la musica
     */
    public void musica(){
        if (flagMusica) {
            if(flagMusicaParada){
                if(mediaPlayer!=null && mediaPlayer.isPlaying()==false){
                    mediaPlayer.seekTo(posicionMusica);
                    mediaPlayer.start();
                }
            }else{
                mediaPlayer.start();
            }

        } else {

            if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                posicionMusica=mediaPlayer.getCurrentPosition();
                flagMusicaParada=true;
            }
        }
    }

    /**
     * Metodo que gestiona el sonido de Daño del personaje
     */
    public void sonido(){

        volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (flagSonido) {
            sonidosAmbiente.setVolume(sonidoDanho,1,1);
            sonidosAmbiente.play(sonidoDanho, volumen, volumen, 1, 0, 1);
        } else {
            sonidosAmbiente.setVolume(sonidoDanho,0,0);
        }
    }

    /**
     * Metodo que gestiona el sonido de la derrota
     */
    public void  sonidoDerrota(){
        volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (flagSonido) {
            sonidosAmbiente.setVolume(sonidoDerrota,1,1);
            sonidosAmbiente.play(sonidoDerrota, volumen, volumen, 1, 0, 1);

        } else {
            sonidosAmbiente.setVolume(sonidoVida,0,0);
        }
    }


    /**
     * Metodo en donde se gestionan las colisiones entre personajes y su sonido
     */
    public void colisiones(){

        for (int i = enemigosV1.size() - 1; i >= 0; i--) {
            if (enemigosV1.get(i).isColision()) {
                if ((enemigosV1.get(i).colision(personajePrincipal.getHitbox())) || enemigosV1.get(i).colision(personajePrincipal.getHitbox2())) {
                   enemigosV1.get(i).setColision(false);
                    personajePrincipal.vidas--;

                    if (contPuntos - 2 < 0) {
                        contPuntos = 0;
                    } else {
                        contPuntos -= 2;
                    }
                    sonido();


                    if (flagVibracion) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//vibrator
                            vibrator.vibrate(VibrationEffect.createOneShot(350, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(350);
                        }
                    }

                    if (personajePrincipal.vidas >= 0) {

                        if (personajePrincipal.vidas == 2) {

                            vida3.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                        } else if (personajePrincipal.vidas == 1) {
                            vida2.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                        } else if (personajePrincipal.vidas == 0) {
                            vida1.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                            personajePrincipal.frames = muerto;
                            mediaPlayer.stop();
                            sonidoDerrota();
                            cambiaEscenaDerrota =true;

                        }
                    }
                }
            }

        }
        colisionesV2();
        colisionesV3();
        guardarPuntuacion();
    }

    /**
     * Metodo que gestiona las colisiones de los EnemigosV2 con el personaje
     */
    public void colisionesV2(){
        for (int i = enemigosV2.size() - 1; i >= 0; i--) {
            if(enemigosV2.get(i).isColision()) {
                if ((enemigosV2.get(i).colision(personajePrincipal.getHitbox()))||(enemigosV2.get(i).colision(personajePrincipal.getHitbox2()))) {
                    enemigosV2.get(i).setColision(false);
                    if(contPuntos-4<0){
                        contPuntos=0;
                    }else {
                        contPuntos-=4;
                    }
                    sonido();
                    if(flagVibracion){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(350, VibrationEffect.DEFAULT_AMPLITUDE));
                        }else {
                            vibrator.vibrate(350);
                        }
                    }
                    if (personajePrincipal.vidas != 0) {

                        if (personajePrincipal.vidas == 3) {
                            personajePrincipal.vidas-=2;
                            vida3.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                            vida2.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                        } else if (personajePrincipal.vidas == 2) {
                            personajePrincipal.vidas-=2;
                            vida2.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                            vida1.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                            personajePrincipal.frames = muerto;
                            mediaPlayer.stop();
                            sonidoDerrota();
                            cambiaEscenaDerrota =true;

                        } else if (personajePrincipal.vidas == 1) {
                            personajePrincipal.vidas--;
                            vida1.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                            personajePrincipal.frames = muerto;
                            mediaPlayer.stop();
                            sonidoDerrota();
                            cambiaEscenaDerrota =true;

                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo que gestiona las colisiones de los EnemigosV3 con el personaje
     */
    public void colisionesV3(){
        for (int i = enemigosV3.size() - 1; i >= 0; i--) {
            if(enemigosV3.get(i).isColision()) {
                if ((enemigosV3.get(i).colision(personajePrincipal.getHitbox()))||(enemigosV3.get(i).colision(personajePrincipal.getHitbox2()))) {
                    enemigosV3.get(i).setColision(false);
                    if(contPuntos-6<0){
                        contPuntos=0;
                    }else {
                        contPuntos-=6;
                    }
                    sonido();
                    if(flagVibracion){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//vibrator
                            vibrator.vibrate(VibrationEffect.createOneShot(350, VibrationEffect.DEFAULT_AMPLITUDE));
                        }else {
                            vibrator.vibrate(350);
                        }
                    }
                    if (personajePrincipal.vidas != 0) {
                        personajePrincipal.vidas=0;
                        vida3.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                        vida2.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                        vida1.setImagen(escalaAltura(getBitmapFromAssets("Vidas/border.png"), altoPantalla / 8));
                        personajePrincipal.frames = muerto;
                        mediaPlayer.stop();
                        sonidoDerrota();
                        cambiaEscenaDerrota =true;
                    }
                }
            }
        }
    }


    /**
     * Metodo que gestiona la colision del personaje con la vida y su respectivo sonido
     */
    public void colisionVida(){
        for (int i = corazones.size() - 1; i >= 0; i--) {
            if (colisionVida) {
                if (corazones.get(i).colisionCorazon(personajePrincipal.getHitbox2())) {
                    colisionVida = false;

                    if(personajePrincipal.vidas!=3 && personajePrincipal.vidas !=0){

                        volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                        if (flagSonido) {
                            sonidosAmbiente.setVolume(sonidoVida,1,1);
                            sonidosAmbiente.play(sonidoVida, volumen, volumen, 1, 0, 1);

                        } else {
                            sonidosAmbiente.setVolume(sonidoVida,0,0);
                        }

                        corazones.remove(i);
                    }

                    if (personajePrincipal.vidas == 2) {
                        personajePrincipal.vidas++;
                        vida3.setImagen(escalaAltura(getBitmapFromAssets("Vidas/heart.png"), altoPantalla / 8));
                    } else if (personajePrincipal.vidas == 1) {
                        personajePrincipal.vidas++;
                        vida2.setImagen(escalaAltura(getBitmapFromAssets("Vidas/heart.png"), altoPantalla / 8));
                    }
                }

            } else {
                if (!anhadirVida.colisionCorazon(personajePrincipal.getHitbox2())) {
                    colisionVida = true;

                }
            }
        }
    }

    /**
     * Metodo que gestiona la colision del personaje con el trebol final y su respectivo sonido
     */
    public void colisionFinal(){
        for (int i = treboles.size() - 1; i >= 0; i--) {
            if (treboles.get(i).colisionCorazon(personajePrincipal.getHitbox()) || treboles.get(i).colisionCorazon(personajePrincipal.getHitbox2())) {
                volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                if (flagSonido) {
                    sonidosAmbiente.setVolume(sonidoVictoria, 1, 1);
                    sonidosAmbiente.play(sonidoVictoria, volumen, volumen, 1, 0, 1);

                } else {
                    sonidosAmbiente.setVolume(sonidoVictoria, 0, 0);
                }
                treboles.remove(i);
                cambiaEscenaVictoria=true;
                mediaPlayer.stop();
                numEscena = 8;
            }
        }
    }


    /**
     * Metodo que guarda la puntuación del Jugador
     */
    public void guardarPuntuacion(){
        SharedPreferences preferencias = context.getSharedPreferences("puntuacion",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=preferencias.edit();
        editor.putInt("puntos",contPuntos);
        editor.commit();

    }

    /**
     * Metodo que mide los valores del sensor
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        float luz=event.values[0];

        if(luz>10){
            flagLuz=true;
        }else{
            flagLuz=false;
        }
    }

    /**
     * Metodo que no usamos
     * @param sensor
     * @param accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
