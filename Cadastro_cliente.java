package com.example.gavin.aplicacaosiga;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.exemplo.gavin.DAO.DaoCliente;
import com.exemplo.gavin.Model.ModelCliente;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;


public class Cadastro_cliente extends Activity {

    private EditText edtNome, edtEndereco,edtbairro, edtcpf, edtcidade;

    private ClienteBO cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        cliente = new ClienteBO(this);


        edtNome = (EditText) findViewById(R.id.edt_nome);
        edtEndereco =(EditText) findViewById(R.id.edt_endereco);
        edtbairro = (EditText) findViewById(R.id.edt_bairro);
        edtcpf = (EditText) findViewById(R.id.edt_cpf);
        edtcidade = (EditText) findViewById(R.id.edt_cidade);
    }

            public void CadastrarCliente(View v){

                ModelCliente mo = new ModelCliente();
                mo.setNOME(edtNome.getText().toString());
                mo.setCPF(Long.parseLong(edtcpf.getText().toString()));
                mo.setENDERECO(edtEndereco.getText().toString());
                mo.setBAIRRO(edtbairro.getText().toString());
                mo.setCIDADE(edtcidade.getText().toString());

                  ValidacaoCliente resultado =  cliente.cadastrarCliente(mo);
                  MensagemUtil.addMsg(this,resultado.getMensagem());
                  Intent i = new Intent(this,listaCliente.class);
                  startActivity(i);
                  finish();

            }





}
