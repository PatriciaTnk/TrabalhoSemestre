/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatrizFranquiaDAO {

    public List<MatrizFranquia> matrizes = new ArrayList<>();

    public boolean adiciona(MatrizFranquia mf) {
        String sql = "INSERT INTO franquias (nome, cnpj, cidade, endereco, responsavel, dataCriacao, dataModificacao, visible) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mf.getNome());
            stmt.setString(2, mf.getCnpj());
            stmt.setString(3, mf.getCidade());
            stmt.setString(4, mf.getEndereco());
            stmt.setLong(5, mf.getDono());
            stmt.setDate(6, java.sql.Date.valueOf(mf.getDataCriacao()));
            stmt.setDate(7, java.sql.Date.valueOf(mf.getDatamodificacao()));
            stmt.setBoolean(8, true);

            stmt.execute();
            System.out.println("Matriz adicionada com sucesso.");

            String mudarUsuario = "UPDATE clinicasmedica.pessoas SET tipoDeUsuario = 'Dono de Franquia' WHERE (id = ?)";

            try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement upst = con.prepareStatement(mudarUsuario)) {
                upst.setLong(1, mf.getDono());
                upst.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populaLista() {
        matrizes.clear();
        String sql = "SELECT * FROM franquias";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cnpj = rs.getString("cnpj");
                String cidade = rs.getString("cidade");
                String endereco = rs.getString("endereco");
                Long responsavel = rs.getLong("responsavel");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();
                boolean ver = rs.getBoolean("visible");

                MatrizFranquia nova = new MatrizFranquia();
                nova.setId(id);
                nova.setNome(nome);
                nova.setCnpj(cnpj);
                nova.setCidade(cidade);
                nova.setEndereco(endereco);
                nova.setDono(responsavel);
                nova.setDataCriacao(dataCriacao);
                nova.setDatamodificacao(dataModificacao);
                nova.setVisible(ver);

                matrizes.add(nova);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostraTodos() {
        populaLista();
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.isVisible()) {
                System.out.println(matriz);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (MatrizFranquia mf : matrizes) {
                if (mf.isVisible()) {
                    writer.write(mf.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MatrizFranquia verificaRegistro(long id) {
        populaLista();
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == id) {
                return matriz;
            }
        }
        return null;
    }

    public boolean remove(long idRemover) {
        String sql = "UPDATE franquias SET visible = false WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idRemover);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Matriz removida com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarNome(long idMatriz, String novoNome) {
        String sql = "UPDATE franquias SET nome = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, idMatriz);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nome da Matriz alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarCnpj(long idMatriz, String novoCnpj) {
        String sql = "UPDATE franquias SET cnpj = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoCnpj);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, idMatriz);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cnpj da Matriz alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarCidade(long idMatriz, String novaCidade) {
        String sql = "UPDATE franquias SET cidade = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novaCidade);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, idMatriz);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cidade da Matriz alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarEndereco(long idMatriz, String novoEndereco) {
        String sql = "UPDATE franquias SET endereco = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoEndereco);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, idMatriz);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Endereco da Matriz alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public long getPessoa(long idFranquia) {
        return verificaRegistro(idFranquia).getDono();
    }
}
