/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import franquiamedica.Utilitario;
import java.util.Scanner;

public class ControllerResponsavelFranquia {

    Scanner scanner = new Scanner(System.in);

    public ControllerResponsavelFranquia(Controller controller) {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 6) {
            opcaoUsuario = this.menuResponsavel();
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

                    break;

                case 8:
                    break;

                case 9:
                    break;

                case 10:
                    break;

                case 11:
                    break;

                case 12:
                    Utilitario.setPessoaLogada(null);
                    return;
            }
        }

    }

    private int menuResponsavel() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Responsavel pela Franquia\n\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Alterar tipo de um usuario");
        builderAdm.append("\n3 - Relatorio de Informacoes de consulta");
        builderAdm.append("\n4 - Relatorio de Consultas");
        builderAdm.append("\n5 - Relatorio de Procedimentos");
        builderAdm.append("\n6 - Relatorio de Financeiro do Medico");
        builderAdm.append("\n7 - Relatorio de Financeiro da Franquia");
        builderAdm.append("\n8 - Marcar consulta");
        builderAdm.append("\n9 - Marcar Procedimento");
        builderAdm.append("\n10 - Realizar pagamento para a Matriz");
        builderAdm.append("\n11 - Extra");
        builderAdm.append("\n12 - Para voltar à tela inicial\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    private int pessoaAlterarDados() {

        StringBuilder builderAdm = new StringBuilder("");

        System.out.println("\nGostaria de alterar qual informação?\n");
        builderAdm.append("\n1 - Alterar nome");
        builderAdm.append("\n2 - Alterar endereco");
        builderAdm.append("\n3 - Alterar CPF");
        builderAdm.append("\n4 - Alterar telefone");
        builderAdm.append("\n5 - Alterar Login");
        builderAdm.append("\n6 - Alterar Senha\n");
        builderAdm.append("\n7 - Voltar\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
