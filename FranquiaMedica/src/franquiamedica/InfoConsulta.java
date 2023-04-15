/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class InfoConsulta {
    
    private long id;
    private static long serial; //fica com o valor padrão
    private String descricao;

    private Consulta consulta;

    private LocalDateTime dataCriacao;
    private LocalDateTime datamodificacao;

    //so inicia quando
    public InfoConsulta(Consulta origem) {
        this.id = InfoConsulta.serial++;
        this.descricao = "Esta acontecendo";
        this.consulta = origem;
        origem.setEstado("realizada");
        this.dataCriacao = LocalDateTime.now();
        this.datamodificacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        sb.append("InfoConsulta{");
        sb.append("id=").append(id);
        sb.append(", descricao=").append(descricao);
        sb.append(", consulta=").append(consulta);
        sb.append(", dataCriacao=").append(dataCriacao);
        sb.append(", datamodificacao=").append(datamodificacao);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.consulta);
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
        final InfoConsulta other = (InfoConsulta) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.consulta, other.consulta);
    }
    
    
    

}
