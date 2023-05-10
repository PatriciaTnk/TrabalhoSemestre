/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProcedimentoDAO {

    Procedimento[] proceds = new Procedimento[50];

    public ProcedimentoDAO(ConsultaDAO consultadao) {
        Procedimento novo = new Procedimento(consultadao.verificaRegistro(Long.parseLong("0")));
        novo.setLaudo("Identificado manchas brancas no pulmao");
        novo.setValorPro(new BigDecimal(89.40));
        adiciona(novo);

        Procedimento novo1 = new Procedimento(consultadao.verificaRegistro(Long.parseLong("1")));
        novo1.setLaudo("Encontrado mais plaquetas que o ideal");
        novo1.setValorPro(new BigDecimal(4.40));
        adiciona(novo1);

        Procedimento novo2 = new Procedimento(consultadao.verificaRegistro(Long.parseLong("2")));
        novo2.setLaudo("Paciente ainda nao realizou exame");
        novo2.setValorPro(new BigDecimal(72.80));
        adiciona(novo2);

    }

    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < proceds.length; i++) {
            Procedimento p = proceds[i];
            if (p == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(Procedimento p) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            proceds[x] = p;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (Procedimento p : proceds) {
            if (p != null && p.getVisible()) {
                System.out.println(p);
            }
        }
    }

    /**
     * A Procedimento verifica registro de acordo com o nome da Matriz
     *
     * @param idProcedimento
     * @return
     */
    public Procedimento verificaRegistro(long idProcedimento) {
        for (Procedimento p : proceds) {
            if (p != null && p.getId() == idProcedimento && p.getVisible()) {
                return p;
            }
        }
        return null;
    }

    public boolean cancelaProcedimento(long idCancelar) {
        for (Procedimento proced : proceds) {
            if (proced.getId() == idCancelar) {
                proced.setEstado("cancelada");
                proced.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean adicionaMaisInfo(long idProcedimento, String maisInfo) {
        for (Procedimento proced : proceds) {
            if (proced.getId() == idProcedimento) {
                proced.setLaudo(proced.getLaudo() + maisInfo);
                proced.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

}
