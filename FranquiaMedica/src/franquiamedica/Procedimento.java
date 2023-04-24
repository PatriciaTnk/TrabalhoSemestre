/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    //so inicia medico depois de escolher a Pessoa
    public Procedimento(Consulta c) {
        this.id = Procedimento.serial++;
        this.consulta = c;
        c.setValor(BigDecimal.valueOf(0));
        this.estado = "Agendado";
        this.dataCriacao = LocalDateTime.now();
        this.datamodificacao = LocalDateTime.now();
    }
    
}
