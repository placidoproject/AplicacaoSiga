package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.gavin.aplicacaosiga.BO.ApiarioBO;
import com.example.gavin.aplicacaosiga.BO.CaixaBO;
import com.example.gavin.aplicacaosiga.BO.EstoqueBO;
import com.example.gavin.aplicacaosiga.BO.TarefaBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.Model.ModelProduto;
import com.exemplo.gavin.Model.ModelTarefa;

import java.util.List;


public class exibirRelatorio extends Activity{

    private TextView txtRelatorio;
    //private Button btGerar;
    private SearchView sv;

    TarefaBO tarefabo = new TarefaBO(this);
    CaixaBO caixabo = new CaixaBO(this);
    ApiarioBO apiariobo = new ApiarioBO(this);
    EstoqueBO estoquebo = new EstoqueBO(this);

    public Long getPosicao() {
        return posicao;
    }

    public void setPosicao(Long posicao) {
        this.posicao = posicao;
    }

    private Long posicao;

    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir_relatorio);

        txtRelatorio = (TextView) findViewById(R.id.text_relatorio);
        /*
        btGerar = (Button) findViewById(R.id.btn_gerar);
        btGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipo.equals("Relatório de Terefas")){
                    Relatorios.checkRelatorios().gerarPDFRelatorioTarefa();

                } else if (tipo.equals("Relatório de Caixas")){

                } else if (tipo.equals("Relatório de Produtos")){

                }
            }
        });*/

        tipo = getIntent().getExtras().getString("tipo");

        if (tipo.equals("Relatório de Terefas")){
            setTitle("Relatório de Terefas");
            relatorioTarefa();

        } else if (tipo.equals("Relatório de Caixas")){
            setTitle("Relatório de Caixas");
            relatorioCaixa();

        } else if (tipo.equals("Relatório de Produtos")){
            setTitle("Relatório de Produtos");
            relatorioProduto();

        }
    }

    private void relatorioTarefa(){
        String relatorio = "";
        List<ModelTarefa> lstTarefa = tarefabo.listTarefa();
        for (ModelTarefa tarefa : lstTarefa) {
            relatorio = relatorio+tarefa.getTAREFAS()+"  "+tarefa.getDATATAREFA()+"  Atividade:"+checkBool(tarefa.getATIVIDADE())+"  Caixa:"+caixabo.consultaCaixa(tarefa.getFk_id_caixa()).getNOMECAIXA()+"  Apiario:"+apiariobo.consultaApiario(tarefa.getFk_id_apiario()).getDESCRICAO()+"\n\n";
        }
        txtRelatorio.setText(relatorio);
    }

    private void relatorioCaixa(){
        String relatorio = "";
        List<ModelCaixa> lstCaixa = caixabo.listCaixa();
        for (ModelCaixa caixa : lstCaixa) {
            relatorio = relatorio+caixa.getNOMECAIXA()+"  "+caixa.getDATAMANUTENCAO()+"  "+caixa.getCOLETA()+"  Apiario:"+apiariobo.consultaApiario(caixa.getFk_id_apiario()).getDESCRICAO()+"\n\n";
        }
        txtRelatorio.setText(relatorio);
    }

    private void relatorioProduto(){
        String relatorio = "";
        List<ModelProduto> lstProduto = estoquebo.listProduto();
        for (ModelProduto produto : lstProduto) {
            relatorio = relatorio+produto.getTIPO()+"  "+produto.getQUANTIDADE()+"  "+produto.getDATAPRODUTO()+"  Caixa:"+caixabo.consultaCaixa(produto.getFk_id_caixa()).getNOMECAIXA()+"\n\n";
        }
        txtRelatorio.setText(relatorio);
    }

    public String checkBool (boolean bool){
        if (bool)
            return "Sim";
        return "Não";
    }
}
