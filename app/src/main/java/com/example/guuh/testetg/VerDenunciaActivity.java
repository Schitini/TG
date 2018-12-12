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
    public TextView mostra_nome,mostra_rg,mostra_cpf,mostra_telefone;
    public TextView mostra_nome1,mostra_rg1,mostra_cpf1,mostra_telefone1;
    public TextView mostra_endfinal,mostra_numfinal,mostra_bairrofinal,mostra_cidfinal,mostra_estado,mostra_cep,mostra_pais;
    public Button bt_mostraDenuncia,bt_mostraAcionamento;
    public EditText busca;
    int i = 0;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_denuncia);

        db = new DBHelper(this);
        text();
        text2();
        mostraDenuncia();
        aciona();
    }

    private void text(){
        tipo1 = (TextView) findViewById(R.id.tipo12);
        mostra_endereco = (TextView)findViewById(R.id.endereco);
        mostra_bairro = (TextView)findViewById(R.id.bairro);
        mostra_cidade = (TextView)findViewById(R.id.cidade);
        mostra_id = (TextView)findViewById(R.id.id);
        //busca = (EditText)findViewById(R.id.buscaID);

        mostra_nome = (TextView)findViewById(R.id.exibeNome);
        mostra_rg = (TextView)findViewById(R.id.exibeRG);
        mostra_cpf = (TextView)findViewById(R.id.exibeCPF);
        mostra_telefone = (TextView)findViewById(R.id.exibeTel);

        bt_mostraDenuncia = (Button)findViewById(R.id.btnMostraDenuncia);
    }

    private void text2(){
        mostra_nome1 = (TextView)findViewById(R.id.exibeNome1);
        mostra_rg1 = (TextView)findViewById(R.id.exibeRG1);
        mostra_cpf1 = (TextView)findViewById(R.id.exibeCPF1);
        mostra_telefone1 = (TextView)findViewById(R.id.exibeTel1);
        mostra_endfinal = (TextView)findViewById(R.id.exibeEndFinal);
        mostra_numfinal = (TextView)findViewById(R.id.exibeNumfinal);
        mostra_bairrofinal = (TextView)findViewById(R.id.exibeBairroFinal);
        mostra_cidfinal = (TextView)findViewById(R.id.exibeCidFinal);
        mostra_estado = (TextView)findViewById(R.id.exibeEstado);
        mostra_cep = (TextView)findViewById(R.id.exibeCEP);
        mostra_pais = (TextView)findViewById(R.id.exibePais);

        bt_mostraAcionamento = (Button)findViewById(R.id.btnMostraAcionamento);
    }

    private void mostraDenuncia() {

        bt_mostraDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostra_nome.setText("Nome: " + db.mostraNome());
                mostra_rg.setText("RG: " + db.mostraRG());
                mostra_cpf.setText("CPF: " + db.mostraCPF());
                mostra_telefone.setText("Telefone: " + db.mostraTel());
                tipo1.setText("Tipo da Denúncia: " + db.mostraTipo());
                mostra_endereco.setText("Endereço da Denúncia: " + db.mostraEndereco());
                mostra_bairro.setText("Bairro da Denúncia: " + db.mostraBairro());
                mostra_cidade.setText("Cidade da Denúncia: " + db.mostraCidade());
                mostra_id.setText(String.valueOf("Número da Denúncia: " + db.mostraId()));
            }
        });
    }

    private void aciona(){
        bt_mostraAcionamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostra_nome1.setText("Nome: " + db.mostraNome());
                mostra_rg1.setText("RG: " + db.mostraRG());
                mostra_cpf1.setText("CPF: " + db.mostraCPF());
                mostra_telefone1.setText("Telefone: " + db.mostraTel());
                mostra_endfinal.setText(db.mostraEndFinal());
                mostra_numfinal.setText("Número: "+db.mostraNumFinal());
                mostra_bairrofinal.setText("Bairro: "+db.mostraBairroFinal());
                mostra_cidfinal.setText("Cidade: "+db.mostraCidFinal());
                mostra_estado.setText("Estado: "+db.mostraEstado());
                mostra_cep.setText("CEP: "+db.mostraCEP());
                mostra_pais.setText("País: "+db.mostraPais());
            }
        });
    }
}