package com.example.guuh.testetg;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Selection;

public class DBHelper extends SQLiteOpenHelper {

    private static int versao = 1;
    private static String nome = "Login_Registro_BaseDados.db";

    public DBHelper(Context context) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE Utilizador(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username VARCHAR(30) NOT NULL, password VARCHAR(20) NOT NULL, nome VARCHAR(60) NOT NULL, rg VARCHAR(15) NOT NULL, cpf INTEGER NOT NULL, rua VARCHAR(60) NOT NULL, bairro VARCHAR(60) NOT NULL, num INTEGER NOT NULL, telefone INTEGER NOT NULL);";
        String reg = "CREATE TABLE Registro(id1 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,tipo VARCHAR(60) NOT NULL, endereco VARCHAR(60) NOT NULL, bairro VARCHAR(60) NOT NULL, cidade VARCHAR(60) NOT NULL);";
        db.execSQL(str);
        db.execSQL(reg);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Utilizador;");
        db.execSQL("DROP TABLE IF EXISTS Registro;");
        onCreate(db);
    }

    public void deletaTabela(Integer id) {
        SQLiteDatabase db = getWritableDatabase();
        //String whereClause = "id=?";
        //db.delete("Utilizador",whereClause,null);
        //db.execSQL("DROP TABLE IF EXISTS Utilizador;");
        //db.execSQL("DROP TABLE IF EXISTS Registro;");
        //db.execSQL("DELETE FROM Utilizador WHERE id = ?;");
        //onCreate(db);
        db.delete("Utilizador","id = ?",new String[]{String.valueOf(id)});
    }

    public void alteraSenha(Integer id,String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password",password);

        //db.execSQL("UPDATE Utilizador SET password = ? WHERE id = ?",new String[]{String.valueOf(id)});
        db.update("Utilizador", cv, "id = ? ", new String[]{String.valueOf(id)});
    }

    public void alteraEndereco(Integer id, String rua, String bairro, String num){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rua",rua);
        cv.put("bairro",bairro);
        cv.put("num",num);

        db.update("Utilizador", cv, "id = ? ", new String[]{String.valueOf(id)});
    }
/*
    public long CriarUtilizador(String username, String password, String nome, String rg, String cpf, String rua, String bairro, String num, String telefone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("nome",nome);
        cv.put("rg",rg);
        cv.put("cpf",cpf);
        cv.put("rua",rua);
        cv.put("bairro",bairro);
        cv.put("num",num);
        cv.put("telefone",telefone);
        long result = db.insert("Utilizador", null, cv);
        return result;
    }
*/

    public long CriarAdm(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);

        long result = db.insert("Utilizador",null,cv);
        return result;
    }

    public String ADM(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "";
        sql = "INSERT INTO Utilizador (username,password) VALUES (?,?)";
        return sql;
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

    public String ValidarCPF(String cpf) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE cpf=?",new String[]{cpf});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }



    public String Validarlogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE username = ? AND password = ?", new String[]{username, password});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }

    public String ValidarUsuario(String username){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE username = ?",new String[]{username});
        if (c.getCount()>0){
            return "OK";
        }
        return "ERRO";

    }


    public String ValidarRG(String rg) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE rg=?", new String[]{rg});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }

    public String ValidarSenha(String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE password=?", new String[]{password});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }

    public String ValidarRua(String rua){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE rua=?", new String[]{rua});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }

    public String ValidarBairro(String bairro){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE bairro=?", new String[]{bairro});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }

    public String ValidarNum(String num){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE num=?", new String[]{num});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }


    public String mostraTipo() {
        String SelectQuery = "SELECT tipo FROM Registro";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while (c.moveToNext()) {
            total = c.getString(c.getColumnIndex("tipo"));
        }
        return total;
    }

    public String mostraEndereco() {
        String SelectQuery = "SELECT endereco FROM Registro";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total = "";
        while (c.moveToNext()) {
            total = c.getString(c.getColumnIndex("endereco"));
        }
        return total;
    }

    public String mostraBairro() {
        String SelectQuery = "SELECT bairro FROM Registro";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total = "";
        while (c.moveToNext()) {
            total = c.getString(c.getColumnIndex("bairro"));
        }
        return total;
    }

    public String mostraCidade() {
        String SelectQuery = "SELECT cidade FROM Registro";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total = "";
        while(c.moveToNext()) {
            total = c.getString(c.getColumnIndex("cidade"));
        }
        return total;
    }

    public Integer mostraId(){
        String SelectQuery = "SELECT id1 FROM Registro";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        Integer total=0;
        while(c.moveToNext()) {
            total = c.getInt(c.getColumnIndex("id1"));
        }
        return total;
    }

    public Integer Id_user(){
        String SelectQuery = "SELECT id FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery,null);
        Integer total = 0;
        while(c.moveToNext()){
            total = c.getInt(c.getColumnIndex("id"));
        }
        return total;
    }


    public String mostraRua(){
        String SelectQuery = "SELECT rua FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while(c.moveToNext()) {
            total = c.getString(c.getColumnIndex("rua"));
        }
        return total;
    }

    public String mostraBairro2(){
        String SelectQuery = "SELECT bairro FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while(c.moveToNext()) {
            total = c.getString(c.getColumnIndex("bairro"));
        }
        return total;
    }

    public String mostraNum(){
        String SelectQuery = "SELECT num FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while(c.moveToNext()) {
            total = c.getString(c.getColumnIndex("num"));
        }
        return total;
    }



    public String mostraUsuario(){
        String SelectQuery = "SELECT username FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while(c.moveToNext()) {
            total = c.getString(c.getColumnIndex("username"));
        }
        return total;
    }

    public String mostraSenha() {
        String SelectQuery = "SELECT senha FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while (c.moveToNext()) {
            total = c.getString(c.getColumnIndex("senha"));
        }
        return total;
    }

    public String mostraNome() {
        String SelectQuery = "SELECT nome FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while (c.moveToNext()) {
            total = c.getString(c.getColumnIndex("nome"));
        }
        return total;
    }

    public String mostraRG() {
        String SelectQuery = "SELECT rg FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while (c.moveToNext()) {
            total = c.getString(c.getColumnIndex("rg"));
        }
        return total;
    }

    public String mostraCPF() {
        String SelectQuery = "SELECT cpf FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while (c.moveToNext()) {
            total = c.getString(c.getColumnIndex("cpf"));
        }
        return total;
    }

    public String mostraTel() {
        String SelectQuery = "SELECT telefone FROM Utilizador";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SelectQuery, null);
        String total="";
        while (c.moveToNext()) {
            total = c.getString(c.getColumnIndex("telefone"));
        }
        return total;
    }
}