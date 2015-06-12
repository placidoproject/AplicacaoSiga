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
import com.example.gavin.aplicacaosiga.BO.EstoqueBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.Model.ModelProduto;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.ArrayList;
import java.util.List;


public class listaProdutos extends Activity implements SearchView.OnQueryTextListener{

    private String tipo;

    private EstoqueBO estoquebo;
    private CaixaBO caixabo;
    private DaoCaixa caixadao;

    private List<ModelProduto> lista;
    private ListView lstProdutos;
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
        setTitle("Lista de Produtos");

        tipo = getIntent().getExtras().getString("tipo");

        lstProdutos = (ListView) findViewById(R.id.lst_cadastro);

        estoquebo = new EstoqueBO(this);
        caixabo = new CaixaBO(this);
        caixadao = new DaoCaixa(this);
        this.listarProdutos(null);
        this.consultarPorId();

    }

    public void novoCadastro(View viw) {
        Intent i = new Intent(this,estoque.class);
        startActivity(i);
        finish();
    }

    private void listarProdutos(String param) {


        lista = estoquebo.listProduto();

        List<CharSequence> valores = new ArrayList<CharSequence>();
        for (ModelProduto modelproduto : lista) {
            if (tipo.equals("Block "+modelproduto.getTIPO()))
                if (param == null || modelproduto.getTIPO().startsWith(param))
                    valores.add(modelproduto.getTIPO()+" - "+modelproduto.getDATAPRODUTO());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, valores);

        lstProdutos.setAdapter(adapter);


        lstProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int id, long position) {
                setPosicao(position);
                return false;
            }
        });

        lstProdutos.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
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
                ModelProduto produto =lista.get(posicao.intValue());
                Intent i = new Intent(this,EditEstoque.class);
                i.putExtra("estoque",produto);
                startActivity(i);
                finish();
                break;
            case 2:
                MensagemUtil.addMsgConfirm(this, "Alerta", "Deseja realmente excluir o Produto selecionado", R.drawable.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ModelProduto produto = lista.get(posicao.intValue());
                        estoquebo.RemoverProduto(produto.getID_PRODUTO());

                        MensagemUtil.addMsg(listaProdutos.this, "Produto excluida com sucesso");
                        Intent i = new Intent(listaProdutos.this, listaProdutos.class);
                        startActivity(i);
                        finish();


                    }
                });
                break;



        }


        return super.onContextItemSelected(item);
    }


    private void consultarPorId() {
        lstProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int id, long position) {

                ModelProduto produto=lista.get((int)position);
                ModelProduto mo = estoquebo.consultaProdutos(produto.getID_PRODUTO());

                String msg = "TIPO= " + mo.getTIPO() +  "\nDATA= " + mo.getDATAPRODUTO() + "\nQUANTIDADE = "+mo.getQUANTIDADE()+ "\nCaixa =" + caixadao.pesquisaCaixaPorID(mo.getFk_id_caixa()).getNOMECAIXA();

                MensagemUtil.addMsgOk(listaProdutos.this, "Info", msg, R.drawable.ic_launcher);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_lista_produtos,menu);

        MenuItem item = menu.findItem(R.id.pesquisaProduto);
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
        if (newText!=null){
            listarProdutos(newText);
            lista = estoquebo.listProduto(newText);
        }
        return false;
    }
}
