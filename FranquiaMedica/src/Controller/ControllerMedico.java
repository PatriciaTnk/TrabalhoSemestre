/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.GUI;
import java.util.Scanner;

public class ControllerMedico {

    Scanner scanner = new Scanner(System.in);

    public ControllerMedico() {
        int opcaoUsuario = 0;
        long id;

        while (opcaoUsuario != 6) {
            try {
                opcaoUsuario = this.menuMedico();
            } catch (NumberFormatException e) {
                System.out.println("NumberFormat Exception: invalid input string");
            } finally {
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
                        GUI voltar = new GUI();
                        break;

                }
            }
        }
    }

    private int menuMedico() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Medico\n\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Verificar Consultas");
        builderAdm.append("\n3 - Registro de Consultas");
        builderAdm.append("\n4 - Verificar Procedimentos");
        builderAdm.append("\n5 - Registro de Procedimentos");
        builderAdm.append("\n6 - Para voltar à tela inicial\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
