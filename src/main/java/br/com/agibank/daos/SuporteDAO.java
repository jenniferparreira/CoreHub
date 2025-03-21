package br.com.agibank.daos;

import br.com.agibank.beans.Conexao;
import br.com.agibank.beans.Suporte;

import java.sql.*;

public class SuporteDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;


    public SuporteDAO() throws SQLException {
        con = Conexao.getConnection();
    }


    public void fecharConexao() throws SQLException {
        con.close();
    }


    public void inserirSuporte(int id, String descricao) throws SQLException{
        String sql = "INSERT INTO Suporte (id_usuario,descricao,data_abertura) VALUES (?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.setString(2, descricao);
        stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        stmt.executeUpdate();
        System.out.println("Chamado cadastrado com sucesso");
    }

    public Suporte buscarChamadoPorId(int id_suporte) throws SQLException{
        String sql = "SELECT id_usuario, descricao, responsavel, resolucao FROM Suporte WHERE id_suporte = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_suporte);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Suporte suporte = new Suporte();
            suporte.setidCliente(rs.getInt("id_usuario"));
            suporte.setDescricao(rs.getString("descricao"));
            suporte.setidFuncionario(rs.getInt("responsavel"));
            suporte.setResolucao(rs.getString("resolucao"));
            return suporte;
        }

        return null;
    }

    public void listarChamados() throws SQLException{
        String sql = "SELECT * FROM Suporte INNER JOIN Usuario ON Usuario.id_usuario = Suporte.id_usuario";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getString("Usuario.nome") + " ");
            System.out.print(rs.getString("descricao") + " ");
            System.out.print(rs.getInt("responsavel") + " ");
            System.out.print(rs.getString("resolucao") + "\n");
        }

    }

    public void listarChamadosAtendidos() throws SQLException{
        String sql = "SELECT * FROM Suporte INNER JOIN Usuario ON Usuario.id_usuario = Suporte.id_usuario WHERE responsavel IS NOT NULL";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getInt("id_usuario") + " ");
            System.out.print(rs.getString("descricao") + " ");
            System.out.print(rs.getInt("responsavel") + " ");
            System.out.print(rs.getString("resolucao\n"));
        }
    }

    public void listarChamadosPendentes() throws SQLException{
        String sql = "SELECT * FROM Suporte INNER JOIN Usuario ON Usuario.id_usuario = Suporte.id_usuario WHERE responsavel IS NULL";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getInt("id_usuario") + " ");
            System.out.print(rs.getString("descricao") + " ");
            System.out.print(rs.getString("resolucao\n"));
        }

    }

    public void inserirAtendenteSuporte(int id_suporte, int id_funcionario) throws SQLException{
        String sql = "UPDATE Suporte SET responsavel = ? WHERE id_suporte = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_funcionario);
        stmt.setInt(2, id_suporte);;
        stmt.executeUpdate();
        System.out.println("Teste realizado com sucesso");
    }
}
