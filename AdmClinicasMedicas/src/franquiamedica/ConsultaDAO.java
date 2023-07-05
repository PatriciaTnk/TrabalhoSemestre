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

public class ConsultaDAO {

    public List<Consulta> consultas = new ArrayList<>();

    public boolean adiciona(Consulta c) {
        String sql = "INSERT INTO consultas (diaEhorario, estado, medico, paciente, valor, unidade, dataCriacao, dataModificacao, visible) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(c.getDiaHorario()));
            stmt.setString(2, c.getEstado());
            stmt.setLong(3, c.getMedico());
            stmt.setLong(4, c.getPaciente());
            stmt.setBigDecimal(5, c.getValor());
            stmt.setLong(6, c.getUnidade());
            stmt.setDate(7, java.sql.Date.valueOf(c.getDataCriacao()));
            stmt.setDate(8, java.sql.Date.valueOf(c.getDatamodificacao()));
            stmt.setBoolean(9, true);

            stmt.execute();
            System.out.println("Consulta adicionada com sucesso.");
            populaLista();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populaLista() {
        consultas.clear();
        String sql = "SELECT * FROM consultas";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Date diaD = rs.getDate("diaEHorario");
                LocalDate diaEHorario = diaD.toLocalDate();
                String estado = rs.getString("estado");
                Long medico = rs.getLong("medico");
                Long paciente = rs.getLong("paciente");
                BigDecimal valor = rs.getBigDecimal("valor");
                Long unidade = rs.getLong("unidade");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();
                boolean ver = rs.getBoolean("visible");

                Consulta novo = new Consulta();
                novo.setId(id);
                novo.setDiaHorario(diaEHorario.toString());
                novo.setEstado(estado);
                novo.setMedico(medico);
                novo.setPaciente(paciente);
                novo.setValor(valor);
                novo.setUnidade(unidade);
                novo.setDataCriacao(dataCriacao);
                novo.setDatamodificacao(dataModificacao);
                novo.setVisible(ver);

                consultas.add(novo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostraTodos() {
        populaLista();
        for (Consulta c : consultas) {
            if (c.isVisible()) {
                System.out.println(c);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (Consulta con : consultas) {
                if (con.isVisible()) {
                    writer.write(con.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Consulta verificaRegistro(long id) {
        populaLista();
        for (Consulta c : consultas) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean cancelaConsulta(long idCancelar) {
        String sql = "UPDATE consultas SET estado = 'Cancelada' WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idCancelar);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("consulta cancelada com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarDiaEHorario(long idConsulta, String novoDia) {
        String sql = "UPDATE consultas SET diaEhorario = ?, dataModificacao = ? WHERE id = ?";
        
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

    public boolean alterarEstado(long idConsulta, String estadoAtual) {
        String sql = "UPDATE consultas SET estado = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, estadoAtual);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(3, idConsulta);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("consultas do médico alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarMedico(long idConsulta, long novoMedico) {
        String sql = "UPDATE consultas SET medico = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, novoMedico);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, idConsulta);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Medico da consulta alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarValor(long idConsulta, BigDecimal novoValor) {
        String sql = "UPDATE consultas SET valor = ?, dataModificacao = ? WHERE id = ?";
        novoValor = novoValor.setScale(2, RoundingMode.HALF_DOWN);
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, novoValor);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(3, idConsulta);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("consultas do médico alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarUnidadeAtendimento(long idConsulta, long novoLocal) {
        String sql = "UPDATE consultas SET unidade = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, novoLocal);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(3, idConsulta);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("consultas do médico alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public long getPaciente(long idConsulta) {
        return verificaRegistro(idConsulta).getPaciente();
    }

}
