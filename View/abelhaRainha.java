package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gavin.aplicacaosiga.BO.AbelhaBO;
import com.example.gavin.aplicacaosiga.R;

import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.Model.ModelAbelha;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.List;


public class abelhaRainha extends Activity {

    private EditText edtDesAbelha;
    private EditText edtDataIns;
    private Spinner spAbelha;
    private Button btCadastroAbelha;

    private AbelhaBO abelha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abelha_rainha);

        abelha = new AbelhaBO(this);

        edtDesAbelha = (EditText) findViewById(R.id.edt_DescricãoAbelha);
        edtDataIns = (EditText) findViewById(R.id.edt_dataInsercao);
        spAbelha = (Spinner) findViewById(R.id.sp_caixaAbelha);

        preencheAbelha();

    }
    public void CadastrarAbelha(View v){

        ModelAbelha mo = new ModelAbelha();
        mo.setDESCRICAOABELHA(edtDesAbelha.getText().toString());
        mo.setDATAINSERCAO(edtDataIns.getText().toString());
        ModelCaixa caixa = (ModelCaixa) spAbelha.getSelectedItem();
        mo.setId_fk_caixa(caixa.getID_CAIXA());


        Validation resultado =  abelha.cadastrarAbelha(mo);
        MensagemUtil.addMsg(this, resultado.getMensagem());
        Intent i = new Intent(this,listaAbelhasRainhas.class);
        startActivity(i);
        finish();

    }

    private void preencheAbelha(){

        DaoCaixa dao = new DaoCaixa(this);
        List<ModelCaixa> listacaixa = dao.ListaCaixa();

        ArrayAdapter<ModelCaixa> adapter = new ArrayAdapter<ModelCaixa>(this, android.R.layout.simple_spinner_item);
        for (int  i= 0; i < listacaixa.size(); i++){
            adapter.add(listacaixa.get(i));
        }
        spAbelha.setAdapter(adapter);

    }


}
