package com.example.casadoacaitcc.ComprasProduto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.casadoacaitcc.ConfirmarPedido;
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterPicole;
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterSacole;
import com.example.casadoacaitcc.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.produto;
import utils.utilsProduto;

public class ComprarSacole extends AppCompatActivity implements AdapterView.OnItemClickListener {

    conectarBD listar;
    ListView lstSacole;

    List<produto> ListaSacoleTela;
    ListaAdapterSacole adapterListaSacole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_sacole);

        try{
            lstSacole = findViewById(R.id.lstSacole);

            listar = new conectarBD(this);
            listar.execute(8).get();

            ListaSacoleTela = listar.getListaSacole();

            adapterListaSacole = new ListaAdapterSacole(ListaSacoleTela,this);
            lstSacole.setAdapter(adapterListaSacole);

            lstSacole.setOnItemClickListener(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        produto prodSelecionado;

        prodSelecionado = (produto) adapterListaSacole.getItem(position);

        utilsProduto.setIdProdSelecionado(prodSelecionado.getId_prod());

        Intent confirmar = new Intent(this, ConfirmarPedido.class);
        startActivity(confirmar);

        finish();

    }
}