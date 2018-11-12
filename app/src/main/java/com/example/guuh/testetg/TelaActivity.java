package com.example.guuh.testetg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaActivity extends AppCompatActivity {
    public Button btnDenuncia,btnPolicia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela);
        btnDenuncia = (Button)findViewById(R.id.btnRealizarDenuncia);
        btnPolicia = (Button)findViewById(R.id.btnAcionar);
        botaoDenuncia();
        botaoPolicia();
    }

    private void botaoDenuncia(){
        btnDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaActivity.this,DenunciaActivity.class);
                startActivity(i);
            }
        });
    }

    private void botaoPolicia(){


        btnPolicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaActivity.this,DadosActivity.class);
                startActivity(intent);
            }
        });
    }

}
