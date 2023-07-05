/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.Scanner;
import Controller.Controller;
import franquiamedica.Consulta;
import franquiamedica.FuncionarioAdm;
import franquiamedica.Medico;
import franquiamedica.Pessoa;
import franquiamedica.Procedimento;
import franquiamedica.Utilitario;
import java.math.BigDecimal;
import java.time.LocalDate;

public class View {

    Scanner scanner = new Scanner(System.in);
    Controller ctrOrig = new Controller();

    public static void main(String[] args) {
        View gui = new View();
        gui.ctrOrig.setGUI(gui);
        gui.padraoAll();
    }

    public void padraoAll() {
        int opcaoUsuario = 0;
        String login;
        String senha;
        ctrOrig.primeiroCadastro();

        while (opcaoUsuario != 7) {
            opcaoUsuario = this.menuPadrao();
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;

                case 1:
                    System.out.println("\nLOGIN\n");
                    login = scanner.nextLine();

                    System.out.println("\nSENHA\n");
                    senha = scanner.nextLine();

                    ctrOrig.validaLogin(login, senha);
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

                    if (ctrOrig.novoCadastro(novaP)) {
                        System.out.println("Seu cadastro foi realizado com sucesso");
                    } else {
                        System.out.println("Cadastro não deu certo");
                    }
                    break;

                case 3:
                    System.out.println("Programa encerrado");
                    //apesar de descartar as excecoes que nao foram tratadas, aqui nao tem problema
                    return;

                case 4:
                    System.out.println("Chamando funcao Incrementar dias");
                    ctrOrig.alterarDiaSistema(Integer.parseInt(scanner.nextLine()));
                    break;

                case 5:
                    System.out.println("Alterando consultas");
                    ctrOrig.alteraConsulta(Utilitario.diaAntesIncremento);
                    System.out.println("Alterando procedimentos");
                    ctrOrig.alteraProcedimento(Utilitario.diaAntesIncremento);
                    //Só usar quando resetar o banco
                    System.out.println("Alterando o Financeiro Administrativo");
                    ctrOrig.valorRecebidoConsulta(Utilitario.diaAntesIncremento);
                    System.out.println("Alterando o Financeiro dos Medicos");
                    ctrOrig.valorRecebidoProcedimento(Utilitario.diaAntesIncremento);
                    break;

                case 6:
                    ctrOrig.copyTxtToPdf(Utilitario.arquivoRelatorio.toString(), Utilitario.result.toString());
            }
        }

    }

    public int menuPadrao() {

        StringBuilder builderAdm = new StringBuilder("");
        builderAdm.append("Bem-Vindo\n\n");
        builderAdm.append("\n1 - Fazer Login");
        builderAdm.append("\n2 - Novo Cadastro");
        builderAdm.append("\n3 - Encerrar Programa");
        builderAdm.append("\n4 - Extra Incrementar Dias");
        builderAdm.append("\n5 - Atualiza Financeiros");
        builderAdm.append("\n6 - Copia arquivo txt para pdf");
        builderAdm.append("\n7 - Encerra o programa");

        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    private void menuAlterarDados() {
        int alterarDado = this.pessoaAlterarDados();

        switch (alterarDado) {
            case 1:
                System.out.println("\nQual o novo nome:");
                ctrOrig.p.alterarNome(Utilitario.getPessoaLogada().getId(), scanner.nextLine());
                break;

            case 2:
                System.out.println("\nQual o novo endereço ?");
                ctrOrig.p.alterarEndereco(Utilitario.getPessoaLogada().getId(), scanner.nextLine());
                break;

            case 3:
                System.out.println("\nQual o novo CPF ?");
                ctrOrig.p.alterarCpf(Utilitario.getPessoaLogada().getId(), scanner.nextLine());
                break;

            case 4:
                System.out.println("\nQual o novo telefone ?");
                ctrOrig.p.alterarTelefone(Utilitario.getPessoaLogada().getId(), scanner.nextLine());
                break;

            case 5:
                System.out.println("\nQual o novo Login ?");
                ctrOrig.p.alterarLogin(Utilitario.getPessoaLogada().getId(), scanner.nextLine());
                break;

            case 6:
                System.out.println("\nQual a nova Senha ?");
                ctrOrig.p.alterarSenha(Utilitario.getPessoaLogada().getId(), scanner.nextLine());
                break;
        }

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

    public void pacienteAll() {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 6) {
            opcaoUsuario = this.menuPaciente();
            switch (opcaoUsuario) {
                default:
                    System.out.println("\nOpcao Nao encontrada");
                    break;

                case 1:
                    menuAlterarDados();
                    break;

                case 2:
                    System.out.println("\nRelatorio de consultas: ");
                    ctrOrig.relatConsulPaciente();
                    break;

                case 3:
                    System.out.println("\nRelatorio de Procedimentos: ");
                    ctrOrig.relProcPaciente();
                    break;

                case 4:
                    Utilitario.setPessoaLogada(null);
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

    public void medicoAll() {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 7) {
            opcaoUsuario = this.menuMedico();
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;

                case 1:
                    menuAlterarDados();
                    break;

                case 2:
                    ctrOrig.relConsulMedico();
                    break;

                case 3:
                    System.out.println("Informe a descricao");
                    String description = scanner.nextLine();
                    System.out.println("Escolha a consulta para adicionar descricao");
                    ctrOrig.relConsulMedico();
                    long id = Long.parseLong(scanner.nextLine());
                    ctrOrig.addDescriConsul(id, description);
                    break;

                case 4:
                    ctrOrig.relProcMedico();
                    break;

                case 5:
                    System.out.println("Informe o laudoo");
                    String laudo = scanner.nextLine();
                    System.out.println("Escolha o procedimento para adicionar laudo");
                    ctrOrig.relProcMedico();
                    long idP = Long.parseLong(scanner.nextLine());
                    ctrOrig.addDescriProc(idP, laudo);

                    break;

                case 6:
                    ctrOrig.relPagamentoUmMedico();
                    break;

                case 7:
                    Utilitario.setPessoaLogada(null);
                    return;
            }
        }

    }

    private int menuMedico() { //ok

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Medico\n\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Verificar Consultas");
        builderAdm.append("\n3 - Adicionar descricao da Consulta");
        builderAdm.append("\n4 - Verificar Procedimentos");
        builderAdm.append("\n5 - Adicionar Laudo no Procedimento");
        builderAdm.append("\n6 - Relatorio Financeiro");
        builderAdm.append("\n7 - Deslogar\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public void funcionarioAdmAll() {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 7) {
            opcaoUsuario = this.menuFuncAdministrativo();
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;

                case 1:
                    menuAlterarDados();
                    break;

                case 2:

                    ctrOrig.c.populaLista();
                    ctrOrig.c.mostraTodos();
                    break;

                case 3:
                    ctrOrig.ic.populaLista();
                    ctrOrig.ic.mostraTodos();
                    break;

                case 4:
                    ctrOrig.proc.populaLista();
                    ctrOrig.proc.mostraTodos();
                    break;

                case 5://marcar consulta
                    Consulta consulta = new Consulta();
                    System.out.println("Informe a data no formato 'yyyy-mm-dd' : ");
                    consulta.setDiaHorario(scanner.nextLine());

                    System.out.println("Informe o Id do Medico: ");
                    consulta.setMedico(Long.parseLong(scanner.nextLine()));

                    System.out.println("Informe o Id do Local de Unidade");
                    consulta.setUnidade(Long.parseLong(scanner.nextLine()));

                    consulta.setEstado("Agendado");
                    consulta.setPaciente(Utilitario.getPessoaLogada().getId());

                    System.out.println("Qual o valor da consulta?");
                    consulta.setValor(new BigDecimal(scanner.nextLine()));

                    consulta.setDataCriacao(LocalDate.now());
                    consulta.setDatamodificacao(LocalDate.now());
                    consulta.setVisible(true);

                    ctrOrig.c.adiciona(consulta);
                    break;

                case 6://marcar procedimento
                    Procedimento procedimento = new Procedimento();
                    System.out.println("Informe o nome do procedimento: ");
                    procedimento.setNome(scanner.nextLine());

                    System.out.println("Informe a data no formato 'yyyy-mm-dd' : ");
                    procedimento.setDiaHorario(scanner.nextLine());

                    System.out.println("Qual o valor do procedimento?");
                    procedimento.setValorPro(new BigDecimal(scanner.nextLine()));

                    System.out.println("Informe o Id da Consulta: ");
                    procedimento.setConsulta(Long.parseLong(scanner.nextLine()));

                    procedimento.setEstado("Agendado");
                    procedimento.setLaudo("A ser realizado");
                    procedimento.setDataCriacao(LocalDate.now());
                    procedimento.setDatamodificacao(LocalDate.now());
                    procedimento.setVisible(true);

                    ctrOrig.proc.adiciona(procedimento);
                    break;

                case 7:
                    Utilitario.setPessoaLogada(null);
                    return;
            }
        }

    }

    private int menuFuncAdministrativo() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Funcionario_Administrativo\n\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Relatorio de consultas");
        builderAdm.append("\n3 - Relatorio de descricao das consultas");
        builderAdm.append("\n4 - Relatorio de procedimentos");
        builderAdm.append("\n5 - Marcar consulta");
        builderAdm.append("\n6 - Marcar procedimento");
        builderAdm.append("\n7 - Deslogar\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    private boolean menuAlterarUsuario() {
        int alterarDado = this.menuAlterartipoUsuario();

        switch (alterarDado) {
            case 1:
                Medico novo = new Medico();
                System.out.println("Informe o CRM: ");
                novo.setCrm(scanner.nextLine());

                System.out.println("\nInforme o id da Pessoa:");
                novo.setPessoa(ctrOrig.p.verificaRegistro(Long.parseLong(scanner.nextLine())).getId());

                System.out.println("Informe a especialidade: ");
                novo.setEspecialidade(scanner.nextLine());

                novo.setDataCriacao(LocalDate.now());
                novo.setDatamodificacao(LocalDate.now());
                novo.setVisible(true);
                ctrOrig.m.adiciona(novo);
                return true;

            case 2:
                FuncionarioAdm novoF = new FuncionarioAdm();
                System.out.println("Informe qual o id da Franquia: ");
                novoF.setIdFranquia(ctrOrig.f.verificaRegistro(Long.parseLong(scanner.nextLine())).getId());

                System.out.println("\nInforme o id da Pessoa:");
                novoF.setFuncionario(ctrOrig.p.verificaRegistro(Long.parseLong(scanner.nextLine())).getId());

                novoF.setDataCriacao(LocalDate.now());
                novoF.setDatamodificacao(LocalDate.now());
                novoF.setVisible(true);
                ctrOrig.fa.adiciona(novoF);
                return true;
        }
        return false;
    }

    private int menuAlterartipoUsuario() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Para qual funcao?\n\n");
        builderAdm.append("\n1 - Medico");
        builderAdm.append("\n2 - Funcionario administrativo");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public void responsavelFranquiaAll() {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 13) {
            opcaoUsuario = this.menuResponsavel();
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;

                case 1:
                    menuAlterarDados();
                    break;

                case 2:
                    if (menuAlterarUsuario()) {
                        System.out.println("Alterado com sucesso");
                    } else {
                        System.out.println("Nao deu certo");
                    }

                    break;

                case 3:
                    ctrOrig.ic.populaLista();
                    ctrOrig.ic.mostraTodos();
                    break;

                case 4:
                    ctrOrig.c.populaLista();
                    ctrOrig.c.mostraTodos();
                    break;

                case 5:
                    ctrOrig.proc.populaLista();
                    ctrOrig.proc.mostraTodos();
                    break;

                case 6:
                    ctrOrig.finMed.populaLista();
                    ctrOrig.finMed.mostraTodos();
                    break;

                case 7:
                    System.out.println("Informe o id da Franquia para relatório financeiro");
                    ctrOrig.relFinUmaFranquia(Long.parseLong(scanner.nextLine()));
                    break;

                case 8:
                    Consulta consulta = new Consulta();
                    System.out.println("Informe a data no formato 'yyyy-mm-dd' : ");
                    consulta.setDiaHorario(scanner.nextLine());

                    System.out.println("Informe o Id do Medico: ");
                    consulta.setMedico(Long.parseLong(scanner.nextLine()));

                    System.out.println("Informe o Id do Local de Unidade");
                    consulta.setUnidade(Long.parseLong(scanner.nextLine()));

                    consulta.setEstado("Agendado");
                    consulta.setPaciente(Utilitario.getPessoaLogada().getId());

                    System.out.println("Qual o valor da consulta?");
                    consulta.setValor(new BigDecimal(scanner.nextLine()));

                    consulta.setDataCriacao(LocalDate.now());
                    consulta.setDatamodificacao(LocalDate.now());
                    consulta.setVisible(true);

                    ctrOrig.c.adiciona(consulta);
                    break;

                case 9:
                    Procedimento procedimento = new Procedimento();
                    System.out.println("Informe o nome do procedimento: ");
                    procedimento.setNome(scanner.nextLine());

                    System.out.println("Informe a data no formato 'yyyy-mm-dd' : ");
                    procedimento.setDiaHorario(scanner.nextLine());

                    System.out.println("Qual o valor do procedimento?");
                    procedimento.setValorPro(new BigDecimal(scanner.nextLine()));

                    System.out.println("Informe o Id da Consulta: ");
                    procedimento.setConsulta(Long.parseLong(scanner.nextLine()));

                    procedimento.setEstado("Agendado");
                    procedimento.setLaudo("A ser realizado");
                    procedimento.setDataCriacao(LocalDate.now());
                    procedimento.setDatamodificacao(LocalDate.now());
                    procedimento.setVisible(true);

                    ctrOrig.proc.adiciona(procedimento);
                    break;

                case 10:
                    ctrOrig.calcularFaturamentoTotal();
                    break;
                    
                case 11:
                    System.out.println("Informe a nova Cidade: ");
                    ctrOrig.f.alterarCidade(ctrOrig.buscaIdFranquia(), scanner.nextLine());
                    break;

                case 12:
                    System.out.println("Informe o novo Endereco: ");
                    ctrOrig.f.alterarEndereco(ctrOrig.buscaIdFranquia(), scanner.nextLine());
                    break;

                case 13:
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
        builderAdm.append("\n7 - Relatorio Financeiro das Franquia");
        builderAdm.append("\n8 - Marcar consulta");
        builderAdm.append("\n9 - Marcar Procedimento");
        builderAdm.append("\n10 - Pagamento para a Matriz, referente ao ultimo mes");
        builderAdm.append("\n11 - Alterar Cidade da Matriz");
        builderAdm.append("\n12 - Alterar endereco da Matriz");
        builderAdm.append("\n13 - Deslogar\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    private boolean menuAlterarUsuarioDono() {
        int alterarDado = this.menuAlterartipoUsuarioDono();

        switch (alterarDado) {
            case 1:
                Medico novo = new Medico();
                System.out.println("Informe o CRM: ");
                novo.setCrm(scanner.nextLine());

                System.out.println("\nInforme o id da Pessoa:");
                novo.setPessoa(ctrOrig.p.verificaRegistro(Long.parseLong(scanner.nextLine())).getId());

                System.out.println("Informe a especialidade: ");
                novo.setEspecialidade(scanner.nextLine());

                novo.setDataCriacao(LocalDate.now());
                novo.setDatamodificacao(LocalDate.now());
                novo.setVisible(true);
                ctrOrig.m.adiciona(novo);
                return true;

            case 2:
                System.out.println("\nInforme o id da Pessoa:");
                ctrOrig.p.verificaRegistro(Long.parseLong(scanner.nextLine())).setTipoUsuario("FuncAdministrativo");

                FuncionarioAdm novoF = new FuncionarioAdm();
                System.out.println("Informe qual o id da Franquia: ");
                novoF.setIdFranquia(ctrOrig.f.verificaRegistro(Long.parseLong(scanner.nextLine())).getId());

                System.out.println("\nInforme o id da Pessoa:");
                novoF.setFuncionario(ctrOrig.p.verificaRegistro(Long.parseLong(scanner.nextLine())).getId());

                novoF.setDataCriacao(LocalDate.now());
                novoF.setDatamodificacao(LocalDate.now());
                novoF.setVisible(true);
                ctrOrig.fa.adiciona(novoF);
                return true;

            case 3:
                System.out.println("\nInforme o id da Pessoa:");
                ctrOrig.p.verificaRegistro(Long.parseLong(scanner.nextLine())).setTipoUsuario("Responsavel pela Franquia");
                return true;
        }
        return false;
    }

    private int menuAlterartipoUsuarioDono() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Para qual funcao?\n\n");
        builderAdm.append("\n1 - Medico");
        builderAdm.append("\n2 - Funcionario administrativo");
        builderAdm.append("\n3 - Responsavel pela Franquia");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public void donoFranquiaAll() {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 17) {
            opcaoUsuario = this.menuDonoMatriz();
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;

                case 1:
                    menuAlterarDados();
                    break;

                case 2:
                    if (menuAlterarUsuarioDono()) {
                        System.out.println("Alterado com sucesso");
                    } else {
                        System.out.println("Nao deu certo");
                    }
                    break;

                case 3:
                    ctrOrig.ic.populaLista();
                    ctrOrig.ic.mostraTodos();
                    break;

                case 4:
                    ctrOrig.c.populaLista();
                    ctrOrig.c.mostraTodos();
                    break;

                case 5:
                    ctrOrig.proc.populaLista();
                    ctrOrig.proc.mostraTodos();
                    break;

                case 6:
                    ctrOrig.finMed.populaLista();
                    ctrOrig.finMed.mostraTodos();
                    break;

                case 7:
                    ctrOrig.fin.populaLista();
                    ctrOrig.fin.mostraTodos();
                    break;

                case 8:
                    Consulta consulta = new Consulta();
                    System.out.println("Informe a data no formato 'yyyy-mm-dd' : ");
                    consulta.setDiaHorario(scanner.nextLine());

                    System.out.println("Informe o Id do Medico: ");
                    consulta.setMedico(Long.parseLong(scanner.nextLine()));

                    System.out.println("Informe o Id do Local de Unidade");
                    consulta.setUnidade(Long.parseLong(scanner.nextLine()));

                    consulta.setEstado("Agendado");
                    consulta.setPaciente(Utilitario.getPessoaLogada().getId());

                    System.out.println("Qual o valor da consulta?");
                    consulta.setValor(new BigDecimal(scanner.nextLine()));

                    consulta.setDataCriacao(LocalDate.now());
                    consulta.setDatamodificacao(LocalDate.now());
                    consulta.setVisible(true);

                    ctrOrig.c.adiciona(consulta);
                    break;

                case 9:
                    Procedimento procedimento = new Procedimento();
                    System.out.println("Informe o nome do procedimento: ");
                    procedimento.setNome(scanner.nextLine());

                    System.out.println("Informe a data no formato 'yyyy-mm-dd' : ");
                    procedimento.setDiaHorario(scanner.nextLine());

                    System.out.println("Qual o valor do procedimento?");
                    procedimento.setValorPro(new BigDecimal(scanner.nextLine()));

                    System.out.println("Informe o Id da Consulta: ");
                    procedimento.setConsulta(Long.parseLong(scanner.nextLine()));

                    procedimento.setEstado("Agendado");
                    procedimento.setLaudo("A ser realizado");
                    procedimento.setDataCriacao(LocalDate.now());
                    procedimento.setDatamodificacao(LocalDate.now());
                    procedimento.setVisible(true);

                    ctrOrig.proc.adiciona(procedimento);
                    break;

                case 10:
                    System.out.println("Informe o Id da Franquia: ");
                    ctrOrig.relFinUmaFranquia(Long.parseLong(scanner.nextLine()));
                    break;

                case 11:
                    ctrOrig.relFinFranquias();
                    break;

                case 12:
                    ctrOrig.pagamentoMedicos();
                    break;

                case 13:
                    System.out.println("Informe o novo Nome: ");
                    ctrOrig.mf.alterarNome(ctrOrig.buscaIdMatriz(), scanner.nextLine());
                    break;

                case 14:
                    System.out.println("Informe o novo CNPJ: ");
                    ctrOrig.mf.alterarCnpj(ctrOrig.buscaIdMatriz(), scanner.nextLine());
                    break;

                case 15:
                    System.out.println("Informe a nova Cidade: ");
                    ctrOrig.mf.alterarCidade(ctrOrig.buscaIdMatriz(), scanner.nextLine());
                    break;

                case 16:
                    System.out.println("Informe o novo Endereco: ");
                    ctrOrig.mf.alterarEndereco(ctrOrig.buscaIdMatriz(), scanner.nextLine());
                    break;

                case 17:
                    Utilitario.setPessoaLogada(null);
                    return;
            }
        }

    }

    private int menuDonoMatriz() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Responsavel pela Franquia\n\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Alterar tipo de um usuario");
        builderAdm.append("\n3 - Relatorio de Informacoes de consulta");
        builderAdm.append("\n4 - Relatorio de Consultas");
        builderAdm.append("\n5 - Relatorio de Procedimentos");
        builderAdm.append("\n6 - Relatorio de Financeiro do Medico");
        builderAdm.append("\n7 - Relatorio Financeiro das Franquia");
        builderAdm.append("\n8 - Marcar consulta");
        builderAdm.append("\n9 - Marcar Procedimento");
        builderAdm.append("\n10 - Relatorios de uma franquia");
        builderAdm.append("\n11 - Relatorios de todas as franquias");
        builderAdm.append("\n12 - Pagamento por cada médico");
        builderAdm.append("\n13 - Alterar Nome da Matriz");
        builderAdm.append("\n14 - Alterar CNPJ da Matriz");
        builderAdm.append("\n15 - Alterar Cidade da Matriz");
        builderAdm.append("\n16 - Alterar endereco da Matriz");
        builderAdm.append("\n17 - Deslogar\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
