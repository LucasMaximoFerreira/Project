package com.example.casadoacaitcc.ComprasProduto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.casadoacaitcc.ListaAdapter.ListaAdapterAcai;
import com.example.casadoacaitcc.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.produto;
import utils.utilsProduto;

public class ComprarAcai1 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    conectarBD listar;
    ListView lstAcai;

    List<produto> listaAcaiTela;
    ListaAdapterAcai adapterListaAcai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_acai1);

        try{
            lstAcai = findViewById(R.id.lstAcai);

            listar = new conectarBD(this);
            listar.execute(4).get();

            listaAcaiTela = listar.getListaAcai();

            adapterListaAcai = new ListaAdapterAcai(listaAcaiTela,this);
            lstAcai.setAdapter(adapterListaAcai);

            lstAcai.setOnItemClickListener(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        produto prodSelecionado;

        prodSelecionado = (produto) adapterListaAcai.getItem(position);

        utilsProduto.setIdProdSelecionado(prodSelecionado.getId_prod());


        Intent carrinho = new Intent(this, ComprarAcai2.class);
        startActivity(carrinho);

        finish();

    }
}