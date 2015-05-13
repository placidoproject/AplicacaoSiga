package com.example.gavin.aplicacaosiga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class principal extends Activity {

    Button btapiario,btcliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btapiario = (Button) findViewById(R.id.btn_CadApiario);
        btcliente = (Button) findViewById(R.id.btn_CadClientes);

        btapiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirApiario();
            }
        });

        btcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirCliente();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void exibirApiario(){
        Intent chamar = new Intent(this,apiario.class);
        startActivity(chamar);

    }

    public void exibirCliente(){
        Intent chamar = new Intent(this,Cadastro_cliente.class);
        startActivity(chamar);

    }
}
