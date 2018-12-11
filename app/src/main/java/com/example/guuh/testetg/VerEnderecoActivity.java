package com.example.guuh.testetg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerEnderecoActivity extends AppCompatActivity {
public TextView mostra_rua,mostra_bairro2,mostra_num;
public Button btnVerEnd;
DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_endereco);

        db = new DBHelper(this);

        botoes();
        verEnd();
    }

    private void botoes(){
        mostra_rua = (TextView)findViewById(R.id.exibeRua);
        mostra_bairro2 = (TextView)findViewById(R.id.exibeBairro2);
        mostra_num = (TextView)findViewById(R.id.exibeNum);

        btnVerEnd = (Button)findViewById(R.id.VerUltimoEnd);
    }

    private void verEnd(){
        btnVerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostra_rua.setText("Rua: " + db.mostraRua());
                mostra_bairro2.setText("Bairro: " + db.mostraBairro2());
                mostra_num.setText("Número: " + db.mostraNum());
            }
        });
    }
}
