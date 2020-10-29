package com.example.casadoacaitcc.CadastroCliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.casadoacaitcc.R;

import utils.utilsCadastro_cliente;

public class Cadastro1 extends AppCompatActivity implements View.OnClickListener {

    EditText txtNome, txtSenha, txtEmail, txtData;
    Button btnCad1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro1);

        txtNome = findViewById(R.id.txtNome);
        txtSenha = findViewById(R.id.txtSenha);
        txtEmail = findViewById(R.id.txtEmail);
        txtData = findViewById(R.id.txtData);
        btnCad1 = findViewById(R.id.btnCad1);

        btnCad1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCad1:

                utilsCadastro_cliente.setUnome_cli(txtNome.getText().toString());
                utilsCadastro_cliente.setUsenha_cli(txtSenha.getText().toString());
                utilsCadastro_cliente.setUemail_cli(txtEmail.getText().toString());
                utilsCadastro_cliente.setUdtnasc_cli(txtData.getText().toString());


                Intent telaCad2 = new Intent(this, Cadastro2.class);
                startActivity(telaCad2);

                break;
        }
    }
}