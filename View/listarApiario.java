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

import com.example.gavin.aplicacaosiga.BO.ApiarioBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.Model.ModelApiario;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;


public class listarApiario extends Activity {

    private ApiarioBO apiariobo;
    private DaoCaixa caixaodao;

    private List<ModelApiario> lista;
    private ListView lstApiario;

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
        setTitle("Lista de Apiario");

        lstApiario = (ListView) findViewById(R.id.lst_cadastro);

        apiariobo = new ApiarioBO(this);
        caixaodao = new DaoCaixa(this);
        this.listarApiario();
        this.consultarPorId();


    }

    public void novoCadastro(View viw) {
        Intent i = new Intent(this, apiario.class);
        startActivity(i);
        finish();
    }

    private void listarApiario() {

        lista = apiariobo.listapiario();

        List<CharSequence> valores = new ArrayList<CharSequence>();
        for (ModelApiario modelApiario : lista) {
            valores.add(modelApiario.getDESCRICAO());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.listitem, valores);

        lstApiario.setAdapter(adapter);


        lstApiario.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int id, long position) {
                setPosicao(position);
                return false;
            }
        });

        lstApiario.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0, 1, 1, "Detalhes");
                menu.add(0, 2, 2, "Deletar");
            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        item.getItemId();
        switch (item.getItemId()) {
            case 1:
                ModelApiario apiario=lista.get(posicao.intValue());
                Intent i = new Intent(this,listaCaixaPorApiario.class);
                i.putExtra("apiario",apiario);
                startActivity(i);
                finish();
                break;
            case 2:
                MensagemUtil.addMsgConfirm(this, "Alerta", "Deseja realmente excluir o apiario selecionada", R.drawable.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ModelApiario apiario = lista.get(posicao.intValue());
                        apiariobo.RemoverApiario(apiario.getID_APIARIO());

                        MensagemUtil.addMsg(listarApiario.this, "Apiario excluido com sucesso");
                        Intent i = new Intent(listarApiario.this, listarApiario.class);
                        startActivity(i);
                        finish();


                    }
                });
                break;



        }


        return super.onContextItemSelected(item);
    }


    private void consultarPorId() {
        lstApiario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int id, long position) {

                ModelApiario apiario=lista.get((int)position);
                ModelApiario mo = apiariobo.consultaApiario(apiario.getID_APIARIO());

                List<ModelCaixa> list = caixaodao.ListaCaixa();

                String msg = "DESCRICAO " + mo.getDESCRICAO()  + "\nCaixa cadastrada= \n";

                for (int i = 0; i < list.size(); i++){
                    if(list.get(i).getFk_id_apiario().equals(mo.getID_APIARIO()))
                        msg = msg + list.get(i).getNOMECAIXA() + "\n";
                }

                MensagemUtil.addMsgOk(listarApiario.this, "Info", msg, R.drawable.ic_launcher);

            }
        });
    }



}
