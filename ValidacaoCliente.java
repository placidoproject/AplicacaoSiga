package com.example.gavin.aplicacaosiga;

/**
 * Created by Gavin on 09/05/2015.
 */
public class ValidacaoCliente {

    private boolean valido;

    private String mensagem;

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
