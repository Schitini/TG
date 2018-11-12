package com.example.guuh.testetg;

import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DenunciaActivity extends AppCompatActivity {
    EditText et_tipo,et_endereco,et_bairro,et_cidade;
    Button bt_denuncia,bt_pesquisa;
    SupportMapFragment pesquisa_map;
    public GoogleMap map;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        db = new DBHelper(this);
        botoes2();
        fazDenuncia();
        //pesquisaLocal();
    }

    public void botoes2(){
        et_tipo = (EditText)findViewById(R.id.edtTipoD);
        et_endereco = (EditText)findViewById(R.id.edtEndereco);
        et_bairro = (EditText)findViewById(R.id.edtBairro);
        et_cidade = (EditText)findViewById(R.id.edtCidade);
        bt_denuncia = (Button)findViewById(R.id.btnRealizarDenuncia);
        bt_pesquisa = (Button)findViewById(R.id.btnPesquisa);
    }

    public void fazDenuncia(){
        bt_denuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipo = et_tipo.getText().toString();
                String endereco = et_endereco.getText().toString();
                String bairro = et_bairro.getText().toString();
                String cidade = et_cidade.getText().toString();

                if (tipo.equals("") || endereco.equals("") || bairro.equals("") || cidade.equals("")){
                    Toast.makeText(DenunciaActivity.this,"Todos os campos são obrigatórios! Tente novamente", Toast.LENGTH_SHORT).show();
                }
                else {
                    long res = db.CriarRegistro(tipo, endereco, bairro, cidade);
                    if (res > 0) {
                        Toast.makeText(DenunciaActivity.this, "Denúncia realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        //onBackPressed();
                    } else {
                        Toast.makeText(DenunciaActivity.this, "Inválido, Tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void pesquisaLocal(){

    }
}
