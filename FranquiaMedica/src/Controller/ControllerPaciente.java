/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import franquiamedica.Utilitario;

public class ControllerPaciente {

    public void mostraRelatorioConsultas(Controller controller) {
        for (int i = 0; i < controller.getC().consultas.length; ++i) {
            if (controller.getC().consultas[i] != null
                    && controller.getC().consultas[i].getPaciente().getId() == Utilitario.getPessoaLogada().getId()) {
                System.out.println("\nHistorico de consultas");
                System.out.println("\n" + controller.getC().consultas[i]);
                //^^^^ -> ate aqui eu mostrei toodas as consultas que o paciente fez ou marcou
                
                //vvvv -> aqui vou mostrar as informacoes de consulta
                if (controller.getC().consultas[i].getEstado().equalsIgnoreCase("realizada")){
                    for (int j = 0; j < controller.getIc().infoCon.length; ++j){
                        //como ainda estou dentro do if que validou a pessoa logada com a consulta nao preciso validar de novo aqui
                        if(controller.getIc().infoCon[j] != null
                                && controller.getIc().infoCon[j].getConsulta().getId() == controller.getC().consultas[i].getId()){
                            System.out.println("\nHistorico de Informacao de consultas");
                            System.out.println("\n" + controller.getIc().infoCon[j]);
                        }
                    }
                    
                }
            }
        }
    }

    public void mostraRelatorioProcedimento(Controller controller) {
        for (int i = 0; i < controller.getProc().proceds.length; ++i) {
            if (controller.getProc().proceds[i] != null
                    && controller.getProc().proceds[i].getConsulta().getPaciente().getId() == Utilitario.getPessoaLogada().getId()) {
                System.out.println("\nHistorico de procedimentos");
                System.out.println("\n" + controller.getProc().proceds[i]);
            }
        }
    }

}
