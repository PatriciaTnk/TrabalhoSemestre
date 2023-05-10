/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import franquiamedica.Utilitario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Procedimento {

    private long id;
    private static long serial; //fica com o valor padrão
    private String nome;
    private Consulta consulta;
    private LocalDateTime diaHorario;
    private String estado;
    private BigDecimal valorPro;
    private String laudo;

    private LocalDateTime dataCriacao;
    private LocalDateTime datamodificacao;
    private boolean visible;

    //so inicia medico depois de escolher a Pessoa
    public Procedimento(Consulta c) {
        this.id = Procedimento.serial++;
        this.visible = true;
        this.consulta = c;
        c.setValor(BigDecimal.valueOf(0));
        this.estado = "Agendado";
        this.laudo = "A ser completado";
        this.dataCriacao = Utilitario.dataCriacao;
        this.datamodificacao = Utilitario.dataCriacao;
    }

    //O procedimento pode ser solicitado pelo paciente, sem realizar consulta
    public Procedimento() {
        this.id = Procedimento.serial++;
        this.visible = true;
        this.consulta = null;
        this.estado = "Agendado";
        this.laudo = "A ser completado";
        this.dataCriacao = Utilitario.dataCriacao;
        this.datamodificacao = Utilitario.dataCriacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDiaHorario() {
        return diaHorario;
    }

    public boolean setDiaHorario(String dia, String horario) {
        DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate day = LocalDate.parse(dia, formatterDia);

        LocalTime hour = LocalTime.parse(horario);

        LocalDateTime hoje = LocalDateTime.now();
        this.diaHorario = LocalDateTime.of(day, hour);
        if (this.diaHorario.isBefore(hoje)) {
            return false;
        }
        return true;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getValorPro() {
        return valorPro;
    }

    public void setValorPro(BigDecimal valorPro) {
        this.valorPro = valorPro;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public LocalDateTime getDatamodificacao() {
        return datamodificacao;
    }

    public void setDatamodificacao(LocalDateTime datamodificacao) {
        this.datamodificacao = datamodificacao;
    }

    public long getId() {
        return id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
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
        sb.append("Procedimento{");
        sb.append("id=").append(id);
        sb.append(", nome=").append(nome);
        sb.append(", consulta=").append(consulta);
        sb.append(", diaHorario=").append(diaHorario);
        sb.append(", estado=").append(estado);
        sb.append(", valorPro=").append(valorPro);
        sb.append(", laudo=").append(laudo);
        sb.append(", dataCriacao=").append(dataCriacao);
        sb.append(", datamodificacao=").append(datamodificacao);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 11 * hash + Objects.hashCode(this.nome);
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
        final Procedimento other = (Procedimento) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }

}
