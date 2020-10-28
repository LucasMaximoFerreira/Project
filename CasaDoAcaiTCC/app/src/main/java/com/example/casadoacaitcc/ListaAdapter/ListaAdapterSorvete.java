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

public class ListaAdapterSorvete extends BaseAdapter {

    private LayoutInflater telaItem;
    private List<produto> listaSorvete;

    Context tela;

    public ListaAdapterSorvete(List<produto> listaProd, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaSorvete = listaProd;
        this.tela = tela;
    }

    @Override
    public int getCount() {
        return listaSorvete.size();
    }

    @Override
    public Object getItem(int position) {
        return listaSorvete.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaSorvete.get(position).getId_prod();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaAdapterSorvete.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_sorvete, null);

            itemTela = new ListaAdapterSorvete.ItemSuporte();

            itemTela.lblSorvete = convertView.findViewById(R.id.lblSorvete);
            itemTela.lblPrecoSorvete = convertView.findViewById(R.id.lblPrecoSorvete);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaAdapterSorvete.ItemSuporte) convertView.getTag();
        }

        produto prod = listaSorvete.get(position);

        itemTela.lblSorvete.setText(prod.getNome_prod());
        itemTela.lblPrecoSorvete.setText(String.valueOf(prod.getPreco_prod()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblSorvete, lblPrecoSorvete;
    }
}
