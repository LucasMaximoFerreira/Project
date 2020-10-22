package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.cadastro_cliente;
import utils.utilsCadastro_cliente;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText txtLogin, txtSenha;
    Button btnCad, btnLogar;

    cadastro_cliente telaCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnCad = findViewById(R.id.btnCadastrarLogin);
        btnLogar = findViewById(R.id.btnLogar);

        telaCliente = new cadastro_cliente();

        btnLogar.setOnClickListener(this);
        btnCad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogar:
                try {
                    conectarBD logar = new conectarBD(this);
                    telaCliente.setCpf_cli(txtLogin.getText().toString());
                    telaCliente.setSenha_cli(txtSenha.getText().toString());
                    utilsCadastro_cliente.setCpfPesq(txtLogin.getText().toString());
                    logar.setClasseCli(telaCliente);

                    logar.execute(1).get();

                    if(logar.getLogin() == true){
                        Intent telaLogin = new Intent(this, MenuProdutos.class);
                        startActivity(telaLogin);
                    }else{
                        telaCliente = new cadastro_cliente();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnCadastrarLogin:
                Intent telaCad = new Intent(this, Cadastro1.class);
                startActivity(telaCad);
                break;
        }

    }
}