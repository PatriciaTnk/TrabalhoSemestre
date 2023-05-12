/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class FuncionarioAdm {

    private long id;
    private static long serial; //fica com o valor padrão
    private long idFranquia;
    private Pessoa funcionario;

    private LocalDateTime dataCriacao;
    private LocalDateTime datamodificacao;
    private boolean visible;

    public FuncionarioAdm(Pessoa trabalhador) {
        this.id = FuncionarioAdm.serial++;
        this.visible = true;
        this.funcionario = trabalhador;
        funcionario.setTipoUsuario("FuncAdministrativo");
        this.dataCriacao = Utilitario.dataCriacao;
        this.datamodificacao = Utilitario.dataCriacao;
    }

    public long getId() {
        return id;
    }

    public long getIdFranquia() {
        return idFranquia;
    }

    public void setIdFranquia(long idFranquia) {
        this.idFranquia = idFranquia;
    }

    public Pessoa getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Pessoa trabalhador) {
        this.funcionario = trabalhador;
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

    public void notVisible(boolean isDeleted) {
        if (isDeleted == true) {
            this.visible = false;
        }
    }

    public boolean getVisible() {
        return this.visible;
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
