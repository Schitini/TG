package com.example.guuh.testetg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.guuh.testetg.ValidaCPF;

public class RegistrarActivity extends AppCompatActivity {
    EditText edt_usuario,edt_senha,edt_nome,edt_rg,edt_cpf,edt_rua,edt_bairro,edt_num,edt_tel;
    Button bt_registrar;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        db = new DBHelper(this);
        botoes();
        cadastro();

    }
    public void botoes() {
        edt_usuario = (EditText) findViewById(R.id.usuario);
        edt_senha = (EditText) findViewById(R.id.senha);
        edt_nome = (EditText)findViewById(R.id.nome);
        edt_rg = (EditText)findViewById(R.id.rg);
        edt_cpf = (EditText)findViewById(R.id.cpf);
        //edt_rua = (EditText)findViewById(R.id.ruaAtual);
        //edt_bairro = (EditText)findViewById(R.id.bairroAtual);
        //edt_num = (EditText)findViewById(R.id.numero);
        edt_tel = (EditText)findViewById(R.id.telefone);
        bt_registrar = (Button) findViewById(R.id.bt_registrarnovo);
    }

    public void cadastro(){
        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_usuario.getText().toString();
                String pass = edt_senha.getText().toString();
                String nome = edt_nome.getText().toString();
                String rg = edt_rg.getText().toString();
                String cpf = edt_cpf.getText().toString();
                String tel = edt_tel.getText().toString();


                if(username.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo Usuário, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if (username.equals("admin")){
                    long res1 = db.CriarAdm(username,pass);

                    if(res1>0){
                        Toast.makeText(RegistrarActivity.this,"Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                    else {
                        Toast.makeText(RegistrarActivity.this,"Inválido, Tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (pass.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo Senha, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if(nome.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo Nome, tente novamente", Toast.LENGTH_SHORT).show();
                }
                else if(rg.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo RG, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if(cpf.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo CPF, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if(tel.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo Telefone, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else{
                    long res = db.CriarUtilizador(username,pass,nome,rg,cpf,tel);
                    if(res>0){
                        if(ValidaCPF.isCPF(cpf) == true) {
                            Toast.makeText(RegistrarActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                        else{
                            Toast.makeText(RegistrarActivity.this, "CPF Inválido, digite um CPF válido!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegistrarActivity.this,"Inválido, Tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
