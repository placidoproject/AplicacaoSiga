package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.gavin.aplicacaosiga.BO.CaixaBO;
import com.example.gavin.aplicacaosiga.BO.TarefaBO;
import com.example.gavin.aplicacaosiga.OnModelCheck;
import com.example.gavin.aplicacaosiga.R;
import com.example.gavin.aplicacaosiga.tarefa;
import com.exemplo.gavin.Adapters.CheckAdapter;
import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.DAO.DaoTarefa;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.Model.ModelCheck;
import com.exemplo.gavin.Model.ModelTarefa;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gavin on 08/06/2015.
 */
public class listaTarefa extends Activity implements OnModelCheck{

    private DaoApiario apiariodao;
    private DaoCaixa caixadao;
    private TarefaBO tarefabo;
    private DaoTarefa tarefadao;

    private List<ModelTarefa> lista;
    private ListView lstTarefa;
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
        setContentView(R.layout.activity_lista_cadastro);
        setTitle("Lista de Atividades Agendadas");

        lstTarefa = (ListView) findViewById(R.id.lst_cadastro);

        tarefabo = new TarefaBO(this);
        tarefadao = new DaoTarefa(this);
        apiariodao = new DaoApiario(this);
        caixadao = new DaoCaixa(this);
        this.listarTarefa(null);
        this.consultarPorId();

    }

    public void novoCadastro(View viw) {
        Intent i = new Intent(this, tarefa.class);
        startActivity(i);
        finish();
    }

    private void listarTarefa(String param) {
        lista = tarefabo.listTarefa();

        List<ModelCheck> valores = new ArrayList<ModelCheck>();
        for (ModelTarefa modeltarefa : lista) {
            if(param==null || modeltarefa.getTAREFAS().startsWith(param))
                valores.add(new ModelCheck(modeltarefa.getTAREFAS() + "-" + modeltarefa.getDATATAREFA(), modeltarefa.getATIVIDADE()));

        }

        CheckAdapter adapter = new CheckAdapter(this, valores);
        adapter.setOnModelCheck(this);

        lstTarefa.setAdapter(adapter);


        lstTarefa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int id, long position) {
                setPosicao(position);
                return false;
            }
        });

        lstTarefa.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.add(0, 1, 1, "Deletar");

            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        item.getItemId();
        switch (item.getItemId()) {

            case 1:
                MensagemUtil.addMsgConfirm(this, "Alerta", "Deseja realmente excluir a Tarefa selecionada", R.drawable.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ModelTarefa tarefa = lista.get(posicao.intValue());
                        tarefabo.RemoverTarefa(tarefa.getID_TAREFA());

                        MensagemUtil.addMsg(listaTarefa.this, "Tarefa excluida com sucesso");
                        Intent i = new Intent(listaTarefa.this, listaTarefa.class);
                        startActivity(i);
                        finish();


                    }
                });





        }


        return super.onContextItemSelected(item);
    }


    private void consultarPorId() {
        lstTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int id, long position) {

                ModelTarefa tarefa = lista.get((int) position);
                ModelTarefa mo = tarefabo.consultaTarefa(tarefa.getID_TAREFA());

                String msg = "TAREFAS= " + mo.getTAREFAS() + "\nDATATAREFA= " + mo.getDATATAREFA() + "\nfk_id_apiario= " + apiariodao.pesquisaID(mo.getFk_id_apiario()).getDESCRICAO() + "\nfk_id_caixa= " + caixadao.pesquisaCaixaPorID(mo.getFk_id_caixa()).getNOMECAIXA();

                MensagemUtil.addMsgOk(listaTarefa.this, "Info", msg, R.drawable.ic_launcher);

            }
        });
    }


    @Override
    public void OnCheckSelected(ModelCheck model, int position) {
        lista.get(position).setATIVIDADE(model.isChecked());
        tarefabo.AtualizarTarefa(lista.get(position));
    }
}
