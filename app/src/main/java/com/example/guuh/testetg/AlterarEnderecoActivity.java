package com.example.guuh.testetg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterarEnderecoActivity extends AppCompatActivity {
    private EditText et_ruaAntigo,et_ruaAtual,et_bairroAntigo,et_bairroAtual,et_numAntigo,et_numAtual;
    private Button btnAlterar;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterarendereco);

        db = new DBHelper(this);
        botoes();
        alteraEnd();
    }

    private void botoes(){
        et_ruaAntigo = (EditText)findViewById(R.id.ruaAntiga);
        et_ruaAtual = (EditText)findViewById(R.id.ruaAtual);
        et_bairroAntigo = (EditText)findViewById(R.id.bairroAntigo);
        et_bairroAtual = (EditText)findViewById(R.id.bairroAtual);
        et_numAntigo = (EditText)findViewById(R.id.numAntigo);
        et_numAtual = (EditText)findViewById(R.id.numAtual);
        btnAlterar = (Button)findViewById(R.id.btnAlterarEndereco);
    }

    private void alteraEnd(){
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ruaold = et_ruaAntigo.getText().toString();
                String ruanew = et_ruaAtual.getText().toString();
                String bairroold = et_bairroAntigo.getText().toString();
                String bairronew = et_bairroAtual.getText().toString();
                String numold = et_numAntigo.getText().toString();
                String numnew = et_numAtual.getText().toString();

                Integer id = db.Id_user();

                if (db.ValidarRua(ruaold).equals("OK") && db.ValidarBairro(bairroold).equals("OK") && db.ValidarNum(numold).equals("OK")){
                    db.alteraEndereco(id,ruanew,bairronew,numnew);
                    Toast.makeText(AlterarEnderecoActivity.this,"Endereço alterado com sucesso!",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                else{
                    Toast.makeText(AlterarEnderecoActivity.this,"Campos inválidos, tente novamente!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}