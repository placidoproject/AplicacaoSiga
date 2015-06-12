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
import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.Model.ModelApiario;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.List;

/**
 * Created by Gavin on 17/05/2015.
 */
public class editarCaixa extends Activity {

   private CaixaBO caixabo;
    private ModelCaixa caixa;
    private EditText edtNome;
    private EditText edtColeta;
    private EditText edtData = null;
    private EditText edtDataNamutencao = null;
    private Spinner spCaixa = null;
    private Button btnatualizar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_caixa);
        setTitle("Atualizar Caixas");

        caixabo = new CaixaBO(this);

        caixa = (ModelCaixa) getIntent().getExtras().getSerializable("caixa");

        edtNome = (EditText) findViewById(R.id.edt_nomeCaixa);
        edtData = (EditText) findViewById(R.id.edt_datacaixa);
        edtDataNamutencao = (EditText) findViewById(R.id.edt_datamanutencao);
        edtColeta = (EditText) findViewById(R.id.edt_coletaDados);
        spCaixa = (Spinner) findViewById(R.id.sp_caixa);
        btnatualizar =(Button) findViewById(R.id.btn_atualizaCaixa);


        btnatualizar.setText("Atualizar");
        btnatualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtualizacaoCaixa();

            }
        });

        edtNome.setText(caixa.getNOMECAIXA());
        edtData.setText(caixa.getDATACAIXA());
        edtDataNamutencao.setText(caixa.getDATAMANUTENCAO());
        edtColeta.setText(caixa.getCOLETA());

        preencheCaixa();

    }


    private void preencheCaixa(){

        DaoApiario dao = new DaoApiario(this);
        List<ModelApiario> listapiario = dao.ListaApiario();

        int selection = 0;
        ArrayAdapter<ModelApiario> adapter = new ArrayAdapter<ModelApiario>(this, android.R.layout.simple_spinner_item);
        for (int  i= 0; i < listapiario.size(); i++){
            adapter.add(listapiario.get(i));
            if (listapiario.get(i).getID_APIARIO().equals(caixa.getFk_id_apiario()))
                selection = i;
        }
        spCaixa.setAdapter(adapter);

        spCaixa.setSelection(selection);

    }

    private void AtualizacaoCaixa(){
        ModelCaixa mo = new ModelCaixa();
        mo.setID_CAIXA(caixa.getID_CAIXA());
        mo.setNOMECAIXA(edtNome.getText().toString());
        mo.setDATACAIXA(edtData.getText().toString());
        mo.setDATAMANUTENCAO(edtDataNamutencao.getText().toString());
        mo.setCOLETA(edtColeta.getText().toString());
        ModelApiario apiario = (ModelApiario)spCaixa.getSelectedItem();
        mo.setFk_id_apiario(apiario.getID_APIARIO());
        caixabo.AtualizarCaixa(mo);
        MensagemUtil.addMsg(this,"Caixa atualizada com sucesso!");

        Intent i = new Intent(this,listaCaixaEditada.class);
        startActivity(i);
        finish();
    }
}
