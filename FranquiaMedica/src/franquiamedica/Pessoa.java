/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import franquiamedica.Utilitario;

/**
 * Toda vez que criar classe criar os getters e setters para o id usar o serial
 * (static)
 *
 * Para cada classe para mexer nos dados é necessário criar o DAO Data Access
 * Object É nele que vai ficar o vetor
 */
public class Pessoa {

    private long id;
    private static long serial; //fica com o valor padrão
    private String nome;
    private String endereco;
    private String cpf;
    private String telefone;
    private String login;
    private String senha;
    private String tipoUsuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime datamodificacao;

    public Pessoa() {
        this.id = Pessoa.serial++;
        this.tipoUsuario = "Paciente";
        this.dataCriacao = Utilitario.dataCriacao;
        this.datamodificacao = Utilitario.dataCriacao;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDatamodificacao() {
        return datamodificacao;
    }

    public void setDatamodificacao(LocalDateTime datamodificacao) {
        this.datamodificacao = datamodificacao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pessoa{");
        sb.append("id=").append(id);
        sb.append(", nome=").append(nome);
        sb.append(", login=").append(login);
        sb.append(", senha=").append(senha);
        sb.append('}');
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.login);
        hash = 83 * hash + Objects.hashCode(this.senha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return Objects.equals(this.senha, other.senha);
    }

}
