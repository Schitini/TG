package com.example.guuh.testetg;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static int versao = 1;
    private static String nome = "Login_Registro_BaseDados.db";

    public DBHelper(Context context) {
        super(context, nome,  null, versao);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE Utilizador(username TEXT PRIMARY KEY, password TEXT);";
        String reg = "CREATE TABLE Registro(tipo TEXT PRIMARY KEY, endereco TEXT, bairro TEXT, cidade TEXT);";
        db.execSQL(str);
        db.execSQL(reg);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Utilizador;");
        db.execSQL("DROP TABLE IF EXISTS Registro;");
        onCreate(db);
    }

    public long CriarUtilizador(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        long result = db.insert("Utilizador", null, cv);
        return result;
    }

    public long CriarRegistro(String tipo, String endereco, String bairro, String cidade) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tipo", tipo);
        cv.put("endereco", endereco);
        cv.put("bairro", bairro);
        cv.put("cidade", cidade);

        long result = db.insert("Registro", null, cv);
        return result;
    }


    public String Validarlogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE username=? AND password=?", new String[]{username, password});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }
/*
    public String ValidarDenuncia(String tipo, String endereco, String bairro, String cidade){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Registro WHERE tipo=? AND endereco=? AND bairro=? AND cidade=?",new String[]{tipo,endereco,bairro,cidade});
        if (c.getCount() > 0){
            return "OK";
        }
        return "ERRO";
    }
    */
}