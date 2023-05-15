/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

//Criar o menu do login e os menus de cada um dos 5 perfis
//Quando for mexer com os menus lembrar de trocar o equals hash code de TODAS as CLASSES sem DAO
import View.GUI;
import franquiamedica.ConsultaDAO;
import franquiamedica.FinanceiroAdmDAO;
import franquiamedica.FinanceiroMedicoDAO;
import franquiamedica.FranquiaDAO;
import franquiamedica.FuncionarioAdmDAO;
import franquiamedica.MatrizFranquiaDAO;
import franquiamedica.Pessoa;
import franquiamedica.PessoaDAO;
import franquiamedica.MedicoDAO;
import franquiamedica.InfoConsultaDAO;
import franquiamedica.ProcedimentoDAO;
import franquiamedica.Utilitario;

public class Controller {

    private PessoaDAO p = new PessoaDAO();
    private MedicoDAO m = new MedicoDAO(p);
    private MatrizFranquiaDAO mf = new MatrizFranquiaDAO(p);
    private FranquiaDAO f = new FranquiaDAO(p, mf);
    private ConsultaDAO c = new ConsultaDAO(p, m, mf, f);
    private InfoConsultaDAO ic = new InfoConsultaDAO(c);
    private ProcedimentoDAO proc = new ProcedimentoDAO(c);
    private FinanceiroAdmDAO fin = new FinanceiroAdmDAO(c, proc, f);
    private FinanceiroMedicoDAO finMed = new FinanceiroMedicoDAO(c, proc, f);
    private FuncionarioAdmDAO fa = new FuncionarioAdmDAO(f, p);

    public boolean validaLogin(String login, String senha, Controller controller) {
        p.mostraTodos();
        if (p.buscaLoginSenha(login, senha) != null) {
            System.out.println("Login Realizado com sucesso");
            Utilitario.setPessoaLogada(p.buscaLoginSenha(login, senha));
            chamaMenus(controller);
            return true;
        } else {
            System.out.println("Login não encontrado.\nTente novamente");
            return false;
        }
    }

    public boolean novoCadastro(Pessoa novoRegistro) {
        //validacao do input de dados mas pode pular aqui
        return p.adiciona(novoRegistro);
    }

