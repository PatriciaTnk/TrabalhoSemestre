/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Uifm;

//Criar o menu do login e os menus de cada um dos 5 perfis
//Quando for mexer com os menus lembrar de trocar o equals hash code de TODAS as CLASSES sem DAO
import franquiamedica.Medico;
import java.util.Scanner;
import franquiamedica.PessoaDAO;
import franquiamedica.MedicoDAO;
import franquiamedica.Pessoa;

public class UiMenus {

    Scanner scanner = new Scanner(System.in);
    PessoaDAO p = new PessoaDAO();
    MedicoDAO m = new MedicoDAO();

    public UiMenus() {

        //tela de login, senha, cadastrar nova pessoa
        int opcaoUsuario = 4;
        String recebeString;
        while (opcaoUsuario != 9) {
            opcaoUsuario = this.opcaoUsuarioTelaInicial();
            switch (opcaoUsuario) {
                case 0:
                    System.out.println("\nmostrar pessoas");
                    p.mostraTodos();
                    break;

                case 1:
                    System.out.println("\nmostrar medicos");
                    m.mostraTodos();
                    break;

                case 2: //testado alterado o nome
                    System.out.println("\nalterar pessoa nome");
                    //p.mostraTodos();
                    System.out.println("\nQual o nome ?");
                    recebeString = scanner.nextLine();
                    if (p.verificaRegistro(recebeString) != null) {
                        System.out.println("\nQual o novo nome ?");
                        p.alterarNome(recebeString, scanner.nextLine());
                        System.out.println("Alteração realizada");
                    } else {
                        System.out.println("Pessoa não encontrada");
                    }
                    //p.mostraTodos();
                    break;

                case 3://testado alterado o nome médico
                    System.out.println("\nalterar medico nome");
                    //m.mostraTodos();
                    System.out.println("\nQual o nome ?");
                    recebeString = scanner.nextLine();
                    if (m.verificaRegistro(recebeString) != null) {
                        System.out.println("\nQual o novo nome ?");
                        m.alterarNome(recebeString, scanner.nextLine());
                        System.out.println("Alteração realizada");
                    } else {
                        System.out.println("Medico não encontrado");
                    }
                    //m.mostraTodos();
                    break;

                case 4://adicionar Pessoa
                    Pessoa novaP = new Pessoa();
                    System.out.println("\nadicionar pessoa");
                    System.out.println("\nQual o nome ?");
                    recebeString = scanner.nextLine();

                    if (p.verificaRegistro(recebeString) != null) {
                        System.out.println("\nJá temos um cadastro");
                    } else {

                        novaP.setNome(recebeString);

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
                    }

                    p.mostraTodos();
                    System.out.println("Seu cadastro foi realizado com sucesso");

                    break;

                case 5://adicionar Medico precisa de uma pessoa cadastrada                    
                    System.out.println("\nadicionar medico");
                    //p.mostraTodos();
                    System.out.println("\nInforme o nome da pessoa, para realizar uma busca em nosso cadastro: ");
                    recebeString = scanner.nextLine();

                    if (p.verificaRegistro(recebeString) == null) {
                        System.out.println("\nNao esta cadastrado, por gentileza realizar cadastro.");
                    } else {
                        Medico novoM = new Medico(p.verificaRegistro(recebeString));
                        System.out.println("\nQual a especialidade ?");
                        novoM.setEspecialidade(scanner.nextLine());

                        System.out.println("\nQual o CRM ?");
                        novoM.setCrm(scanner.nextLine());
                        m.adiciona(novoM);
                        System.out.println("Novo registro de medico com sucesso");
                    }
                    //m.mostraTodos();

                    break;

                case 6://alterar franquia
                    break;
                case 7://adicionar franquia
                    break;
                case 8:
                    break;
            }
        }
        System.out.println("Saí do menu");
    }

    public static void main(String[] args) {
        new UiMenus();
    }

    //1ª tela - Início
    private int opcaoUsuarioTelaInicial() {

        StringBuilder builderInicio = new StringBuilder("");

        builderInicio.append("TELA INICIAL\n\n");
        builderInicio.append("\n0 - Mostrar pessoas");
        builderInicio.append("\n1 - Mostrar medicos");
        builderInicio.append("\n2 - Teste - alterar o nome pessoa");
        builderInicio.append("\n3 - Teste - alterar o nome medico");
        builderInicio.append("\n4 - Teste - adicionar pessoa");
        builderInicio.append("\n5 - Teste - adicionar medico");
        builderInicio.append("\nQual sua opção ? R: ");

        System.out.print(builderInicio.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    //2ª tela - Login
    private long opcaoUsuarioTelaLogin() {

        StringBuilder builderLogin = new StringBuilder("");

        builderLogin.append("TELA LOGIN\n\n");
        builderLogin.append("\nInsira o login: ");
        builderLogin.append("\nInsira a senha: ");
        builderLogin.append("\nQual seu perfil");

        scanner.nextLine();

        Long id = Long.parseLong(scanner.nextLine());

        System.out.print(builderLogin.toString());

        return Long.parseLong(scanner.nextLine());
    }

    private int recebeOpcaoUsuario() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("ADMNISTRADOR\n\n");
        builderAdm.append("\n0 - Para inserir pessoa");
        builderAdm.append("\n1 - Listar pessoas");
        builderAdm.append("\n2 - Excluir um pessoa");
        builderAdm.append("\n3 - Alterar um pessoa");
        builderAdm.append("\n4 - Buscar salários > 5000");
        builderAdm.append("\n9 - Para sair do programa\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
