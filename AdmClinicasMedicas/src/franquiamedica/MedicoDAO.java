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

public class MedicoDAO {

    public List<Medico> medicos = new ArrayList<>();

    public boolean adiciona(Medico m) {
        String sql = "INSERT INTO medicos (crm, pessoa, especialidade, dataCriacao, dataModificacao, visible) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, m.getCrm());
            stmt.setLong(2, m.getPessoa());
            stmt.setString(3, m.getEspecialidade());
            stmt.setDate(4, java.sql.Date.valueOf(m.getDataCriacao()));
            stmt.setDate(5, java.sql.Date.valueOf(m.getDatamodificacao()));
            stmt.setBoolean(6, true);

            stmt.execute();
            System.out.println("Médico adicionado com sucesso.");

            String mudarUsuario = "UPDATE clinicasmedica.pessoas SET tipoDeUsuario = 'Medico' WHERE (id = ?)";

            try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement upst = con.prepareStatement(mudarUsuario)) {
                upst.setLong(1, m.getPessoa());
                upst.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            populaLista();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populaLista() {
        medicos.clear();
        String sql = "SELECT * FROM medicos";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String crm = rs.getString("crm");
                Long pessoa = rs.getLong("pessoa");
                String especialidade = rs.getString("especialidade");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();
                boolean ver = rs.getBoolean("visible");

                Medico medico = new Medico();
                medico.setId(id);
                medico.setCrm(crm);
                medico.setPessoa(pessoa);
                medico.setEspecialidade(especialidade);
                medico.setDataCriacao(dataCriacao);
                medico.setDatamodificacao(dataModificacao);
                medico.setVisible(ver);

                medicos.add(medico);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostraTodos() {
        populaLista();
        for (Medico m : medicos) {
            if (m.isVisible()) {
                System.out.println(m);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (Medico med : medicos) {
                if (med.isVisible()) {
                    writer.write(med.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Medico verificaRegistro(long id) {
        populaLista();
        for (Medico medico : medicos) {
            if (medico.getId() == id) {
                return medico;
            }
        }
        return null;
    }

    public boolean remove(long idRemover) {
        String sql = "UPDATE medicos SET visible = false WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idRemover);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Médico removido com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarEspecialidade(long idMedico, String novaEspecialidade) {
        String sql = "UPDATE medicos SET especialidade = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novaEspecialidade);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, idMedico);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Especialidade do médico alterada com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean alterarCrm(long idMedico, String novoCrm) {
        String sql = "UPDATE medicos SET crm = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoCrm);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(3, idMedico);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("CRM do médico alterado com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public long getPessoa(long idMedico) {
        return verificaRegistro(idMedico).getPessoa();
    }

}
