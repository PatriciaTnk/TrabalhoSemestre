/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDateTime;
import java.util.Objects;

public class MatrizFranquia {
    
    private long id;
    private static long serial; //fica com o valor padrão
    private String nome;
    private String cnpj;
    private String endereco;
    private String cidade;
    private Pessoa dono;

    private LocalDateTime dataCriacao;
    private LocalDateTime datamodificacao;

    public MatrizFranquia(Pessoa dono) {
        this.id = MatrizFranquia.serial++;
        this.dono = dono;
        this.dono.setTipoUsuario("Dono da Matriz");
        this.dataCriacao = LocalDateTime.now();
        this.datamodificacao = LocalDateTime.now();
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Pessoa getDono() {
        return dono;
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
        sb.append("MatrizFranquia{");
        sb.append("id=").append(id);
        sb.append(", nome=").append(nome);
        sb.append(", dono=").append(dono);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.dono);
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
        final MatrizFranquia other = (MatrizFranquia) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.dono, other.dono);
    }    
    
}
