package com.example.gavin.aplicacaosiga.BO;

import android.content.Context;


import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoAbelha;
import com.exemplo.gavin.Model.ModelAbelha;

import java.util.List;

/**
 * Created by Gavin on 23/05/2015.
 */
public class AbelhaBO {
    private DaoAbelha abelhaDAO;

    public AbelhaBO(Context ctx){
        abelhaDAO = new DaoAbelha(ctx);
    }

    public Validation cadastrarAbelha(ModelAbelha abelhaModel){
        Validation retorno = new Validation();
        abelhaDAO.inserirAbelha(abelhaModel);
        retorno.setValido(true);
        retorno.setMensagem("Abelha Rainha cadastrada com sucesso");

        return retorno;
    }

    public List<ModelAbelha> listAbelha(){
        return abelhaDAO.ListaAbelhas();
    }

    public ModelAbelha consultaAbelha(Integer idabelha){
        return abelhaDAO.pesquisaAbelhaPorID(idabelha);
    }

    public void RemoverAbelha(Integer IdAbelha){
        abelhaDAO.RemoverAbelha(IdAbelha);
    }

    public void AtualizarAbelha(ModelAbelha abelhaModel){
        abelhaDAO.AtualizarAbelhaPorId(abelhaModel);
    }


}
