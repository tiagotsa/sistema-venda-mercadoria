package Dao;

import Dto.ClientesDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientesDao {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ClientesDto> lista = new ArrayList<>();

    public void cadastrarClientes(ClientesDto dtoclientes) {
        String sql = "insert into clientes(nome_clientes, endereco_clientes, email_clientes, produto_clientes,valor_clientes, total_clientes) values (?,?,?,?,?,?)";

        conn = new ConexaoDao().conectaBd();
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, dtoclientes.getNome_clientes());
            pstm.setString(2, dtoclientes.getEndereco_clientes());
            pstm.setString(3, dtoclientes.getEmail_clientes());
            pstm.setInt(4, dtoclientes.getProduto_clientes());
            pstm.setInt(5, dtoclientes.getValor_clientes());
            pstm.setInt(6, dtoclientes.getTotal_clientes());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {

      }
    }

    public ArrayList<ClientesDto> pesquisarClientes() {
        String sql = "select * from clientes";
        conn = new ConexaoDao().conectaBd();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ClientesDto dtoclientes = new ClientesDto();
                dtoclientes.setId(rs.getInt("id"));
                dtoclientes.setNome_clientes(rs.getString("nome_clientes"));
                dtoclientes.setEndereco_clientes(rs.getString("endereco_clientes"));
                dtoclientes.setEmail_clientes(rs.getString("email_clientes"));
                dtoclientes.setProduto_clientes(rs.getInt("produto_clientes"));
                dtoclientes.setValor_clientes(rs.getInt("valor_clientes"));
                dtoclientes.setTotal_clientes(rs.getInt("total_clientes"));

                lista.add(dtoclientes);
            }
        } catch (Exception erro) {
        }
        return lista;

    }

    public void alterarCliente(ClientesDto dtoclientes) {
        String sql = "update clientes set nome_clientes = ?, endereco_clientes = ?, email_clientes = ?, produto_clientes = ?, valor_clientes = ?, total_clientes = ? where id = ?";
        conn = new ConexaoDao().conectaBd();

        try {
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, dtoclientes.getNome_clientes());
            pstm.setString(2, dtoclientes.getEndereco_clientes());
            pstm.setString(3, dtoclientes.getEmail_clientes());
            pstm.setInt(4, dtoclientes.getProduto_clientes());
            pstm.setInt(5, dtoclientes.getValor_clientes());
            pstm.setInt(6, dtoclientes.getTotal_clientes());
            pstm.setInt(7, dtoclientes.getId());

            pstm.execute();
            pstm.close();

        } catch (Exception e) {
        }

    }

    public void excluirCliente(ClientesDto clientesdto) {
        String sql = "delete from clientes where id = ?";

        try {
            conn = new ConexaoDao().conectaBd();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, clientesdto.getId());

            pstm.execute();
            pstm.close();

        } catch (Exception e) {
        }
    }

}
