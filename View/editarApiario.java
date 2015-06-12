package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gavin.aplicacaosiga.BO.ApiarioBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.Model.ModelApiario;
import com.exemplo.gavin.msg.MensagemUtil;


public class editarApiario extends Activity  {

    private ApiarioBO bo;
    private ModelApiario modelapiario;
    private EditText edtApiario;
    private Button btAtualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_apiario);

        setTitle("Atualizar apiario");

        bo = new ApiarioBO(this);
        modelapiario = (ModelApiario) getIntent().getExtras().getSerializable("apiario");

        edtApiario = (EditText) findViewById(R.id.edt_descricao);
        btAtualizar = (Button) findViewById(R.id.btn_entrar);

        btAtualizar.setText("Aualizar");

        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtualizacaoApiario();
            }
        });


        edtApiario.setText(modelapiario.getDESCRICAO());

    }

    private void AtualizacaoApiario(){
        ModelApiario mo = new ModelApiario();
        mo.setID_APIARIO(modelapiario.getID_APIARIO());
        mo.setDESCRICAO(modelapiario.getDESCRICAO());
        bo.AtualizarApiario(mo);
        MensagemUtil.addMsg(this, "Apiario atualizado com sucesso!");

        Intent i = new Intent(this,listarApiario.class);
        startActivity(i);
        finish();
    }




}
