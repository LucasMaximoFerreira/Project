package utils;

abstract public class utilsCompra {
    private static String novaCompra;
    private static int ultimaVenda;
    private static double totalCompra;
    private static double troco;


    public static String getNovaCompra() {
        return novaCompra;
    }

    public static void setNovaCompra(String novaCompra) {
        utilsCompra.novaCompra = novaCompra;
    }

    public static int getUltimaVenda() {
        return ultimaVenda;
    }

    public static void setUltimaVenda(int ultimaVenda) {
        utilsCompra.ultimaVenda = ultimaVenda;
    }

    public static double getTotalCompra() {
        return totalCompra;
    }

    public static void setTotalCompra(double totalCompra) {
        utilsCompra.totalCompra = totalCompra;
    }

    public static double getTroco() {
        return troco;
    }

    public static void setTroco(double troco) {
        utilsCompra.troco = troco;
    }
}
