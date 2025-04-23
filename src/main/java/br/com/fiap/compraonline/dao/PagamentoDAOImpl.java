package br.com.fiap.compraonline.dao;

import br.com.fiap.compraonline.entities.Boleto;
import br.com.fiap.compraonline.entities.CartaoCredito;
import br.com.fiap.compraonline.entities.Pagamento;
import br.com.fiap.compraonline.entities.Pix;
import br.com.fiap.compraonline.exceptions.PagamentoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAOImpl implements PagamentoDAO {

    private Connection conn;

    public PagamentoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    // METODO SALVAR
    @Override
    public void salvar(Pagamento pagamento) throws PagamentoException {


        String insert = "INSERT INTO PAGAMENTO (IDPAGAMENTO, TIPOPAGAMENTO, DATA, VALOR, STATUS, IDCLIENTE) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(insert)){

            String dataFormatted = pagamento.getData().toString();

            preparedStatement.setInt(1, (int) pagamento.getId());
            preparedStatement.setInt(2, pagamento.getTipoPagamento());
            preparedStatement.setString(3, pagamento.getData().toString());
            preparedStatement.setDouble(4,pagamento.getValor());
            preparedStatement.setString(5, pagamento.getStatus());
            preparedStatement.setInt(6, pagamento.getIdCliente());

            preparedStatement.executeUpdate();


            System.out.println("Inclusão efetuada com sucesso!");

        } catch (SQLException e) {
            throw new PagamentoException("❌ Erro ao salvar pagamento no banco de dados: " + e.getMessage(), e);
        }

    }

    // MÉTODO BUSCA PAGAMENTOS DO CLIENTE
    @Override
    public List<Pagamento> buscarPorId(int idCliente) throws PagamentoException {

        String selectById = "SELECT * FROM PAGAMENTO WHERE IDCLIENTE = ?";
        List<Pagamento> pagamentosDoCliente = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        try (PreparedStatement preparedStatement = conn.prepareStatement(selectById)){

            preparedStatement.setInt(1, idCliente);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idPag = rs.getInt("IDPAGAMENTO");
                double valor = rs.getDouble("VALOR");
                int tipoPag = rs.getInt("TIPOPAGAMENTO");
                String status = rs.getString("STATUS");
                String data = rs.getString("DATA");

                LocalDate dataformat = LocalDate.parse(data, formatter);
                Pagamento pagamento = null;

                if (tipoPag == 1) {
                    pagamento = new CartaoCredito(valor, idCliente, tipoPag);
                } else if (tipoPag == 2) {
                    pagamento = new Pix(valor, idCliente, tipoPag);
                } else if (tipoPag == 3) {
                    pagamento = new Boleto(valor, idCliente, tipoPag);
                }

                if (pagamento != null) {
                    pagamento.setId(idPag);
                    pagamento.setStatus(status);
                    pagamento.setData(dataformat);

                    pagamentosDoCliente.add(pagamento);
                }

            }
            if (pagamentosDoCliente.isEmpty()) {
                throw new PagamentoException("❌ Nenhum pagamento encontrado para o cliente ID: " + idCliente);
            }
        } catch (SQLException e) {
            throw new PagamentoException("❌ Erro ao acessar o banco de dados: " + e.getMessage());
        }

        return pagamentosDoCliente;


    }


}

