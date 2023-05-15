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
    
    public boolean novoCadastro (Pessoa novoRegistro){
        //validacao do input de dados mas pode pular aqui
        return p.adiciona(novoRegistro);
    }
    
    private void chamaMenus (Controller controller) {
        if(Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Paciente")){
            ControllerPaciente cp = new ControllerPaciente(controller);
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Medico")) {
            ControllerMedico cm = new ControllerMedico(controller);
        }else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("FuncAdministrativo")) {
            ControllerFuncionarioAdm cfa = new ControllerFuncionarioAdm(controller);
        }else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Responsavel pela Franquia")) {
            ControllerResponsavelFranquia crf = new ControllerResponsavelFranquia(controller);
        }else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Dono da Matriz")) {
            ControllerDonoDeMatriz cdm = new ControllerDonoDeMatriz(controller);
        } else { //Controle do Admin
            ControllerRegente cR = new ControllerRegente(controller);
        }        
    }  
    
    public void primeiroCadastro (){
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

}
