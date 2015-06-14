package com.example.gavin.aplicacaosiga;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gavin.aplicacaosiga.BO.CaixaBO;
import com.example.gavin.aplicacaosiga.BO.TarefaBO;
import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.example.gavin.aplicacaosiga.View.listaCaixa;
import com.example.gavin.aplicacaosiga.View.listaTarefa;
import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.Model.ModelApiario;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.Model.ModelTarefa;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.List;


public class tarefa extends Activity {

    private EditText edtTarefa;
    private EditText edtDataTarefa;
    private Spinner spTarefaApiario;
    private Spinner spTarefaCaixa;
    private CheckBox cbAtividade;
    private Button btCadastrarTarefa;

    private TarefaBO tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);
        tarefa = new TarefaBO(this);

        edtTarefa = (EditText) findViewById(R.id.edt_tarefa);
        edtDataTarefa = (EditText) findViewById(R.id.edt_dataTarefa);
        spTarefaApiario = (Spinner) findViewById(R.id.sp_tarefaApiario);
        spTarefaCaixa = (Spinner) findViewById(R.id.sp_tarefaCaixa);
        btCadastrarTarefa = (Button) findViewById(R.id.btn_cadastrarTarefa);


        preencheApiario();
        preencheCaixa();


    }
    public void CadastrarTarefa(View v){

        ModelTarefa mo = new ModelTarefa();
        mo.setTAREFAS(edtTarefa.getText().toString());
        mo.setDATATAREFA(edtDataTarefa.getText().toString());
        mo.setATIVIDADE(false);
        ModelApiario apiario = (ModelApiario) spTarefaApiario.getSelectedItem();
        mo.setFk_id_apiario(apiario.getID_APIARIO());
        ModelCaixa caixa = (ModelCaixa) spTarefaCaixa.getSelectedItem();
        mo.setFk_id_caixa(caixa.getID_CAIXA());


        Validation resultado =  tarefa.cadastrarTarefa(mo);
        MensagemUtil.addMsg(this, resultado.getMensagem());
        Intent i = new Intent(this,listaTarefa.class);
        startActivity(i);
        finish();

    }

    private void preencheApiario(){

        DaoApiario dao = new DaoApiario(this);
        List<ModelApiario> listapiario = dao.ListaApiario();

        ArrayAdapter<ModelApiario> adapter = new ArrayAdapter<ModelApiario>(this, android.R.layout.simple_spinner_item);
        for (int  i= 0; i < listapiario.size(); i++){
            adapter.add(listapiario.get(i));
        }
        spTarefaApiario.setAdapter(adapter);

    }

    private void preencheCaixa(){

        DaoCaixa dao = new DaoCaixa(this);
        List<ModelCaixa> listacaixa= dao.ListaCaixa();

        ArrayAdapter<ModelCaixa> adapter = new ArrayAdapter<ModelCaixa>(this, android.R.layout.simple_spinner_item);
        for (int  i= 0; i < listacaixa.size(); i++){
            adapter.add(listacaixa.get(i));
        }
        spTarefaCaixa.setAdapter(adapter);

    }


}
