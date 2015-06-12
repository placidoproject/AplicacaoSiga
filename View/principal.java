package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gavin.aplicacaosiga.R;
import com.example.gavin.aplicacaosiga.tarefa;


public class principal extends Activity {

    Button btapiario,btcliente,btcaixa,btfornecedor,btProduto,btAbelha,btTarefa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btapiario = (Button) findViewById(R.id.btn_CadApiario);
        btcliente = (Button) findViewById(R.id.btn_CadClientes);
        btcaixa = (Button) findViewById(R.id.btn_CadCaixas);
        btfornecedor = (Button) findViewById(R.id.btn_CadFornecedor);
        btProduto = (Button) findViewById(R.id.btn_CadProduto);
        btAbelha = (Button) findViewById(R.id.btn_CadRainha);
        btTarefa = (Button) findViewById(R.id.btn_CadTarefa);


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

        btcaixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirCaixa();
            }
        });

        btfornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirFornecedor();
            }
        });

        btProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirEstoque();
            }
        });

        btAbelha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               exibirAbelhas();
            }
        });

        btTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirTarefas();
            }
        });

        btTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirRelatorioTarefas();
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

    public void exibirCaixa(){
        Intent chamar = new Intent(this,caixa.class);
        startActivity(chamar);

    }

    public void exibirFornecedor(){
        Intent chamar = new Intent(this,fornecedor.class);
        startActivity(chamar);

    }
    public void exibirEstoque(){
        Intent chamar = new Intent(this,estoque.class);
        startActivity(chamar);

    }

    public void exibirAbelhas(){
        Intent chamar = new Intent(principal.this,abelhaRainha.class);
        startActivity(chamar);

    }


    public void exibirTarefas(){
        Intent chamar = new Intent(this,tarefa.class);
        startActivity(chamar);

    }

    public void exibirRelatorioTarefas(){
        Relatorios.checkRelatorios().gerarPDFRelatorioTarefa();
        Relatorios.checkRelatorios().AbrirRelatorio("sample.pdf");
        Intent chamar = new Intent(this, Relatorios.class);
        startActivity(chamar);

    }
}
