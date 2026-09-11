package br.com.fiap.bean;
import br.com.fiap.bean.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO implements IDAO {
    private Connection con;

    public ClienteDAO(Connection con) {
        this.con = con;
    }

    @Override
    public String inserir(Object obj) {
        Cliente cliente = (Cliente) obj;
        String sql = "INSERT INTO ddd_cliente (nome_cliente, placa) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getPlaca());
            ps.executeUpdate();
            return "Cliente cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao inserir: " + e.getMessage();
        }
    }

    @Override
    public String alterar(Object obj) {
        Cliente cliente = (Cliente) obj;
        String sql = "UPDATE ddd_cliente SET nome_cliente = ?, placa = ? WHERE id_cliente = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getPlaca());
            ps.setInt(3, cliente.getIdCliente());
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                return "Cliente alterado com sucesso!";
            } else {
                return "Cliente não encontrado para alteração.";
            }
        } catch (SQLException e) {
            return "Erro ao alterar: " + e.getMessage();
        }
    }

    @Override
    public String excluir(Object obj) {
        Cliente cliente = (Cliente) obj;
        String sql = "DELETE FROM ddd_cliente WHERE id_cliente = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdCliente());
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                return "Cliente excluído com sucesso!";
            } else {
                return "Cliente não encontrado para exclusão.";
            }
        } catch (SQLException e) {
            return "Erro ao excluir: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object obj) {
        Cliente cliente = (Cliente) obj;
        String sql = "SELECT * FROM ddd_cliente WHERE id_cliente = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdCliente());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return "ID: " + rs.getInt("id_cliente") +
                            " | Nome: " + rs.getString("nome_cliente") +
                            " | Placa: " + rs.getString("placa");
                } else {
                    return "Cliente não encontrado.";
                }
            }
        } catch (SQLException e) {
            return "Erro ao listar: " + e.getMessage();
        }
    }
}