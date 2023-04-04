/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Consulta {

    //
    private long id;
    private String estado;
    private LocalDateTime diaHorario;
    private BigDecimal valor;
    private Pessoa paciente;
    private Medico medico;
    private Franquia unidade;

    private LocalDateTime dataCriacao;
    private LocalDateTime datamodificacao;

    //so inicia medico depois de escolher a Pessoa
    public Consulta(Pessoa paciente, Medico medico, Franquia franquia) {
        this.id = paciente.getId();
        this.paciente = paciente;
        this.medico = medico;
        this.unidade = franquia;
        this.estado = "Agendado";
        this.dataCriacao = LocalDateTime.now();
        this.datamodificacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getDiaHorario() {
        return diaHorario;
    }
    
    //"05/05/2023", "10:30"
    public void setDiaHorario(String dia, String horario) {
        //DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate day = LocalDate.parse(dia);
                        
        //DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("mm:HH");
        LocalTime hour = LocalTime.parse(horario);
               
        String str = dia.concat(horario);
        this.diaHorario = LocalDateTime.of(day,hour);
    }
    
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    public Pessoa getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Consulta{");
        sb.append("id=").append(id);
        sb.append(", estado=").append(estado);
        sb.append(", diaHorario=").append(diaHorario);
        sb.append(", valor=").append(valor);
        sb.append(", paciente=").append(paciente);
        sb.append(", medico=").append(medico);
        sb.append(", unidade=").append(unidade);
        sb.append(", dataCriacao=").append(dataCriacao);
        sb.append(", datamodificacao=").append(datamodificacao);
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
