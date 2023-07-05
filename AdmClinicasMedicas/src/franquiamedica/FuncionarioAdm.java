/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDate;
import java.util.Objects;

public class FuncionarioAdm {

    private long id;
    private long idFranquia;
    private long funcionario;

    private LocalDate dataCriacao;
    private LocalDate datamodificacao;
    private boolean visible;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdFranquia() {
        return idFranquia;
    }

    public void setIdFranquia(long idFranquia) {
        this.idFranquia = idFranquia;
    }

    public long getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(long funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDatamodificacao() {
        return datamodificacao;
    }

    public void setDatamodificacao(LocalDate datamodificacao) {
        this.datamodificacao = datamodificacao;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Funcionario_Adm{");
        sb.append("id=").append(id);
        sb.append(", idFranquia=").append(idFranquia);
        sb.append(", idFuncionario=").append(funcionario);
        sb.append(", dataCriacao=").append(dataCriacao);
        sb.append(", datamodificacao=").append(datamodificacao);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + (int) (this.idFranquia ^ (this.idFranquia >>> 32));
        hash = 89 * hash + Objects.hashCode(this.funcionario);
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
        final FuncionarioAdm other = (FuncionarioAdm) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idFranquia != other.idFranquia) {
            return false;
        }
        return Objects.equals(this.funcionario, other.funcionario);
    }

}
