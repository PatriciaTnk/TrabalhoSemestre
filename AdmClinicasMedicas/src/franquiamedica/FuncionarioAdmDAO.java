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

public class FuncionarioAdmDAO {

    public List<FuncionarioAdm> trabalhador = new ArrayList<>();

    public boolean adiciona(FuncionarioAdm tr) {
        String sql = "insert into funcionarios_administrativo "
                + "(id_franquia, id_funcionario,dataCriacao,dataModificacao, visible)" + " values (?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setLong(1, tr.getIdFranquia());
            stmt.setLong(2, tr.getFuncionario());
            stmt.setDate(3, java.sql.Date.valueOf(tr.getDataCriacao()));
            stmt.setDate(4, java.sql.Date.valueOf(tr.getDatamodificacao()));
            stmt.setBoolean(5, true);

            stmt.execute();
            System.out.println("Elemento inserido com sucesso.");

            String mudarUsuario = "UPDATE clinicasmedica.pessoas SET tipoDeUsuario = 'FuncAdministrativo' WHERE (id = ?)";

            try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement upst = con.prepareStatement(mudarUsuario)) {
                upst.setLong(1, tr.getFuncionario());
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

    private void populaLista() {
        trabalhador.clear();
        String sql = "select * from funcionarios_administrativo";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long idF = rs.getLong("id");
                Long idP = rs.getLong("id");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();

                FuncionarioAdm outro = new FuncionarioAdm();
                outro.setIdFranquia(idF);
                outro.setFuncionario(idP);
                outro.setDataCriacao(dataCriacao);
                outro.setDatamodificacao(dataModificacao);

                trabalhador.add(outro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostraTodos() {
        populaLista();
        for (FuncionarioAdm fa : trabalhador) {
            if (fa.isVisible()) {
                System.out.println(fa);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (FuncionarioAdm fun : trabalhador) {
                if (fun.isVisible()) {
                    writer.write(fun.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verificaRegistroTrabalhador(long idPessoa) {
        populaLista();
        for (FuncionarioAdm fa : trabalhador) {
            if (fa.getFuncionario() == idPessoa) {
                return true;
            }
        }
        return false;
    }

    public FuncionarioAdm verificaVinculo(long idVinculo) {
        populaLista();
        for (FuncionarioAdm fa : trabalhador) {
            if (fa.getId() == idVinculo) {
                return fa;
            }
        }
        return null;
    }

    public boolean desliga(long idVinculo) {
        String sql = "UPDATE pessoas SET visible = false WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idVinculo);

            String mudarUsuario = "UPDATE clinicasmedica.pessoas SET tipoDeUsuario = 'Paciente' WHERE (id = ?)";

            try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement upst = con.prepareStatement(mudarUsuario)) {
                upst.setLong(1, verificaVinculo(idVinculo).getFuncionario());
                upst.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Elemento excluído com sucesso.");
        populaLista();
        return true;
    }

}
