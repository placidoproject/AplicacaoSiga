package com.example.gavin.aplicacaosiga;

import android.content.Context;

import com.exemplo.gavin.DAO.DaoCliente;
import com.exemplo.gavin.Model.ModelCliente;

import java.util.List;


/**
 * Created by Gavin on 09/05/2015.
 */
public class ClienteBO {

    private DaoCliente cliDAO;

    public ClienteBO(Context ctx){
        cliDAO = new DaoCliente(ctx);
    }

    public ValidacaoCliente cadastrarCliente(ModelCliente clienteModel){
        ValidacaoCliente retorno = new ValidacaoCliente();
        cliDAO.inserirCliente(clienteModel);
        retorno.setValido(true);
        retorno.setMensagem("Cliente cadastrado com sucesso");

        return retorno;
    }

    public List<ModelCliente> listCliente(){
        return cliDAO.ListaCliente();
    }

    public ModelCliente consultaCliente(Integer idcliente){
        return cliDAO.pesquisaClientePorID(idcliente);
    }

    public void RemoverCliente(Integer IdCliente){
        cliDAO.RemoverCliente(IdCliente);
    }
}
