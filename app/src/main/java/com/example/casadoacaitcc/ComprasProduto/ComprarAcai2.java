package com.example.casadoacaitcc.ComprasProduto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.casadoacaitcc.Carrinho;
import com.example.casadoacaitcc.ConfirmarPedido;
import com.example.casadoacaitcc.R;

import model.it_venda;
import model.produto;
import utils.utilsProduto;

public class ComprarAcai2 extends AppCompatActivity implements View.OnClickListener {

    Button btnComprarAcai2;

    CheckBox chkAmendoim, chkGranulado, chkGranola, chkLeiteEmPo, chkLeiteCondensado, chkChocobol,
            chkPacoca, chkMorango, chkChocolate, chkCaramelo, chkMenta, chkTutiFruti, chkMaracuja;

    String amendoim, granulado, granola, leiteEmPo, leiteCondensado, chocobol, pacoca, morango, chocolate,
            caramelo, menta, tutiFruti, maracuja, adicionais;

    produto prodTela = new produto();
    it_venda itVendaTela = new it_venda();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_acai2);

        btnComprarAcai2 = findViewById(R.id.btnComprarAcai2);

        chkAmendoim = findViewById(R.id.chkAmendoim);
        chkGranulado = findViewById(R.id.chkGranulado);
        chkGranola = findViewById(R.id.chkGranola);
        chkLeiteEmPo = findViewById(R.id.chkLeiteEmPo);
        chkLeiteCondensado = findViewById(R.id.chkLeiteCondensado);
        chkChocobol = findViewById(R.id.chkChocobol);
        chkPacoca = findViewById(R.id.chkPacoca);
        chkMorango = findViewById(R.id.chkMorango);
        chkChocolate = findViewById(R.id.chkChocolate);
        chkCaramelo = findViewById(R.id.chkCaramelo);
        chkMenta = findViewById(R.id.chkMenta);
        chkTutiFruti = findViewById(R.id.chkTutiFruti);
        chkMaracuja = findViewById(R.id.chkMaracuja);

        amendoim = granulado = granola = leiteEmPo = leiteCondensado = chocobol = pacoca = morango =
                chocolate = caramelo = menta = tutiFruti = maracuja = "";

        btnComprarAcai2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnComprarAcai2:

                AdicionarItem();

                Intent telaProd = new Intent(this, ConfirmarPedido.class);
                startActivity(telaProd);

                break;

            case R.id.chkAmendoim:
                if(chkAmendoim.isChecked()){
                    amendoim = "Amendoim";
                }else{
                    amendoim = " ";
                }
                break;
            case R.id.chkGranulado:
                if(chkGranulado.isChecked()){
                    granulado = "Granulado";
                }else{
                    granulado = " ";
                }
                break;
            case R.id.chkGranola:
                if(chkGranola.isChecked()){
                    granola = "Granola";
                }else{
                    granola = " ";
                }
                break;
            case R.id.chkLeiteEmPo:
                if(chkLeiteEmPo.isChecked()){
                    leiteEmPo = "Leite em Po";
                }else{
                    leiteEmPo = " ";
                }
                break;
            case R.id.chkLeiteCondensado:
                if(chkLeiteCondensado.isChecked()){
                    leiteCondensado = "Leite Condensado";
                }else{
                    leiteCondensado = " ";
                }
                break;
            case R.id.chkChocobol:
                if(chkChocobol.isChecked()){
                    chocobol = "Chocobol";
                }else{
                    chocobol = " ";
                }
                break;
            case R.id.chkPacoca:
                if(chkPacoca.isChecked()){
                    pacoca = "Paçoca";
                }else{
                    pacoca = " ";
                }
                break;
            case R.id.chkMorango:
                if(chkMorango.isChecked()){
                    morango = "Morango";
                }else{
                    morango = " ";
                }
                break;
            case R.id.chkChocolate:
                if(chkChocolate.isChecked()){
                    chocolate = "Chocolate";
                }else{
                    chocolate = " ";
                }
                break;
            case R.id.chkCaramelo:
                if(chkCaramelo.isChecked()){
                    caramelo = "Caramelo";
                }else{
                    caramelo = " ";
                }
                break;
            case R.id.chkMenta:
                if(chkMenta.isChecked()){
                    menta = "Menta";
                }else{
                    menta = " ";
                }
                break;
            case R.id.chkTutiFruti:
                if(chkTutiFruti.isChecked()){
                    tutiFruti = "Tuti-Fruti";
                }else{
                    tutiFruti = " ";
                }
                break;
            case R.id.chkMaracuja:
                if(chkMaracuja.isChecked()){
                    maracuja = "Maracuja";
                }else{
                    maracuja = " ";
                }
                break;

        }
    }
    private void AdicionarItem(){
        adicionais = amendoim + " " + granulado + " " + granola + " " + leiteEmPo + " " + leiteCondensado + " " +
                chocobol + " " + pacoca + " " + morango + " " + chocolate + " " + caramelo + " " +
                menta + " " + tutiFruti + " " + maracuja;

        //COLCOAR ADICIONAL IGUAL A 0 PARA OS OUTROS PRODUTOS

        utilsProduto.setNomeAdicionais(adicionais);
    }
}

