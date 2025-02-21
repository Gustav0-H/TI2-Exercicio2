package ativ;

import java.sql.*;

public class DAO {
    private Connection conexao;

    public DAO() {
        conexao = null;
    }

    // Método de conexão com o banco de dados
    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "ativ";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "ti2cc";
        String password = "Gugu2005";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            if (conexao != null) {
                status = true;  // A conexão foi bem-sucedida
                System.out.println("Conexão efetuada com o postgres!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;  // Retorna o status da conexão
    }

    // Método para fechar a conexão
    public boolean close() {
        boolean status = false;

        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                status = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    // Inserir biscoito
    public boolean inserirBiscoito(Biscoito biscoito) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO biscoito (codigo, marca, recheio) "
                    + "VALUES (" + biscoito.getCodigo() + ", '" + biscoito.getMarca() + "', '"
                    + biscoito.getRecheio() + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    // Atualizar biscoito
    public boolean atualizarBiscoito(Biscoito biscoito) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE biscoito SET marca = '" + biscoito.getMarca() + "', recheio = '"
                    + biscoito.getRecheio() + "' WHERE codigo = " + biscoito.getCodigo();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    // Excluir biscoito
    public boolean excluirBiscoito(int codigo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM biscoito WHERE codigo = " + codigo);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    // Obter todos os biscoitos
    public Biscoito[] getBiscoitos() {
        Biscoito[] biscoitos = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM biscoito");
            if (rs.next()) {
                rs.last();
                biscoitos = new Biscoito[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    biscoitos[i] = new Biscoito(rs.getInt("codigo"), rs.getString("marca"), rs.getString("recheio"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return biscoitos;
    }
}
