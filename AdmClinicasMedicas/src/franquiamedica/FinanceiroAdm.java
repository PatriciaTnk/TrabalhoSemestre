/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class FinanceiroAdm {

    private long id;
    private String tipoDeMovim;
    private BigDecimal valor;
    private String descritivo;
    private long unidade;

    private LocalDate dataCriacao;
    private LocalDate datamodificacao;
    private boolean visible;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoDeMovim() {
        return tipoDeMovim;
    }

    public void setTipoDeMovim(String tipoDeMovim) {
        this.tipoDeMovim = tipoDeMovim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal preco) {
        this.valor = preco.setScale(2, RoundingMode.HALF_DOWN);
    }

    public String getDescritivo() {
        return descritivo;
    }

    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }

    public long getUnidade() {
        return unidade;
    }

    public void setUnidade(long unidade) {
        this.unidade = unidade;
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
        sb.append("\n\n");
        sb.append("Financeiro_Adm{");
        sb.append("id=").append(id);
        sb.append(", tipoDeMovim=").append(tipoDeMovim);
        sb.append(", valor=").append(valor);
        sb.append(", descritivo=").append(descritivo);
        sb.append(", unidade=").append(unidade);
        sb.append(", dataCriacao=").append(dataCriacao);
        sb.append(", datamodificacao=").append(datamodificacao);
        sb.append(", visible=").append(visible);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 17 * hash + Objects.hashCode(this.tipoDeMovim);
        hash = 17 * hash + Objects.hashCode(this.valor);
        hash = 17 * hash + Objects.hashCode(this.unidade);
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
        final FinanceiroAdm other = (FinanceiroAdm) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.tipoDeMovim, other.tipoDeMovim)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        return Objects.equals(this.unidade, other.unidade);
    }

}
