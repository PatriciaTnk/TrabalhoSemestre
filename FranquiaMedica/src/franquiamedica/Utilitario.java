/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;

public class Utilitario {
    //parte de calendario
    public static LocalDateTime dataCriacao = LocalDateTime.of(2023, 1, 10, 12, 00);
    
    public static LocalDateTime diaSistema = LocalDateTime.of(2023, 4, 10, 12, 00);
   
    public static LocalDateTime setDiaSistema (String diaEscolhido){
        LocalDate day = LocalDate.parse(diaEscolhido);

        LocalTime hour = LocalTime.parse("18:00");
        return Utilitario.diaSistema = LocalDateTime.of(day,hour);
    }

    public static boolean confereDiaHora(LocalDateTime diaAgendado, LocalDateTime diaSistema) {
        return diaAgendado == diaSistema;
    }

    public static void incremetarDias(int qtdDias) {
        for (int i = 1; i <= qtdDias; ++i) {
            diaSistema = diaSistema.plusDays(1);
            //adicionar aqui a funcao do financeiro
        }
        System.out.println("Novo dia do sistema" + diaSistema);
    }

    public static void incrementaMes(int qtdMeses) {
        diaSistema.plusMonths(qtdMeses);
        int[] mesesUm = {1, 3, 5, 7, 8, 10, 12};
        int[] mesesZero = {4, 6, 9, 11};

        if ((qtdMeses + 12) % 12 == 2 && !Year.isLeap(diaSistema.getYear())) {
            incremetarDias(qtdMeses * 28);
        } else if ((qtdMeses + 12) % 12 == 2 && Year.isLeap(diaSistema.getYear())) {
            incremetarDias(qtdMeses * 29);
        } else {
            if (mesesZero.equals(qtdMeses)) {
                for (int i = 0; i < mesesZero.length; ++i) {
                    if ((qtdMeses + 12) % 12 == mesesZero[i]) {
                        incremetarDias(qtdMeses * 30);
                    }
                }
            } else {
                for (int i = 0; i < mesesUm.length; ++i) {
                    if ((qtdMeses + 12) % 12 == mesesUm[i]) {
                        incremetarDias(qtdMeses * 31);
                    }
                }
            }
        }
        System.out.println("Novo dia do sistema" + diaSistema);
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
