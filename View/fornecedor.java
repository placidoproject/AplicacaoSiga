package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gavin.aplicacaosiga.BO.FornecedorBO;
import com.example.gavin.aplicacaosiga.R;
import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.Model.ModelFornecedor;
import com.exemplo.gavin.msg.MensagemUtil;


public class fornecedor extends Activity {

    private EditText edtNomeFor,edtEnderecoFor,edtCidadeFor,edtEmailFor,edtFoneFor,edtCompraFor,edtValorFor;
    private Button btCadastrar;
    private FornecedorBO fornecedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor);
        fornecedor = new FornecedorBO(this);


        edtNomeFor = (EditText) findViewById(R.id.edt_nomeFor);
        edtEnderecoFor =(EditText) findViewById(R.id.edt_EnderecoFor);
        edtCidadeFor = (EditText) findViewById(R.id.edt_CidadeFOr);
        edtEmailFor = (EditText) findViewById(R.id.edt_EmailFor);
        edtFoneFor = (EditText) findViewById(R.id.edt_TelefoneFor);
        edtCompraFor = (EditText) findViewById(R.id.edt_compraFor);
        edtValorFor = (EditText) findViewById(R.id.edt_valorFor);
        btCadastrar = (Button) findViewById(R.id.btn_cadastarFornecedor);
    }

    public void CadastrarFornecedor(View v){

        ModelFornecedor mo = new ModelFornecedor();
        mo.setNOMEFOR(edtNomeFor.getText().toString());
        mo.setENDERECOFOR(edtEnderecoFor.getText().toString());
        mo.setCIDADEFOR(edtCidadeFor.getText().toString());
        mo.setEMAILFOR(edtEmailFor.getText().toString());
        mo.setTELEFONEFOR(edtFoneFor.getText().toString());
        mo.setCOMPRAFOR(edtCompraFor.getText().toString());
        //mo.setVALORCOMPRAFOR(edtValorFor.getText().toString());

        Validation resultado =  fornecedor.cadastrarFornecedor(mo);
        MensagemUtil.addMsg(this, resultado.getMensagem());
        Intent i = new Intent(this,listaFornecedor.class);
        startActivity(i);
        finish();

    }



}
