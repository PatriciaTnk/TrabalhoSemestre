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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Controller {

    GUI telaIni = null;

    public void setGUI(GUI tela) {
        this.telaIni = tela;
    }

    public GUI getTela() {
        return telaIni;
    }

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

    private ControllerPaciente cPaciente = new ControllerPaciente();
    private ControllerMedico cMedico = new ControllerMedico();
    private ControllerFuncionarioAdm cFuncAdm = new ControllerFuncionarioAdm();
    private ControllerResponsavelFranquia cResp = new ControllerResponsavelFranquia();
    //private ControllerRegente cReg = new ControllerRegente();

    public boolean validaLogin(String login, String senha, Controller controller) {
        p.mostraTodos();
        fin.mostraTodos();
        finMed.mostraTodos();       
        if (p.buscaLoginSenha(login, senha) != null) {
            System.out.println("\n\nLogin Realizado com sucesso");
            Utilitario.setPessoaLogada(p.buscaLoginSenha(login, senha));
            chamaMenus(controller);
            return true;
        } else {
            System.out.println("\n\nLogin não encontrado.\nTente novamente");
            return false;
        }
    }

    public boolean novoCadastro(Pessoa novoRegistro) {
        //validacao do input de dados mas pode pular aqui
        return p.adiciona(novoRegistro);
    }

    private void chamaMenus(Controller controller) {
        System.out.println(Utilitario.getPessoaLogada());
        if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Paciente")) {
            telaIni.pacienteAll(this, cPaciente);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Medico")) {
            telaIni.medicoAll(this, cMedico);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("FuncAdministrativo")) {
            telaIni.funcionarioAdmAll(controller, cFuncAdm);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Responsavel pela Franquia")) {
            telaIni.responsavelFranquiaAll(controller, cResp,cFuncAdm);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Dono da Matriz")) {
            //Tenho que alterar e colocar o controller de Dono de Matriz que é a mesma coisa mas adicionado a função de verificar todas as franquias
            telaIni.responsavelFranquiaAll(controller, cResp,cFuncAdm);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Regente")) { //Controle do Admin
            //ControllerRegente cR = new ControllerRegente(controller);
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
            } else if (validaNoLugar.equalsIgnoreCase("FinanceiroMedico")) {
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null
                            && finMed.finMedico[i].getMedico().getPessoa().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            }

        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("FuncAdministrativo")
                || Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Responsavel pela Franquia")
                || Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Dono da Matriz")) {
            if (validaNoLugar.equalsIgnoreCase("consulta")) {
                for (int j = 0; j < fa.funcAdm.length; ++j) {
                    for (int i = 0; i < c.consultas.length; ++i) {
                        if (c.consultas[i] != null
                                && fa.funcAdm[j] != null
                                && c.consultas[i].getUnidade().getId() == fa.funcAdm[j].getIdFranquia()                                
                                && fa.funcAdm[j].getFuncionario().getId() == Utilitario.getPessoaLogada().getId()
                                || c.consultas[i] != null
                                && c.consultas[i].getUnidade().getResponsavel().getId() == Utilitario.getPessoaLogada().getId()
                                || c.consultas[i] != null
                                && c.consultas[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                            return true;
                        }
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("procedimento")) {
                for (int j = 0; j < fa.funcAdm.length; ++j) {
                    for (int i = 0; i < proc.proceds.length; ++i) {
                        if (proc.proceds[i] != null
                                && proc.proceds[i].getConsulta().getUnidade().getId() == fa.funcAdm[j].getIdFranquia()
                                && fa.funcAdm[j] != null
                                && fa.funcAdm[j].getFuncionario().getId() == Utilitario.getPessoaLogada().getId()
                                || proc.proceds[i] != null
                                && proc.proceds[i].getConsulta().getUnidade().getResponsavel().getId() == Utilitario.getPessoaLogada().getId()
                                || proc.proceds[i] != null
                                && proc.proceds[i].getConsulta().getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                            return true;
                        }
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("InfoConsulta")) {
                for (int j = 0; j < fa.funcAdm.length; ++j) {
                    for (int i = 0; i < ic.infoCon.length; ++i) {
                        if (ic.infoCon[i] != null
                                && ic.infoCon[i].getConsulta().getUnidade().getId() == fa.funcAdm[j].getIdFranquia()
                                && fa.funcAdm[j] != null
                                && fa.funcAdm[j].getFuncionario().getId() == Utilitario.getPessoaLogada().getId()
                                || ic.infoCon[i] != null
                                && ic.infoCon[i].getConsulta().getUnidade().getResponsavel().getId() == Utilitario.getPessoaLogada().getId()
                                || ic.infoCon[i] != null
                                && ic.infoCon[i].getConsulta().getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                            return true;
                        }
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("FinanceiroAdm")) {
                for (int j = 0; j < fa.funcAdm.length; ++j) {
                    for (int i = 0; i < fin.finAdm.length; ++i) {
                        if (ic.infoCon[i] != null
                                && ic.infoCon[i].getConsulta().getUnidade().getId() == fa.funcAdm[j].getIdFranquia()
                                && fa.funcAdm[j] != null
                                && fa.funcAdm[j].getFuncionario().getId() == Utilitario.getPessoaLogada().getId()
                                || fin.finAdm[i] != null
                                && fin.finAdm[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()
                                || fin.finAdm[i] != null
                                && fin.finAdm[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                            return true;
                        }
                    }
                }
            }

        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Responsavel pela Franquia")
                || Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Dono da Matriz")) {
            if (validaNoLugar.equalsIgnoreCase("FinanceiroMedico")) {
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null
                            && finMed.finMedico[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()
                            || finMed.finMedico[i] != null
                            && finMed.finMedico[i].getUnidade().getFranquia().getDono().getId() == Utilitario.getPessoaLogada().getId()) {
                        return true;
                    }
                }
            }

        } else { //Controle do Admin
            if (validaNoLugar.equalsIgnoreCase("consulta")) {
                for (int i = 0; i < c.consultas.length; ++i) {
                    if (c.consultas[i] != null) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("InfoConsulta")) {
                for (int i = 0; i < ic.infoCon.length; ++i) {
                    if (ic.infoCon[i] != null) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("procedimento")) {
                for (int i = 0; i < proc.proceds.length; ++i) {
                    if (proc.proceds[i] != null) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("FinanceiroAdm")) {
                for (int i = 0; i < fin.finAdm.length; ++i) {
                    if (fin.finAdm[i] != null) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("FinanceiroMedico")) {
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("Franquia")) {
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("MatrizFranquia")) {
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null) {
                        return true;
                    }
                }
            } else if (validaNoLugar.equalsIgnoreCase("Medico")) {
                for (int i = 0; i < finMed.finMedico.length; ++i) {
                    if (finMed.finMedico[i] != null) {
                        return true;
                    }
                }
            }

        }
        System.out.println("Teste em cada lugar que chamar a funcao escreve a mensagem que quer apresentar para o usuario");
        return false;
    }

    public LocalDateTime validaDiaHora(String dia, String horario) {
        DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate day = LocalDate.parse(dia, formatterDia);

        LocalTime hour = LocalTime.parse(horario);

        return LocalDateTime.of(day, hour);
    }

    public BigDecimal validaBigDecimal(String valorB) {
        BigDecimal novoB = new BigDecimal(valorB);
        novoB = novoB.setScale(2, RoundingMode.HALF_DOWN);
        return novoB;
    }

}
