/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinanceiroMedicoDAO {

    public List<FinanceiroMedico> financas = new ArrayList<>();

    public boolean adiciona(FinanceiroMedico pr) {
        String sql = "INSERT INTO financeiro_medicos (valor, medico, estado, franquia, dataCriacao, dataModificacao, visible) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, pr.getValor());
            stmt.setLong(2, pr.getMedico());
            stmt.setString(3, pr.getEstado());
            stmt.setLong(4, pr.getUnidade());
            stmt.setDate(5, java.sql.Date.valueOf(pr.getDataCriacao()));
            stmt.setDate(6, java.sql.Date.valueOf(pr.getDatamodificacao()));
            stmt.setBoolean(7, true);

            stmt.execute();
            populaLista();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populaLista() {
        financas.clear();
        String sql = "SELECT * FROM financeiro_medicos";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                BigDecimal valor = rs.getBigDecimal("valor");
                Long medico = rs.getLong("medico");
                String estado = rs.getString("estado");
                Long unidade = rs.getLong("franquia");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();
                boolean ver = rs.getBoolean("visible");

                FinanceiroMedico novo = new FinanceiroMedico();
                novo.setId(id);
                novo.setValor(valor);
                novo.setMedico(medico);
                novo.setEstado(estado);
                novo.setUnidade(unidade);
                novo.setDataCriacao(dataCriacao);
                novo.setDatamodificacao(dataModificacao);
                novo.setVisible(ver);

                financas.add(novo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostraTodos() {
        populaLista();
        for (FinanceiroMedico pr : financas) {
            if (pr.isVisible()) {
                System.out.println(pr);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (FinanceiroMedico fin : financas) {
                if (fin.isVisible()) {
                    writer.write(fin.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FinanceiroMedico verificaRegistro(long id) {
        populaLista();
        for (FinanceiroMedico pr : financas) {
            if (pr.getId() == id) {
                return pr;
            }
        }
        return null;
    }

       public boolean apagaRgistro(long idCancelar) {
        String sql = "UPDATE financeiro_medicos SET visible = false WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idCancelar);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registro apagado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }    
}
