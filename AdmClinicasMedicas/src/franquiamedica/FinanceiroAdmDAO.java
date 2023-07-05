/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinanceiroAdmDAO {

    public List<FinanceiroAdm> financas = new ArrayList<>();

    public boolean adiciona(FinanceiroAdm pr) {
        String sql = "INSERT INTO financeiro_administrativo (tipoDeMovimentacao, valor, unidade, descritivo, dataCriacao, dataModificacao, visible) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pr.getTipoDeMovim());
            stmt.setBigDecimal(2, pr.getValor());
            stmt.setLong(3, pr.getUnidade());
            stmt.setString(4, pr.getDescritivo());
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
        String sql = "SELECT * FROM financeiro_administrativo";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String tipoDeMovimentacao = rs.getString("tipoDeMovimentacao");
                BigDecimal valor = rs.getBigDecimal("valor");
                Long unidade = rs.getLong("unidade");
                String descritivo = rs.getString("descritivo");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();
                boolean ver = rs.getBoolean("visible");

                FinanceiroAdm novo = new FinanceiroAdm();
                novo.setId(id);
                novo.setTipoDeMovim(tipoDeMovimentacao);
                novo.setValor(valor);
                novo.setUnidade(unidade);
                novo.setDescritivo(descritivo);
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
        for (FinanceiroAdm pr : financas) {
            if (pr.isVisible()) {
                System.out.println(pr);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (FinanceiroAdm pr : financas) {
                if (pr.isVisible()) {
                    writer.write(pr.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FinanceiroAdm verificaRegistro(long id) {
        populaLista();
        for (FinanceiroAdm pr : financas) {
            if (pr.getId() == id) {
                return pr;
            }
        }
        return null;
    }

    public boolean apagaRgistro(long idCancelar) {
        String sql = "UPDATE financeiro_administrativo SET visible = false WHERE id = ?";

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
