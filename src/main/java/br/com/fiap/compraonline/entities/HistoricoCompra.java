package br.com.fiap.compraonline.entities;

public class HistoricoCompra {

    private int idUsuario;

    public void visualizarCompras() {
        // HistoricoCompraDAO dao = new HistoricoCompraDAO();
        // dao.visualizarCompra(this);
    }

    public HistoricoCompra(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}

