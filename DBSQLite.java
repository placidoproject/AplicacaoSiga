package com.example.gavin.aplicacaosiga;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gavin on 01/05/2015.
 */
public class DBSQLite extends SQLiteOpenHelper{

    private static String DataBase_Name ="DBsiga.db";
    private String scriptSQLCreateApiario = "CREATE TABLE APIARIO (ID_APIARIO INTEGER PRIMARY KEY AUTOINCREMENT, DESCRICAO VARCHAR(30)) ;";
    private String getScriptSQLCreateCliente="CREATE TABLE CLIENTE(ID_CLIENTE INTEGER PRIMARY KEY AUTOINCREMENT, NOME VARCHAR(20), CPF VARCHAR(11), ENDERECO VARCHAR(50), BAIRRO VARCHAR(30), CIDADE VARCHAR(20));";
    private static int versao = 1;
   public DBSQLite(Context ctx){
       super(ctx,DataBase_Name,null,versao);
   }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(scriptSQLCreateApiario);
        db.execSQL(getScriptSQLCreateCliente);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}