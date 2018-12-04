package com.example.guuh.testetg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class VerDenunciaActivity extends AppCompatActivity {
    public TextView tipo1,mostra_cidade, mostra_bairro, mostra_endereco,mostra_id;
    public Button bt_mostraDenuncia;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_denuncia);

        db = new DBHelper(this);
        text();
        mostraDenuncia();
    }

    private void text(){
        tipo1 = (TextView) findViewById(R.id.tipo12);
        mostra_endereco = (TextView)findViewById(R.id.endereco);
        mostra_bairro = (TextView)findViewById(R.id.bairro);
        mostra_cidade = (TextView)findViewById(R.id.cidade);
        mostra_id = (TextView)findViewById(R.id.id);
        //busca = (EditText)findViewById(R.id.buscaId);
        bt_mostraDenuncia = (Button)findViewById(R.id.btnMostraDenuncia);

    }

    private void mostraDenuncia(){
        bt_mostraDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    tipo1.setText("Tipo da Denúncia: " + db.mostraTipo());
                    mostra_endereco.setText("Endereço da Denúncia: " + db.mostraEndereco());
                    mostra_bairro.setText("Bairro da Denúncia: " + db.mostraBairro());
                    mostra_cidade.setText("Cidade da Denúncia: " + db.mostraCidade());
                    mostra_id.setText(String.valueOf("Número da Denúncia: " + db.mostraId()));
                }
                /*String tipo = tipo1.getText().toString();
                String endereco = mostra_endereco.getText().toString();
                String bairro = mostra_bairro.getText().toString();
                String cidade = mostra_cidade.getText().toString();
                    Integer res = db.validarId(tipo,endereco,bairro,cidade);
                    if(res.equals(-1)){
                        tipo1.setText("Tipo da Denúncia: "+db.mostraTipo());
                        mostra_endereco.setText("Endereço da Denúncia: "+db.mostraEndereco());
                        mostra_bairro.setText("Bairro da Denúncia: "+db.mostraBairro());
                        mostra_cidade.setText("Cidade da Denúncia: "+db.mostraCidade());
                    }
                    else{
                        Toast.makeText(VerDenunciaActivity.this,"Login inválido, Tente novamente",Toast.LENGTH_SHORT).show();
                    }
                }*/

        });

    }

}