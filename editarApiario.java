package com.example.gavin.aplicacaosiga;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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


public class editarApiario extends Activity  {

    private int ID = 0;
    private EditText edtId;
    private EditText edtApiario;
    private Button btAtualiza, btApagar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_apiario);

        Intent it = getIntent();
        ID = it.getIntExtra("codigo",1);



       final DaoApiario dao = new DaoApiario(getBaseContext());
        final ModelApiario Mo = dao.pesquisaID(ID);


        edtId = (EditText) findViewById(R.id.edt_codigo);
        edtApiario = (EditText) findViewById(R.id.edt_descricao);
        btAtualiza = (Button) findViewById(R.id.btn_atualizar);
        btApagar = (Button) findViewById(R.id.btn_apagar);

        edtId.setText(Mo.getID_APIARIO().toString());
        edtApiario.setText(Mo.getDESCRICAO());

        btApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msg = new AlertDialog.Builder(editarApiario.this);
                msg.setMessage("Deseja excluir o Apiário" + Mo.getDESCRICAO() + "?");
                msg.setPositiveButton("SIM",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       if (dao.deleteApiario(Mo)){
                           Toast.makeText(getBaseContext(),"Sucesso!",Toast.LENGTH_SHORT);
                       }
                            finish();
                    }
                });

                msg.setNegativeButton("NAO",null);

                msg.show();

            }
        });


        btAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelApiario mo = new ModelApiario();
                mo.setID_APIARIO(ID);
                mo.setDESCRICAO(edtApiario.getText().toString());

                DaoApiario dao = new DaoApiario(getBaseContext());
                if(dao.alterarApiaro(mo)){
                    Toast.makeText(getBaseContext(),"Sucesso!",Toast.LENGTH_SHORT);

                    finish();;
                }


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
