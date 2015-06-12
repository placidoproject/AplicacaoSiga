package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.gavin.aplicacaosiga.BO.EstoqueBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.Model.ModelProduto;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;


public class listaBlock extends Activity{

    private EstoqueBO estoquebo;

    private ListView lstBlock;
    private SearchView sv;

    public Long getPosicao() {
        return posicao;
    }

    public void setPosicao(Long posicao) {
        this.posicao = posicao;
    }

    private Long posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaitemproduto);
        setTitle("Lista de Blocks de Estoque");

        lstBlock = (ListView) findViewById(R.id.lst_itemProd);

        estoquebo = new EstoqueBO(this);
        this.listarBlock(null);
        this.consultarPorId();


    }

    private void listarBlock(String param) {

        List<CharSequence> valores = new ArrayList<CharSequence>();

        valores.add("Block Mel Claro");
        valores.add("Block Mel Escuro");
        valores.add("Block Cera");
        valores.add("Block Geléia Real");
        valores.add("Block Própolis");
        valores.add("Block Mel Intermediario");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, valores);

        lstBlock.setAdapter(adapter);

    }

    private void consultarPorId() {
        lstBlock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int id, long position) {

                //MensagemUtil.addMsgOk(listaBlock.this, "Info", lstBlock.getItemAtPosition((int)position).toString(), R.drawable.ic_launcher);

                Intent i = new Intent(view.getContext(),listaProdutos.class);
                i.putExtra("tipo",lstBlock.getItemAtPosition((int)position).toString());
                startActivity(i);
                finish();
            }
        });
    }
}
