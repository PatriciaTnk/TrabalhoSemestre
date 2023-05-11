/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

public class FinanceiroMedico {

    private long id;
    private static long serial; //fica com o valor padrão
    private BigDecimal valor;
    private Medico medico;
    private String estado;
    private Franquia unidade;

    private LocalDateTime dataCriacao;
    private LocalDateTime datamodificacao;
    private boolean visible;

    public FinanceiroMedico(Franquia pagador, Medico recebedor) {
        this.id = FinanceiroMedico.serial++;
        this.visible = true;
        this.medico = recebedor;
        this.unidade = pagador;
        this.dataCriacao = Utilitario.dataCriacao;
        this.datamodificacao = Utilitario.dataCriacao;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }
    
    public void setValor(BigDecimal preco) {
        this.valor = preco.setScale(2, RoundingMode.HALF_DOWN);
    }

    public Medico getMedico() {
        return medico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Franquia getUnidade() {
        return unidade;
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
        sb.append("Financeiro_Medico{");
        sb.append("id=").append(id);
        sb.append(", valor=").append(valor);
        sb.append(", medico=").append(medico);
        sb.append(", estado=").append(estado);
        sb.append(", unidade=").append(unidade);
        sb.append(", dataCriacao=").append(dataCriacao);
        sb.append(", datamodificacao=").append(datamodificacao);
        sb.append(", visible=").append(visible);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 73 * hash + Objects.hashCode(this.valor);
        hash = 73 * hash + Objects.hashCode(this.medico);
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
        final FinanceiroMedico other = (FinanceiroMedico) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        return Objects.equals(this.medico, other.medico);
    }

}
