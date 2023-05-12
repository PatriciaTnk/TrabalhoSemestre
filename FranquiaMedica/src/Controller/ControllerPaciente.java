/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.GUI;
import franquiamedica.ConsultaDAO;
import franquiamedica.FranquiaDAO;
import franquiamedica.InfoConsultaDAO;
import franquiamedica.MatrizFranquiaDAO;
import franquiamedica.MedicoDAO;
import franquiamedica.Pessoa;
import franquiamedica.PessoaDAO;
import franquiamedica.ProcedimentoDAO;
import franquiamedica.Utilitario;
import java.util.Scanner;

public class ControllerPaciente {

    Scanner scanner = new Scanner(System.in);

    PessoaDAO p = new PessoaDAO();
    MedicoDAO m = new MedicoDAO(p);
    MatrizFranquiaDAO mf = new MatrizFranquiaDAO(p);
    FranquiaDAO f = new FranquiaDAO(p, mf);
    ConsultaDAO c = new ConsultaDAO(p, m, mf, f);
    InfoConsultaDAO ic = new InfoConsultaDAO(c);
    ProcedimentoDAO proc = new ProcedimentoDAO(c);

    public ControllerPaciente() {

        int opcaoUsuario = 0;
        long id;

        while (opcaoUsuario != 6) {
            {
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

                    case 2:
                        System.out.println("Relatorio de consultas: ");
                        for (int i = 0; i < c.consultas.length; ++i) {
                            if (c.consultas[i].getPaciente().getId() == Utilitario.getPessoaLogada().getId()) {
                                System.out.println("\n" + c.consultas[i]);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Relatorio de Procedimentos: ");
                        for (int i = 0; i < proc.proceds.length; ++i) {
                            if (proc.proceds[i].getConsulta().getPaciente().getId() == Utilitario.getPessoaLogada().getId()) {
                                System.out.println("\n" + proc.proceds[i]);
                            }
                        }
                        break;

                    case 4:
                        GUI voltar = new GUI();
                        break;
                }
            }
            System.out.println("\nSaí do menu");
        }
    }

    private int menuPaciente() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("\n\nPaciente\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Verificar Consultas");
        builderAdm.append("\n3 - Verificar Procedimentos");
        builderAdm.append("\n4 - Para voltar à tela inicial\n");
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

}
