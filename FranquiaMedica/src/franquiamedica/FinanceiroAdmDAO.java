/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import franquiamedica.FinanceiroAdm;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FinanceiroAdmDAO {

    public FinanceiroAdm[] finAdm = new FinanceiroAdm[100];

    public FinanceiroAdmDAO(ConsultaDAO consultadao, ProcedimentoDAO procedimentodao, FranquiaDAO franquiadao) {
        FinanceiroAdm novo = new FinanceiroAdm(franquiadao.franquias[0]);
        novo.setTipoDeMovim("entrada");        
        novo.setDescritivo("Valor: " + consultadao.consultas[0].getValor() + "\nRealizado com medico: " + consultadao.consultas[0].getMedico() + "\nPaciente: " + consultadao.consultas[0].getPaciente()+ "\nRealizada no dia: " + consultadao.consultas[0].getDiaHorario());
        novo.setValor(new BigDecimal(consultadao.consultas[0].getValor().toString()));
        adiciona(novo);
        
        FinanceiroAdm novo1 = new FinanceiroAdm(franquiadao.franquias[0]);
        novo1.setTipoDeMovim("entrada");        
        novo1.setDescritivo("Valor: " + procedimentodao.proceds[0].getValorPro()+ "\nRealizado com medico: " + procedimentodao.proceds[0].getConsulta().getMedico()+ "\nPaciente: " + procedimentodao.proceds[0].getConsulta().getPaciente()+ "\nRealizada no dia: " + procedimentodao.proceds[0].getDiaHorario());
        novo1.setValor(new BigDecimal(procedimentodao.proceds[0].getValorPro().toString()));
        adiciona(novo1);
        
        FinanceiroAdm novo2 = new FinanceiroAdm(franquiadao.franquias[0]);
        novo2.setTipoDeMovim("entrada");        
        novo2.setDescritivo("Valor: " + consultadao.consultas[1].getValor() + "\nRealizado com medico: " + consultadao.consultas[1].getMedico() + "\nPaciente: " + consultadao.consultas[1].getPaciente()+ "\nRealizada no dia: " + consultadao.consultas[1].getDiaHorario());
        novo2.setValor(new BigDecimal(consultadao.consultas[1].getValor().toString()));
        adiciona(novo2);
        
        FinanceiroAdm novo3 = new FinanceiroAdm(franquiadao.franquias[0]);
        novo3.setTipoDeMovim("entrada");        
        novo3.setDescritivo("Valor: " + procedimentodao.proceds[1].getValorPro()+ "\nRealizado com medico: " + procedimentodao.proceds[1].getConsulta().getMedico() + "\nPaciente: " + procedimentodao.proceds[1].getConsulta().getPaciente()+ "\nRealizada no dia: " + procedimentodao.proceds[1].getDiaHorario());
        novo3.setValor(new BigDecimal(procedimentodao.proceds[1].getValorPro().toString()));
        adiciona(novo3);

    }

    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < finAdm.length; i++) {
            FinanceiroAdm fin = finAdm[i];
            if (fin == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(FinanceiroAdm fin) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            finAdm[x] = fin;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (FinanceiroAdm fin : finAdm) {
            if (fin != null && fin.getVisible() == true) {
                System.out.println(fin);
            }
        }
    }

    public FinanceiroAdm verificaRegistro(long id) {
        for (FinanceiroAdm fin : finAdm) {
            if (fin != null && fin.getId() == id && fin.getVisible()) {
                return fin;
            }
        }
        return null;
    }

    public boolean remove(long idRemover) {
        for (FinanceiroAdm fin : finAdm) {
            if (idRemover == (fin.getId())) {
                fin.notVisible(true);
                return true;
            }
        }
        return false;
    }

    public String getUnidade(long idConsulta) {
        if (verificaRegistro(idConsulta) == null) {
            return "Unidade nao encontrada";
        }
        return verificaRegistro(idConsulta).getUnidade().toString();
    }

    public boolean trocaUnidade(long idConsulta, Franquia novoLocal) {
        if (verificaRegistro(idConsulta) != null) {
            verificaRegistro(idConsulta).setUnidade(novoLocal);
            return true;
        } else {
            return false;
        }
    }
    
}
