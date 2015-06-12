package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gavin.aplicacaosiga.BO.ClienteBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.Model.ModelCliente;
import com.exemplo.gavin.msg.MensagemUtil;


public class editarCliente extends Activity {

    private ClienteBO clibo;
    private ModelCliente cliente;
    private EditText edtNome = null;
    private EditText edtEndereco = null;
    private EditText edtbairro = null;
    private EditText edtcpf = null;
    private EditText edtcidade = null;
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
        btnatualizar =(Button) findViewById(R.id.btn_cadastraCliente);

        btnatualizar.setText("Atualizar");

        btnatualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtualizacaoCliente();
            }
        });

        edtNome.setText(cliente.getNOME());
        edtEndereco.setText(cliente.getENDERECO());
        edtbairro.setText(cliente.getBAIRRO());
        edtcpf.setText(cliente.getCPF());
        edtcidade.setText(cliente.getCIDADE());

    }
    private void AtualizacaoCliente(){
        ModelCliente mo = new ModelCliente();
        mo.setID_CLIENTE(cliente.getID_CLIENTE());
        mo.setNOME(edtNome.getText().toString());
        mo.setCPF(edtEndereco.getText().toString());
        mo.setENDERECO(edtEndereco.getText().toString());
        mo.setBAIRRO(edtbairro.getText().toString());
        mo.setCIDADE(edtcidade.getText().toString());
        clibo.AtualizarCliente(mo);
        MensagemUtil.addMsg(this, "CLiente atualizado com sucesso!");

        Intent i = new Intent(this,listaCliente.class);
        startActivity(i);
        finish();
    }


}
