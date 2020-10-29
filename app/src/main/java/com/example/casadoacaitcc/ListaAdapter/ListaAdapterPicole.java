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

public class ListaAdapterPicole extends BaseAdapter {

    private LayoutInflater telaItem;
    private List<produto> listaPicole;

    Context tela;

    public ListaAdapterPicole(List<produto> listaProd, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaPicole = listaProd;
        this.tela = tela;
    }

    @Override
    public int getCount() {
        return listaPicole.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPicole.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaPicole.get(position).getId_prod();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaAdapterPicole.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_picole, null);

            itemTela = new ListaAdapterPicole.ItemSuporte();

            itemTela.lblPicole = convertView.findViewById(R.id.lblPicole);
            itemTela.lblPrecoPicole = convertView.findViewById(R.id.lblPrecoPicole);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaAdapterPicole.ItemSuporte) convertView.getTag();
        }

        produto prod = listaPicole.get(position);

        itemTela.lblPicole.setText(prod.getNome_prod());
        itemTela.lblPrecoPicole.setText(String.valueOf(prod.getPreco_prod()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblPicole, lblPrecoPicole;
    }

}
