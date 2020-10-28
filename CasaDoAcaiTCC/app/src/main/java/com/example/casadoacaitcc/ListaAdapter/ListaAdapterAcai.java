package com.example.casadoacaitcc.ListaAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.casadoacaitcc.R;

import org.w3c.dom.Text;

import java.util.List;

import model.produto;

public class ListaAdapterAcai extends BaseAdapter {

    private LayoutInflater telaItem;
    private List<produto> listaAcai;

    Context tela;

    public ListaAdapterAcai(List<produto> listaProd, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaAcai = listaProd;
        this.tela = tela;
    }


    @Override
    public int getCount() {
        return listaAcai.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAcai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaAcai.get(position).getId_prod();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_acai, null);

            itemTela = new ItemSuporte();

            itemTela.lblAcai = convertView.findViewById(R.id.lblAcai);
            itemTela.lblPrecoAcai = convertView.findViewById(R.id.lblPrecoAcai);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ItemSuporte) convertView.getTag();
        }

        produto prod = listaAcai.get(position);

        itemTela.lblAcai.setText(prod.getNome_prod());
        itemTela.lblPrecoAcai.setText(String.valueOf(prod.getPreco_prod()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblAcai, lblPrecoAcai;
    }
}
