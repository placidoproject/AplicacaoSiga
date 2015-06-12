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

import com.example.gavin.aplicacaosiga.BO.FornecedorBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.Model.ModelFornecedor;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;


public class listaFornecedor extends Activity {

    private FornecedorBO forbo;

    private List<ModelFornecedor> lista;
    private ListView lstFornecedor;

    private Long posicao;

    public Long getPosicao() {
        return posicao;
    }

    public void setPosicao(Long posicao) {
        this.posicao = posicao;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cadastro);

        lstFornecedor = (ListView) findViewById(R.id.lst_cadastro);

        forbo= new FornecedorBO(this);
        this.listarFornecedor();
        this.consultarPorId();


    }

    public void novoCadastro(View viw) {
        Intent i = new Intent(this, fornecedor.class);
        startActivity(i);
        finish();
    }

    private void listarFornecedor() {

        lista = forbo.listaFornecedor();

        List<CharSequence> valores = new ArrayList<CharSequence>();
        for (ModelFornecedor modelfornecedor : lista) {
            valores.add(modelfornecedor.getNOMEFOR());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, valores);

        lstFornecedor.setAdapter(adapter);


        lstFornecedor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int id, long position) {
                setPosicao(position);
                return false;
            }
        });

        lstFornecedor.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
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
                ModelFornecedor fornecedor = lista.get(posicao.intValue());
                Intent i = new Intent(this, editarFornecedor.class);
                i.putExtra("fornecedor", fornecedor);
                startActivity(i);
                finish();
                break;
            case 2:
                MensagemUtil.addMsgConfirm(this, "Alerta", "Deseja realmente excluir o fornecedor selecionado", R.drawable.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ModelFornecedor fornecedor = lista.get(posicao.intValue());
                        forbo.RemoverFornecedor(fornecedor.getID_FORNECEDOR());

                        MensagemUtil.addMsg(listaFornecedor.this, "Fornecedor Cadastrado com sucesso");
                        Intent i = new Intent(listaFornecedor.this, listaFornecedor.class);
                        startActivity(i);
                        finish();


                    }
                });
                break;


        }


        return super.onContextItemSelected(item);

    }

    private void consultarPorId() {
        lstFornecedor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int id, long position) {

                ModelFornecedor mf = lista.get((int)position);
                ModelFornecedor mo = forbo.consultaFornecedor(mf.getID_FORNECEDOR());

                String msg = "NOME= " + mo.getNOMEFOR() + "\nENDERECOFOR= " + mo.getENDERECOFOR() + "\nCIDADEFOR= " + mo.getCIDADEFOR() + "\nEMAILFOR= " + mo.getEMAILFOR() + "\nTELEFONEFOR= " + mo.getTELEFONEFOR()+ "\nCOMPRAFOR= " + mo.getCOMPRAFOR()+ "\nVALORCOMPRAFOR= " + mo.getVALORCOMPRAFOR();

                MensagemUtil.addMsgOk(listaFornecedor.this, "Info", msg, R.drawable.ic_launcher);

            }
        });
    }
}
