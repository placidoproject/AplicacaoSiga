package com.example.gavin.aplicacaosiga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.Model.ModelApiario;


public class apiario extends Activity  {

    private EditText edtApiario;
    private Button btApiario, btListar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiario);

        edtApiario = (EditText) findViewById(R.id.edt_descricao);
        btApiario = (Button) findViewById(R.id.btn_entrar);
        btListar = (Button) findViewById(R.id.btn_listar);



        btApiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelApiario mo = new ModelApiario();
                mo.setDESCRICAO(edtApiario.getText().toString());

                DaoApiario dao = new DaoApiario(getBaseContext());
               if(dao.inserirApiario(mo)){
                   Toast.makeText(getBaseContext(),"Sucesso!",Toast.LENGTH_SHORT).show();
               }


            }
        });

        findViewById(R.id.btn_listar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),listarApiario.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_apiario, menu);
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



}
