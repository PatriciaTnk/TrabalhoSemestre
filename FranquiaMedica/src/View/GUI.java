/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import franquiamedica.PessoaDAO;
import java.util.Scanner;
import Controller.Controller;
import franquiamedica.Pessoa;

public class GUI {

    Scanner scanner = new Scanner(System.in);
    Controller novo = new Controller();
    
    public GUI() {
        int opcaoUsuario = 0;
        String login;
        String senha;
        novo.primeiroCadastro();
        
        while (opcaoUsuario != 5) {
            try {
                opcaoUsuario = this.menuPadrao();
            } catch (NumberFormatException e) {
                System.out.println("NumberFormat Exception: invalid input string");
            } finally {
                switch (opcaoUsuario) {
                    default:
                        System.out.println("Opcao Nao encontrada");
                        break;

                    case 1:
                        System.out.println("\nLOGIN\n");
                        login = scanner.nextLine();

                        System.out.println("\nSENHA\n");
                        senha = scanner.nextLine();

                        novo.validaLogin(login, senha);
                        //como salvei a pessoa logada no utilitario agora posso usar o utilitario.getPessoaLogada();                        
                        break;

                    case 2:
                        Pessoa novaP = new Pessoa();
                        System.out.println("\nNovo Cadastro");

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

                        if (novo.novoCadastro(novaP)) {
                            System.out.println("Seu cadastro foi realizado com sucesso");
                        } else {
                            System.out.println("Cadastro não deu certo");
                        }
                        break;

                    case 3:
                        System.out.println("Programa encerrado");
                        return;

                }
            }
        }
    }

    public static void main(String[] args) {
        new GUI();
    }

    private int menuPadrao() {

        StringBuilder builderAdm = new StringBuilder("");
        builderAdm.append("Bem-Vindo\n\n");
        builderAdm.append("\n1 - Fazer Login");
        builderAdm.append("\n2 - Novo Cadastro");
        builderAdm.append("\n3 - Encerrar Programa");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
