/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import franquiamedica.Utilitario;

public class ControllerMedico {

    public void mostraRelatorioConsultas(Controller controller) {
        for (int i = 0; i < controller.getC().consultas.length; ++i) {
            if (controller.getC().consultas[i] != null
                    && controller.getC().consultas[i].getMedico().getId() == Utilitario.getPessoaLogada().getId()) {
                System.out.println("\nRelatorio de consultas");
                System.out.println("\n" + controller.getC().consultas[i]);
            }
        }
    }

    public void mostraConsultasRealizadas(Controller controller) {
        System.out.println("\nRelatorio de consultas");
        for (int i = 0; i < controller.getC().consultas.length; ++i) {
            if (controller.getC().consultas[i] != null
                    && controller.getC().consultas[i].getMedico().getId() == Utilitario.getPessoaLogada().getId() //coloquei por estado, pq se fosse por data as canceladas tbm iriam aparecer
                    //&& controller.getC().consultas[i].getEstado().equalsIgnoreCase("realizada")
                    ) {

                System.out.println("\n" + controller.getC().consultas[i]);
            }
        }
    }

    public void mostraRelatorioProcedimento(Controller controller) {
        System.out.println("\nRelatorio de procedimentos");
        for (int i = 0; i < controller.getProc().proceds.length; ++i) {
            if (controller.getProc().proceds[i] != null
                    && controller.getProc().proceds[i].getConsulta().getMedico().getId() == Utilitario.getPessoaLogada().getId()) {

                System.out.println("\n" + controller.getProc().proceds[i]);
            }
        }
    }

    public void mostraRelatorioFinanceiroMedico(Controller controller) {
        System.out.println("\nRelatorio Financeiro");
        for (int i = 0; i < controller.getFinMed().finMedico.length; ++i) {
            if (controller.getFinMed().finMedico[i] != null
                    && controller.getFinMed().finMedico[i].getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {

                System.out.println("\n" + controller.getFinMed().finMedico[i]);
            }
        }
    }

}
