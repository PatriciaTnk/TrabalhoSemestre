/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

//Criar o menu do login e os menus de cada um dos 5 perfis
//Quando for mexer com os menus lembrar de trocar o equals hash code de TODAS as CLASSES sem DAO
import franquiamedica.Consulta;
import franquiamedica.ConsultaDAO;
import franquiamedica.FinanceiroAdmDAO;
import franquiamedica.FinanceiroMedicoDAO;
import franquiamedica.Franquia;
import franquiamedica.FranquiaDAO;
import franquiamedica.FuncionarioAdmDAO;
import franquiamedica.MatrizFranquia;
import franquiamedica.MatrizFranquiaDAO;
import franquiamedica.Pessoa;
import franquiamedica.PessoaDAO;
import franquiamedica.Medico;
import franquiamedica.MedicoDAO;
import franquiamedica.InfoConsultaDAO;
import franquiamedica.ProcedimentoDAO;
import java.util.Scanner;
import java.math.BigDecimal;

public class Controller {

    Scanner scanner = new Scanner(System.in);

    PessoaDAO p = new PessoaDAO();
    MedicoDAO m = new MedicoDAO(p);
    MatrizFranquiaDAO mf = new MatrizFranquiaDAO(p);
    FranquiaDAO f = new FranquiaDAO(p, mf);
    ConsultaDAO c = new ConsultaDAO(p, m, mf, f);
    InfoConsultaDAO ic = new InfoConsultaDAO(c);
    ProcedimentoDAO proc = new ProcedimentoDAO(c);
    FinanceiroAdmDAO fin = new FinanceiroAdmDAO(c, proc, f);
    FinanceiroMedicoDAO finMed = new FinanceiroMedicoDAO(c, proc, f);
    FuncionarioAdmDAO fa = new FuncionarioAdmDAO(f, p);

