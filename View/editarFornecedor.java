package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gavin.aplicacaosiga.BO.FornecedorBO;
import com.example.gavin.aplicacaosiga.R;
import com.exemplo.gavin.Model.ModelFornecedor;
import com.exemplo.gavin.msg.MensagemUtil;

/**
 * Created by Gavin on 17/05/2015.
 */
public class editarFornecedor extends Activity {
    private FornecedorBO forbo;
    private ModelFornecedor fornecedor;
    private EditText edtNomeFor = null;
    private EditText edtEnderecoFor = null;
    private EditText edtCidadeFor = null;
    private EditText edtEmailFor = null;
    private EditText edtFoneFor = null;
    private EditText edtCompraFor = null;
    private EditText edtValorFor =null;
    private Button btnatualizar= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor);
        setTitle("Atualizar Fornecedores");

        forbo = new FornecedorBO(this);

        fornecedor = (ModelFornecedor) getIntent().getExtras().getSerializable("fornecedor");

        edtNomeFor = (EditText) findViewById(R.id.edt_nomeFor);
        edtEnderecoFor =(EditText) findViewById(R.id.edt_EnderecoFor);
        edtCidadeFor = (EditText) findViewById(R.id.edt_CidadeFOr);
        edtEmailFor = (EditText) findViewById(R.id.edt_EmailFor);
        edtFoneFor = (EditText) findViewById(R.id.edt_TelefoneFor);
        edtCompraFor = (EditText) findViewById(R.id.edt_compraFor);
        edtValorFor = (EditText) findViewById(R.id.edt_valorFor);
        btnatualizar =(Button) findViewById(R.id.btn_cadastarFornecedor);

        btnatualizar.setText("Atualizar");

        btnatualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtualizacaoFornecedor();
            }
        });

        edtNomeFor.setText(fornecedor.getNOMEFOR());
        edtEnderecoFor.setText(fornecedor.getENDERECOFOR());
        edtCidadeFor.setText(fornecedor.getCIDADEFOR());
        edtEmailFor.setText(fornecedor.getEMAILFOR());
        edtFoneFor.setText(fornecedor.getTELEFONEFOR());
        edtCompraFor.setText(fornecedor.getCOMPRAFOR());
       // edtValorFor.setText(fornecedor.getVALORCOMPRAFOR());
    }

    private void AtualizacaoFornecedor(){
        ModelFornecedor mo = new ModelFornecedor();
        mo.setID_FORNECEDOR(fornecedor.getID_FORNECEDOR());
        mo.setNOMEFOR(edtNomeFor.getText().toString());
        mo.setENDERECOFOR(edtEnderecoFor.getText().toString());
        mo.setCIDADEFOR(edtCidadeFor.getText().toString());
        mo.setEMAILFOR(edtEmailFor.getText().toString());
        mo.setTELEFONEFOR(edtFoneFor.getText().toString());
        mo.setCOMPRAFOR(edtCompraFor.getText().toString());
        //mo.setVALORCOMPRAFOR(edtValorFor.getText().toString());
        forbo.AtulaizarFornecedor(mo);
        MensagemUtil.addMsg(this, "Fornecedor atualizado com sucesso!");

        Intent i = new Intent(this,listaFornecedor.class);
        startActivity(i);
        finish();
    }

}
