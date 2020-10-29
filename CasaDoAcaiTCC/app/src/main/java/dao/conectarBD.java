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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.cadastro_cliente;
import model.produto;
import utils.criptografia;
import utils.utilsProduto;

public class conectarBD extends AsyncTask<Integer, Object, Boolean> {


    Connection conexao;

    Context tela;

    ProgressDialog dialogo;

    int op;


    ////////////////////////////////////////// - Boolean Login

    private Boolean login;

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Classe Cripto

    criptografia cripto;
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Acai

    private List<produto> listaAcai = new ArrayList<produto>();

    public List<produto> getListaAcai() {
        return listaAcai;
    }
    public void setListaAcai(List<produto> listaAcai) {
        this.listaAcai = listaAcai;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Cremosinho

    private List<produto> listaCremosinho = new ArrayList<produto>();
    public List<produto> getListaCremosinho() {
        return listaCremosinho;
    }

    public void setListaCremosinho(List<produto> listaCremosinho) {
        this.listaCremosinho = listaCremosinho;
    }

    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Geladinho

    private List<produto> listaGeladinho = new ArrayList<produto>();
    public List<produto> getListaGeladinho() {
        return listaGeladinho;
    }

    public void setListaGeladinho(List<produto> listaGeladinho) {
        this.listaGeladinho = listaGeladinho;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Picole

    private List<produto> listaPicole = new ArrayList<produto>();
    public List<produto> getListaPicole() {
        return listaPicole;
    }

    public void setListaPicole(List<produto> listaPicole) {
        this.listaPicole = listaPicole;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Sacole

    private List<produto> listaSacole = new ArrayList<produto>();
    public List<produto> getListaSacole() {
        return listaSacole;
    }

    public void setListaSacole(List<produto> listaSacole) {
        this.listaSacole = listaSacole;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Sorvete

    private List<produto> listaSorvete = new ArrayList<produto>();
    public List<produto> getListaSorvete() {
        return listaSorvete;
    }

    public void setListaSorvete(List<produto> listaSorvete) {
        this.listaSorvete = listaSorvete;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Clasee Cliente

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
            conexao = DriverManager.getConnection("jdbc:mysql://192.168.15.14:3306/casadoacai", "root", "lucas4max");
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
            case 4:
                resp = listarAcai();
                break;
            case 5:
                resp = listarCremosinho();
                break;
            case 6:
                resp = listarGeladinho();
                break;
            case 7:
                resp = listarPicole();
                break;
            case 8:
                resp = listarSacole();
                break;
            case 9:
                resp = listarSorvete();
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
            case 4:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Acai", Toast.LENGTH_SHORT).show();
                }
                break;
            case 5:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Cremosinho", Toast.LENGTH_SHORT).show();
                }
                break;
            case 6:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Geladinho", Toast.LENGTH_SHORT).show();
                }
                break;
            case 7:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Picole", Toast.LENGTH_SHORT).show();
                }
                break;
            case 8:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Sacole", Toast.LENGTH_SHORT).show();
                }
                break;
            case 9:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Sorvete", Toast.LENGTH_SHORT).show();
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
            comando.setString(1, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n",""));
            comando.setString(2, cripto.encrypt(classeCli.getSenha_cli().getBytes()).replace("\n",""));
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
            comando.setString(1, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n",""));
            ResultSet tabelamemoria = comando.executeQuery();

            if (tabelamemoria.next()) {
                classeCli.setNome_cli(cripto.decrypt(tabelamemoria.getString("nome_cli")));
                classeCli.setSenha_cli(cripto.decrypt(tabelamemoria.getString("senha_cli")));
                classeCli.setEmail_cli(cripto.decrypt(tabelamemoria.getString("email_cli")));
                classeCli.setCep_cli(cripto.decrypt(tabelamemoria.getString("cep_cli")));
                classeCli.setNum_cli(cripto.decrypt(tabelamemoria.getString("num_cli")));
                classeCli.setComp_cli(cripto.decrypt(tabelamemoria.getString("comp_cli")));
                classeCli.setTel_cli(cripto.decrypt(tabelamemoria.getString("tel_cli")));
                classeCli.setGen_cli(cripto.decrypt(tabelamemoria.getString("gen_cli")));
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
            comando.setString(1, cripto.encrypt(classeCli.getNome_cli().getBytes()).replace("\n",""));
            comando.setString(2, cripto.encrypt(classeCli.getSenha_cli().getBytes()).replace("\n",""));
            comando.setString(3, cripto.encrypt(classeCli.getEmail_cli().getBytes()).replace("\n",""));
            comando.setString(4, cripto.encrypt(classeCli.getCep_cli().getBytes()).replace("\n",""));
            comando.setString(5, cripto.encrypt(classeCli.getNum_cli().getBytes()).replace("\n",""));
            comando.setString(6, cripto.encrypt(classeCli.getComp_cli().getBytes()).replace("\n",""));
            comando.setString(7, cripto.encrypt(classeCli.getTel_cli().getBytes()).replace("\n",""));
            comando.setString(8, cripto.encrypt(classeCli.getGen_cli().getBytes()).replace("\n",""));
            comando.setString(9, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n",""));

            comando.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public Boolean listarAcai(){

        try{
            String sql = "select * from produto where id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsProduto.getIdTipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            while(tabelaMemoria.next()){

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaAcai.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean listarCremosinho() {
        try{
            String sql = "select * from produto where id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsProduto.getIdTipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            while(tabelaMemoria.next()){

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaCremosinho.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean listarSorvete() {
        try{
            String sql = "select * from produto where id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsProduto.getIdTipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            while(tabelaMemoria.next()){

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaSorvete.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean listarGeladinho() {
        try{
            String sql = "select * from produto where id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsProduto.getIdTipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            while(tabelaMemoria.next()){

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaGeladinho.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean listarPicole() {
        try{
            String sql = "select * from produto where id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsProduto.getIdTipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            while(tabelaMemoria.next()){

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaPicole.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean listarSacole() {
        try{
            String sql = "select * from produto where id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsProduto.getIdTipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            while(tabelaMemoria.next()){

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaSacole.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
