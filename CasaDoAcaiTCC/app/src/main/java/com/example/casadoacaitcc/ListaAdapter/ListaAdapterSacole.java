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

public class ListaAdapterSacole extends BaseAdapter {

    private LayoutInflater telaItem;
    private List<produto> listaSacole;

    Context tela;

    public ListaAdapterSacole(List<produto> listaProd, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaSacole = listaProd;
        this.tela = tela;
    }

    @Override
    public int getCount() {
        return listaSacole.size();
    }

    @Override
    public Object getItem(int position) {
        return listaSacole.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaSacole.get(position).getId_prod();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaAdapterSacole.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_sacole, null);

            itemTela = new ListaAdapterSacole.ItemSuporte();

            itemTela.lblSacole = convertView.findViewById(R.id.lblSacole);
            itemTela.lblPrecoSacole = convertView.findViewById(R.id.lblPrecoSacole);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaAdapterSacole.ItemSuporte) convertView.getTag();
        }

        produto prod = listaSacole.get(position);

        itemTela.lblSacole.setText(prod.getNome_prod());
        itemTela.lblPrecoSacole.setText(String.valueOf(prod.getPreco_prod()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblSacole, lblPrecoSacole;
    }
}
