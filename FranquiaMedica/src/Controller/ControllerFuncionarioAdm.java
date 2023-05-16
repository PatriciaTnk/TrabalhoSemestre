/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import franquiamedica.Consulta;
import franquiamedica.Franquia;
import franquiamedica.Medico;
import franquiamedica.Pessoa;
import franquiamedica.Procedimento;

public class ControllerFuncionarioAdm {

    public void mostraRelatorioConsultas(Controller controller) {
        for (int i = 0; i < controller.getC().consultas.length; ++i) {
            if (controller.getC().consultas[i] != null
                    && controller.getC().consultas[i].getVisible()) {
                System.out.println("\nRelatorio de consultas");
                System.out.println("\n" + controller.getC().consultas[i]);
            }
        }
    }

    public void mostraInformacaoConsulta(Controller controller) {
        for (int i = 0; i < controller.getIc().infoCon.length; ++i) {
            if (controller.getIc().infoCon[i] != null
                    && controller.getIc().infoCon[i].getVisible()) {
                System.out.println("\nDescricao de consultas");
                System.out.println("\n" + controller.getC().consultas[i]);
            }
        }
    }

    public void mostraRelatorioProcedimento(Controller controller) {
        for (int i = 0; i < controller.getProc().proceds.length; ++i) {
            if (controller.getProc().proceds[i] != null
                    && controller.getProc().proceds[i].getVisible()) {
                System.out.println("\nRelatorio de procedimentos");
                System.out.println("\n" + controller.getProc().proceds[i] + "\n" + controller.getProc().proceds[i].getLaudo());
            }
        }
    }

    public boolean marcarConsulta(Controller controller, String dia, String hora, String valor, Pessoa paciente, Medico medico, Franquia franquia) {
        if (controller.validaDiaHora(dia, hora)!= null
                &&controller.validaBigDecimal(valor) != null){
            Consulta nova = new Consulta(paciente, medico, franquia);
            controller.getC().adiciona(nova);
            return true;
        }
        return false;
    }

    public boolean marcarProcedimento(Controller controller, String dia, String hora, String valor, Consulta consultaP) {
        if (controller.validaDiaHora(dia, hora)!= null
                &&controller.validaBigDecimal(valor) != null){
            Procedimento novo = new Procedimento(consultaP);
            controller.getProc().adiciona(novo);
            return true;
        }
        return false;
    }

}
