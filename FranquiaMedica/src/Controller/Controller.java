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
import franquiamedica.Utilitario;
import java.util.Scanner;
import java.math.BigDecimal;

public class Controller {

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

    public boolean validaLogin(String login, String senha) {
        p.mostraTodos();
        if (p.buscaLoginSenha(login, senha) != null) {
            System.out.println("Login Realizado com sucesso");
            Utilitario.setPessoaLogada(p.buscaLoginSenha(login, senha));
            chamaMenus();
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
    
    private void chamaMenus () {
        if(Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Paciente")){
            ControllerPaciente cp = new ControllerPaciente();
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Medico")) {
            ControllerMedico cm = new ControllerMedico();
        }else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("FuncAdministrativo")) {
            ControllerFuncionarioAdm cfa = new ControllerFuncionarioAdm();
        }else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Responsavel pela Franquia")) {
            ControllerResponsavelFranquia crf = new ControllerResponsavelFranquia();
        }else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Dono da Matriz")) {
            ControllerDonoDeMatriz cdm = new ControllerDonoDeMatriz();
        } else { //Controle do Admin
            ControllerRegente cR = new ControllerRegente();
        }        
    }  
    
    public void primeiroCadastro (){
        p.verificaRegistro(Long.parseLong("0")).setTipoUsuario("Regente");
        p.verificaRegistro(Long.parseLong("1")).setTipoUsuario("Dono da Matriz");
    }

}
