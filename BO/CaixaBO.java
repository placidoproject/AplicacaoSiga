package com.example.gavin.aplicacaosiga.BO;

import android.content.Context;


import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.Model.ModelCaixa;

import java.util.List;

/**
 * Created by Gavin on 17/05/2015.
 */
public class CaixaBO {

    private DaoCaixa caixaDAO;

    public CaixaBO(Context ctx){
        caixaDAO = new DaoCaixa(ctx);
    }

    public Validation cadastrarCaixa(ModelCaixa caixaModel){
        Validation retorno = new Validation();
        caixaDAO.inserirCaixa(caixaModel);
        retorno.setValido(true);
        retorno.setMensagem("Caixa cadastrado com sucesso");

        return retorno;
    }

    public List<ModelCaixa> listCaixa(){
        return caixaDAO.ListaCaixa();
    }

    public ModelCaixa consultaCaixa(Integer idcaixa){
        return caixaDAO.pesquisaCaixaPorID(idcaixa);
    }

    public void RemoverCaixa(Integer IdCaixa){
        caixaDAO.RemoverCaixa(IdCaixa);
    }

    public void AtualizarCaixa(ModelCaixa caixaModel){
        caixaDAO.AtualizarCaixaPorId(caixaModel);
    }





}
