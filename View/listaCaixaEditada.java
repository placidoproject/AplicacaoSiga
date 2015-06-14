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

import com.example.gavin.aplicacaosiga.BO.CaixaBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;


public class listaCaixaEditada extends Activity {

    private CaixaBO caixabo;
    private DaoApiario apiariodao;

    private List<ModelCaixa> lista;
    private ListView lstCaixa;

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
        setTitle("Lista de Caixa");

        lstCaixa = (ListView) findViewById(R.id.lst_cadastro);

        caixabo = new CaixaBO(this);
        apiariodao = new DaoApiario(this);
        this.listarCaixas();
        this.consultarPorId();


    }

    public void novoCadastro(View viw) {
        Intent i = new Intent(this, caixa.class);
        startActivity(i);
        finish();
    }

    private void listarCaixas() {

        lista = caixabo.listCaixa();

        List<CharSequence> valores = new ArrayList<CharSequence>();
        for (ModelCaixa modelcaixa : lista) {
            valores.add(modelcaixa.getNOMECAIXA());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.listitem, valores);

        lstCaixa.setAdapter(adapter);


        lstCaixa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int id, long position) {
                setPosicao(position);
                return false;
            }
        });

        lstCaixa.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
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
                ModelCaixa caixa=lista.get(posicao.intValue());
                Intent i = new Intent(this,editarCaixa.class);
                i.putExtra("caixa",caixa);
                startActivity(i);
                finish();
                break;
            case 2:
                MensagemUtil.addMsgConfirm(this, "Alerta", "Deseja realmente excluir a caixa selecionada", R.drawable.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ModelCaixa caixa = lista.get(posicao.intValue());
                        caixabo.RemoverCaixa(caixa.getID_CAIXA());

                        MensagemUtil.addMsg(listaCaixaEditada.this, "Caixa excluida com sucesso");
                        Intent i = new Intent(listaCaixaEditada.this, listaCaixaEditada.class);
                        startActivity(i);
                        finish();


                    }
                });
                break;



        }


        return super.onContextItemSelected(item);
    }


    private void consultarPorId() {
        lstCaixa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int id, long position) {

                ModelCaixa caixa=lista.get((int)position);
                ModelCaixa mo = caixabo.consultaCaixa(caixa.getID_CAIXA());

                String msg = "Nome= " + mo.getNOMECAIXA() + "\nData= " + mo.getDATACAIXA() + "\nData da manutenção= " + mo.getDATAMANUTENCAO() + "\nColeta atualizada=" + mo.getCOLETA() +  "\nApiário= " + apiariodao.pesquisaID(mo.getFk_id_apiario()).getDESCRICAO();

                MensagemUtil.addMsgOk(listaCaixaEditada.this, "Info", msg, R.drawable.ic_launcher);

            }
        });
    }

}
