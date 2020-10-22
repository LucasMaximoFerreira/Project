package dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.cadastro_cliente;
import utils.criptografia;

public class conectarBD extends AsyncTask<Integer, Object, Boolean> {


    Connection conexao;

    Context tela;

    ProgressDialog dialogo;

    int op;
    //////////////////////////////////////////
    private Boolean login;

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    //////////////////////////////////////////

    criptografia cripto;
    //////////////////////////////////////////

    //--------------------------------------//

    //////////////////////////////////////////
    private cadastro_cliente classeCli = new cadastro_cliente();

    public cadastro_cliente getClasseCli() {
        return classeCli;
    }

    public void setClasseCli(cadastro_cliente classeCli) {
        this.classeCli = classeCli;
    }
    //////////////////////////////////////////

    public Boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/casadoacai", "root", "lucas4max");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public void disconnect(){
        try{
            conexao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public conectarBD(Context tela) {
        super();
        this.tela = tela;
        cripto = new criptografia("ETEP");
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        Boolean resp = null;

        op = integers[0];

        connect();

        switch (op){
            case 0:
                resp = inserir();
                break;
            case 1:
                resp = logar();
                break;
            case 2:
                resp = pesquisarPerfil();
                break;
            case 3:
                resp = alterar();
                break;
        }

        return resp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialogo = new ProgressDialog(tela);
        dialogo.setMessage("Aguarde conectando...");
        dialogo.show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        switch (op) {
            case 0:
                if (aBoolean == true) {
                    Toast.makeText(tela, "cadastro ok", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(tela, "erro", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (aBoolean == false) {
                    Toast.makeText(tela, "usuario nao cadastrado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(tela, "usuario cadastrado", Toast.LENGTH_SHORT).show();

                }
                break;
            case 3:
                if (aBoolean == true) {
                    Toast.makeText(tela, "Informações alteradas com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(tela, "erro na alteração - verifique as informações", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        dialogo.dismiss();

        disconnect();
    }
    private Boolean inserir(){
        try{
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            Date dataUtil = formato.parse(classeCli.getDtnasc_cli());
            java.sql.Date dataMYSQL = new java.sql.Date(dataUtil.getTime());

            String sql = "insert into cadastro_cliente values (0,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, cripto.encrypt(classeCli.getNome_cli().getBytes()).replace("\n", ""));
            comando.setString(2, cripto.encrypt(classeCli.getSenha_cli().getBytes()).replace("\n", ""));
            comando.setString(3, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n", ""));
            comando.setString(4, cripto.encrypt(classeCli.getTel_cli().getBytes()).replace("\n", ""));
            comando.setString(5, cripto.encrypt(classeCli.getCep_cli().getBytes()).replace("\n", ""));
            comando.setString(6, cripto.encrypt(classeCli.getNum_cli().getBytes()).replace("\n", ""));
            comando.setString(7, cripto.encrypt(classeCli.getComp_cli().getBytes()).replace("\n", ""));
            comando.setString(8, cripto.encrypt(classeCli.getEmail_cli().getBytes()).replace("\n", ""));
            comando.setString(9, cripto.encrypt(String.valueOf(dataUtil).getBytes()).replace("\n", ""));
            comando.setString(10, cripto.encrypt(classeCli.getGen_cli().getBytes()).replace("\n", ""));

            comando.executeUpdate();

            return true;

        }catch (ParseException e){
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
    private Boolean logar(){
        try{

            String sql = "select * from cadastro_cliente where cpf_cli=? and senha_cli=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, classeCli.getCpf_cli());
            comando.setString(2, classeCli.getSenha_cli());
            ResultSet tabelamemoria = comando.executeQuery();

            if (tabelamemoria.next()) {
                login = true;
                return true;
            } else {
                login = false;
                return false;
            }

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    private Boolean pesquisarPerfil() {

        try {

            String sql = "select nome_cli, senha_cli, email_cli, cep_cli, num_cli, comp_cli, tel_cli, gen_cli from cadastro_cliente where cpf_cli=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, classeCli.getCpf_cli());
            ResultSet tabelamemoria = comando.executeQuery();

            if (tabelamemoria.next()) {
                classeCli.setNome_cli(tabelamemoria.getString("nome_cli"));
                classeCli.setSenha_cli(tabelamemoria.getString("senha_cli"));
                classeCli.setEmail_cli(tabelamemoria.getString("email_cli"));
                classeCli.setCep_cli(tabelamemoria.getString("cep_cli"));
                classeCli.setNum_cli(tabelamemoria.getString("num_cli"));
                classeCli.setComp_cli(tabelamemoria.getString("comp_cli"));
                classeCli.setTel_cli(tabelamemoria.getString("tel_cli"));
                classeCli.setGen_cli(tabelamemoria.getString("gen_cli"));
                return true;

            } else {
                classeCli = null;

                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean alterar() {
        try {


            String sql = "update cadastro_cliente set nome_cli=?, senha_cli=?, email_cli=?, " +
                    "cep_cli=?, num_cli=?, comp_cli=?, tel_cli=?, gen_cli=? where cpf_cli=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, classeCli.getNome_cli());
            comando.setString(2, classeCli.getSenha_cli());
            comando.setString(3, classeCli.getEmail_cli());
            comando.setString(4, classeCli.getCep_cli());
            comando.setString(5, classeCli.getNum_cli());
            comando.setString(6, classeCli.getComp_cli());
            comando.setString(7, classeCli.getTel_cli());
            comando.setString(8, classeCli.getGen_cli());
            comando.setString(9, classeCli.getCpf_cli());

            comando.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
