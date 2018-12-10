package com.example.guuh.testetg;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DesativarContaActivity extends AppCompatActivity {
    private EditText et_rgexcluir, et_senhaexcluir;
    private Button btDesativar;
    DBHelper db;
    private final Usuario usuario = new Usuario(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desativarconta);
        db = new DBHelper(this);

        botoes();
        botaoExcluir();

    }

    private void botoes(){
        et_rgexcluir = (EditText)findViewById(R.id.rgExcluir);
        et_senhaexcluir = (EditText)findViewById(R.id.senhaExcluir);
        btDesativar = (Button)findViewById(R.id.btnExcluir);
    }


    private void botaoExcluir(){
        btDesativar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rg = et_rgexcluir.getText().toString();
                String senha = et_senhaexcluir.getText().toString();

                String res = db.ValidarRG(rg);
                String res1 = db.ValidarSenha(senha);
                //Integer xd = db.mostraId_user();
                Integer id = db.Id_user();

                if ((res.equals("OK")) && (res1.equals("OK"))){
                    //Toast.makeText(DesativarContaActivity.this,"Teste",Toast.LENGTH_SHORT).show();
                    //usuario.excluir();
                    Toast.makeText(DesativarContaActivity.this,"Conta Excluída "+db.Id_user(),Toast.LENGTH_SHORT).show();
                    db.deletaTabela(id);
                    Intent i = new Intent(DesativarContaActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(DesativarContaActivity.this,"RG ou senha inválidos, tente novamente",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}