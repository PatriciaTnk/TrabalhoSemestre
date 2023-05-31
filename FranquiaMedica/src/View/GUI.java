/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.Scanner;
import Controller.Controller;
import Controller.ControllerFuncionarioAdm;
import Controller.ControllerMedico;
import Controller.ControllerPaciente;
import Controller.ControllerResponsavelFranquia;
import franquiamedica.Consulta;
import franquiamedica.FinanceiroAdm;
import franquiamedica.Franquia;
import franquiamedica.InfoConsulta;
import franquiamedica.Medico;
import franquiamedica.Pessoa;
import franquiamedica.Utilitario;
import java.math.BigDecimal;

public class GUI {

    Scanner scanner = new Scanner(System.in);
    Controller ctrOrig = new Controller();

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.ctrOrig.setGUI(gui);
        gui.padraoAll();
    }

    public void padraoAll() {
        int opcaoUsuario = 0;
        String login;
        String senha;
        ctrOrig.primeiroCadastro();

        while (opcaoUsuario != 5) {
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

                    ctrOrig.validaLogin(login, senha, ctrOrig);
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

            }
        }

    }

    public int menuPadrao() {

        StringBuilder builderAdm = new StringBuilder("");
        builderAdm.append("Bem-Vindo\n\n");
        builderAdm.append("\n1 - Fazer Login");
        builderAdm.append("\n2 - Novo Cadastro");
        builderAdm.append("\n3 - Encerrar Programa");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    private void menuAlterarDados() {
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

    public void pacienteAll(Controller novo, ControllerPaciente cp) {
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
                    if (novo.validaGeral("CONSULTA")) {
                        cp.mostraRelatorioConsultas(novo);
                    } else {
                        System.out.println("\nPaciente nao tem historico de consultas");
                    }
                    break;

                case 3:
                    System.out.println("\nRelatorio de Procedimentos: ");
                    //validando se tem historico ou nao
                    if (novo.validaGeral("Procedimento")) {
                        //se tiver entao vai mostrar
                        cp.mostraRelatorioProcedimento(novo);
                    } else {
                        System.out.println("\nPaciente nao tem historico de procedimentos");
                    }
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

    public void medicoAll(Controller novo, ControllerMedico cm) {
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
                    if (novo.validaGeral("consulTa")) {
                        cm.mostraRelatorioConsultas(novo);
                    } else {
                        System.out.println("\nMedico nao tem consultas registradas");
                    }
                    break;

                case 3:
                    System.out.println("Escolha a consulta que vai adicionar descricao");
                    if (novo.validaGeral("consulTa")) {
                        cm.mostraRelatorioConsultas(novo);
                        for (int i = 0; i < novo.getC().consultas.length; ++i) {
                            if (novo.getC().consultas[i] != null
                                    && novo.getC().consultas[i].getMedico().getId() == Utilitario.getPessoaLogada().getId()) {
                                InfoConsulta outra = new InfoConsulta(novo.getC().consultas[i]);
                                outra.setDescricao(scanner.nextLine());
                                novo.getIc().adiciona(outra);
                            }
                        }
                    } else {
                        System.out.println("\nMedico nao tem consultas registradas");
                    }
                    break;

                case 4:
                    if (novo.validaGeral("procEdimento")) {
                        cm.mostraRelatorioProcedimento(novo);
                    } else {
                        System.out.println("\nMedico nao tem procedimentos registrados");
                    }
                    break;

                case 5:
                    System.out.println("Escolha o procedimento que vai adicionar laudo");
                    if (novo.validaGeral("procedimentO")) {
                        cm.mostraRelatorioProcedimento(novo);
                        for (int i = 0; i < novo.getProc().proceds.length; ++i) {
                            if (novo.getProc().proceds[i] != null
                                    && novo.getProc().proceds[i].getConsulta().getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {
                                novo.getProc().proceds[i].setEstado(scanner.nextLine());
                            }
                        }
                    } else {
                        System.out.println("\nMedico nao tem procedimentos registrados");
                    }

                    break;

                case 6:
                    if (novo.validaGeral("FinanceiroMedico")) {
                        cm.mostraRelatorioFinanceiroMedico(novo);
                    } else {
                        System.out.println("\nMedico nao teve movimentacao Financeira");
                    }
                    break;

                case 7:
                    Utilitario.setPessoaLogada(null);
                    return;
            }
        }

    }

    private int menuMedico() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Medico\n\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Agenda de Consultas");
        builderAdm.append("\n3 - Adicionar descricao da Consulta");
        builderAdm.append("\n4 - Verificar Procedimentos");
        builderAdm.append("\n5 - Adicionar Laudo no Procedimento");
        builderAdm.append("\n6 - Relatorio Financeiro");
        builderAdm.append("\n7 - Deslogar\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public void funcionarioAdmAll(Controller controller, ControllerFuncionarioAdm cAdm) {
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
                    if (controller.validaGeral("Consulta")) {
                        cAdm.mostraRelatorioConsultas(controller);
                    } else {
                        System.out.println("Nao tem consultas cadastradas");
                    }

                    break;

                case 3:
                    if (controller.validaGeral("Consulta")) {
                        cAdm.mostraInformacaoConsulta(controller);
                    } else {
                        System.out.println("Nao tem informacoes de consulta cadastradas");
                    }

                    break;

                case 4:
                    if (controller.validaGeral("Procedimento")) {
                        cAdm.mostraRelatorioProcedimento(controller);
                    } else {
                        System.out.println("Nao tem procedimentos cadastrados");
                    }

                    break;

                case 5:
                    if (controller.validaGeral("Consulta")) {
                        int sair = 0;
                        Pessoa paciente = null;
                        Medico medico = null;
                        Franquia franquia = null;
                        String dia = null;
                        String horario = null;
                        String valor = null;
                        while (sair != 4) {
                            System.out.println("Informe o paciente que gostaria de consultar: ");
                            paciente = controller.getP().verificaRegistro(Long.parseLong(scanner.nextLine()));

                            System.out.println("Informe o paciente que gostaria de consultar: ");
                            medico = controller.getM().verificaRegistro(Long.parseLong(scanner.nextLine()));

                            System.out.println("Informe em qual Franquia gostaria de consultar: ");
                            franquia = controller.getF().verificaRegistro(Long.parseLong(scanner.nextLine()));

                            if (paciente == null || medico == null || franquia == null) {
                                System.out.println("Informaces inconsistentes");
                                System.out.println("1 - Gostaria de tentar novamente");
                                System.out.println("2 - sair");
                                sair = Integer.parseInt(scanner.nextLine());
                            } else {
                                System.out.println("\nInforme qual o dia que gostaria de consultar (No formato dd/MM/yyyy): ");
                                dia = scanner.nextLine();

                                System.out.println("\nE qual o horario (No formato hh:mm): ");
                                horario = scanner.nextLine();

                                System.out.println("\nQual o valor da consulta?");
                                valor = scanner.nextLine();
                                sair = 4;
                            }
                        }
                        if (cAdm.marcarConsulta(controller, dia, horario, valor, paciente, medico, franquia)) {
                            System.out.println("\nConsulta marcada com sucesso");
                        } else {
                            System.out.println("\nNao deu certo marcar a consulta");
                        }
                    } else {
                        System.out.println("Nao tem consultas cadastradas");
                    }

                    break;

                case 6:
                    if (controller.validaGeral("Procedimento")) {
                        int sairP = 0;
                        Consulta consultaP = null;
                        String diaP = null;
                        String horarioP = null;
                        String valorP = null;
                        while (sairP != 4) {
                            System.out.println("\nO procecedimento foi pedido: ");
                            System.out.println("\n1 - Na consulta");
                            System.out.println("\n2 - Pelo paciente");
                            sairP = Integer.parseInt(scanner.nextLine());

                            if (sairP == 2) {
                                consultaP = null;
                            } else {
                                System.out.println("Informe a consulta: ");
                                consultaP = controller.getC().verificaRegistro(Long.parseLong(scanner.nextLine()));

                                System.out.println("\nInforme qual o dia que gostaria de consultar (No formato dd/MM/yyyy): ");
                                diaP = scanner.nextLine();

                                System.out.println("\nE qual o horario (No formato hh:mm): ");
                                horarioP = scanner.nextLine();

                                System.out.println("\nQual o valor da consulta?");
                                valorP = scanner.nextLine();
                                sairP = 4;
                            }
                        }
                        if (cAdm.marcarProcedimento(controller, diaP, horarioP, valorP, consultaP)) {
                            System.out.println("\nConsulta marcada com sucesso");
                        } else {
                            System.out.println("\nNao deu certo marcar a consulta");
                        }
                    } else {
                        System.out.println("Nao pode marcar procedimento");
                    }

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
        builderAdm.append("\n7 - Deslogarl\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public void responsavelFranquiaAll(Controller controller, ControllerResponsavelFranquia controllerRF, ControllerFuncionarioAdm cAdm) {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 11) {
            opcaoUsuario = this.menuResponsavel();
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;

                case 1:
                    menuAlterarDados();
                    break;

                case 2:
                    System.out.println("Informe o Id do usuario que gostaria de mudar?");
                    for (int i = 0; i < controller.getP().pessoas.length; ++i) {
                        if (controller.getP().pessoas[i] != null
                                && controller.getP().pessoas[i].getTipoUsuario().equalsIgnoreCase("Paciente")) {
                            System.out.println("\nID: " + controller.getP().pessoas[i].getId() + "\nNome: " + controller.getP().pessoas[i].getNome());
                        }
                    }
                    long idRF = Long.parseLong(scanner.nextLine());
                    if (controllerRF.alterarTipodoUsuario(controller, idRF)) {
                        System.out.println("\nTipo alterado com sucesso");
                    } else {
                        System.out.println("\nNao foi possivel alterar");
                    }
                    break;

                case 3://
                    if (controller.validaGeral("Consulta")) {
                        cAdm.mostraInformacaoConsulta(controller);
                    } else {
                        System.out.println("Nao tem informacoes de consulta cadastradas");
                    }

                    break;

                case 4://
                    if (controller.validaGeral("Consulta")) {
                        cAdm.mostraRelatorioConsultas(controller);
                    } else {
                        System.out.println("Nao tem consultas cadastradas");
                    }
                    break;

                case 5://
                    if (controller.validaGeral("Procedimento")) {
                        cAdm.mostraRelatorioProcedimento(controller);
                    } else {
                        System.out.println("Nao tem procedimentos cadastrados");
                    }
                    break;

                case 6:
                    if (controller.validaGeral("FinanceiroMedico")) {
                        controllerRF.relatorioFinanceiroMedico(controller);
                    } else {
                        System.out.println("Sem Relatorio financeiro Medico gerado");
                    }

                    break;

                case 7:
                    if (controller.validaGeral("FinanceiroAdm")) {
                        controllerRF.relatorioFinanceiroFranquia(controller);
                    } else {
                        System.out.println("Sem Relatorio financeiro Medico gerado");
                    }
                    break;

                case 8://
                    if (controller.validaGeral("Consulta")) {
                        int sair = 0;
                        Pessoa paciente = null;
                        Medico medico = null;
                        Franquia franquia = null;
                        String dia = null;
                        String horario = null;
                        String valor = null;
                        while (sair != 4) {
                            System.out.println("Informe o paciente que gostaria de consultar: ");
                            paciente = controller.getP().verificaRegistro(Long.parseLong(scanner.nextLine()));

                            System.out.println("Informe o paciente que gostaria de consultar: ");
                            medico = controller.getM().verificaRegistro(Long.parseLong(scanner.nextLine()));

                            System.out.println("Informe em qual Franquia gostaria de consultar: ");
                            franquia = controller.getF().verificaRegistro(Long.parseLong(scanner.nextLine()));

                            if (paciente == null || medico == null || franquia == null) {
                                System.out.println("Informaces inconsistentes");
                                System.out.println("1 - Gostaria de tentar novamente");
                                System.out.println("2 - sair");
                                sair = Integer.parseInt(scanner.nextLine());
                            } else {
                                System.out.println("\nInforme qual o dia que gostaria de consultar (No formato dd/MM/yyyy): ");
                                dia = scanner.nextLine();

                                System.out.println("\nE qual o horario (No formato hh:mm): ");
                                horario = scanner.nextLine();

                                System.out.println("\nQual o valor da consulta?");
                                valor = scanner.nextLine();
                                sair = 4;
                            }
                        }
                        if (cAdm.marcarConsulta(controller, dia, horario, valor, paciente, medico, franquia)) {
                            System.out.println("\nConsulta marcada com sucesso");
                        } else {
                            System.out.println("\nNao deu certo marcar a consulta");
                        }
                    } else {
                        System.out.println("Nao tem consultas cadastradas");
                    }

                    break;

                case 9://
                    if (controller.validaGeral("Procedimento")) {
                        int sairP = 0;
                        Consulta consultaP = null;
                        String diaP = null;
                        String horarioP = null;
                        String valorP = null;
                        while (sairP != 4) {
                            System.out.println("\nO procecedimento foi pedido: ");
                            System.out.println("\n1 - Na consulta");
                            System.out.println("\n2 - Pelo paciente");
                            sairP = Integer.parseInt(scanner.nextLine());

                            if (sairP == 2) {
                                consultaP = null;
                            } else {
                                System.out.println("Informe a consulta: ");
                                consultaP = controller.getC().verificaRegistro(Long.parseLong(scanner.nextLine()));

                                System.out.println("\nInforme qual o dia que gostaria de consultar (No formato dd/MM/yyyy): ");
                                diaP = scanner.nextLine();

                                System.out.println("\nE qual o horario (No formato hh:mm): ");
                                horarioP = scanner.nextLine();

                                System.out.println("\nQual o valor da consulta?");
                                valorP = scanner.nextLine();
                                sairP = 4;
                            }
                        }
                        if (cAdm.marcarProcedimento(controller, diaP, horarioP, valorP, consultaP)) {
                            System.out.println("\nConsulta marcada com sucesso");
                        } else {
                            System.out.println("\nNao deu certo marcar a consulta");
                        }
                    } else {
                        System.out.println("Nao pode marcar procedimento");
                    }

                    break;

                case 10:
                    Franquia fran = null;
                    System.out.println("Gostaria de verificar qual Franquia? ");
                    String verifica = scanner.nextLine();

                    for (int l = 0; l < controller.getF().franquias.length; ++l) {
                        if (controller.getF().franquias[l].getId() == Long.parseLong(verifica)) {
                            fran = controller.getF().franquias[l];
                        }
                    }

                    controllerRF.pagamentoMensalParaMatriz(controller, fran);

                    FinanceiroAdm novoFin = new FinanceiroAdm(controller.getF().franquias[0]);
                    novoFin.setTipoDeMovim("entrada");
                    novoFin.setDescritivo("Valor: " + controller.getProc().proceds[0].getValorPro() + "\nRealizado com medico: " + controller.getProc().proceds[0].getConsulta().getMedico() + "\nPaciente: " + controller.getProc().proceds[0].getConsulta().getPaciente() + "\nRealizada no dia: " + controller.getProc().proceds[0].getDiaHorario());
                    novoFin.setValor(new BigDecimal(controller.getProc().proceds[0].getValorPro().toString()));

                    break;

                case 11:
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
        builderAdm.append("\n11 - Deslogars\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }
    
        public void donoFranquiaAll(Controller controller, ControllerResponsavelFranquia controllerRF, ControllerFuncionarioAdm cAdm) {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 12) {
            opcaoUsuario = this.menuDonoMatriz();
            switch (opcaoUsuario) {
                default:
                    System.out.println("Opcao Nao encontrada");
                    break;

                case 1:
                    menuAlterarDados();
                    break;

                case 2:
                    System.out.println("Informe o Id do usuario que gostaria de mudar?");
                    for (int i = 0; i < controller.getP().pessoas.length; ++i) {
                        if (controller.getP().pessoas[i] != null
                                && controller.getP().pessoas[i].getTipoUsuario().equalsIgnoreCase("Paciente")) {
                            System.out.println("\nID: " + controller.getP().pessoas[i].getId() + "\nNome: " + controller.getP().pessoas[i].getNome());
                        }
                    }
                    long idRF = Long.parseLong(scanner.nextLine());
                    if (controllerRF.alterarTipodoUsuario(controller, idRF)) {
                        System.out.println("\nTipo alterado com sucesso");
                    } else {
                        System.out.println("\nNao foi possivel alterar");
                    }
                    break;

                case 3://
                    if (controller.validaGeral("Consulta")) {
                        cAdm.mostraInformacaoConsulta(controller);
                    } else {
                        System.out.println("Nao tem informacoes de consulta cadastradas");
                    }

                    break;

                case 4://
                    if (controller.validaGeral("Consulta")) {
                        cAdm.mostraRelatorioConsultas(controller);
                    } else {
                        System.out.println("Nao tem consultas cadastradas");
                    }
                    break;

                case 5://
                    if (controller.validaGeral("Procedimento")) {
                        cAdm.mostraRelatorioProcedimento(controller);
                    } else {
                        System.out.println("Nao tem procedimentos cadastrados");
                    }
                    break;

                case 6:
                    if (controller.validaGeral("FinanceiroMedico")) {
                        controllerRF.relatorioFinanceiroMedico(controller);
                    } else {
                        System.out.println("Sem Relatorio financeiro Medico gerado");
                    }

                    break;

                case 7:
                    if (controller.validaGeral("FinanceiroAdm")) {
                        controllerRF.relatorioFinanceiroFranquia(controller);
                    } else {
                        System.out.println("Sem Relatorio financeiro Medico gerado");
                    }
                    break;

                case 8://
                    if (controller.validaGeral("Consulta")) {
                        int sair = 0;
                        Pessoa paciente = null;
                        Medico medico = null;
                        Franquia franquia = null;
                        String dia = null;
                        String horario = null;
                        String valor = null;
                        while (sair != 4) {
                            System.out.println("Informe o paciente que gostaria de consultar: ");
                            paciente = controller.getP().verificaRegistro(Long.parseLong(scanner.nextLine()));

                            System.out.println("Informe o paciente que gostaria de consultar: ");
                            medico = controller.getM().verificaRegistro(Long.parseLong(scanner.nextLine()));

                            System.out.println("Informe em qual Franquia gostaria de consultar: ");
                            franquia = controller.getF().verificaRegistro(Long.parseLong(scanner.nextLine()));

                            if (paciente == null || medico == null || franquia == null) {
                                System.out.println("Informaces inconsistentes");
                                System.out.println("1 - Gostaria de tentar novamente");
                                System.out.println("2 - sair");
                                sair = Integer.parseInt(scanner.nextLine());
                            } else {
                                System.out.println("\nInforme qual o dia que gostaria de consultar (No formato dd/MM/yyyy): ");
                                dia = scanner.nextLine();

                                System.out.println("\nE qual o horario (No formato hh:mm): ");
                                horario = scanner.nextLine();

                                System.out.println("\nQual o valor da consulta?");
                                valor = scanner.nextLine();
                                sair = 4;
                            }
                        }
                        if (cAdm.marcarConsulta(controller, dia, horario, valor, paciente, medico, franquia)) {
                            System.out.println("\nConsulta marcada com sucesso");
                        } else {
                            System.out.println("\nNao deu certo marcar a consulta");
                        }
                    } else {
                        System.out.println("Nao tem consultas cadastradas");
                    }

                    break;

                case 9://
                    if (controller.validaGeral("Procedimento")) {
                        int sairP = 0;
                        Consulta consultaP = null;
                        String diaP = null;
                        String horarioP = null;
                        String valorP = null;
                        while (sairP != 4) {
                            System.out.println("\nO procecedimento foi pedido: ");
                            System.out.println("\n1 - Na consulta");
                            System.out.println("\n2 - Pelo paciente");
                            sairP = Integer.parseInt(scanner.nextLine());

                            if (sairP == 2) {
                                consultaP = null;
                            } else {
                                System.out.println("Informe a consulta: ");
                                consultaP = controller.getC().verificaRegistro(Long.parseLong(scanner.nextLine()));

                                System.out.println("\nInforme qual o dia que gostaria de consultar (No formato dd/MM/yyyy): ");
                                diaP = scanner.nextLine();

                                System.out.println("\nE qual o horario (No formato hh:mm): ");
                                horarioP = scanner.nextLine();

                                System.out.println("\nQual o valor da consulta?");
                                valorP = scanner.nextLine();
                                sairP = 4;
                            }
                        }
                        if (cAdm.marcarProcedimento(controller, diaP, horarioP, valorP, consultaP)) {
                            System.out.println("\nConsulta marcada com sucesso");
                        } else {
                            System.out.println("\nNao deu certo marcar a consulta");
                        }
                    } else {
                        System.out.println("Nao pode marcar procedimento");
                    }

                    break;

                case 10:
                    Franquia fran = null;
                    System.out.println("Gostaria de verificar qual Franquia? ");
                    String verifica = scanner.nextLine();

                    for (int l = 0; l < controller.getF().franquias.length; ++l) {
                        if (controller.getF().franquias[l].getId() == Long.parseLong(verifica)) {
                            fran = controller.getF().franquias[l];
                        }
                    }

                    controllerRF.pagamentoMensalParaMatriz(controller, fran);
                    break;

                case 11:
                    Franquia franQ = null;
                    System.out.println("\nRelatorio de todas as Franquia: ");
                    String verific = scanner.nextLine();

                    for (int l = 0; l < controller.getF().franquias.length; ++l) {
                        if (controller.getF().franquias[l].getId() == Long.parseLong(verific)) {
                            franQ = controller.getF().franquias[l];
                            controllerRF.pagamentoMensalParaMatriz(controller, franQ);
                        }
                    }
                    break;

                case 12:
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
        builderAdm.append("\n7 - Relatorio de Financeiro da Franquia");
        builderAdm.append("\n8 - Marcar consulta");
        builderAdm.append("\n9 - Marcar Procedimento");
        builderAdm.append("\n10 - Relatorios de uma franquia");
        builderAdm.append("\n11 - Relatorios de todas as franquias");
        builderAdm.append("\n12 - Deslogar\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
