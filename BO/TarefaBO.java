package com.example.gavin.aplicacaosiga.BO;

import android.content.Context;

import com.example.gavin.aplicacaosiga.Validacao.Validation;
import com.exemplo.gavin.DAO.DaoCaixa;
import com.exemplo.gavin.DAO.DaoTarefa;
import com.exemplo.gavin.Model.ModelCaixa;
import com.exemplo.gavin.Model.ModelTarefa;

import java.util.List;

/**
 * Created by Gavin on 07/06/2015.
 */
public class TarefaBO {

    private DaoTarefa tarefaDAO;

    public TarefaBO(Context ctx){
        tarefaDAO = new DaoTarefa(ctx);
    }

    public Validation cadastrarTarefa(ModelTarefa tarefaModel){
        Validation retorno = new Validation();
        tarefaDAO.inserirTarefa(tarefaModel);
        retorno.setValido(true);
        retorno.setMensagem("Tarefa cadastrada com sucesso");

        return retorno;
    }

    public List<ModelTarefa> listTarefa(){
        return tarefaDAO.ListaTarefas();
    }

    public ModelTarefa consultaTarefa(Integer idtarefa){
        return tarefaDAO.pesquisaTarefaPorID(idtarefa);
    }

    public void RemoverTarefa(Integer Idtarefa){
        tarefaDAO.RemoverTarefa(Idtarefa);
    }

    public void AtualizarTarefa(ModelTarefa tarefaModel){
        tarefaDAO.AtualizarTarefaPorId(tarefaModel);
    }

}
