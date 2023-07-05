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

public class FranquiaDAO {

    public List<Franquia> franquias = new ArrayList<>();

    public boolean adiciona(Franquia f) {
        String sql = "INSERT INTO unidades_franquias (franquia, cidade, endereco, responsavel, dataCriacao, dataModificacao, visible) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, f.getFranquia());
            stmt.setString(2, f.getCidade());
            stmt.setString(3, f.getEndereco());
            stmt.setLong(4, f.getResponsavel());
            stmt.setDate(5, java.sql.Date.valueOf(f.getDataCriacao()));
            stmt.setDate(6, java.sql.Date.valueOf(f.getDatamodificacao()));
            stmt.setBoolean(7, true);

            stmt.execute();
            System.out.println("Franquia adicionada com sucesso.");

            String mudarUsuario = "UPDATE clinicasmedica.pessoas SET tipoDeUsuario = 'Responsavel pela Franquia' WHERE (id = ?)";

            try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement upst = con.prepareStatement(mudarUsuario)) {
                upst.setLong(1, f.getResponsavel());
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
        franquias.clear();
        String sql = "SELECT * FROM unidades_franquias";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long franquia = rs.getLong("franquia");
                String cidade = rs.getString("cidade");
                String endereco = rs.getString("endereco");
                Long responsavel = rs.getLong("responsavel");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();
                boolean ver = rs.getBoolean("visible");

                Franquia nova = new Franquia();
                nova.setId(id);
                nova.setFranquia(franquia);
                nova.setCidade(cidade);
                nova.setEndereco(endereco);
                nova.setResponsavel(responsavel);
                nova.setDataCriacao(dataCriacao);
                nova.setDatamodificacao(dataModificacao);
                nova.setVisible(ver);

                franquias.add(nova);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostraTodos() {
        populaLista();
        for (Franquia franquia : franquias) {
            if (franquia.isVisible()) {
                System.out.println(franquia);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (Franquia f : franquias) {
                if (f.isVisible()) {
                    writer.write(f.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Franquia verificaRegistro(long id) {
        populaLista();
        for (Franquia franquia : franquias) {
            if (franquia.getId() == id) {
                return franquia;
            }
        }
        return null;
    }

    public boolean remove(long idRemover) {
        String sql = "UPDATE unidades_franquias SET visible = false WHERE id = ?";

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

    public boolean alterarCidade(long idMatriz, String novaCidade) {
        String sql = "UPDATE unidades_franquias SET cidade = ?, dataModificacao = ? WHERE id = ?";

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
        String sql = "UPDATE unidades_franquias SET endereco = ?, dataModificacao = ? WHERE id = ?";

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
        return verificaRegistro(idFranquia).getResponsavel();
    }

}
