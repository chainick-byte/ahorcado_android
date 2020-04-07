package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int numeroDeFallos = 0;
    String palabraEligida=eligePlabra();
    boolean unavez=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            //crea la ventana del juego
            getSupportFragmentManager().beginTransaction().add(R.id.ventanaJuego, new VentanaAhorcado()).commit();
        }
    }
    public String eligePlabra(){
        String[] palabraOculta ={"CETYS","ORDENADOR","VEHICULO", "COHETE", "GIGANTE"} ;
        Random r=new Random();
        int posicionArray=r.nextInt(palabraOculta.length);
        return palabraOculta[posicionArray];
    }

    public void botonPulsado (View vista){
        Button boton = findViewById(vista.getId());
        boton.setVisibility(View.INVISIBLE);
        chequeaLetra(boton.getText().toString());
    }


    private void chequeaLetra(String letra){
        letra = letra.toUpperCase();
        ImageView imagenAhorcado = ((ImageView) findViewById(R.id.imagenAhorcado));
        TextView textoConGuiones = ((TextView) findViewById(R.id.palabraConGuiones));
        String auxiliar="";

        if(unavez){
            for(int i = 0; i<palabraEligida.length();i++){
                auxiliar=auxiliar+"_ ";
            }
            textoConGuiones.setText(auxiliar);

        }
        unavez=false;

        String palabraConGuiones=textoConGuiones.getText().toString();

        boolean acierto = false;

            for (int i = 0; i < palabraEligida.length(); i++) {
                if (palabraEligida.charAt(i) == letra.charAt(0)) {
                    //quita el guión bajo de la letra correspondiente
                    palabraConGuiones = palabraConGuiones.substring(0, 2 * i)
                            + letra
                            + palabraConGuiones.substring(2 * i + 1);
                    acierto = true;
                }
            }
            if (!palabraConGuiones.contains("_")) {
                imagenAhorcado.setImageResource(R.drawable.acertastetodo);
            }

            textoConGuiones.setText(palabraConGuiones);

        if (!acierto){
            numeroDeFallos++;

            switch (numeroDeFallos){
                case 0 : imagenAhorcado.setImageResource(R.drawable.ahorcado_0); break;
                case 1 : imagenAhorcado.setImageResource(R.drawable.ahorcado_1); break;
                case 2 : imagenAhorcado.setImageResource(R.drawable.ahorcado_2); break;
                case 3 : imagenAhorcado.setImageResource(R.drawable.ahorcado_3); break;
                case 4 : imagenAhorcado.setImageResource(R.drawable.ahorcado_4); break;
                case 5 : imagenAhorcado.setImageResource(R.drawable.ahorcado_5); break;
                default : imagenAhorcado.setImageResource(R.drawable.ahorcado_fin); break;
            }
        }

    }






}