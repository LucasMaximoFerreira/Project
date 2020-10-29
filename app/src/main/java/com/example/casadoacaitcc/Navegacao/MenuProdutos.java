package com.example.casadoacaitcc.Navegacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.casadoacaitcc.ComprasProduto.ComprarAcai1;
import com.example.casadoacaitcc.ComprasProduto.ComprarCremosinho;
import com.example.casadoacaitcc.ComprasProduto.ComprarGeladinho;
import com.example.casadoacaitcc.ComprasProduto.ComprarPicole;
import com.example.casadoacaitcc.ComprasProduto.ComprarSacole;
import com.example.casadoacaitcc.ComprasProduto.ComprarSorvete;
import com.example.casadoacaitcc.R;

import utils.utilsProduto;

public class MenuProdutos extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnAcai, btnSacole, btnGeladinho, btnSorvete, btnPicole, btnCremosinho;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_produtos);
        btnAcai = findViewById(R.id.btnAcai);
        btnSacole = findViewById(R.id.btnSacole);
        btnGeladinho = findViewById(R.id.btnGeladinho);
        btnSorvete = findViewById(R.id.btnSorvete);
        btnPicole = findViewById(R.id.btnPicole);
        btnCremosinho = findViewById(R.id.btnCremosinho);

        drawerLayout = findViewById(R.id.drawer_layout);
        
        btnAcai.setOnClickListener(this);
        btnSacole.setOnClickListener(this);
        btnGeladinho.setOnClickListener(this);
        btnSorvete.setOnClickListener(this);
        btnPicole.setOnClickListener(this);
        btnCremosinho.setOnClickListener(this);
    }

    public void ClickMenu(View view) {
        //Abrir o Drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Abrir o layout do Drawer
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void CLickLogo(View view) {
        //Fechar drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Fechar o layout do Drawer
        //Verificar condição
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //Quando o Drawer estiver aberto
            //Fechar Drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    ///////////////////////////////////////////////////////
    public void ClickMenuProdutos(View view) {
        Intent MenuProd = new Intent(this, MenuProdutos.class);
        startActivity(MenuProd);
    }

    public void ClickPerfil(View view) {
        Intent perfil = new Intent(this, Perfil.class);
        startActivity(perfil);
    }

    public void ClickFavoritos(View view) {
        Intent perfil = new Intent(this, Favoritos.class);
        startActivity(perfil);
    }

    public void ClickHistorico(View view) {
        Intent perfil = new Intent(this, Historico.class);
        startActivity(perfil);
    }

    public void ClickSobreApp(View view) {
        Intent perfil = new Intent(this, SobreApp.class);
        startActivity(perfil);
    }

    public void ClickRelatar(View view) {
        Intent perfil = new Intent(this, RelatarProblema.class);
        startActivity(perfil);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAcai:
                utilsProduto.setIdTipoProd(1);
                Intent Acai = new Intent(this, ComprarAcai1.class);
                startActivity(Acai);
                break;
            case R.id.btnSacole:
                utilsProduto.setIdTipoProd(2);
                Intent sacole = new Intent(this, ComprarSacole.class);
                startActivity(sacole);
                break;
            case R.id.btnGeladinho:
                utilsProduto.setIdTipoProd(3);
                Intent geladinho = new Intent(this, ComprarGeladinho.class);
                startActivity(geladinho);
                break;
            case R.id.btnSorvete:
                utilsProduto.setIdTipoProd(4);
                Intent sorvete = new Intent(this, ComprarSorvete.class);
                startActivity(sorvete);
                break;
            case R.id.btnPicole:
                utilsProduto.setIdTipoProd(5);
                Intent picole = new Intent(this, ComprarPicole.class);
                startActivity(picole);
                break;
            case R.id.btnCremosinho:
                utilsProduto.setIdTipoProd(6);
                Intent cremosinho = new Intent(this, ComprarCremosinho.class);
                startActivity(cremosinho);
                break;
        }
    }

    ///////////////////////////////////////////////////////
}












