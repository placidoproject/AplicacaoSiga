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

import com.example.gavin.aplicacaosiga.BO.CaixaBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;


public class listaCaixa extends Activity implements SearchView.OnQueryTextListener{

    private CaixaBO caixabo;
    private DaoApiario apiariodao;

    private List<ModelCaixa> lista;
    private ListView lstCaixa;
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
        setTitle("Lista de Caixa");

        lstCaixa = (ListView) findViewById(R.id.lst_cadastro);

        caixabo = new CaixaBO(this);
        apiariodao = new DaoApiario(this);
        this.listarCaixas(null);
        this.consultarPorId();


    }

    public void novoCadastro(View viw) {
        Intent i = new Intent(this, caixa.class);
        startActivity(i);
        finish();
    }

    private void listarCaixas(String param) {

        lista = caixabo.listCaixa();

        List<CharSequence> valores = new ArrayList<CharSequence>();
        for (ModelCaixa modelcaixa : lista) {
            if(param==null || modelcaixa.getNOMECAIXA().startsWith(param))
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
                menu.add(0, 3, 3, "Detalhes");
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

                        MensagemUtil.addMsg(listaCaixa.this, "Caixa excluida com sucesso");
                        Intent i = new Intent(listaCaixa.this, listaCaixa.class);
                        startActivity(i);
                        finish();



                    }
                });

          case 3:
                ModelCaixa caixa2 = lista.get(posicao.intValue());
                Intent j = new Intent(this, listaProdutosPorCaixa.class);
                j.putExtra("caixa",caixa2);
                startActivity(j);
                finish();
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

                String msg = "Nome= " + mo.getNOMECAIXA() + "\nData= " + mo.getDATACAIXA() + "\nApiário= " + apiariodao.pesquisaID(mo.getFk_id_apiario()).getDESCRICAO();

                MensagemUtil.addMsgOk(listaCaixa.this, "Info", msg, R.drawable.ic_launcher);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_lista_caixa,menu);

        MenuItem item = menu.findItem(R.id.pesquisaCaixa);
        sv = (SearchView) item.getActionView();
        configurarSearchView();

        return true;

    }


    public void configurarSearchView(){

        sv.setIconifiedByDefault(true);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (manager!=null){
            List<SearchableInfo> global = manager.getSearchablesInGlobalSearch();

            SearchableInfo info = manager.getSearchableInfo(getComponentName());
            for (SearchableInfo i : global){
                if (i.getSuggestAuthority()!=null && i.getSuggestAuthority().startsWith("application")){
                    info =i;
                }

                sv.setSearchableInfo(info);
            }

            sv.setOnQueryTextListener(this);
        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if(newText!=null){
            listarCaixas(newText);
        }
        return false;
    }
}
