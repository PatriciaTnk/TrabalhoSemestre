/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Consulta {

    private long id;
    private String estado;
    private LocalDate diaHorario;
    private BigDecimal valor;
    private long paciente;
    private long medico;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(String dia) {
        DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate day = LocalDate.parse(dia, formatterDia);
        this.diaHorario = day;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal preco) {
        this.valor = preco.setScale(2, RoundingMode.HALF_DOWN);    
    }

    public long getPaciente() {
        return paciente;
    }

    public void setPaciente(long paciente) {
        this.paciente = paciente;
    }

    public long getMedico() {
        return medico;
    }

    public void setMedico(long medico) {
        this.medico = medico;
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
        sb.append("Consulta{");
        sb.append("id=").append(id);
        sb.append(", estado=").append(estado);
        sb.append(", diaHorario=").append(diaHorario.plusDays(1));
        sb.append(", valor=").append(valor);
        sb.append(", paciente=").append(paciente);
        sb.append(", medico=").append(medico);
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
        hash = 17 * hash + Objects.hashCode(this.estado);
        hash = 17 * hash + Objects.hashCode(this.diaHorario);
        hash = 17 * hash + Objects.hashCode(this.paciente);
        hash = 17 * hash + Objects.hashCode(this.medico);
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
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.diaHorario, other.diaHorario)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        return Objects.equals(this.unidade, other.unidade);
    }

}
