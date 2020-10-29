package com.example.casadoacaitcc.ComprasProduto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.casadoacaitcc.ConfirmarPedido;
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterCremosinho;
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterGeladinho;
import com.example.casadoacaitcc.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.produto;
import utils.utilsProduto;

public class ComprarGeladinho extends AppCompatActivity implements AdapterView.OnItemClickListener {

    conectarBD listar;
    ListView lstGeladinho;

    List<produto> listaGeladinhoTela;
    ListaAdapterGeladinho adapterListaGeladinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_geladinho);

        try{
            lstGeladinho = findViewById(R.id.lstGeladinho);

            listar = new conectarBD(this);
            listar.execute(6).get();

            listaGeladinhoTela = listar.getListaGeladinho();

            adapterListaGeladinho = new ListaAdapterGeladinho(listaGeladinhoTela,this);
            lstGeladinho.setAdapter(adapterListaGeladinho);

            lstGeladinho.setOnItemClickListener(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        produto prodSelecionado;

        prodSelecionado = (produto) adapterListaGeladinho.getItem(position);

        utilsProduto.setIdProdSelecionado(prodSelecionado.getId_prod());

        Intent confirmar = new Intent(this, ConfirmarPedido.class);
        startActivity(confirmar);

        finish();
    }
}