    public Controller() {

        //tela de login, senha, cadastrar nova pessoa
        int opcaoUsuario = 45;
        String recebeString;
        String string2;
        String string3;

        long id;
        long id2;
        long id3;
        while (opcaoUsuario != 30) {
            
            try{
            opcaoUsuario = this.opcaoUsuarioTelaInicial();
            } catch (NumberFormatException e){
                System.out.println("NumberFormat Exception: invalid input string");
            } finally {            
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;
                    
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
                    ic.mostraTodos();
                    System.out.println("\n\n\n");
                    proc.mostraTodos();
                    System.out.println("\n\n\n");
                    fin.mostraTodos();
                    System.out.println("\n\n\n");
                    finMed.mostraTodos();
                    System.out.println("\n\n\n");
                    fa.mostraTodos();
                    System.out.println("\n\n\n");

                    break;

                case 1:
                    System.out.println("\nmostrar medicos");
                    m.mostraTodos();
                    break;

                case 2: //testado alterado o nome
                    System.out.println("\nalterar pessoa nome");
                    //p.mostraTodos();
                    System.out.println("\nQual o id da pessoa?");
                    id = Long.parseLong(scanner.nextLine());

                    if (p.verificaRegistro(id) != null) {
                        System.out.println("\nQual o novo nome ?");
                        p.alterarNome(id, scanner.nextLine());
                        System.out.println("Alteração realizada");
                    } else {
                        System.out.println("Pessoa não encontrada");
                    }
                    //p.mostraTodos();
                    break;

                case 3://testado alterado o nome médico
                    System.out.println("\nalterar medico nome");
                    //m.mostraTodos();
                    System.out.println("\nQual o id do medico?");
                    id = Long.parseLong(scanner.nextLine());
                    if (m.verificaRegistro(id) != null) {
                        System.out.println("\nQual o novo nome ?");
                        m.getPessoa(id).setNome(scanner.nextLine());
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

                case 5://testado adicionar Medico precisa de uma pessoa cadastrada                    
                    System.out.println("\nadicionar medico");
                    //p.mostraTodos();

                    System.out.println("\nQual o id da pessoa?");
                    id = Long.parseLong(scanner.nextLine());

                    if (p.verificaRegistro(id) == null) {
                        System.out.println("\nNao esta cadastrado, por gentileza realizar cadastro.");
                    } else {
                        Medico novoM = new Medico(p.verificaRegistro(id));
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
                    System.out.println("\nQual o id da matriz?");
                    id = Long.parseLong(scanner.nextLine());
                    if (mf.verificaRegistro(id) == null) {
                        System.out.println("Matriz não cadastrada");
                    } else {
                        System.out.println("\nQual o novo nome ?");
                        mf.alterarNome(id, scanner.nextLine());
                        System.out.println("Alteração realizada");
                    }
                    mf.mostraTodos();
                    break;

                case 7://testado adicionar matriz
                    System.out.println("\nadicionar matriz");
                    System.out.println("\nQual o id do dono?");
                    id = Long.parseLong(scanner.nextLine());
                    if (p.verificaRegistro(id) != null) {
                        MatrizFranquia novaMF = new MatrizFranquia(p.verificaRegistro(id));
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
                    } else {
                        System.out.println("Dono nao cadastrado, realizar cadastro");
                    }

                    mf.mostraTodos();
                    break;

                case 8://testado alterar franquia
                    System.out.println("\nalterar dados da franquia");
                    f.mostraTodos();

                    System.out.println("\nQual o id da franquia?");
                    id = Long.parseLong(scanner.nextLine());

                    if (f.verificaRegistro(id) == null) {
                        System.out.println("Franquia não encontrada");
                    } else {
                        System.out.println("\nQual o novo login?");
                        f.verificaRegistro(id).getResponsavel().setLogin(scanner.nextLine());
                        System.out.println("Alteração realizada");
                    }
                    mf.mostraTodos();
                    break;

                case 9://testado adicionar franquia
                    System.out.println("\nadicionar franquia");
                    //p.mostraTodos();
                    System.out.println("\nQual o id do responsavel?");
                    id = Long.parseLong(scanner.nextLine());

                    if (p.verificaRegistro(id) == null) {
                        System.out.println("\nResponsavel nao esta cadastrado, por gentileza realizar cadastro.");
                    } else {
                        System.out.println("\nInforme o id da matriz?");
                        id2 = Long.parseLong(scanner.nextLine());

                        if (mf.verificaRegistro(id2) == null) {
                            System.out.println("\nMatriz nao cadastrada, por gentileza realizar cadastro.");
                        } else {
                            Franquia novaF = new Franquia(p.verificaRegistro(id), mf.verificaRegistro(id2));
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

                    System.out.println("\nQual o id da consulta?");
                    id = Long.parseLong(scanner.nextLine());

                    if (c.verificaRegistro(id) != null) {
                        System.out.println("\nInforme o novo valor: ");
                        BigDecimal novoValor = new BigDecimal(scanner.nextLine());

                        c.alteraValor(id, novoValor);
                        System.out.println("Valor alterado com sucesso");
                    }

                    c.mostraTodos();

                    break;
                case 11://testado adiciona consulta
                    c.mostraTodos();
                    System.out.println("\nadicionar consulta");

                    System.out.println("\nQual o id do Paciente que vai consultar?");
                    id = Long.parseLong(scanner.nextLine());
                    if (p.verificaRegistro(id) == null) {
                        System.out.println("Paciente não cadastrado, realize o cadastro");
                    } else {
                        System.out.println("\nQual o id do Medico com quem gostaria de consultar?");
                        id2 = Long.parseLong(scanner.nextLine());
                        if (m.verificaRegistro(id2) != null) {
                            System.out.println("\nQual o id da Franquia onde gostaria de consultar?");
                            id3 = Long.parseLong(scanner.nextLine());
                            if (f.verificaRegistro(id3) != null) {
                                System.out.println("");
                                Consulta novaC = new Consulta(p.verificaRegistro(id), m.verificaRegistro(id2), f.verificaRegistro(id3));
                                System.out.println("\nInforme qual o dia que gostaria de consultar (No formato dd/MM/yyyy): ");
                                recebeString = scanner.nextLine();

                                System.out.println("\nE qual o horario (No formato hh:mm): ");
                                string2 = scanner.nextLine();

                                System.out.println("\nQual o valor da consulta? ");
                                string3 = scanner.nextLine();
                                novaC.setValor(new BigDecimal(string3));

                                novaC.setDiaHorario(recebeString, string2);
                                c.adiciona(novaC);
                                System.out.println("\nNova consulta registrada com sucesso");

                            } else {
                                System.out.println("\nFranquia não encontrada");
                            }
                        } else {
                            System.out.println("\nMedico não encontrado");
                        }
                    }

                    c.mostraTodos();
                    break;

                /*case 12://altera procedimento
                    break;

                case 13://adiciona procedimento
                    break;

                case 14://mostra crud financeiro medico       
                    break;*/
                case 15: //testado - remove medico
                    System.out.println("\nremover medico");
                    //m.mostraTodos();
                    System.out.println("\nQual o id do medico?");
                    id = Long.parseLong(scanner.nextLine());
                    if (m.verificaRegistro(id) != null) {
                        m.remove(id);
                        System.out.println("Concluido com sucesso");
                    } else {
                        System.out.println("Medico não encontrado");
                    }
                    //m.mostraTodos();
                    break;

                case 16://testado - delete FuncionarioAdm com null
                    System.out.println("\nremover Funcionario_Adm");

                    System.out.println("\nQual o id do funcionario?");
                    id = Long.parseLong(scanner.nextLine());
                    if (fa.verificaRegistro(id) != null) {
                        fa.remove(id);
                        System.out.println("Concluido com sucesso");
                    } else {
                        System.out.println("Funcionario não encontrado");
                    }
                    fa.mostraTodos();
                    break;

                /*case 17:
                    break;

                case 18:
                    break;

                case 19:
                    break;

                case 20:
                    break;*/
            }
        }
        }
        System.out.println("Saí do menu");
    }

    public static void main(String[] args) {
        new Controller();
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
        builderInicio.append("\n15 - Testado - remover medico");
        builderInicio.append("\n16 - Testado - remover Funcionario_Adm");
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
