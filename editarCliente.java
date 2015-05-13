package com.example.gavin.aplicacaosiga;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.exemplo.gavin.Model.ModelCliente;


public class editarCliente extends Activity {

    private ClienteBO clibo;
    private ModelCliente cliente;
    private EditText edtNome, edtEndereco,edtbairro, edtcpf, edtcidade;
    private Button btnatualizar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        setTitle("Atualizar Pessoas");

        clibo = new ClienteBO(this);

        cliente = (ModelCliente) getIntent().getExtras().getSerializable("cliente");

        edtNome = (EditText) findViewById(R.id.edt_nome);
        edtEndereco =(EditText) findViewById(R.id.edt_endereco);
        edtbairro = (EditText) findViewById(R.id.edt_bairro);
        edtcpf = (EditText) findViewById(R.id.edt_cpf);
        edtcidade = (EditText) findViewById(R.id.edt_cidade);

        btnatualizar.setText("Atualizar");

        edtNome.setText(cliente.getNOME());
    }



}
