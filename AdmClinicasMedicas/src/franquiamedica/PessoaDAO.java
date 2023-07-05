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

public class PessoaDAO {

    public List<Pessoa> pessoas = new ArrayList<>();

    /**
     * adicionar, mostrar, conferir se tem registro, deletar e atualizar.para já
     * ter dados cadastrados, popular dados no construtor preciso de um array
     * para popular OBS: Para atender Uma pessoa pode ser cadastrada com
     * diferentes papéis.Cada login diferente irá diferenciar os usuários.
     * Deverão ser realizados mais cadastros da mesma pessoa, pois para cada id
     * de cadastro só pode ser atribuido um login e um papel
     *
     * @param p
     * @return
     */
    public boolean adiciona(Pessoa p) {
        String sql = "insert into contatos "
                + "(nome,endereco,cpf,telefone,login,senha,tipoDeUsuario,dataCriacao,dataModificacao, visible)" + " values (?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEndereco());
            stmt.setString(3, p.getCpf());
            stmt.setString(4, p.getTelefone());
            stmt.setString(5, p.getLogin());
            stmt.setString(6, p.getSenha());
            stmt.setString(7, p.getTipoUsuario());
            stmt.setDate(8, java.sql.Date.valueOf(p.getDataCriacao()));
            stmt.setDate(9, java.sql.Date.valueOf(p.getDatamodificacao()));
            stmt.setBoolean(10, true);

            stmt.execute();
            System.out.println("Elemento inserido com sucesso.");
            populaLista();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populaLista() {
        pessoas.clear();
        String sql = "select * from pessoas";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String end = rs.getString("endereco");
                String cpf = rs.getString("cpf");
                String tel = rs.getString("telefone");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String tipoUsuario = rs.getString("tipoDeUsuario");
                Date creationDate = rs.getDate("dataCriacao");
                LocalDate dataCriacao = creationDate.toLocalDate();
                Date modifcDate = rs.getDate("dataModificacao");
                LocalDate dataModificacao = modifcDate.toLocalDate();

                Pessoa outro = new Pessoa();
                outro.setId(id);
                outro.setNome(nome);
                outro.setEndereco(end);
                outro.setCpf(cpf);
                outro.setTelefone(tel);
                outro.setLogin(login);
                outro.setSenha(senha);
                outro.setTipoUsuario(tipoUsuario);
                outro.setDataCriacao(dataCriacao);
                outro.setDatamodificacao(dataModificacao);

                pessoas.add(outro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // itera no ResultSet
        //return pessoas;
    }

    public void mostraTodos() {
        populaLista();
        for (Pessoa peop : pessoas) {
            if (peop.isVisible()) {
                System.out.println(peop);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/Relatorio.txt", true))) {
            for (Pessoa peop : pessoas) {
                if (peop.isVisible()) {
                    writer.write(peop.toString()); // Escreve a representação do objeto no arquivo
                    writer.newLine(); // Adiciona uma nova linha após cada objeto para melhor formatação
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pessoa verificaRegistro(long id) {
        populaLista();
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }

    public boolean remove(long id) {
        String sql = "UPDATE pessoas SET visible = false WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pessoa removida com sucesso.");
                populaLista();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarNome(long id, String novoNome) {
        String sql = "update pessoas set nome = ?, dataModificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nome alterado com sucesso.");
                populaLista();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarEndereco(long id, String novoEndereco) {
        String sql = "update pessoas set endereco = ?, dataModificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoEndereco);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Endereco alterado com sucesso.");
                populaLista();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarCpf(long id, String novoCpf) {
        String sql = "update pessoas set cpf = ?, dataModificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoCpf);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cpf alterado com sucesso.");
                populaLista();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarTelefone(long id, String novoTelefone) {
        String sql = "update pessoas set telefone = ?, dataModificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoTelefone);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Telefone alterado com sucesso.");
                populaLista();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarLogin(long id, String novoLogin) {
        String sql = "update pessoas set login = ?, dataModificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoLogin);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(3, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Login alterado com sucesso.");
                populaLista();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarSenha(long id, String novaSenha) {
        String sql = "update pessoas set senha = ?, dataModificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novaSenha);
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setLong(2, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Senha alterada com sucesso.");
                populaLista();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public long buscaLoginSenha(String login, String senha) {
        this.populaLista();
        for (Pessoa pessoaConfirmada : pessoas) {
            if (pessoaConfirmada.getLogin().equals(login)
                    && pessoaConfirmada.getSenha().equals(senha)) {
                Utilitario.setPessoaLogada(pessoaConfirmada);
                return pessoaConfirmada.getId();
            }
        }
        return -1;
    }

}
