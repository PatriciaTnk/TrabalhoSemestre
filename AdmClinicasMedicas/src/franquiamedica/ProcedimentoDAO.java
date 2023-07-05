/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProcedimentoDAO {

    public List<Procedimento> procedimentos = new ArrayList<>();

    public boolean adiciona(Procedimento pr) {
        String sql = "INSERT INTO procedimentos (nome, consulta, diaEhorario, estado, valor, laudo, dataCriacao, dataModificacao, visible) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pr.getNome());
            stmt.setLong(2, pr.getConsulta());
            stmt.setDate(3, java.sql.Date.valueOf(pr.getDiaHorario()));
            stmt.setString(4, pr.getEstado());
            stmt.setBigDecimal(5, pr.getValorPro());
            stmt.setString(6, pr.getLaudo());
            stmt.setDate(7, java.sql.Date.valueOf(pr.getDataCriacao()));
            stmt.setDate(8, java.sql.Date.valueOf(pr.getDatamodificacao()));
            stmt.setBoolean(9, true);

            stmt.execute();
            System.out.println("procedimentos adicionada com sucesso.");
            populaLista();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populaLista() {
        procedimentos.clear();
        String sql = "SELECT * FROM procedimentos";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                Long consulta = rs.getLong("consulta");
                Date diaD = rs.getDate("diaEHorario");
                LocalDate diaEHorario = diaD.toLocalDate();
                String estado = rs.getString("estado");
                BigDecimal valor = rs.getBigDecimal("valor");
                String laudo = rs.getString("laudo");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();
                boolean ver = rs.getBoolean("visible");

                Procedimento novo = new Procedimento();
                novo.setId(id);
                novo.setNome(nome);
                novo.setConsulta(consulta);
                novo.setDiaHorario(diaEHorario.toString());
                novo.setEstado(estado);
                novo.setValorPro(valor);
                novo.setLaudo(laudo);
                novo.setDataCriacao(dataCriacao);
                novo.setDatamodificacao(dataModificacao);
                novo.setVisible(ver);

                procedimentos.add(novo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostraTodos() {
        populaLista();
        for (Procedimento pr : procedimentos) {
            if (pr.isVisible()) {
                System.out.println(pr);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (Procedimento pro : procedimentos) {
                if (pro.isVisible()) {
                    writer.write(pro.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Procedimento verificaRegistro(long id) {
        populaLista();
        for (Procedimento pr : procedimentos) {
            if (pr.getId() == id) {
                return pr;
            }
        }
        return null;
    }

    public boolean cancelaProcedimento(long idCancelar) {
        String sql = "UPDATE procedimentos SET estado = 'Cancelada' WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idCancelar);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Procedimento cancelado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarNome(long idConsulta, String novoNome) {
        String sql = "UPDATE procedimentos SET nome = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, idConsulta);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nome do Procedimento alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarDiaEHorario(long idConsulta, String novoDia) {
        String sql = "UPDATE procedimentos SET diaEhorario = ?, dataModificacao = ? WHERE id = ?";

        DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate day = LocalDate.parse(novoDia, formatterDia);

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(day));
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, idConsulta);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dia alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarEstado(long idProcedimento, String estadoAtual) {
        String sql = "UPDATE procedimentos SET estado = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, estadoAtual);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(3, idProcedimento);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("procedimentos do médico alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarValor(long idConsulta, BigDecimal novoValor) {
        String sql = "UPDATE procedimentos SET valor = ?, dataModificacao = ? WHERE id = ?";
        novoValor = novoValor.setScale(2, RoundingMode.HALF_DOWN);
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, novoValor);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(3, idConsulta);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("procedimentos do médico alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarLaudo(long idProcedimento, String novoLaudo) {
        String sql = "UPDATE procedimentos SET laudo = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoLaudo);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(3, idProcedimento);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Laudo do procedimento alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}
