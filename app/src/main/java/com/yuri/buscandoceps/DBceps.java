package com.yuri.buscandoceps;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBceps extends SQLiteOpenHelper {

    //nome do banco de dados
    private static final String nome = "CEPS";
    //a versão dele
    private static final int versao = 1;
    //cria a tabela
    private static final String criadora =
            "CREATE TABLE ceps (" +
                    "cep INTEGER PRIMARY KEY," +
                    "logradoura TEXT," +
                    "bairro TEXT" +
                    ");";

    //Construtor do DB (banco de dados)
    public DBceps(Context context) {
        super(
                context,
                nome,
                null,
                versao
        );
    }

    //agora vamos criar o DB propriamente dito
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(criadora);
    }


    //carregando os ceps no aplicativo

    public int inserir() {
        //comando para inserir dados na tabela
        String sql = "INSERT INTO ceps (cep, logardouro, bairro) values (?,?,?);";

        //criou um objeto da classe SQLiteDataBase colocando o metodo desta classe
        SQLiteDatabase db = this.getWritableDatabase();
        /*
        //colocou o comando compilado no SQLite
        SQLiteStatement cmd = db.compileStatement(sql);

        cmd.bindString(1, "44056-186");
        cmd.bindString(2,"Mangabeira");
        cmd.bindString(3,"Acesso 2 (Feira V)");

        return cmd.executeInsert();
        */

        ContentValues obj = new ContentValues();
        //usar array para inserir os dados
        int cep;
        String bairro;
        String logradouro;
        Ceps_dados cp = new Ceps_dados();


        for (int n = 0; n > 976; n++) {

            obj.put("cep", cp.ceps(n));
            obj.put("bairro", cp.bairro(n));
            obj.put("logradouro", cp.Logradoura(n));
        }

        return obj.describeContents();

    }

    //Mudar DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS ceps");

        onCreate(db);

    }
}