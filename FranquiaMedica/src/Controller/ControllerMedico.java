/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.GUI;
import franquiamedica.Utilitario;
import java.util.Scanner;

public class ControllerMedico {

    Scanner scanner = new Scanner(System.in);

    public ControllerMedico(Controller controller) {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 6) {
            opcaoUsuario = this.menuMedico();
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;

                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    Utilitario.setPessoaLogada(null);
                    Utilitario.getTelaInicial();
                    return;
            }
        }

    }

    private int menuMedico() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Medico\n\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Verificar Consultas");
        builderAdm.append("\n3 - Verificar Procedimentos");
        builderAdm.append("\n4 - Relatorio Financeiro");
        builderAdm.append("\n6 - Para voltar à tela inicial\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
