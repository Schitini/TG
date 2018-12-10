package com.example.guuh.testetg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class Usuario {

    private int id;
    private String username;
    private String password;
    private String nome;
    private String rg;
    private String cpf;
    private String rua;
    private String bairro;
    private String num;
    private String telefone;
    private Context context;
    private boolean excluir;

    public Usuario (Context context){
        this.context = context;
        id = -1;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }



    public boolean excluir(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            sqLiteDatabase.beginTransaction();
            String sql="";

            sqLiteDatabase.delete("Utilizador","id = ?",new String[]{String.valueOf(id)});

            excluir = true;

            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
    }

    public boolean salvar(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            String sql = "";
            if (id == -1){
                sql = "INSERT INTO Utilizador (username,password,nome,rg,cpf,rua,bairro,num,telefone) VALUES (?,?,?,?,?,?,?,?,?)";
            }else{
                sql = "UPDATE Utilizador SET username=?, password=?, nome=?,rg=?, cpf=?,rua=?,bairro=?,num=?,telefone=? WHERE id = ?";
            }
            sqLiteDatabase.beginTransaction();
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1,username);
            sqLiteStatement.bindString(2,password);
            sqLiteStatement.bindString(3,nome);
            sqLiteStatement.bindString(4,rg);
            sqLiteStatement.bindString(5,cpf);
            sqLiteStatement.bindString(6,rua);
            sqLiteStatement.bindString(7,bairro);
            sqLiteStatement.bindString(8,num);
            sqLiteStatement.bindString(9,telefone);
            if (id != -1)
                sqLiteStatement.bindString(5,String.valueOf(id));
            sqLiteStatement.executeInsert();
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
    }

    public ArrayList<Usuario> getUsuarios(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("Utilizador",null,null,null,null,null,null);
            while (cursor.moveToNext()){
                Usuario usuario = new Usuario(context);
                usuario.id = cursor.getInt(cursor.getColumnIndex("id"));
                usuario.username = cursor.getString(cursor.getColumnIndex("username"));
                usuario.password = cursor.getString(cursor.getColumnIndex("password"));
                usuario.nome = cursor.getString(cursor.getColumnIndex("nome"));
                usuario.rg = cursor.getString(cursor.getColumnIndex("rg"));
                usuario.cpf = cursor.getString(cursor.getColumnIndex("cpf"));
                usuario.rua = cursor.getString(cursor.getColumnIndex("rua"));
                usuario.bairro = cursor.getString(cursor.getColumnIndex("bairro"));
                usuario.num = cursor.getString(cursor.getColumnIndex("num"));
                usuario.telefone = cursor.getString(cursor.getColumnIndex("telefone"));
                usuarios.add(usuario);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
        return usuarios;
    }

    public void carregaUsuarioPeloCodigo(int codigo){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("Utilizador",null,"id = ?",new String[]{String.valueOf(codigo)},null,null,null);
            excluir = true;
            while (cursor.moveToNext()){
                this.id = cursor.getInt(cursor.getColumnIndex("id"));
                username = cursor.getString(cursor.getColumnIndex("username"));
                password = cursor.getString(cursor.getColumnIndex("password"));
                nome = cursor.getString(cursor.getColumnIndex("nome"));
                rg = cursor.getString(cursor.getColumnIndex("rg"));
                cpf = cursor.getString(cursor.getColumnIndex("cpf"));
                rua = cursor.getString(cursor.getColumnIndex("rua"));
                bairro = cursor.getString(cursor.getColumnIndex("bairro"));
                num = cursor.getString(cursor.getColumnIndex("num"));
                telefone = cursor.getString(cursor.getColumnIndex("telefone"));
                excluir = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
    }

}
