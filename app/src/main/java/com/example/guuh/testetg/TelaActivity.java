package com.example.guuh.testetg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.zip.Inflater;

public class TelaActivity extends AppCompatActivity {
    public Button btDenuncia,btPolicia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela);
        botoes();
        botaoDenuncia();
        botaoPolicia();
    }

    private void botoes(){
        btDenuncia = (Button)findViewById(R.id.btnRealizarDenuncia);
        btPolicia = (Button)findViewById(R.id.btnAcionar);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuaction,menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.i_alterarsenha:
                Intent as = new Intent(TelaActivity.this, AlterarSenhaActivity.class);
                startActivity(as);
                return (true);
            //break;
            case R.id.i_alterarendereco:
                Intent ae = new Intent(TelaActivity.this,AlterarEnderecoActivity.class);
                startActivity(ae);
                return (true);
            //break;
            case R.id.i_desativarconta:
                Intent dc = new Intent(TelaActivity.this,DesativarContaActivity.class);
                startActivity(dc);
                return (true);
            //break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void botaoDenuncia(){
        btDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaActivity.this,DenunciaActivity.class);
                startActivity(i);
            }
        });
    }

    private void botaoPolicia(){
        btPolicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaActivity.this,DadosActivity.class);
                startActivity(intent);
            }
        });
    }
}