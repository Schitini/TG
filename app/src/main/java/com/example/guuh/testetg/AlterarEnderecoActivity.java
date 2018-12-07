package com.example.guuh.testetg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AlterarEnderecoActivity extends AppCompatActivity {
    private EditText et_endantigo, et_endatual;
    private Button btnAlterar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterarendereco);

        botoes();
    }

    private void botoes(){
        et_endantigo = (EditText)findViewById(R.id.enderecoAntigo);
        et_endatual = (EditText)findViewById(R.id.enderecoAtual);
        btnAlterar = (Button)findViewById(R.id.btnAlterarEndereco);
    }
}