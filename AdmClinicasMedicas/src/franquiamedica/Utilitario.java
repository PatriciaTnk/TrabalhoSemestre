/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import Controller.Controller;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Utilitario {

    //parte de calendario
    public static LocalDate dataCriacao = LocalDate.of(2023, 3, 10);

    public static LocalDate diaSistema = LocalDate.of(2023, 5, 12);

    public static LocalDate diaAntesIncremento = LocalDate.of(2023, 5, 12);
    
    public static Path arquivoRelatorio = Paths.get("src/files/Relatorio.txt");
    
    public static Path result = Paths.get("src/files/Tentativa.pdf");

    public static LocalDate setDiaSistema(String diaEscolhido) {
        return Utilitario.diaSistema = LocalDate.parse(diaEscolhido);
    }

    public static boolean confereDiaHora(LocalDate diaAgendado, LocalDate diaSistema) {
        return diaAgendado == diaSistema;
    }

    //parte de login
    private static Pessoa pessoaLogando = null;

    public static Pessoa getPessoaLogada() {
        return pessoaLogando;
    }

    public static Pessoa setPessoaLogada(Pessoa pessoaConfirmada) {
        return Utilitario.pessoaLogando = pessoaConfirmada;
    }
    
}
