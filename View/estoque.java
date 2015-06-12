package com.example.gavin.aplicacaosiga.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gavin.aplicacaosiga.BO.EstoqueBO;
import com.example.gavin.aplicacaosiga.R;

import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.Model.ModelProduto;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.List;


public class estoque extends Activity {

    private EditText edtNomeProduto;
    private EditText edtDataEstoque;
    private Spinner spEstoque,spProduto;
    private EditText edtQTD;
    private Button btCadastrarProduto;

    private EstoqueBO estoque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        estoque= new EstoqueBO(this);


        edtDataEstoque = (EditText) findViewById(R.id.edt_dataEstoque);
        edtQTD = (EditText) findViewById(R.id.edt_quantidadeEstoque);
        spEstoque = (Spinner) findViewById(R.id.sp_estoque);
        spProduto = (Spinner) findViewById(R.id.sp_produto);
        btCadastrarProduto = (Button) findViewById(R.id.btn_cadastraEstoque);




        preencheEstoque();
        preencheProduto();

    }

    public void CadastrarProduto(View v){

        ModelProduto mo = new ModelProduto();
        mo.setTIPO(spProduto.getSelectedItem().toString());
        mo.setDATAPRODUTO(edtDataEstoque.getText().toString());
        mo.setQUANTIDADE(edtQTD.getText().toString());
        ModelCaixa caixa = (ModelCaixa)spEstoque.getSelectedItem();
        mo.setFk_id_caixa(caixa.getID_CAIXA());


        Validation resultado =  estoque.cadastrarProduto(mo);
        MensagemUtil.addMsg(this, resultado.getMensagem());
        Intent i = new Intent(this,listaBlock.class);
        startActivity(i);
        finish();

    }

    private void preencheEstoque(){

        DaoCaixa dao = new DaoCaixa(this);
        List<ModelCaixa> listacaixa = dao.ListaCaixa();

        ArrayAdapter<ModelCaixa> adapter = new ArrayAdapter<ModelCaixa>(this, android.R.layout.simple_spinner_item);
        for (int  i= 0; i < listacaixa.size(); i++){
            adapter.add(listacaixa.get(i));
        }
        spEstoque.setAdapter(adapter);

    }

    private void preencheProduto(){


   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        adapter.add("Mel Claro");
        adapter.add("Mel Escuro");
        adapter.add("Cera");
        adapter.add("Geléia Real");
        adapter.add("Própolis");
        adapter.add("Mel Intermediario");

        spProduto.setAdapter(adapter);

    }





}
