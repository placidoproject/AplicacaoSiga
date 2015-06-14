package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.gavin.aplicacaosiga.BO.EstoqueBO;
import com.example.gavin.aplicacaosiga.R;

import java.util.ArrayList;
import java.util.List;


public class listaRelatorio extends Activity{

    private ListView lstRelatorio;
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
        setTitle("Tipo de Relatorio");

        lstRelatorio = (ListView) findViewById(R.id.lst_itemProd);

        this.listarBlock(null);
        this.consultarPorId();


    }

    private void listarBlock(String param) {

        List<CharSequence> valores = new ArrayList<CharSequence>();

        valores.add("Relatório de Terefas");
        valores.add("Relatório de Caixas");
        valores.add("Relatório de Produtos");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.listitem, valores);

        lstRelatorio.setAdapter(adapter);

    }

    private void consultarPorId() {
        lstRelatorio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int id, long position) {

                //MensagemUtil.addMsgOk(listaRelatorio.this, "Info", lstBlock.getItemAtPosition((int)position).toString(), R.drawable.ic_launcher);

                Intent i = new Intent(view.getContext(), exibirRelatorio.class);
                i.putExtra("tipo", lstRelatorio.getItemAtPosition((int) position).toString());
                startActivity(i);
                finish();
            }
        });
    }
}
