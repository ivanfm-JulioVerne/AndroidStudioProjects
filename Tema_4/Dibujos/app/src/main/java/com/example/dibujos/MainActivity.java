package com.example.dibujos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
           Inicializaremos los elementos esenciales para dibujar:
           1) ImageView, es el objeto que se mostrará en pantalla dibujado.
           2) Bitmap es una matriz de píxeles de ese objeto que es lo que pintaremos.
           3) Canvas es un "lienzo", es la herramienta con la que dibujaremos en ese Bitmap
           4) Paint es la pintura con la que pintaremos.
           ImageView es la vista y por tanto la tenemos que tener identificada en nuestros layouts,
           le tenemos que dar una altura y anchura y un identificador. Para un juego lo normal es que ocupe toda la
           pantalla del móvil, aunque podemos combinarla mediante layouts con otros elementos.
        */
        ImageView myImageView=(ImageView)findViewById(R.id.imagen);

        Bitmap myBitmap = Bitmap.createBitmap(750,1500,Bitmap.Config.ARGB_8888);
        Canvas myCanvas=new Canvas (myBitmap);

        /*
            Ahora podemos dibujar con nuestro canvas en el bitmap que hemos creado y que luego asociaremos a la ImageView
            Para ello usaremos la clase Paint que es la pintura con la que pintaré.

        */

        Paint paint=new Paint();

        //Fondo de pantalla negro
        myCanvas.drawColor(Color.BLACK);
        //dibujo punto (no se verá)
        paint.setColor(Color.argb(255,255,10,10));
        myCanvas.drawPoint(0,0,paint);
        //dibujamos una linea
        myCanvas.drawLine(0,0,750,1500,paint);
        //escribimos un texto de tamaño 100 y cambiamos de color
        paint.setTextSize(100f);
        paint.setColor(Color.BLUE);
        myCanvas.drawText("hola mundo",10,750,paint);
        //dibujamos un rectángulo
        paint.setColor(Color.GREEN);
        myCanvas.drawRect(500,10,200,200,paint);
        //dibujamos un círculo
        paint.setColor(Color.YELLOW);
        myCanvas.drawCircle(200,200,100,paint);

        //finalmente asociamos el canvas a la ImageView
        myImageView.setImageBitmap(myBitmap);



    }
}