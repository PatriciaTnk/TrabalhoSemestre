/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDateTime;

public class Utilitario {

    public static LocalDateTime dataCriacao = LocalDateTime.of(2023, 1, 10, 12, 00);

    public static LocalDateTime getDiaSistema() {
        return LocalDateTime.of(2023, 4, 10, 12, 00);
    }

    public static boolean confereDiaHora(LocalDateTime diaAgendado, LocalDateTime diaSistema) {
        return diaAgendado == diaSistema;
    }

    public static void incremetarDias(int qtdDias) {
        for (int i = 1; i <= qtdDias; ++i) {
            getDiaSistema().plusDays(qtdDias);
            //adicionar aqui a funcao do financeiro
        }
    }

}
