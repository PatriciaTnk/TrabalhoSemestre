/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InfoConsultaDAO {

    InfoConsulta[] infoCon = new InfoConsulta[30];

    public InfoConsultaDAO(ConsultaDAO consultadao) {
        InfoConsulta novo = new InfoConsulta(consultadao.verificaRegistro(Long.parseLong("0")));
        novo.setDescricao("Paciente mostra recuperacao");
        adiciona(novo);

        InfoConsulta novo1 = new InfoConsulta(consultadao.verificaRegistro(Long.parseLong("1")));
        novo1.setDescricao("Paciente piorou");
        adiciona(novo1);

        InfoConsulta novo2 = new InfoConsulta(consultadao.verificaRegistro(Long.parseLong("2")));
        novo2.setDescricao("Paciente ainda nao realizou exame");
        adiciona(novo2);

        /*InfoConsulta novo3 = new InfoConsulta(consultadao.verificaRegistro(Long.parseLong("3")));
        novo3.setDescricao("Paciente recuperado");
        adiciona(novo3);*/
    }

    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < infoCon.length; i++) {
            InfoConsulta ic = infoCon[i];
            if (ic == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(InfoConsulta ic) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            infoCon[x] = ic;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (InfoConsulta ic : infoCon) {
            if (ic != null && ic.getVisible()) {
                System.out.println(ic);
            }
        }
    }

    /**
     * A InfoConsulta verifica registro de acordo com o nome da Matriz
     *
     * @param idInfo
     * @return
     */
    public InfoConsulta verificaRegistro(long idInfo) {
        for (InfoConsulta ic : infoCon) {
            if (ic != null && ic.getId() == idInfo && ic.getVisible()) {
                return ic;
            }
        }
        return null;
    }

    public boolean adicionaMaisInfo(long idConsulta, String maisInfo) {
        for (InfoConsulta ic : infoCon) {
            if (ic.getId() == idConsulta) {
                ic.setDescricao(ic.getDescricao() + maisInfo);
                ic.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

}
