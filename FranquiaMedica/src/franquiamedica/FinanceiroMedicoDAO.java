/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;

public class FinanceiroMedicoDAO {

    FinanceiroMedico[] finAdm = new FinanceiroMedico[100];

    public FinanceiroMedicoDAO(ConsultaDAO consultadao, ProcedimentoDAO procedimentodao, FranquiaDAO franquiadao) {
        FinanceiroMedico novo = new FinanceiroMedico(franquiadao.franquias[0],consultadao.consultas[0].getMedico());
        novo.setEstado("A pagar");
        novo.setValor(new BigDecimal(consultadao.consultas[0].getValor().toString()));
        adiciona(novo);
        
        FinanceiroMedico novo1 = new FinanceiroMedico(franquiadao.franquias[0],procedimentodao.proceds[0].getConsulta().getMedico());
        novo1.setEstado("A pagar");
        novo1.setValor(new BigDecimal(procedimentodao.proceds[0].getValorPro().toString()));
        adiciona(novo1);
        
        FinanceiroMedico novo2 = new FinanceiroMedico(franquiadao.franquias[0],consultadao.consultas[0].getMedico());
        novo2.setEstado("A pagar");
        novo2.setValor(new BigDecimal(consultadao.consultas[1].getValor().toString()));
        adiciona(novo2);
        
        FinanceiroMedico novo3 = new FinanceiroMedico(franquiadao.franquias[0],procedimentodao.proceds[0].getConsulta().getMedico());
        novo3.setEstado("A pagar");
        novo3.setValor(new BigDecimal(procedimentodao.proceds[1].getValorPro().toString()));
        adiciona(novo3);

    }

    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < finAdm.length; i++) {
            FinanceiroMedico fin = finAdm[i];
            if (fin == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(FinanceiroMedico fin) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            finAdm[x] = fin;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (FinanceiroMedico fin : finAdm) {
            if (fin != null && fin.getVisible() == true) {
                System.out.println(fin);
            }
        }
    }

    public FinanceiroMedico verificaRegistro(long id) {
        for (FinanceiroMedico fin : finAdm) {
            if (fin != null && fin.getId() == id && fin.getVisible()) {
                return fin;
            }
        }
        return null;
    }

    public boolean remove(long idRemover) {
        for (FinanceiroMedico fin : finAdm) {
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

}
