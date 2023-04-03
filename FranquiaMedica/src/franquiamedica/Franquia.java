/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDateTime;
import java.util.Objects;

public class Franquia {

    private long id;
    private String endereco;
    private String cidade;
    private Pessoa[] responsavel = new Pessoa[5];
    private MatrizFranquia franquia;

    private LocalDateTime dataCriacao;
    private LocalDateTime datamodificacao;

    public Franquia(Pessoa funcionario, MatrizFranquia sede) {
        this.id = responsavel[0].getId();
        this.responsavel[0] = funcionario;
        this.responsavel[0].setTipoUsuario("Responsavel pela Franquia");
        this.franquia = sede;
        this.dataCriacao = LocalDateTime.now();
        this.datamodificacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Pessoa getResponsavelPrincipal() {
        return responsavel[0];
    }

    public void mostraTodosResponsaveis() {
        for (Pessoa pessoa : responsavel) {
            if (pessoa != null) {
                System.out.println(pessoa);
            }
        }
    }

    public boolean addResponsavel(Pessoa responsavelExtra) {
        for (int i = 0; i < responsavel.length; i++) {
            if (responsavel[i] == null) {
                responsavel[i] = responsavelExtra;
                return true;
            }
        }
        return false;
    }

    public Pessoa encontraUmResponsavel(String nome) {
        for (Pessoa pessoa : responsavel) {
            if (pessoa.getNome().equals(nome)) {
                return pessoa;
            }
        }
        return null;
    }

    public MatrizFranquia getFranquia() {
        return franquia;
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
        sb.append("Franquia{");
        sb.append("id=").append(id);
        sb.append(", endereco=").append(endereco);
        sb.append(", cidade=").append(cidade);
        for (Pessoa pessoa : responsavel) {
            sb.append(", responsavel=").append(pessoa);
        }
        sb.append(", franquia=").append(franquia);
        sb.append(", dataCriacao=").append(dataCriacao);
        sb.append(", datamodificacao=").append(datamodificacao);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 61 * hash + Objects.hashCode(this.franquia);
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
        final Franquia other = (Franquia) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.franquia, other.franquia);
    }

}
