package com.example.gavin.aplicacaosiga.BO;

import android.content.Context;


import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoFornecedor;
import com.exemplo.gavin.Model.ModelFornecedor;

import java.util.List;

/**
 * Created by Gavin on 17/05/2015.
 */
public class FornecedorBO {

    private DaoFornecedor forDAO;

    public FornecedorBO(Context ctx){
        forDAO = new DaoFornecedor(ctx);
    }

    public Validation cadastrarFornecedor(ModelFornecedor fornecedorModel){
        Validation retorno = new Validation();
        forDAO.inserirFornecedor(fornecedorModel);
        retorno.setValido(true);
        retorno.setMensagem("Fornecedor cadastrado com sucesso");

        return retorno;
    }

    public List<ModelFornecedor> listaFornecedor(){
        return forDAO.ListaFornecedor();
    }

    public ModelFornecedor consultaFornecedor(Integer idfornecedor){
        return forDAO.pesquisaFornecedorPorID(idfornecedor);
    }

    public void RemoverFornecedor(Integer IdFornecedor){
        forDAO.RemoverFornecedor(IdFornecedor);
    }

    public void AtulaizarFornecedor(ModelFornecedor fornecedorModel){
        forDAO.AtualizarFornecedorPorId(fornecedorModel);
    }
}
