package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gavin.aplicacaosiga.BO.ApiarioBO;
import com.example.gavin.aplicacaosiga.R;

import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.Model.ModelApiario;
import com.exemplo.gavin.msg.MensagemUtil;


public class apiario extends Activity  {

    private EditText edtApiario;
    private Button btApiario;

    private ApiarioBO apiario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiario);

        apiario = new ApiarioBO(this);

        edtApiario = (EditText) findViewById(R.id.edt_descricao);
        btApiario = (Button) findViewById(R.id.btn_entrar);

        btApiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarApiario(v);
            }
        });


    }


    public void CadastrarApiario(View v){

        ModelApiario mo = new ModelApiario();
        mo.setDESCRICAO(edtApiario.getText().toString());

        Validation resultado =  apiario.cadastrarApiario(mo);
        MensagemUtil.addMsg(this, resultado.getMensagem());
        Intent i = new Intent(this,listarApiario.class);
        startActivity(i);
        finish();

    }



}
