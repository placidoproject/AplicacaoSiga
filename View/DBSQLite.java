package com.example.gavin.aplicacaosiga.View;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gavin on 01/05/2015.
 */
public class DBSQLite extends SQLiteOpenHelper{

    private static String DataBase_Name ="DBsiga.db";
    private String scriptSQLCreateApiario = "CREATE TABLE APIARIO (ID_APIARIO INTEGER PRIMARY KEY AUTOINCREMENT, DESCRICAO VARCHAR(30)) ;";
    private String ScriptSQLCreateCliente="CREATE TABLE CLIENTE(ID_CLIENTE INTEGER PRIMARY KEY AUTOINCREMENT, NOME VARCHAR(20), CPF VARCHAR(11), ENDERECO VARCHAR(50), BAIRRO VARCHAR(30), CIDADE VARCHAR(20), TELEFONE VARCHAR(14));";
    private String ScriptSQLCreateCaixa="CREATE TABLE CAIXA (ID_CAIXA INTEGER PRIMARY KEY AUTOINCREMENT,NOMECAIXA VARCHAR(20), DATACAIXA VARCHAR(10), DATAMANUTENCAO VARCHAR(10), COLETA TEXT,  fk_id_apiario INTEGER, FOREIGN KEY (fk_id_apiario) REFERENCES APIARIO(ID_APIARIO));";
    private String ScriptSQLCreateFornecedor ="CREATE TABLE FORNECEDOR(ID_FORNECEDOR INTEGER PRIMARY KEY AUTOINCREMENT, NOMEFOR VARCHAR(20),ENDERECOFOR VARCHAR(30),CIDADEFOR VARCHAR(10),EMAILFOR VARCHAR(30),TELEFONEFOR VARCHAR(14), COMPRAFOR VARCHAR(30), VALORCOMPRAFOR REAL);";
    private String ScriptSQLCreateProduto = "CREATE TABLE PRODUTO (ID_PRODUTO INTEGER PRIMARY KEY AUTOINCREMENT, TIPO VARCHAR(30), DATAPRODUTO VARCHAR(10), QUANTIDADE VARCHAR(10), fk_id_caixa INTEGER, FOREIGN KEY (fk_id_caixa) REFERENCES CAIXA(ID_CAIXA));";
    private String ScriptSQLCreateAbelha = "CREATE TABLE ABELHARAINHA (ID_ABELHA INTEGER PRIMARY KEY AUTOINCREMENT, DESCRICAOABELHA VARCHAR(20), DATAINSERCAO VARCHAR(10), fk_id_caixa INTEGER, FOREIGN KEY (fk_id_caixa) REFERENCES CAIXA(ID_CAIXA));";
  // private String ScriptSQLCreateVenda = "CREATE TABLE VENDA (ID_VENDA INTEGER PRIMARY KEY AUTOINCREMENT, DATAVENDA VARCHAR(10), VQUANTIDADE INTEGER, VPRECO REAL, fk_id_cliente INTEGER, FOREIGN KEY (fk_id_cliente) REFERENCES CLIENTE(ID_CLIENTE), fk_id_produto INTEGER, FOREIGN KEY (fk_id_produto) REFERENCES PRODUTO(ID_PRODUTO));";
   private String ScriptSQLCreateLogin ="CREATE TABLE USUARIO (ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT, LOGIN VARCHAR(10), SENHA VARCHAR(8));";
   private String ScriptSQLCreateTarefas ="CREATE TABLE TAREFA (ID_TAREFA INTEGER PRIMARY KEY AUTOINCREMENT, TAREFAS TEXT, DATATAREFA VARCHAR(10), ATIVIDADE INTEGER, fk_id_apiario INTEGER, fk_id_caixa INTEGER, FOREIGN KEY (fk_id_apiario) REFERENCES APIARIO(ID_APIARIO), FOREIGN KEY (fk_id_caixa) REFERENCES CAIXA(ID_CAIXA)); ";
    private static int versao = 1;
   public DBSQLite(Context ctx){
       super(ctx,DataBase_Name,null,versao);
   }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(scriptSQLCreateApiario);
        db.execSQL(ScriptSQLCreateCliente);
        db.execSQL(ScriptSQLCreateFornecedor);
        db.execSQL(ScriptSQLCreateCaixa);
        db.execSQL(ScriptSQLCreateProduto);
        db.execSQL(ScriptSQLCreateAbelha);
        db.execSQL(ScriptSQLCreateLogin);
        db.execSQL(ScriptSQLCreateTarefas);
      // db.execSQL(ScriptSQLCreateVenda);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}