package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.gavin.aplicacaosiga.BO.ClienteBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.Model.ModelCliente;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;


public class listaCliente extends Activity {

    private ClienteBO clibo;

    private List<ModelCliente> lista;
    private ListView lstCliente;

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
        setContentView(R.layout.activity_lista_cadastro);

        lstCliente = (ListView) findViewById(R.id.lst_cadastro);

        clibo = new ClienteBO(this);
        this.listarClientes();
        this.consultarPorId();


    }

    public void novoCadastro(View viw) {
        Intent i = new Intent(this, Cadastro_cliente.class);
        startActivity(i);
        finish();
    }

    private void listarClientes() {

        lista = clibo.listCliente();

        List<CharSequence> valores = new ArrayList<CharSequence>();
        for (ModelCliente modelcliente : lista) {
            valores.add(modelcliente.getNOME());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.listitem, valores);

        lstCliente.setAdapter(adapter);


        lstCliente.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int id, long position) {
                setPosicao(position);
                return false;
            }
        });

        lstCliente.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0, 1, 1, "Editar");
                menu.add(0, 2, 2, "Deletar");
            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        item.getItemId();
        switch (item.getItemId()) {
            case 1:
                ModelCliente cliente=lista.get(posicao.intValue());
                Intent i = new Intent(this,editarCliente.class);
                i.putExtra("cliente",cliente);
                startActivity(i);
                finish();
                break;
            case 2:
                MensagemUtil.addMsgConfirm(this,"Alerta","Deseja realmente excluir o cliente selecionado",R.drawable.delete,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ModelCliente cliente=lista.get(posicao.intValue());
                        clibo.RemoverCliente(cliente.getID_CLIENTE());

                        MensagemUtil.addMsg(listaCliente.this,"Cliente Cadastrado com sucesso");
                        Intent i = new Intent(listaCliente.this,listaCliente.class);
                        startActivity(i);
                        finish();



                    }
                });
                break;



        }


        return super.onContextItemSelected(item);
    }






    private void consultarPorId() {
        lstCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int id, long position) {

                ModelCliente cliente = lista.get((int)position);
                ModelCliente mo = clibo.consultaCliente(cliente.getID_CLIENTE());

                String msg = "NOME= " + mo.getNOME() + "\nCPF= " + mo.getCPF() + "\nENDERECO= " + mo.getENDERECO() + "\nBAIRRO= " + mo.getBAIRRO() + "\nCIDADE= " + mo.getCIDADE();

                MensagemUtil.addMsgOk(listaCliente.this, "Info", msg, R.drawable.ic_launcher);

            }
        });
    }



}


