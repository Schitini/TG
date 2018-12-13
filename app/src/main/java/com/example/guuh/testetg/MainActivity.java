package com.example.guuh.testetg;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt_entrar, bt_registar;
    EditText username, password;
    TextView lei1;
    DBHelper db;
    public TextView senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);

        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);

        bt_entrar=(Button) findViewById(R.id.bt_entrar);
        bt_registar=(Button) findViewById(R.id.bt_registrar);

        lei1 = (TextView)findViewById(R.id.txtLei);

        botoes();

        curiosidade();
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
                    if(user.equals("admin") && pass.equals("adm1234")){
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

    private void curiosidade(){
        lei1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www12.senado.leg.br/noticias/entenda-o-assunto/lei-maria-da-penha"));
                startActivity(browser);
            }
        });
    }
}