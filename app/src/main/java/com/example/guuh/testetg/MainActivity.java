package com.example.guuh.testetg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt_entrar, bt_registar;
    EditText username, password;
    DBHelper db;
    public TextView senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        esqueceuSenha();
        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);

        bt_entrar=(Button) findViewById(R.id.bt_entrar);
        bt_registar=(Button) findViewById(R.id.bt_registrar);

        botoes();
    }






    private void botoes()
    {
        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(username.equals("")){
                    Toast.makeText(MainActivity.this,"Usuário não inserido, tente novamente",Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("")){
                    Toast.makeText(MainActivity.this,"Senha não inserida, tente novamente",Toast.LENGTH_SHORT).show();
                }
                else{
                    String res = db.Validarlogin(user, pass);
                    if(user.equals("admin")){
                        Toast.makeText(MainActivity.this,"Administrador logado com sucesso!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,VerDenunciaActivity.class);
                        startActivity(intent);
                    }
                    else if(res.equals("OK")){
                        Toast.makeText(MainActivity.this,"Login realizado com sucesso!",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, TelaActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Login inválido, Tente novamente",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        bt_registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistrarActivity.class);
                username.setText("");
                password.setText("");
                startActivity(i);
            }

        });

    }
    private void esqueceuSenha(){
        senha = (TextView)findViewById(R.id.esqueceSenha);
        senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,SenhaActivity.class));
            }
        });
    }
}