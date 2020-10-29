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

public class ListaAdapterCremosinho extends BaseAdapter {

    private LayoutInflater telaItem;
    private List<produto> listaCremosinho;

    Context tela;

    public ListaAdapterCremosinho(List<produto> listaProd, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaCremosinho = listaProd;
        this.tela = tela;
    }

    @Override
    public int getCount() {
        return listaCremosinho.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCremosinho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaCremosinho.get(position).getId_prod();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaAdapterCremosinho.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_cremosinho, null);

            itemTela = new ListaAdapterCremosinho.ItemSuporte();

            itemTela.lblCremosinho = convertView.findViewById(R.id.lblCremosinho);
            itemTela.lblPrecoCremosinho = convertView.findViewById(R.id.lblPrecoCremosinho);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaAdapterCremosinho.ItemSuporte) convertView.getTag();
        }

        produto prod = listaCremosinho.get(position);

        itemTela.lblCremosinho.setText(prod.getNome_prod());
        itemTela.lblPrecoCremosinho.setText(String.valueOf(prod.getPreco_prod()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblCremosinho, lblPrecoCremosinho;
    }

}
