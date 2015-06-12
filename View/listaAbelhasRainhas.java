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

import com.example.gavin.aplicacaosiga.BO.AbelhaBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.Model.ModelAbelha;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gavin on 23/05/2015.
 */
public class listaAbelhasRainhas extends Activity {

    private AbelhaBO abelhabo;
    private DaoCaixa caixadao;

    private List<ModelAbelha> lista;
    private ListView lstAbelha;

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
        setTitle("Lista de Abelhas Rainhas");

        lstAbelha = (ListView) findViewById(R.id.lst_cadastro);

        abelhabo = new AbelhaBO(this);
        caixadao = new DaoCaixa(this);
        this.listarAbelhas();
        this.consultarAbelhaPorId();


    }

    public void novoCadastro(View viw) {
        Intent i = new Intent(this, abelhaRainha.class);
        startActivity(i);
        finish();
    }

    private void listarAbelhas() {

        lista = abelhabo.listAbelha();

        List<CharSequence> valores = new ArrayList<CharSequence>();
        for (ModelAbelha modelabelha : lista) {
            valores.add(modelabelha.getDESCRICAOABELHA());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, valores);

        lstAbelha.setAdapter(adapter);


        lstAbelha.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int id, long position) {
                setPosicao(position);
                return false;
            }
        });

        lstAbelha.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
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
                MensagemUtil.addMsgConfirm(this, "Alerta", "Deseja realmente excluir a caixa selecionada", R.drawable.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ModelAbelha abelha = lista.get(posicao.intValue());
                        abelhabo.RemoverAbelha(abelha.getID_ABELHA());

                        MensagemUtil.addMsg(listaAbelhasRainhas.this, "Abelha rainha excluida com sucesso");
                        Intent i = new Intent(listaAbelhasRainhas.this, listaAbelhasRainhas.class);
                        startActivity(i);
                        finish();


                    }
                });
                break;



        }


        return super.onContextItemSelected(item);
    }


    private void consultarAbelhaPorId() {
        lstAbelha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int id, long position) {

                ModelAbelha abelha=lista.get((int)position);
                ModelAbelha mo = abelhabo.consultaAbelha(abelha.getID_ABELHA());

                String msg = "DESCRICAOABELHA= " + mo.getDESCRICAOABELHA() + "\nDATAINSERCAO= " + mo.getDATAINSERCAO() + "\nfk_id_caixa= " + caixadao.pesquisaCaixaPorID(mo.getId_fk_caixa()).getNOMECAIXA();

                MensagemUtil.addMsgOk(listaAbelhasRainhas.this, "Info", msg, R.drawable.ic_launcher);

            }
        });
    }
}
