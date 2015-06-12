package com.example.gavin.aplicacaosiga.BO;

import android.content.Context;


import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.Model.ModelApiario;

import java.util.List;

/**
 * Created by Gavin on 22/05/2015.
 */
public class ApiarioBO {
    private DaoApiario apiarioDAO;

    public ApiarioBO(Context ctx){
        apiarioDAO = new DaoApiario(ctx);
    }

    public Validation cadastrarApiario(ModelApiario apiarioModel){
        Validation retorno = new Validation();
        apiarioDAO.inserirApiario(apiarioModel);
        retorno.setValido(true);
        retorno.setMensagem("Apiario cadastrado com sucesso");

        return retorno;
    }

    public List<ModelApiario> listapiario(){
        return apiarioDAO.ListaApiario();
    }

    public ModelApiario consultaApiario(Integer idapiario){
        return apiarioDAO.pesquisaID(idapiario);
    }

    public void RemoverApiario(Integer idapiario){
        apiarioDAO.deleteApiario(idapiario);
    }

    public void AtualizarApiario(ModelApiario moapiario){
        apiarioDAO.alterarApiaro(moapiario);
    }

}
