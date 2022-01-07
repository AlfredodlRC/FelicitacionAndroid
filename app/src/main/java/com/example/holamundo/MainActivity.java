package com.example.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation ani01;
        Animation ani02;
        Animation ani03;
        Animation ani04;

        // Elementos de la vista
        TextView cabecera;
        TextView cuerpo;
        ImageView imagen_fondo;
        FloatingActionButton botonIzquierdo;


        // controlador de la rotacion de la imagen de fondo
        Handler customHandler;
        Runnable updateTimerThread;



            // reproductor de musica
        MediaPlayer reproductor01 = MediaPlayer.create(this,R.raw.ding_dong);
        MediaPlayer reproductor02 = MediaPlayer.create(this,R.raw.christmas_day);
        MediaPlayer reproductor03 = MediaPlayer.create(this,R.raw.christmas_atmosphere);
        MediaPlayer reproductor04 = MediaPlayer.create(this,R.raw.christmas_story);
        reproductor01.start();

        // animaciones
        ani01 =  AnimationUtils.loadAnimation (getApplicationContext(), R.anim.animacion_cabecera_01);
        ani02 =  AnimationUtils.loadAnimation (getApplicationContext(), R.anim.animacion_cuerpo_01);
        ani03 =  AnimationUtils.loadAnimation (getApplicationContext(), R.anim.animacion_cabecera_02);
        ani04 =  AnimationUtils.loadAnimation (getApplicationContext(), R.anim.animacion_cuerpo_02);

        // Elementos de la vista
        cabecera = (TextView) findViewById(R.id.texto_cabecera);
        cuerpo =(TextView) findViewById(R.id.texto_cuerpo);
        imagen_fondo = (ImageView) findViewById(R.id.imagen_fondo);
        botonIzquierdo = (FloatingActionButton) findViewById(R.id.botonIzquierdo);

        cabecera.startAnimation(ani01);
        cuerpo.startAnimation(ani02);

        // controlador de la rotacion de la imagen de fondo
        customHandler = new Handler();
        updateTimerThread = new Runnable() {
            final List<Integer> imagenes = new ArrayList<>(
                    Arrays.asList(R.drawable.muneco1,R.drawable.muneco2,R.drawable.muneco3,
                            R.drawable.muneco4,R.drawable.muneco5)
            );
            Integer indice = 0;
            public void run() {
                imagen_fondo.setImageResource(imagenes.get(indice));
                indice = indice + 1;
                if (indice == imagenes.size()) {
                    indice = 0;
                }
                customHandler.postDelayed(this, 2000);
            }
        };

        // Controladores del boton izquierdo
        botonIzquierdo.setOnClickListener(
                new Button.OnClickListener() {
                    Integer conmutador = 0;
                    public void onClick(View v) {
                        cabecera.clearAnimation();
                        cuerpo.clearAnimation();
                        switch (conmutador) {
                            case 0:
                                cabecera.startAnimation(ani01);
                                cuerpo.startAnimation(ani02);
                                break;
                            case 1:
                                cabecera.startAnimation(ani01);
                                cuerpo.startAnimation(ani04);
                                break;
                            case 2:
                                cabecera.startAnimation(ani03);
                                cuerpo.startAnimation(ani04);
                                break;
                            case 3:
                                cabecera.startAnimation(ani03);
                                cuerpo.startAnimation(ani02);
                                break;

                        }
                        conmutador = conmutador + 1;
                        if (conmutador == 5) {
                            conmutador = 0;
                        }
                    }
                });

        customHandler.postDelayed(updateTimerThread, 2000);


        botonIzquierdo.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    Integer indice = 1;
                    public boolean onLongClick(View v) {
                        switch (indice) {
                            case 0:
                                reproductor01.stop();
                                reproductor02.start();
                                break;
                            case 1:
                                reproductor02.stop();
                                reproductor03.start();
                                break;
                            case 2:
                                reproductor03.stop();
                                reproductor04.start();
                                break;
                            case 3:
                                reproductor04.stop();
                                reproductor01.start();
                                break;
                        }
                        indice = indice + 1;
                        if (indice == 4) {
                            indice = 0;
                        }
                        return true;
                    }
                });





    }

}