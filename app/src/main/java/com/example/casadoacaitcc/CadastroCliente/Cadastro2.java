package com.example.casadoacaitcc.CadastroCliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.casadoacaitcc.R;

import utils.utilsCadastro_cliente;

public class Cadastro2 extends AppCompatActivity implements View.OnClickListener {

    EditText txtCPF, txtTelefone;
    Button btnCad2;
    RadioGroup rgGen;
    RadioButton rbMasc, rbFem, rbPnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);

        txtCPF = findViewById(R.id.txtCPF);
        txtTelefone = findViewById(R.id.txtTelefone);
        btnCad2 = findViewById(R.id.btnCad2);
        rgGen = findViewById(R.id.rgGen);
        rbFem = findViewById(R.id.rbFeminino);
        rbMasc = findViewById(R.id.rbMasculino);
        rbPnd = findViewById(R.id.rbPND);

        btnCad2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCad2:

                utilsCadastro_cliente.setUcpf_cli(txtCPF.getText().toString());
                utilsCadastro_cliente.setUtel_cli(txtTelefone.getText().toString());

                int opGen = rgGen.getCheckedRadioButtonId();
                switch (opGen) {
                    case R.id.rbMasculino:
                        utilsCadastro_cliente.setUgen_cli("M");
                        break;
                    case R.id.rbFeminino:
                        utilsCadastro_cliente.setUgen_cli("F");
                        break;
                    case R.id.rbPND:
                        utilsCadastro_cliente.setUgen_cli("PND");
                        break;

                }

                Intent telaCad3 = new Intent(this, Cadastro3.class);
                startActivity(telaCad3);
                break;
        }
    }
}