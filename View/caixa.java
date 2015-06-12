package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gavin.aplicacaosiga.BO.CaixaBO;
import com.example.gavin.aplicacaosiga.R;
import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.Model.ModelApiario;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.List;


public class caixa extends Activity {
  private EditText edtNome;
  private EditText edtData;
  private Spinner spCaixa;
  private Button btCadastrarCaixa;

    private CaixaBO caixa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caixa);

        caixa = new CaixaBO(this);

        edtNome = (EditText) findViewById(R.id.edt_nomeCaixa);
        edtData = (EditText) findViewById(R.id.edt_datacaixa);
        spCaixa = (Spinner) findViewById(R.id.sp_caixa);
        btCadastrarCaixa = (Button) findViewById(R.id.btn_cadastraCaixa);




        preencheCaixa();
    }

    public void CadastrarCaixa(View v){

        ModelCaixa mo = new ModelCaixa();
        mo.setNOMECAIXA(edtNome.getText().toString());
        mo.setDATACAIXA(edtData.getText().toString());
        ModelApiario apiario = (ModelApiario) spCaixa.getSelectedItem();
        mo.setFk_id_apiario(apiario.getID_APIARIO());


        Validation resultado =  caixa.cadastrarCaixa(mo);
        MensagemUtil.addMsg(this, resultado.getMensagem());
        Intent i = new Intent(this,listaCaixa.class);
        startActivity(i);
        finish();

    }

    private void preencheCaixa(){

        DaoApiario dao = new DaoApiario(this);
        List<ModelApiario> listapiario = dao.ListaApiario();

        ArrayAdapter<ModelApiario> adapter = new ArrayAdapter<ModelApiario>(this, android.R.layout.simple_spinner_item);
        for (int  i= 0; i < listapiario.size(); i++){
            adapter.add(listapiario.get(i));
        }
        spCaixa.setAdapter(adapter);

    }



}
