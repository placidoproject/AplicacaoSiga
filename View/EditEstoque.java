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
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.Model.ModelProduto;
import com.exemplo.gavin.msg.MensagemUtil;

import java.util.List;

/**
 * Created by Gavin on 20/05/2015.
 */
public class EditEstoque extends Activity{

    private EstoqueBO estoquebo;
    private ModelProduto estoque;
    private EditText edtNomeProduto;
    private EditText edtDataEstoque;
    private Spinner spEstoque,spProduto;
    private EditText edtQTD;
    private Button btAtualizarProduto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        estoquebo= new EstoqueBO(this);

       estoque = (ModelProduto) getIntent().getExtras().getSerializable("estoque");

        edtDataEstoque = (EditText) findViewById(R.id.edt_dataEstoque);
        edtQTD = (EditText) findViewById(R.id.edt_quantidadeEstoque);
        spEstoque = (Spinner) findViewById(R.id.sp_estoque);
        spProduto = (Spinner) findViewById(R.id.sp_produto);

        btAtualizarProduto = (Button) findViewById(R.id.btn_cadastraEstoque);

        btAtualizarProduto.setText("Atualizar");

        btAtualizarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtualizacaoProduto();
            }
        });



        edtDataEstoque.setText(estoque.getDATAPRODUTO());
        edtQTD.setText(estoque.getQUANTIDADE());


        preencheProduto();
        preencheEstoque();
    }

    private void preencheEstoque() {

        DaoCaixa dao = new DaoCaixa(this);
        List<ModelCaixa> listacaixa = dao.ListaCaixa();

        int selection = 0;
        ArrayAdapter<ModelCaixa> adapter = new ArrayAdapter<ModelCaixa>(this, android.R.layout.simple_spinner_item);
        for (int i = 0; i < listacaixa.size(); i++) {
            adapter.add(listacaixa.get(i));
            if (listacaixa.get(i).getID_CAIXA().equals(estoque.getFk_id_caixa()))
                selection = i;

        }
        spEstoque.setAdapter(adapter);
        spEstoque.setSelection(selection);

    }

    private void preencheProduto(){
    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

    adapter2.add("Mel Claro");
    adapter2.add("Mel Escuro");
    adapter2.add("Cera");
    adapter2.add("Geléia Real");
    adapter2.add("Própolis");
    adapter2.add("Mel intermediario");

    spProduto.setAdapter(adapter2);

    for (int i = 0; i <= 5; i++) {
        if (spProduto.getItemAtPosition(i).toString().equals(estoque.getTIPO())) {
            spProduto.setSelection(i);
            break;
        }
    }

    }


    private void AtualizacaoProduto(){
        ModelProduto mo = new ModelProduto();
        mo.setID_PRODUTO(estoque.getID_PRODUTO());
        mo.setTIPO(spEstoque.getSelectedItem().toString());
        mo.setDATAPRODUTO(edtDataEstoque.getText().toString());
        ModelCaixa caixa = (ModelCaixa) spEstoque.getSelectedItem();
        mo.setFk_id_caixa(caixa.getID_CAIXA());
        estoquebo.AtualizarProduto(mo);
        MensagemUtil.addMsg(this, "Estoque atualizado com sucesso!");

        Intent i = new Intent(this,listaProdutos.class);
        startActivity(i);
        finish();
    }

}
