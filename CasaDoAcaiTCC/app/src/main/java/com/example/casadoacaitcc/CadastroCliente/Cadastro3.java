package com.example.casadoacaitcc.CadastroCliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.casadoacaitcc.R;

import dao.conectarBD;
import model.cadastro_cliente;
import utils.utilsCadastro_cliente;

public class Cadastro3 extends AppCompatActivity implements View.OnClickListener {

    EditText txtCEP, txtNumero, txtComplemento;
    Button btnCad3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro3);

        txtCEP = findViewById(R.id.txtCEP);
        txtNumero = findViewById(R.id.txtNumero);
        txtComplemento = findViewById(R.id.txtComplemento);
        btnCad3 = findViewById(R.id.btnCad3);

        btnCad3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCad3:
                conectarBD cad = new conectarBD(this);

                cadastro_cliente clienteTela = new cadastro_cliente();

                clienteTela.setNome_cli(utilsCadastro_cliente.getUnome_cli());
                clienteTela.setEmail_cli(utilsCadastro_cliente.getUemail_cli());
                clienteTela.setSenha_cli(utilsCadastro_cliente.getUsenha_cli());
                clienteTela.setDtnasc_cli(utilsCadastro_cliente.getUdtnasc_cli());
                clienteTela.setCpf_cli(utilsCadastro_cliente.getUcpf_cli());
                clienteTela.setTel_cli(utilsCadastro_cliente.getUtel_cli());
                clienteTela.setGen_cli(utilsCadastro_cliente.getUgen_cli());
                clienteTela.setCep_cli(txtCEP.getText().toString());
                clienteTela.setNum_cli(txtNumero.getText().toString());
                clienteTela.setComp_cli(txtComplemento.getText().toString());


                cad.setClasseCli(clienteTela);

                cad.execute(0);


                break;

        }
    }
}