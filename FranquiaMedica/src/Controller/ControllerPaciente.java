/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import franquiamedica.Utilitario;
import java.util.Scanner;

public class ControllerPaciente {

    Scanner scanner = new Scanner(System.in);

    public ControllerPaciente(Controller controller) {

        int opcaoUsuario = 0;

        while (opcaoUsuario != 6) {
            opcaoUsuario = this.menuPaciente();
            switch (opcaoUsuario) {
                default:
                    System.out.println("\nOpcao Nao encontrada");
                    break;

                case 1:
                    int alterarDado = this.pacienteAlterarDados();

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
                    System.out.println("\nRelatorio de consultas: ");
                    if (controller.validaGeral("CONSULTA")) {
                        mostraRelatorioConsultas(controller);
                    } else {
                        System.out.println("\nPaciente nao tem historico de consultas");
                    }
                    break;

                case 3:
                    System.out.println("\nRelatorio de Procedimentos: ");
                    //validando se tem historico ou nao
                    if (controller.validaGeral("Procedimento")) {
                        //se tiver entao vai mostrar
                        mostraRelatorioProcedimento(controller);
                    } else {
                        System.out.println("\nPaciente nao tem historico de procedimentos");
                    }
                    break;

                case 4:
                    Utilitario.setPessoaLogada(null);
                    Utilitario.getTelaInicial();
                    return;

            }
        }
        System.out.println("\nSaí do menu");

    }

    private int menuPaciente() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("\n\nPaciente\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Verificar Consultas");
        builderAdm.append("\n3 - Verificar Procedimentos");
        builderAdm.append("\n4 - Deslogar\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    private int pacienteAlterarDados() {

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

    public void mostraRelatorioConsultas(Controller controller) {
        for (int i = 0; i < controller.getC().consultas.length; ++i) {
            if (controller.getC().consultas[i] != null
                    && controller.getC().consultas[i].getPaciente().getId() == Utilitario.getPessoaLogada().getId()) {
                System.out.println("\nHistorico de consultas");
                System.out.println("\n" + controller.getC().consultas[i]);
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
