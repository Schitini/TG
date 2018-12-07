package com.example.guuh.testetg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterarSenhaActivity extends AppCompatActivity {
    private EditText et_senhaantiga, et_senhaatual;
    private Button btnAlterar;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterarsenha);
        db = new DBHelper(this);
        botoes();
        //botaoAlterar();
    }

    private void botoes(){
        et_senhaantiga = (EditText)findViewById(R.id.senhaAntiga);
        et_senhaatual = (EditText)findViewById(R.id.senhaAtual);
        btnAlterar = (Button)findViewById(R.id.btnAlterarSenha);
    }


}