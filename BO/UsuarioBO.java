package com.example.gavin.aplicacaosiga.BO;

import android.content.Context;


import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoApiario;
import com.exemplo.gavin.DAO.DaoUsuario;
import com.exemplo.gavin.Model.ModelApiario;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.Model.ModelUsuario;

/**
 * Created by Gavin on 04/06/2015.
 */
public class UsuarioBO {

    private DaoUsuario usuarioDAO;

    public UsuarioBO(Context ctx){
        usuarioDAO = new DaoUsuario(ctx);
    }

    public Validation cadastrarUsuario(ModelUsuario usuarioModel){
        Validation retorno = new Validation();
        usuarioDAO.inserirUsuario(usuarioModel);
        retorno.setValido(true);
        retorno.setMensagem("Novo Login com cadastrado com sucesso");

        return retorno;
    }

    public boolean AutenticaUsuario(String login, String senha){
        if (usuarioDAO.autenticaUsuario(login, senha) != null)
            return true;
        return false;
    }
}
