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

public class InfoConsultaDAO {

    public List<InfoConsulta> infos = new ArrayList<>();
    
    public boolean adiciona(InfoConsulta ifc) {
        String sql = "INSERT INTO info_consultas (consulta, descricao, dataCriacao, dataModificacao, visible) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ifc.getConsulta());
            stmt.setString(2, ifc.getDescricao());
            stmt.setDate(3, java.sql.Date.valueOf(ifc.getDataCriacao()));
            stmt.setDate(4, java.sql.Date.valueOf(ifc.getDatamodificacao()));
            stmt.setBoolean(5, true);

            stmt.execute();
            System.out.println("Informacao adicionada com sucesso.");

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populaLista() {
        infos.clear();
        String sql = "SELECT * FROM info_consultas";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long consulta = rs.getLong("consulta");
                String descricao = rs.getString("descricao");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();
                boolean ver = rs.getBoolean("visible");

                InfoConsulta novo = new InfoConsulta();
                novo.setId(id);
                novo.setConsulta(consulta);
                novo.setDescricao(descricao);
                novo.setDataCriacao(dataCriacao);
                novo.setDatamodificacao(dataModificacao);
                novo.setVisible(ver);

                infos.add(novo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostraTodos() {
        populaLista();
        for (InfoConsulta ifc : infos) {
            if (ifc.isVisible()) {
                System.out.println(ifc);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (InfoConsulta info : infos) {
                if (info.isVisible()) {
                    writer.write(info.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InfoConsulta verificaRegistro(long id) {
        populaLista();
        for (InfoConsulta ifc : infos) {
            if (ifc.getId() == id) {
                return ifc;
            }
        }
        return null;
    }

    public boolean apagaInfoConsulta(long idCancelar) {
        String sql = "UPDATE info_consultas SET visible = 0 WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idCancelar);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Informacao apagada com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }    
    
    public boolean alterarDescricao(long idInfo, String novaDescricao) {
        String sql = "UPDATE info_consultas SET descricao = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novaDescricao);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, idInfo);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Descricao alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    
}
