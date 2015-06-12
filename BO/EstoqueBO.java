package com.example.gavin.aplicacaosiga.BO;

import android.content.Context;


import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoProduto;
import com.exemplo.gavin.Model.ModelProduto;

import java.util.List;

/**
 * Created by Gavin on 20/05/2015.
 */
public class EstoqueBO {

    private DaoProduto produtoDAO;

    public EstoqueBO(Context ctx){
       produtoDAO = new DaoProduto(ctx);
    }

    public Validation cadastrarProduto(ModelProduto produtoModel){
        Validation retorno = new Validation();
        produtoDAO.inserirProduto(produtoModel);
        retorno.setValido(true);
        retorno.setMensagem("Produto cadastrado com sucesso");

        return retorno;
    }

    public List<ModelProduto> listProduto(){
        return produtoDAO.ListaProdutos();
    }

    public List<ModelProduto> listProduto(String paran){
        return produtoDAO.ListaProdutos(paran);
    }

    public ModelProduto consultaProdutos(Integer idproduto){
        return produtoDAO.pesquisaProdutoPorID(idproduto);
    }

    public void RemoverProduto(Integer Idproduto){
        produtoDAO.RemoverProdutos(Idproduto);
    }

    public void AtualizarProduto(ModelProduto produtoModel){
        produtoDAO.AtualizarProdutoPorId(produtoModel);
    }
}
