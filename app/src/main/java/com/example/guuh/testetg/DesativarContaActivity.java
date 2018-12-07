package com.example.guuh.testetg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DesativarContaActivity extends AppCompatActivity {
    private EditText et_rgexcluir, et_senhaexcluir;
    private Button btDesativar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desativarconta);

        botoes();
    }

    private void botoes(){
        et_rgexcluir = (EditText)findViewById(R.id.rgExcluir);
        et_senhaexcluir = (EditText)findViewById(R.id.senhaExcluir);
        btDesativar = (Button)findViewById(R.id.btnExcluir);
    }
}