package com.example.casadoacaitcc.ListaAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.casadoacaitcc.R;

import java.util.List;

import model.produto;

public class ListaAdapterGeladinho extends BaseAdapter {

    private LayoutInflater telaItem;
    private List<produto> listaGeladinho;

    Context tela;

    public ListaAdapterGeladinho(List<produto> listaProd, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaGeladinho = listaProd;
        this.tela = tela;
    }

    @Override
    public int getCount() {
        return listaGeladinho.size();
    }

    @Override
    public Object getItem(int position) {
        return listaGeladinho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaGeladinho.get(position).getId_prod();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaAdapterGeladinho.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_geladinho, null);

            itemTela = new ListaAdapterGeladinho.ItemSuporte();

            itemTela.lblGeladinho = convertView.findViewById(R.id.lblGeladinho);
            itemTela.lblPrecoGeladinho = convertView.findViewById(R.id.lblPrecoGeladinho);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaAdapterGeladinho.ItemSuporte) convertView.getTag();
        }

        produto prod = listaGeladinho.get(position);

        itemTela.lblGeladinho.setText(prod.getNome_prod());
        itemTela.lblPrecoGeladinho.setText(String.valueOf(prod.getPreco_prod()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblGeladinho, lblPrecoGeladinho;
    }

}
