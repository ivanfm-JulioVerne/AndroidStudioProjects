package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float numEscrito=0;
    float num1=0;
    float numResu=1;
    int opeRealiza=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void escribeNum(View view){

        Button bPulsado = (Button) view;
        TextView tDisplay =((TextView)findViewById(R.id.tDisplay));

        int idBoton=bPulsado.getId();
        //Toast.makeText(this,idBoton+"",Toast.LENGTH_LONG).show();

        if (opeRealiza==5){
            numEscrito=0;
            opeRealiza=0;
        }

        switch(idBoton){
            case 2131230807:
                numEscrito=numEscrito*10+0;
                break;
            case 2131230808:
                numEscrito=numEscrito*10+1;
                break;
            case 2131230809:
                numEscrito=numEscrito*10+2;
                break;
            case 2131230810:
                numEscrito=numEscrito*10+3;
                break;
            case 2131230811:
                numEscrito=numEscrito*10+4;
                break;
            case 2131230812:
                numEscrito=numEscrito*10+5;
                break;
            case 2131230813:
                numEscrito=numEscrito*10+6;
                break;
            case 2131230814:
                numEscrito=numEscrito*10+7;
                break;
            case 2131230815:
                numEscrito=numEscrito*10+8;
                break;
            case 2131230816:
                numEscrito=numEscrito*10+9;
                break;
        }
        tDisplay.setText(numEscrito+"");
    }
    public void operacion(View view) {
        Button bPulsado = (Button) view;
        TextView tDisplay = ((TextView) findViewById(R.id.tDisplay));

        int idBoton = bPulsado.getId();
        if (opeRealiza!=5) {
            num1 = numEscrito;
        }

        switch (idBoton) {
            case 2131230820:
                opeRealiza = 1;
                break;
            case 2131230818:
                opeRealiza = 2;
                break;
            case 2131230822:
                opeRealiza = 3;
                break;
            case 2131230821:
                opeRealiza = 4;
                break;

        }
        numEscrito=0;
        tDisplay.setText("");
    }
    public void borra(View view){
        TextView tDisplay = ((TextView) findViewById(R.id.tDisplay));

        num1=0;
        numEscrito=0;
        numResu=0;
        opeRealiza=0;
        tDisplay.setText("");

    }

    public void delete(View view){
        TextView tDisplay = ((TextView) findViewById(R.id.tDisplay));

        numEscrito=0;
        tDisplay.setText("");

    }

    public void igual(View view){
        TextView tDisplay = ((TextView) findViewById(R.id.tDisplay));

        switch(opeRealiza){
            case 1:
                numResu=num1*numEscrito;
                tDisplay.setText(numResu+"");
                opeRealiza=5;
                num1=numResu;
                break;
            case 2:
                if(numEscrito==0.0f){
                    tDisplay.setText("ERROR '/O'");
                    num1=0;
                    numEscrito=0;
                    numResu=0;
                    opeRealiza=0;
                }else{
                    numResu=num1/numEscrito;
                    tDisplay.setText(numResu+"");
                    opeRealiza=5;
                    num1=numResu;
                }
                break;
            case 3:
                numResu=num1+numEscrito;
                tDisplay.setText(numResu+"");
                opeRealiza=5;
                num1=numResu;
                break;
            case 4:
                numResu=num1-numEscrito;
                tDisplay.setText(numResu+"");
                opeRealiza=5;
                num1=numResu;
                break;
        }

        tDisplay.setText(numResu+"");
    }
}