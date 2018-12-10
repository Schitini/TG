package com.example.guuh.testetg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
    private final Usuario usuario = new Usuario(this);

    DBHelper db;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        db = new DBHelper(this);
        botoes();
        cadastro();

        if (getIntent().getExtras() != null){
            int id = getIntent().getExtras().getInt("consulta");
            usuario.carregaUsuarioPeloCodigo(id);
        }

    }
    public void botoes() {
        edt_usuario = (EditText) findViewById(R.id.usuario);
        edt_senha = (EditText) findViewById(R.id.senha);
        edt_nome = (EditText)findViewById(R.id.nome);
        edt_rg = (EditText)findViewById(R.id.rg);
        edt_cpf = (EditText)findViewById(R.id.cpf);
        edt_rua = (EditText)findViewById(R.id.rua);
        edt_bairro = (EditText)findViewById(R.id.bairro);
        edt_num = (EditText)findViewById(R.id.numero);
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
                String rua = edt_rua.getText().toString();
                String bairro = edt_bairro.getText().toString();
                String num = edt_num.getText().toString();
                String tel = edt_tel.getText().toString();


                if(edt_usuario.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo Usuário, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if (username.equals("admin") && pass.equals("adm1234")) {
                    long res1 = db.CriarAdm(username, pass);
                    if (res1 > 0) {
                        Toast.makeText(RegistrarActivity.this, "Administrador cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(RegistrarActivity.this, "Inválido, Tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }
                if (edt_senha.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo Senha, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if(edt_nome.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo Nome, tente novamente", Toast.LENGTH_SHORT).show();
                }
                else if(edt_rg.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo RG, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if(edt_cpf.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo CPF, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if(edt_rua.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo Rua, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if(edt_bairro.equals("")){
                    Toast.makeText(RegistrarActivity.this, "Deve preencher o campo Bairro, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if(edt_num.equals("")){
                    Toast.makeText(RegistrarActivity.this, "Deve preencher o campo Número, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else if(edt_tel.equals("")){
                    Toast.makeText(RegistrarActivity.this,"Deve preencher o campo Telefone, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //long res = db.CriarUtilizador(username,pass,nome,rg,cpf,rua,bairro,num,tel);
                    /*String cp = db.ValidarCPF();
                    String validauser = db.ValidarUsuario(username);
                    if(cp.equals("OK")){
                        Toast.makeText(RegistrarActivity.this,"CPF já cadastrado!",Toast.LENGTH_SHORT).show();
                    }*/
                    boolean valido = true;
                    usuario.setUsername(edt_usuario.getText().toString().trim());
                    usuario.setPassword(edt_senha.getText().toString().trim());
                    usuario.setNome(edt_nome.getText().toString().trim());
                    usuario.setRg(edt_rg.getText().toString().trim());
                    usuario.setCpf(edt_cpf.getText().toString().trim());
                    usuario.setRua(edt_rua.getText().toString().trim());
                    usuario.setBairro(edt_bairro.getText().toString().trim());
                    usuario.setNum(edt_num.getText().toString().trim());
                    usuario.setTelefone(edt_tel.getText().toString().trim());
                    //if(res>0){
                        /*if(validauser.equals("ERRO")){
                            Toast.makeText(RegistrarActivity.this,"Usuário já cadastrado!",Toast.LENGTH_SHORT).show();
                        }*/

                        if(!ValidaCPF.isCPF(cpf) && nome.length() < 7 || !ValidaCPF.isCPF(cpf) && nome.length() > 50){
                            Toast.makeText(RegistrarActivity.this, "CPF e Nome inválidos, tente novamente!", Toast.LENGTH_SHORT).show();
                        }
                        else if(!ValidaCPF.isCPF(cpf) && pass.length() < 4 || !ValidaCPF.isCPF(cpf) && pass.length() > 10){
                            Toast.makeText(RegistrarActivity.this,"CPF inválido e senha muito curta ou ultrapassou o limite de caracteres", Toast.LENGTH_LONG).show();
                        }
                        else if(nome.length() < 7 && pass.length() < 4 || nome.length() > 50 && pass.length() > 10 || nome.length() < 7 && pass.length() > 10 || nome.length() > 50 && pass.length() < 4){
                            Toast.makeText(RegistrarActivity.this, "Nome inválido e senha muito curta ou ultrapassou o limite de caracteres",Toast.LENGTH_LONG).show();
                        }
                        else if(pass.length() < 4){
                            Toast.makeText(RegistrarActivity.this, "Senha fraca, digite uma senha maior!",Toast.LENGTH_SHORT).show();
                        }
                        else if(pass.length() > 10){
                            Toast.makeText(RegistrarActivity.this, "Senha ultrapassou o limite de caracteres permitido!", Toast.LENGTH_SHORT).show();
                        }
                        else if(!ValidaCPF.isCPF(cpf)){
                            Toast.makeText(RegistrarActivity.this,"CPF inválido, tente novamente!", Toast.LENGTH_SHORT).show();
                        }
                        else if(db.ValidarCPF(cpf).equals("OK")){
                            Toast.makeText(RegistrarActivity.this,"CPF já cadastrado!", Toast.LENGTH_SHORT).show();
                        }
                        else if(db.ValidarUsuario(username).equals("OK")){
                            Toast.makeText(RegistrarActivity.this,"Usuário já cadastrado!", Toast.LENGTH_SHORT).show();
                        }
                        else if(db.ValidarRG(rg).equals("OK")){
                            Toast.makeText(RegistrarActivity.this,"RG já cadastrado!", Toast.LENGTH_SHORT).show();
                        }
                        else if((ValidaCPF.isCPF(cpf)) && (nome.length() >= 7) && (nome.length() <= 50) && (pass.length() >= 4) && (pass.length() <=10)) {
                            if(valido){
                                usuario.salvar();
                                Toast.makeText(RegistrarActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                finish();
                                onBackPressed();
                            }
                        }
                        else{
                            Toast.makeText(RegistrarActivity.this, "Nome Inválido, tente novamente!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //else {
                      //  Toast.makeText(RegistrarActivity.this,"Usuário já cadastrado! Tente novamente", Toast.LENGTH_SHORT).show();
                   // }
                }
        });
    }
}