/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

//Criar o menu do login e os menus de cada um dos 5 perfis
//Quando for mexer com os menus lembrar de trocar o equals hash code de TODAS as CLASSES sem DAO
import franquiamedica.Consulta;
import franquiamedica.ConsultaDAO;
import franquiamedica.ConsultaDAO;
import franquiamedica.Franquia;
import franquiamedica.FranquiaDAO;
import franquiamedica.MatrizFranquia;
import java.util.Scanner;
import franquiamedica.PessoaDAO;
import franquiamedica.Pessoa;
import franquiamedica.MedicoDAO;
import franquiamedica.Medico;
import franquiamedica.MatrizFranquiaDAO;
import java.math.BigDecimal;


public class Controller {

    Scanner scanner = new Scanner(System.in);
    PessoaDAO p = new PessoaDAO();
    MedicoDAO m = new MedicoDAO(p);
    MatrizFranquiaDAO mf = new MatrizFranquiaDAO(p);
    FranquiaDAO f = new FranquiaDAO(p,mf);
    ConsultaDAO c = new ConsultaDAO(p,m,mf,f);

    public Controller() {

        //tela de login, senha, cadastrar nova pessoa
        int opcaoUsuario = 0;
        String recebeString;
        String string2;
        String string3;
        long id;
        while (opcaoUsuario != 30) {
            opcaoUsuario = this.opcaoUsuarioTelaInicial();
            switch (opcaoUsuario) {
                case 0:
                    System.out.println("\nmostrar todos");
                    p.mostraTodos();
                    System.out.println("\n\n\n");
                    m.mostraTodos();
                    System.out.println("\n\n\n");
                    mf.mostraTodos();
                    System.out.println("\n\n\n");
                    f.mostraTodos();
                    System.out.println("\n\n\n");
                    c.mostraTodos();
                    System.out.println("\n\n\n");
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
                    System.out.println("\nQual o id da pessoa?");
                    string2 = scanner.nextLine();
                    id = Long.parseLong(string2);
                    if (p.verificaRegistro(recebeString,id) != null) {
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
                                        System.out.println("\nQual o id do medico?");
                    string2 = scanner.nextLine();
                    id = Long.parseLong(string2);
                    if (m.verificaRegistro(recebeString,id) != null) {
                        System.out.println("\nQual o novo nome ?");
                        m.alterarNome(recebeString, scanner.nextLine());
                        System.out.println("Alteração realizada");
                    } else {
                        System.out.println("Medico não encontrado");
                    }
                    //m.mostraTodos();
                    break;

                case 4://testado adicionar Pessoa
                    Pessoa novaP = new Pessoa();
                    System.out.println("\nadicionar pessoa");
                    System.out.println("\nQual o nome ?");
                    recebeString = scanner.nextLine();

                    

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

                case 5://testado adicionar Medico precisa de uma pessoa cadastrada                    
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

                case 6://testado alterar matriz - Nome precisa ser idêntico
                    System.out.println("\nalterar dados da Matriz");
                    mf.mostraTodos();
                    System.out.println("\nQual o nome ?");
                    recebeString = scanner.nextLine();
                    if (mf.verificaRegistro(recebeString) == null) {
                        System.out.println("Matriz não cadastrada");
                    } else {
                        System.out.println("\nQual o novo nome ?");
                        mf.alterarNome(recebeString, scanner.nextLine());
                        System.out.println("Alteração realizada");
                    }
                    mf.mostraTodos();
                    break;

                case 7://adicionar matriz
                    System.out.println("\nadicionar matriz");
                    //mf.mostraTodos();
                    System.out.println("\nInforme o nome do dono, para realizar uma busca em nosso cadastro: ");
                    recebeString = scanner.nextLine();

                    if (p.verificaRegistro(recebeString) == null) {
                        System.out.println("\nDono nao esta cadastrado, por gentileza realizar cadastro.");
                    } else {
                        MatrizFranquia novaMF = new MatrizFranquia(p.verificaRegistro(recebeString));
                        System.out.println("\nQual o nome da matriz?");
                        novaMF.setNome(scanner.nextLine());

                        System.out.println("\nQual o cnpj ?");
                        novaMF.setCnpj(scanner.nextLine());

                        System.out.println("\nQual a cidade ?");
                        novaMF.setCidade(scanner.nextLine());

                        System.out.println("\nQual o endereço ?");
                        novaMF.setEndereco(scanner.nextLine());

                        mf.adiciona(novaMF);
                        System.out.println("Novo registro de matriz realizado com sucesso");
                    }
                    mf.mostraTodos();
                    break;

                case 8://alterar franquia
                    System.out.println("\nalterar dados da Franquia");
                    f.mostraTodos();
                    System.out.println("\nQual o nome da Franquia?");
                    recebeString = scanner.nextLine();                            
                    
                    if (f.verificaRegistro(recebeString) == null) {
                        System.out.println("Franquia não encontrada");
                    } else {
                        System.out.println("\nQual o login?");
                        recebeString = scanner.nextLine();
                        
                        System.out.println("\nQual o novo login?");
                        string2 = scanner.nextLine();                        
                        
                        f.alterarLogin(recebeString, string2);
                        System.out.println("Alteração realizada");
                    }
                    mf.mostraTodos();
                    break;

                case 9://adicionar franquia
                    System.out.println("\nadicionar franquia");
                    //p.mostraTodos();
                    System.out.println("\nInforme o nome do responsável cadastrado: ");
                    recebeString = scanner.nextLine();

                    if (p.verificaRegistro(recebeString) == null) {
                        System.out.println("\nResponsavel nao esta cadastrado, por gentileza realizar cadastro.");
                    } else {
                        System.out.println("\nInforme o nome da matriz, para realizar uma busca em nosso cadastro: ");
                        string2 = scanner.nextLine();
                        
                        if (mf.verificaRegistro(string2) == null){
                        System.out.println("\nMatriz nao cadastrada, por gentileza realizar cadastro.");
                        } else {                    
                        Franquia novaF = new Franquia(p.verificaRegistro(recebeString), mf.verificaRegistro(string2));
                        System.out.println("\nQual o endereço ?");
                        novaF.setEndereco(scanner.nextLine());

                        System.out.println("\nQual a cidade?");
                        novaF.setCidade(scanner.nextLine());
                        
                        f.adiciona(novaF);
                        System.out.println("Novo registro de franquia realizado com sucesso");
                        }
                    }
                    f.mostraTodos();
                    
                    break;

                case 10:// testado - altera consulta, só pode cancelar ou alterar o valor
                    //para trocar o dia, o medico ou unidade é preciso cancelar a consulta agendada e agendar Nova consulta
                    c.mostraTodos();
                    System.out.println("\nalterar o valor da consulta");
                    
                    System.out.println("\nInforme o nome do paciente: ");
                    recebeString = scanner.nextLine();
                    
                    System.out.println("\nInforme o nome do medico: ");
                    string2 = scanner.nextLine();
                    
                    System.out.println("\nInforme o novo valor: ");
                    string3 = scanner.nextLine();                    
                    BigDecimal novoValor = new BigDecimal(string3);
                    
                    if (c.verificaRegistro(recebeString, string2) != null){
                        c.alteraValor(c.verificaRegistro(recebeString, string2).getId(), novoValor);
                        System.out.println("Valor alterado com sucesso");
                    }
                    
                    c.mostraTodos();
                    
                    break;

                case 11://adiciona consulta
                    c.mostraTodos();
                    System.out.println("\nadicionar consulta");
                    
                    System.out.println("\nInforme o nome do paciente: ");
                    recebeString = scanner.nextLine();
                    
                    System.out.println("\nInforme o nome do medico: ");
                    string2 = scanner.nextLine();  
                    
                    if (c.verificaRegistro(recebeString, string2) == null){      
                        f.mostraTodos();
                        System.out.println("Onde gostaria de realizar a consulta?");
                        string3 = scanner.nextLine();
                        
                    }
                    
                    c.mostraTodos();
                    break;

                case 12://altera procedimento
                    break;

                case 13://adiciona procedimento
                    break;

                case 14://mostra crud financeiro medico       
                    break;

                case 15:
                    break;

                case 16:
                    break;

                case 17:
                    break;

                case 18:
                    break;

                case 19:
                    break;

                case 20:
                    break;
            }
        }
        System.out.println("Saí do menu");
    }

    public static void main(String[] args) {
        new UiMenus();
    }

    //1ª tela - Início || Com - Teste - Ainda para testar, o resto testado
    public int opcaoUsuarioTelaInicial() {

        StringBuilder builderInicio = new StringBuilder("");

        builderInicio.append("TELA INICIAL\n\n");
        builderInicio.append("\n0 - Mostrar pessoas");
        builderInicio.append("\n1 - Mostrar medicos");
        builderInicio.append("\n2 - alterar o nome pessoa");
        builderInicio.append("\n3 - alterar o nome medico");
        builderInicio.append("\n4 - adicionar pessoa");
        builderInicio.append("\n5 - adicionar medico");
        builderInicio.append("\n6 - alterar dados Matriz");
        builderInicio.append("\n7 - Teste - adicionar Matriz");
        builderInicio.append("\n8 - Teste - alterar dados Franquia");
        builderInicio.append("\n9 - Teste - adicionar Franquia");
        builderInicio.append("\n10 - Teste - alterar consulta");
        builderInicio.append("\n11 - Teste - adicionar consulta");
        builderInicio.append("\n12 - Teste - alterar procedimento");
        builderInicio.append("\n13 - Teste - adicionar procedimento");
        builderInicio.append("\n14 - Teste - mostrar financeiro medico");
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
