/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import franquiamedica.ConsultaDAO;
import franquiamedica.FranquiaDAO;
import franquiamedica.InfoConsultaDAO;
import franquiamedica.MatrizFranquiaDAO;
import franquiamedica.MedicoDAO;
import franquiamedica.Pessoa;
import franquiamedica.PessoaDAO;
import franquiamedica.ProcedimentoDAO;
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
        String recebeString;

        long id;

        while (opcaoUsuario != 6) {
            opcaoUsuario = this.recebeOpcaoUsuario();
            switch (opcaoUsuario) {
                
                default:
                    System.out.println("\nOpcao nao encontrada");
                    break;

                case 0:
                    System.out.println("\nmostrar todos");
                    p.mostraTodos();
                    System.out.println("\n\n\n");
                    break;

                case 1:
                    break;

                case 2:
                    System.out.println("\nalterar pessoa nome");
                    System.out.println("\nQual o id da pessoa?");
                    id = Long.parseLong(scanner.nextLine());

                    if (p.verificaRegistro(id) != null) {
                        System.out.println("\nQual o novo nome ?");
                        p.alterarNome(id, scanner.nextLine());
                        System.out.println("Alteração realizada");
                    } else {
                        System.out.println("Pessoa não encontrada");
                    }
                    break;

                case 3:
                    break;

                case 4://testado adicionar Pessoa
                    Pessoa novaP = new Pessoa();
                    System.out.println("\nadicionar pessoa");

                    System.out.println("\nQual o nome ?");
                    novaP.setNome(scanner.nextLine());

                    System.out.println("\nQual o endereço ?");
                    novaP.setEndereco(scanner.nextLine());

                    System.out.println("\nQual o CPF ?");
                    novaP.setCpf(scanner.nextLine());

                    System.out.println("\nQual o telefone ?");
                    novaP.setTelefone(scanner.nextLine());

                    System.out.println("\nQual o Login ?");
                    novaP.setLogin(scanner.nextLine());

                    System.out.println("\nQual a Senha ?");
                    novaP.setSenha(scanner.nextLine());
                    p.adiciona(novaP);

                    p.mostraTodos();
                    System.out.println("Seu cadastro foi realizado com sucesso");

                    break;

                case 5:
                    break;

                case 6:
                    return;
            }
        }
        System.out.println("Saí do menu");
    }

    public static void main(String[] args) {
        new ControllerPaciente();
    }

    private int recebeOpcaoUsuario() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Paciente\n\n");
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
