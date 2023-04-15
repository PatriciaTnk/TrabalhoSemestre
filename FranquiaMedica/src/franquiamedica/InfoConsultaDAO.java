/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class InfoConsultaDAO {

    InfoConsulta[] infoCon = new InfoConsulta[30];
    
    
    
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
            if (ic != null) {
                System.out.println(ic);
            }
        }
    }

    /**
     * A InfoConsulta verifica registro de acordo com o nome da Matriz
     *
     * @param idConsulta
     * @return
     */
    public InfoConsulta verificaRegistro(long idConsulta) {
        for (InfoConsulta ic : infoCon) {
            if (ic.getId() == idConsulta) {
                return ic;
            }
        }
        return null;
    }

    public boolean adicionaMaisInfo (long idConsulta, String maisInfo){
         for (InfoConsulta ic : infoCon) {
            if (ic.getId() == idConsulta) {
                String antes = ic.getDescricao()+maisInfo;
                ic.setDatamodificacao(LocalDateTime.now());
                return true;
            }        
         }
         return false;
    }
    
}