    private void chamaMenus(Controller controller) {
        if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Paciente")) {
            ControllerPaciente cp = new ControllerPaciente(controller);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Medico")) {
            ControllerMedico cm = new ControllerMedico(controller);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("FuncAdministrativo")) {
            ControllerFuncionarioAdm cfa = new ControllerFuncionarioAdm(controller);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Responsavel pela Franquia")) {
            ControllerResponsavelFranquia crf = new ControllerResponsavelFranquia(controller);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Dono da Matriz")) {
            ControllerDonoDeMatriz cdm = new ControllerDonoDeMatriz(controller);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Regente")){ //Controle do Admin
            ControllerRegente cR = new ControllerRegente(controller);
        }
    }

    public void primeiroCadastro() {
        p.verificaRegistro(Long.parseLong("0")).setTipoUsuario("Regente");
        p.verificaRegistro(Long.parseLong("1")).setTipoUsuario("Dono da Matriz");
    }

    public PessoaDAO getP() {
        return p;
    }

    public MedicoDAO getM() {
        return m;
    }

    public MatrizFranquiaDAO getMf() {
        return mf;
    }

    public FranquiaDAO getF() {
        return f;
    }

    public ConsultaDAO getC() {
        return c;
    }

    public InfoConsultaDAO getIc() {
        return ic;
    }

    public ProcedimentoDAO getProc() {
        return proc;
    }

    public FinanceiroAdmDAO getFin() {
        return fin;
    }

    public FinanceiroMedicoDAO getFinMed() {
        return finMed;
    }

    public FuncionarioAdmDAO getFa() {
        return fa;
    }

    public boolean validaGeral(String validaNoLugar) {
        if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Paciente")) {
            if (validaNoLugar.equalsIgnoreCase("consulta")) {
                for (int i = 0; i < c.consultas.length; ++i) {
                    if (c.consultas[i] != null
                            && c.consultas[i].getPaciente().getId() == Utilitario.getPessoaLogada().getId()) {
                        //retorna verdadeiro se o paciente tem pelo menos 1 consulta
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("procedimento")) {
                for (int i = 0; i < proc.proceds.length; ++i) {
                    if (proc.proceds[i] != null
                            && proc.proceds[i].getConsulta().getPaciente().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("InfoConsulta")) {
                for (int i = 0; i < ic.infoCon.length; ++i) {
                    if (ic.infoCon[i] != null
                            && ic.infoCon[i].getConsulta().getPaciente().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            }

        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Medico")) {
            if (validaNoLugar.equalsIgnoreCase("consulta")) {
                for (int i = 0; i < c.consultas.length; ++i) {
                    if (c.consultas[i] != null
                            && c.consultas[i].getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {
                        //retorna verdadeiro se o paciente tem pelo menos 1 consulta
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("InfoConsulta")) {
                for (int i = 0; i < ic.infoCon.length; ++i) {
                    if (ic.infoCon[i] != null
                            && ic.infoCon[i].getConsulta().getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("procedimento")) {
                for (int i = 0; i < proc.proceds.length; ++i) {
                    if (proc.proceds[i] != null
                            && proc.proceds[i].getConsulta().getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else {
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null
                            && finMed.finMedico[i].getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            }

        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("FuncAdministrativo")) {

        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Responsavel pela Franquia")) {
            if (validaNoLugar.equalsIgnoreCase("consulta")) {
                for (int i = 0; i < c.consultas.length; ++i) {
                    if (c.consultas[i] != null
                            && c.consultas[i].getUnidade().getResponsavel().getId() == Utilitario.getPessoaLogada().getId()) {
                        //retorna verdadeiro se o paciente tem pelo menos 1 consulta
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("InfoConsulta")) {
                for (int i = 0; i < ic.infoCon.length; ++i) {
                    if (ic.infoCon[i] != null
                            && ic.infoCon[i].getConsulta().getUnidade().getResponsavel().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("procedimento")) {
                for (int i = 0; i < proc.proceds.length; ++i) {
                    if (proc.proceds[i] != null
                            && proc.proceds[i].getConsulta().getUnidade().getResponsavel().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("FinanceiroAdm")) {
                for (int i = 0; i < fin.finAdm.length; ++i) {
                    if (fin.finAdm[i] != null
                            && fin.finAdm[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("FinanceiroMedico")){
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null
                            && finMed.finMedico[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            }

        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Dono da Matriz")) {
            if (validaNoLugar.equalsIgnoreCase("consulta")) {
                for (int i = 0; i < c.consultas.length; ++i) {
                    if (c.consultas[i] != null
                            && c.consultas[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        //retorna verdadeiro se o paciente tem pelo menos 1 consulta
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("InfoConsulta")) {
                for (int i = 0; i < ic.infoCon.length; ++i) {
                    if (ic.infoCon[i] != null
                            && ic.infoCon[i].getConsulta().getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("procedimento")) {
                for (int i = 0; i < proc.proceds.length; ++i) {
                    if (proc.proceds[i] != null
                            && proc.proceds[i].getConsulta().getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("FinanceiroAdm")) {
                for (int i = 0; i < fin.finAdm.length; ++i) {
                    if (fin.finAdm[i] != null
                            && fin.finAdm[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("FinanceiroMedico")){
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null
                            && finMed.finMedico[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            }

        } else { //Controle do Admin
            if (validaNoLugar.equalsIgnoreCase("consulta")) {
                for (int i = 0; i < c.consultas.length; ++i) {
                    if (c.consultas[i] != null
                            && c.consultas[i].getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {
                        //retorna verdadeiro se o paciente tem pelo menos 1 consulta
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("InfoConsulta")) {
                for (int i = 0; i < ic.infoCon.length; ++i) {
                    if (ic.infoCon[i] != null
                            && ic.infoCon[i].getConsulta().getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("procedimento")) {
                for (int i = 0; i < proc.proceds.length; ++i) {
                    if (proc.proceds[i] != null
                            && proc.proceds[i].getConsulta().getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("FinanceiroAdm")) {
                for (int i = 0; i < fin.finAdm.length; ++i) {
                    if (fin.finAdm[i] != null
                            && fin.finAdm[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            } else {
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null
                            && finMed.finMedico[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            }

        }
        System.out.println("Teste em cada lugar que chamar a funcao escreve a mensagem que quer apresentar para o usuario");
        return false;
    }

}
