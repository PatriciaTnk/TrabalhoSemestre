/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import franquiamedica.Utilitario;
import java.util.Scanner;

public class ControllerDonoDeMatriz {

    Scanner scanner = new Scanner(System.in);

    public ControllerDonoDeMatriz(Controller controller) {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 6) {
            opcaoUsuario = this.menuDono();
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;

                case 1:

                    int alterarDado = this.pessoaAlterarDados();

                    switch (alterarDado) {
                        case 1:
                            System.out.println("\nQual o novo nome:");
                            Utilitario.getPessoaLogada().setNome(scanner.nextLine());
                            break;

                        case 2:
                            System.out.println("\nQual o novo endereço ?");
                            Utilitario.getPessoaLogada().setEndereco(scanner.nextLine());
                            break;

                        case 3:
                            System.out.println("\nQual o novo CPF ?");
                            Utilitario.getPessoaLogada().setCpf(scanner.nextLine());
                            break;

                        case 4:
                            System.out.println("\nQual o novo telefone ?");
                            Utilitario.getPessoaLogada().setTelefone(scanner.nextLine());
                            break;

                        case 5:
                            System.out.println("\nQual o novo Login ?");
                            Utilitario.getPessoaLogada().setLogin(scanner.nextLine());
                            break;

                        case 6:
                            System.out.println("\nQual a nova Senha ?");
                            Utilitario.getPessoaLogada().setSenha(scanner.nextLine());
                            break;
                    }
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

    private int menuDono() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Dono de Matriz de Franquia\n\n");
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
