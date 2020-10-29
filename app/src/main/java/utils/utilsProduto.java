package utils;

abstract public class utilsProduto {

    private static int idProdSelecionado;
    private static int idTipoProd;
    private static String nomeAdicionais;

    public static int getIdProdSelecionado() {
        return idProdSelecionado;
    }

    public static void setIdProdSelecionado(int idProdSelecionado) {
        utilsProduto.idProdSelecionado = idProdSelecionado;
    }

    public static int getIdTipoProd() {
        return idTipoProd;
    }

    public static void setIdTipoProd(int idTipoProd) {
        utilsProduto.idTipoProd = idTipoProd;
    }

    public static String getNomeAdicionais() {
        return nomeAdicionais;
    }

    public static void setNomeAdicionais(String nomeAdicionais) {
        utilsProduto.nomeAdicionais = nomeAdicionais;
    }
}
