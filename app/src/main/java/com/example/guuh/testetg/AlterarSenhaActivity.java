package com.example.guuh.testetg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        botaoAlterar();
    }

    private void botoes(){
        et_senhaantiga = (EditText)findViewById(R.id.senhaAntiga);
        et_senhaatual = (EditText)findViewById(R.id.senhaAtual);
        btnAlterar = (Button)findViewById(R.id.btnAlterarSenha);
    }

    private void botaoAlterar(){
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senhaold = et_senhaantiga.getText().toString();
                String senhanew = et_senhaatual.getText().toString();
                Integer id = db.Id_user();

                if ((db.ValidarSenha(senhaold).equals("OK")) && (senhanew.length() >= 4) && (senhanew.length() <= 10)){
                    db.alteraSenha(id,senhanew);
                    Toast.makeText(AlterarSenhaActivity.this,"Senha Alterada com sucesso!",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                else{
                    Toast.makeText(AlterarSenhaActivity.this, "Senha incorreta ou fora do limite permitido, tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}